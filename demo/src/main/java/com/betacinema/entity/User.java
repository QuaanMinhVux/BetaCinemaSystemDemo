package com.betacinema.entity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserID;
    @Column
    private String UserName;
    @Column
    private Date DOB;
    @Column
    private java.math.BigDecimal Balance;
    @Column
    private boolean VIP;

    public User() {
    }

    public User(int userID, String userName, Date DOB, BigDecimal balance, boolean VIP) {
        UserID = userID;
        UserName = userName;
        this.DOB = DOB;
        Balance = balance;
        this.VIP = VIP;
    }

    public int getUserID() {
        return UserID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public BigDecimal getBalance() {
        return Balance;
    }

    public void setBalance(BigDecimal balance) {
        Balance = balance;
    }

    public boolean isVIP() {
        return VIP;
    }

    public void setVIP(boolean VIP) {
        this.VIP = VIP;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserID=" + UserID +
                ", UserName='" + UserName + '\'' +
                ", DOB=" + DOB +
                ", Balance=" + Balance +
                ", VIP=" + VIP +
                '}';
    }
}
