package com.betacinema.demo.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "Member")
@Table(name = "Member", schema = "dbo")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UserID")
    private int userID;
    @Column(name = "UserName", nullable = false)
    private String userName;
    @Column(name = "Balance", nullable = false)
    private java.math.BigDecimal balance;
    @Column(name = "VIP", nullable = false)
    private boolean vip;
    @Column(name = "Email", nullable = false)
    private String email;
    @Column(name = "Password", nullable = false)
    private String password;
    public User() {
    }


    public User(int userID, String userName, BigDecimal balance, boolean vip, String email, String password) {
        this.userID = userID;
        this.userName = userName;
        this.balance = balance;
        this.vip = vip;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
