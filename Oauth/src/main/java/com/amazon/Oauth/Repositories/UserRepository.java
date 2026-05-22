package com.amazon.Oauth.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.amazon.Oauth.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByEmail(String email);
}
