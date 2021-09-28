package com.rbwsn.entity;

import com.rbwsn.constant.Role;
import com.rbwsn.dto.UserFormDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter @Setter
public class User {

    @Id // 基本のKEY
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role; // USER, ADMIN, MEMBER

    @CreationTimestamp
    private Timestamp createDate;


    public static User createUser(UserFormDto userFormDto, PasswordEncoder passwordEncoder){
        User user =new User();
        user.setUsername(userFormDto.getUsername());
        user.setEmail(userFormDto.getEmail());
        String password = passwordEncoder.encode(userFormDto.getPassword());
        user.setPassword(password);
        user.setRole(Role.USER);
        return user;
    }

}
