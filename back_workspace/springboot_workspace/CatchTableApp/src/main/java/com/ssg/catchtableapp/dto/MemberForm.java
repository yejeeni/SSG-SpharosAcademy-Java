package com.ssg.catchtableapp.dto;

import com.ssg.catchtableapp.domain.Member;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

/**
 * 회원가입에 사용되는 dto
 */
@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "아이디는 필수입니다")
    private String id;

    @NotEmpty(message = "이름은 필수입니다")
    private String name;

    @Email(message = "유효한 이메일 주소를 입력해주세요")
    @NotEmpty(message = "이메일은 필수입니다")
    private String email;

    @NotEmpty(message = "비밀번호는 필수입니다")
    private String password;

    /**
     * DTO -> Entity
     */
    public Member toEntity() {
        return Member.createMember(this.id, this.name, this.email, this.password);
    }
}