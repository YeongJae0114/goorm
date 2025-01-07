package com.example.Tricount.common.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false); // 기존 세션 가져오기

        // 세션에 사용자 정보가 없으면 요청 차단
        if (session == null || session.getAttribute("user") == null) {
            throw new  IllegalArgumentException("Unauthorized access");

        }

        chain.doFilter(request, response); // 다음 필터로 요청 전달
    }
}
