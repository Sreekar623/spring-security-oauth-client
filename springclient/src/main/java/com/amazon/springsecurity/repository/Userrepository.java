package com.amazon.springsecurity.repository;

import com.amazon.springsecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Userrepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
}
