package com.betacinema.demo.controller;

import com.betacinema.demo.entity.User;
import com.betacinema.demo.service.IUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {
    @Autowired
    private IUser iUser;
    @GetMapping("/get")
    public User getUser(@RequestBody User user){
        User u = iUser.getUserByEmail(user.getEmail());
        return u;
    }
    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("user") User user, HttpSession session){
        User u = iUser.getUserByEmail(user.getEmail());
        System.out.println(user.getEmail() + " " + user.getPassword());
        System.out.println(u.getEmail() + " " + u.getPassword());
        if(u == null){
            return new ModelAndView("Login.html");
        }else{
            boolean check = u.getPassword().equals(user.getPassword());
            if(check){
                session.setAttribute("login", u);
                System.out.println("Success");
                return new ModelAndView("redirect:/");
            }else{
                System.out.println("False");
                return new ModelAndView("Login.html");
            }
        }
    }
    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session){
        session.removeAttribute("login");
        System.out.println(session.getAttribute("login"));
        return new ModelAndView("redirect:/");
    }
    @GetMapping("/login")
    public ModelAndView login(){
        System.out.println("Success");
        ModelAndView mav = new ModelAndView("Login.html");
        User user = new User();
        mav.addObject("user", user);
        return mav;
    }
}
