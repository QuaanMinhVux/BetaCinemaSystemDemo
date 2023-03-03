package com.betacinema.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PasswordEntity {
    @Id
    private String password;

    public PasswordEntity() {
    }

    public PasswordEntity(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
