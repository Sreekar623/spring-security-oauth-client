package com.amazon.springsecurity.repository;

import com.amazon.springsecurity.entities.RegistrationVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<RegistrationVerificationToken,Long> {
    RegistrationVerificationToken findByToken(String token);
}
