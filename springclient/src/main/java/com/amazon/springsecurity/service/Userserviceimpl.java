package com.amazon.springsecurity.service;

import com.amazon.springsecurity.entities.PasswordResetToken;
import com.amazon.springsecurity.entities.User;
import com.amazon.springsecurity.entities.RegistrationVerificationToken;
import com.amazon.springsecurity.model.PasswordModel;
import com.amazon.springsecurity.model.Usermodel;
import com.amazon.springsecurity.repository.PasswordResetTokenRepository;
import com.amazon.springsecurity.repository.Userrepository;
import com.amazon.springsecurity.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

@Service
public class Userserviceimpl implements Userservice {
    @Autowired
    PasswordResetTokenRepository prtr;
    @Autowired
    VerificationTokenRepository vtr;
    @Autowired
    private Userrepository ur;
    @Autowired
    private PasswordEncoder pe;

    @Override
    public User registeruser(Usermodel um) {
        User u=new User();
        u.setEmail(um.getEmail());
        u.setFname(um.getFname());
        u.setLname(um.getLname());

        u.setPassword(pe.encode(um.getPassword()));
        u.setRole("ROLE_USER");
        ur.save(u);
        return u;

    }

    @Override
    public void saveVerificationTokenForUser(String token, User u) {
        RegistrationVerificationToken vt=u.getRegistrationVerificationToken();
        if(vt!=null){vt.setToken(token);}
       else{vt=new RegistrationVerificationToken(u,token);}
        vtr.save(vt);
    }

    @Override
    public String verifyRegistration(String token) {
        RegistrationVerificationToken vt= vtr.findByToken(token);
        if(vt==null){return "invalid token";}
        Calendar c=Calendar.getInstance();
        if((vt.getExpirytime().getTime()-c.getTime().getTime())<=0){
            vtr.delete(vt);

            return "expired token";

        }
        User u=vt.getUser();
        u.setEnabled(true);
        ur.save(u);
        return "success";

    }


    @Override
    public User getUserbytoken(String oldtoken) {
        RegistrationVerificationToken vt=vtr.findByToken(oldtoken);
        User u=vt.getUser();
        return u;
    }

    @Override
    public String resetpassword(int id) {

        User u=ur.findById(id).get();

        if(u==null){return "invalid";}
        PasswordResetToken prt0=prtr.findByUser_Id(id);

        String token= UUID.randomUUID().toString();
        if(prt0!=null){
            prt0.setToken(token);
            prtr.save(prt0);
            return token;

        }
        PasswordResetToken prt=new PasswordResetToken(u,token);
        prtr.save(prt);
        String url="/verifyresetpassword"+"?token="+token;
        return url;
    }

    @Override
    public String verifyresetpassword(String token,PasswordModel pm) {
        PasswordResetToken prt=prtr.findByToken(token);
        if(prt==null){return "invalid";}
        Calendar c=Calendar.getInstance();
        if((prt.getExpirytime().getTime()-c.getTime().getTime())<=0){
            prtr.delete(prt);}
        User u=prt.getUser();

        u.setPassword(pe.encode(pm.getNewpassword()));

        return "PASSOWRD CHANGED SUCCESFULLY";



    }

    @Override
    public String resendlinkresetpassword(String oldtoken) {

        return "";
    }
}
