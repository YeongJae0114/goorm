package com.example.Tricount.user.controller;

import com.example.Tricount.user.dto.LoginDto;
import com.example.Tricount.user.dto.SignDto;
import com.example.Tricount.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignDto signDto) {
        userService.sign(signDto);
        return ResponseEntity.ok("Sign up successful");
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session) {
        userService.login(loginDto, session);
        return ResponseEntity.ok("Login successful");
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return ResponseEntity.ok("Logout successful");
    }

}
