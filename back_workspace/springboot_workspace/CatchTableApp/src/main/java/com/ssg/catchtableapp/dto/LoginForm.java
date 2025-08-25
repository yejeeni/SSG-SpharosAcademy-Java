package com.ssg.catchtableapp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * 로그인에 사용하는 dto
 */
@Getter @Setter
public class LoginForm {

    @NotEmpty(message = "아이디는 필수입니다")
    private String id;

    @NotEmpty(message = "비밀번호는 필수입니다")
    private String password;
}