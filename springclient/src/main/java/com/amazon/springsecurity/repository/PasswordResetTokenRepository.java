package com.amazon.springsecurity.repository;

import com.amazon.springsecurity.entities.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,String> {
    PasswordResetToken findByToken(String token);
    PasswordResetToken findByUser_Id(Integer id);
}
