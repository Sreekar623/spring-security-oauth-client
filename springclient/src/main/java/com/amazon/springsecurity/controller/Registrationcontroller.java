package com.amazon.springsecurity.controller;


import com.amazon.springsecurity.entities.PasswordResetToken;
import com.amazon.springsecurity.event.RegistrationCompleteEvent;
import com.amazon.springsecurity.model.PasswordModel;
import com.amazon.springsecurity.model.Usermodel;
import com.amazon.springsecurity.repository.PasswordResetTokenRepository;
import com.amazon.springsecurity.repository.VerificationTokenRepository;
import com.amazon.springsecurity.service.Userservice;
import com.amazon.springsecurity.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController

public class Registrationcontroller {
    private static final Logger log = LoggerFactory.getLogger(Registrationcontroller.class);
    @Autowired
    private ApplicationEventPublisher aep;
@Autowired
private PasswordResetTokenRepository ptr;
    @Autowired
    private Userservice us;

    @PostMapping("register")
    public String registeruser(@RequestBody Usermodel um, HttpServletRequest r){
        User u=us.registeruser(um);
        aep.publishEvent(new RegistrationCompleteEvent(u,applicationUrl(r)));

return "successfully link sent";

    }

    private String applicationUrl(HttpServletRequest r) {
        return "http://"+r.getServerName()+":"+r.getServerPort()+r.getContextPath();
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam String token){
        String result=us.verifyRegistration(token);
        if(result.equalsIgnoreCase("success")){return "successfully acc created";}
        return "bad user";

    }
  @GetMapping("/resendlinkregistration")
    public String resendlink(@RequestParam String oldtoken,HttpServletRequest r){
     User u=us.getUserbytoken(oldtoken);
      aep.publishEvent(new RegistrationCompleteEvent(u,applicationUrl(r)));
      return "successfully link sent";

  }

  @PostMapping("/resetpassword")
    public String resetpassword(@RequestParam int id,HttpServletRequest r){
         String result=us.resetpassword(id);
         if(result.equalsIgnoreCase("invalid")){return "user not found";}

         log.info(applicationUrl(r)+result);
         return "successfully link sent";

  }
    @PostMapping("/verifyresetpassword")
    public String verifyresetpassword(@RequestParam String token,@RequestBody PasswordModel pm){
        String result=us.verifyresetpassword(token,pm);
        if(result.equalsIgnoreCase("invalid")){return "nah man token expired";}
        return result;

    }
    @GetMapping("/resendlinkresetpassword")
    public String resendlinkresetpassword(@RequestParam String oldtoken,HttpServletRequest r) {
        PasswordResetToken prt=ptr.findByToken(oldtoken);
        int id=prt.getUser().getId();
        String token= us.resetpassword(id);
        String url="/verifyresetpassword"+"?token="+token;
        log.info(applicationUrl(r)+url);
        return "sent again check";
    }

    @GetMapping("/api/hello")
    public String hello(){
        return "HELLO USER";
    }

}
