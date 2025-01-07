package com.example.Tricount.user.service;

import com.example.Tricount.common.util.IdGenerator;
import com.example.Tricount.common.util.PasswordEncryptor;
import com.example.Tricount.user.dto.LoginDto;
import com.example.Tricount.user.dto.SignDto;

import com.example.Tricount.user.entity.Users;
import com.example.Tricount.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryptor;

    // 회원가입
    public void sign(SignDto signDto){
        // 중복 확인
        if (userRepository.existsByUserIdentity(signDto.getUserIdentity())) {
            throw new IllegalArgumentException("UserIdentity already exists: " + signDto.getUserIdentity());
        }
        // 비밀번호 암호화
        String encryptedPassword = passwordEncryptor.encryptPassword(signDto.getPassword());

        Long generatedId = IdGenerator.generateId();

        // user 엔티티 저장
        Users newUsers = Users.builder()
                .id(generatedId)
                .userIdentity(signDto.getUserIdentity())
                .password(encryptedPassword)
                .nickname(signDto.getNickname())
                .build();

        userRepository.save(newUsers);
    }

    // 로그인
    public void login(LoginDto loginDto, HttpSession session) {
        Users users = userRepository.findByUserIdentity(loginDto.getUserIdentity());

        if (users == null) {
            throw new IllegalArgumentException("Invalid userIdentity or password.");
        }

        // 비밀번호 검증
        if (!passwordEncryptor.matches(loginDto.getPassword(), users.getPassword())) {
            throw new IllegalArgumentException("Invalid userIdentity or password.");
        }

        // 세션에 사용자 정보 저장
        session.setAttribute("user", users);
    }



}
