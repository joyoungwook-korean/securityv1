package com.rbwsn.service;

import com.rbwsn.entity.User;
import com.rbwsn.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor // private finalをbeenに注入
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User user){
        check_User_Email(user);
        return userRepository.save(user);
    }

    private void check_User_Email(User user){
        User findEmail =userRepository.findByEmailAndProvider(user.getEmail(),"local");
        if(findEmail!=null){
            throw new IllegalStateException("また、加入になる人です。");
        }
    }
}
