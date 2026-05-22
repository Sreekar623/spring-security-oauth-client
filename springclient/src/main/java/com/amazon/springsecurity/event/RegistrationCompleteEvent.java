package com.amazon.springsecurity.event;
import com.amazon.springsecurity.entities.User;

import lombok.Data;
import org.springframework.context.ApplicationEvent;



public class RegistrationCompleteEvent extends ApplicationEvent {
    private User u;
    private String url;

    public RegistrationCompleteEvent(User u, String url) {
        super(u);
        this.u = u;
        this.url = url;
    }

    public User getU() {
        return u;
    }

    public String getUrl() {
        return url;
    }

    public void setU(User u) {
        this.u = u;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
