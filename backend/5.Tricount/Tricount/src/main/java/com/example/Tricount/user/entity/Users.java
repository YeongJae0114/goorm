package com.example.Tricount.user.entity;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Users {
    private Long id;
    private String userIdentity;
    private String password;
    private String nickname;

    @Builder
    public Users(Long id, String userIdentity, String password, String nickname) {
        this.id = id;
        this.userIdentity = userIdentity;
        this.password = password;
        this.nickname = nickname;
    }
}
