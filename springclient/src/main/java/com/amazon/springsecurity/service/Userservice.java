package com.amazon.springsecurity.service;

import com.amazon.springsecurity.model.PasswordModel;
import com.amazon.springsecurity.model.Usermodel;
import com.amazon.springsecurity.entities.User;

public interface Userservice {
    User registeruser(Usermodel um);

    void saveVerificationTokenForUser(String token, User u);

    String verifyRegistration(String token);


    User getUserbytoken(String oldtoken);

    String resetpassword(int id);

    String verifyresetpassword(String token,PasswordModel pm);

    String resendlinkresetpassword(String oldtoken);
}
