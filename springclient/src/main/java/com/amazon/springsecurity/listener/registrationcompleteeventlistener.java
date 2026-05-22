package com.amazon.springsecurity.listener;

import com.amazon.springsecurity.event.RegistrationCompleteEvent;
import com.amazon.springsecurity.service.Userservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import com.amazon.springsecurity.entities.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class registrationcompleteeventlistener implements ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
  private  Userservice us;
    private static Logger lg= LoggerFactory.getLogger(RegistrationCompleteEvent.class);
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent registrationCompleteEvent) {


        User u=registrationCompleteEvent.getU();
        String token= UUID.randomUUID().toString();

        us.saveVerificationTokenForUser(token,u);

        String url=registrationCompleteEvent.getUrl()+"/verifyRegistration?token="+token;

        lg.info("click this below link to verify ur registration"+url);

    }
}
