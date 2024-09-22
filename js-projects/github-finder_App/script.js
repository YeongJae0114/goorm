class GitHubUserFinder {
    constructor() {
      this.form = document.getElementById("searchForm");
      this.usernameInput = document.getElementById("username");
      this.userInfo = document.getElementById("userInfo");
      this.repoList = document.querySelector(".repo-list");
      this.form.addEventListener("submit", (e) => this.handleSearch(e));
    }
  
    async handleSearch(event) {
      event.preventDefault();
      const username = this.usernameInput.value.trim();
      if (username === "") return;
  
      this.showSpinner();
      this.clearRepoList();
      try {
        const user = await this.requestWithRetry(
          `https://api.github.com/users/${username}`
        );
        const repos = await this.requestWithRetry(
          `https://api.github.com/users/${username}/repos?sort=created&per_page=5`
        );
        this.renderUser(user, repos);
      } catch (error) {
        this.userInfo.innerHTML = `<p class="error">사용자를 찾을 수 없습니다.</p>`;
        this.clearRepoList();
      } finally {
        this.hideSpinner();
      }
    }
  
    clearRepoList() {
      if (this.repoList) {
        this.repoList.innerHTML = "";
      }
    }
  
    /**
     * 트래픽률 제한을 초과했을 때 재시도 함수
     * @note 토큰 발급 없이 사용했기 때문에 트래픽률 제한을 초과하면 403 에러가 발생합니다.
     * @참고 - https://docs.github.com/ko/rest/guides/scripting-with-the-rest-api-and-javascript?apiVersion=2022-11-28#handling-rate-limit-errors
     * @param {string} url 요청할 URL
     * @returns {Promise<Response>} 요청 결과
     */
    async requestWithRetry(url) {
      try {
        const response = await fetch(url);
        if (
          response.status === 403 &&
          response.headers.get("x-ratelimit-remaining") === "0"
        ) {
          const resetTime = parseInt(
            response.headers.get("x-ratelimit-reset"),
            10
          );
          const currentTime = Math.floor(Date.now() / 1000);
          const waitTime = resetTime - currentTime;
          if (waitTime > 0) {
            console.log(
              `트래픽률 제한을 초과했습니다. ${waitTime}초 후에 다시 시도합니다.`
            );
            await this.sleep(waitTime * 1000);
            return this.requestWithRetry(url);
          }
        }
        if (!response.ok) {
          throw new Error("네트워크 요청이 실패했습니다.");
        }
        return response.json();
      } catch (error) {
        throw error;
      }
    }
  
    sleep(ms) {
      return new Promise((resolve) => setTimeout(resolve, ms));
    }
  
    renderUser(user, repos) {
      this.userInfo.innerHTML = `
        <div class="user-profile">
          <div class="profile-left">
            <img src="${user.avatar_url}" alt="${user.login}" class="avatar" />
            <a href="${
              user.html_url
            }" target="_blank" class="github-link">View profile</a>
          </div>
          <div class="user-details">
            <div class="user-header">
              <h2>${user.name || user.login}</h2>
              <div class="badges">
                <span class="badge">Public Repos: ${user.public_repos}</span>
                <span class="badge1">Public Gists: ${user.public_gists}</span>
                <span class="badge2">Followers: ${user.followers}</span>
                <span class="badge3">Following: ${user.following}</span>
              </div>
            </div>
            <p>${user.bio || " "}
            <div class="user-info-grid">
              <div class="info-item">
                <span class="info-label">Company</span>
                <span class="info-value">${user.company || "-"}</span>
              </div>
              <div class="info-item">
                <span class="info-label">Website</span>
                <span class="info-value">${
                  user.blog
                    ? `<a href="${user.blog}" target="_blank">${user.blog}</a>`
                    : "-"
                }</span>
              </div>
              <div class="info-item">
                <span class="info-label">Location</span>
                <span class="info-value">${user.location || "-"}</span>
              </div>
              <div class="info-item">
                <span class="info-label">Member Since</span>
                <span class="info-value">${new Date(
                  user.created_at
                ).toLocaleDateString()}</span>
              </div>
            </div>
          </div>
        </div>
        <div class="contribution-graph">
          <img src="https://ghchart.rshah.org/${user.login}" alt="잔디 그래프" />
        </div>
      `;
  
      // 최신 저장소 목록 업데이트
      if (this.repoList) {
        this.repoList.innerHTML = repos
          .map(
            (repo) => `
          <li>
            <a href="${repo.html_url}" target="_blank">${repo.name}</a>
            <p>${repo.description || " "}</p>
          </li>
        `
          )
          .join("");
      }
    }
  
    showSpinner() {
      this.userInfo.innerHTML = `<div class="spinner"></div>`;
    }
  
    hideSpinner() {
      const spinner = this.userInfo.querySelector(".spinner");
      if (spinner) {
        spinner.remove();
      }
    }
  }
  
  document.addEventListener("DOMContentLoaded", () => {
    new GitHubUserFinder();
  });
  