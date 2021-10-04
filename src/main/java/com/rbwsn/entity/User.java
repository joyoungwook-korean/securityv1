package com.rbwsn.entity;

import com.rbwsn.constant.Role;
import com.rbwsn.dto.UserFormDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter @Setter
@ToString
@NoArgsConstructor
public class User {

    @Id // 基本のKEY
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String name;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role; // USER, ADMIN, MEMBER

    private String provider;
    private String provider_id;


    @CreationTimestamp
    private Timestamp createDate;




    public static User createUser(UserFormDto userFormDto, PasswordEncoder passwordEncoder){
        User user =new User();
        user.setProvider("local");
        user.setProvider_id("1234");
        user.setUsername(userFormDto.getUsername());
        user.setName(userFormDto.getUsername()+user.getProvider_id()+user.getProvider());
        user.setEmail(userFormDto.getEmail());
        String password = passwordEncoder.encode(userFormDto.getPassword());
        user.setPassword(password);
        user.setRole(Role.ROLE_ADMIN);
        return user;
    }

    @Builder
    public User(String username, String password, String email, Role role, String provider, String provider_id, Timestamp createDate,String name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.name=name;
        this.provider = provider;
        this.provider_id = provider_id;
        this.createDate = createDate;
    }
}
