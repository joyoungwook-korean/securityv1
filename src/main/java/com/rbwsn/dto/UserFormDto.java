package com.rbwsn.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class UserFormDto {

    @NotBlank(message = "nameは必須入力値です。")
    private String username;

    @NotBlank(message = "emailは必須入力値です。")
    @Email(message = "email 形式で入力をお願いします。")
    private String email;

    @NotBlank(message = "passwordは必須入力値です。")
    @Length(min = 6,max = 16, message = "passwordは 6文字以上　16文字以下で入力をお願いします。")
    private String password;
}
