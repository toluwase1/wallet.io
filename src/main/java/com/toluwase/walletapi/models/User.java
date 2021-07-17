package com.toluwase.walletapi.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
//@Table(name = "users")
public class User extends BaseModel {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String pin;
    private String bvn;
    private Double balance;
    private Boolean verifiedEmail;
    private String verifiedPhone;
    private String verifiedBvn;
    private Level levels = Level.LEVEL_ONE;
    @Column(name = ("verification_code"), length = 64)
    private String verificationCode;
    private boolean enabled;
    private String accountNumber;
}