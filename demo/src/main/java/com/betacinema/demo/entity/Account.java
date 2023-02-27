package com.betacinema.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccountID")
    private int accountID;
    @Column(name = "Email")
    private String email;
    @Column(name = "Password")
    private String password;
    @Column(name = "UserID")
    private Integer userID;
    @Column(name = "AdminID")
    private String adminID;

    public Account() {
    }

    public Account(int accountID, String email, String password, Integer userID, String adminID) {
        this.accountID = accountID;
        this.email = email;
        this.password = password;
        this.userID = userID;
        this.adminID = adminID;
    }

    public int getAccountID() {
        return accountID;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }
}
