package com.example.Tricount.user.repository;

import com.example.Tricount.user.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    // userIdentity 존재 여부 확인
    public boolean existsByUserIdentity(String userIdentity) {
        String sql = "SELECT COUNT(*) FROM users WHERE user_identity = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userIdentity);
        return count != null && count > 0;
    }

    // Users 저장
    public void save(Users users) {
        String sql = "INSERT INTO users (id, user_identity, password, nickname) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, users.getId(), users.getUserIdentity(), users.getPassword(), users.getNickname());
    }

    // userIdentity로 Users 조회
    public Users findByUserIdentity(String userIdentity) {
        String sql = "SELECT * FROM users WHERE user_identity = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                        Users.builder()
                                .id(rs.getLong("id"))
                                .userIdentity(rs.getString("user_identity"))
                                .password(rs.getString("password"))
                                .nickname(rs.getString("nickname"))
                                .build(),
                userIdentity
        );
    }
}
