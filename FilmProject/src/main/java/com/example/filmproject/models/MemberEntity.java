package com.example.filmproject.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Member", schema = "dbo", catalog = "movie_webdb")
public class MemberEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "UserID")
    private int userId;
    @Basic
    @Column(name = "UserName")
    private String userName;
    @Basic
    @Column(name = "DOB")
    private Date dob;
    @Basic
    @Column(name = "Balance")
    private float balance;
    @Basic
    @Column(name = "VIP")
    private boolean vip;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberEntity that = (MemberEntity) o;
        return userId == that.userId && vip == that.vip && Objects.equals(userName, that.userName) && Objects.equals(dob, that.dob) && Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, dob, balance, vip);
    }
}
