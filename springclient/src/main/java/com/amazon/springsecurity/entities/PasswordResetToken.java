package com.amazon.springsecurity.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expirytime;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id",nullable=false,foreignKey=@ForeignKey(name="FK_USERVERIFY_TOKEN"))
    private User user;

    private static final int EXP_TIME=5;

    public PasswordResetToken(User u, String token){
        super();
        this.user=u;
        this.token=token;
        this.expirytime=calculateExpiryTime(EXP_TIME);
    }

    private Date calculateExpiryTime(int expTime) {

        Calendar c=Calendar.getInstance();
        c.add(Calendar.MINUTE,EXP_TIME);
        return c.getTime();//converts to Date from calendar
    }

    public PasswordResetToken(String token){
        super();
        this.token=token;
        this.expirytime=calculateExpiryTime(EXP_TIME);
    }




}
