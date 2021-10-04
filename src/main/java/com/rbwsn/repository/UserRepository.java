package com.rbwsn.repository;

import com.rbwsn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findByEmailAndProvider(String email,String provider);
}
