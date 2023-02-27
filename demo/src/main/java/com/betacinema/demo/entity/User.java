package com.betacinema.demo.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.sql.Date;
import java.math.BigDecimal;

@Entity
@Table(name = "Member")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private int UserID;
    @Column(name = "UserName")
    private String UserName;
    @Column(name = "DOB")
    private Date DOB;
    @Column(name = "Balance")
    private java.math.BigDecimal Balance;
    @Column(name = "VIP")
    private boolean VIP;
    @Column(name = "Email")
    private String Email;

    public User() {
    }

    public User(int userID, String userName, Date DOB, BigDecimal balance, boolean VIP, String email) {
        UserID = userID;
        UserName = userName;
        this.DOB = DOB;
        Balance = balance;
        this.VIP = VIP;
        Email = email;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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
