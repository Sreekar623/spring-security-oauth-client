package com.amazon.springsecurity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usermodel {
    private String fname;
    private String lname;
    @Column(unique = true)
    private String email;
    private String password;
    private String matchingpswd;

}
