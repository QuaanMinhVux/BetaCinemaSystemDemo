package com.betacinema.demo.service;

import jakarta.servlet.http.HttpSession;

public class Authorize {
    public boolean checkLogin(HttpSession session){
        if(session.getAttribute("login") != null){
            return true;
        }
        return false;
    }
}
