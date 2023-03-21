package com.betacinema.demo.controller;

import com.betacinema.demo.entity.PasswordEntity;
import com.betacinema.demo.entity.User;
import com.betacinema.demo.service.IUser;
import com.betacinema.demo.service.ResetPasswordService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private IUser iUser;
    @Autowired
    private ResetPasswordService resetPasswordService;
    @GetMapping("/users")
    public List<User> getAll(){
        return iUser.getAllUser();
    }
    @PostMapping("/register")
    public ModelAndView createNew(@ModelAttribute("user") User user) {
        User u = iUser.getUserByEmail(user.getEmail());
        System.out.println(u);
        if(u != null){
            System.out.println("Fail");
            return new ModelAndView("SignUp.html");
        }else{
            try {
                user.setBalance(BigDecimal.valueOf(0));
                user.setVip(false);
                System.out.println("Success");
                iUser.addUser(user);
            }catch(Exception exception){
                exception.printStackTrace();
            }
            return new ModelAndView("Login.html");
        }
    }
    @GetMapping("/user-update")
    public ModelAndView userUpdate(HttpSession session){
        ModelAndView mav = new ModelAndView("Setting.html");
        User u = new User();
        mav.addObject("user", u);
        mav.addObject("flag", null);
        return mav;
    }
    @PostMapping("/user-update")
    public ModelAndView userUpdate(HttpSession session, @ModelAttribute("user") User user, HttpServletRequest request) {
        User u = (User)session.getAttribute("login");
        String newPwd = request.getParameter("newPwd");
        ModelAndView mav = new ModelAndView("Setting.html");
        if(u.getPassword().equals(user.getPassword()) && user.getPassword().matches("[a-zA-Z0-9]+")){
            u.setUserName(user.getUserName());
            u.setPassword(newPwd);
            iUser.update(u);
            u = iUser.getUserByEmail(u.getEmail());
            session.removeAttribute("login");
            session.setAttribute("login", u);
            mav.addObject("flag", "Change success");
        }else if (user.getPassword().trim().isBlank() || user.getPassword() == null){
            u.setUserName(user.getUserName());
            iUser.update(u);
            u = iUser.getUserByEmail(u.getEmail());
            session.removeAttribute("login");
            session.setAttribute("login", u);
            mav.addObject("flag", "Change success");
        }else{
            mav.addObject("flag", "Something gone wrong, try again");
        }
        return mav;
    }
    @GetMapping("/user-profile")
    public ModelAndView userProfile(HttpSession session){
        User u = (User)session.getAttribute("login");
        ModelAndView mav = new ModelAndView("Profile.html");
        mav.addObject("user", u);
        return mav;
    }
    @GetMapping("/register")
    public ModelAndView registerGet(){
        ModelAndView mav = new ModelAndView("SignUp");
        User user = new User();
        mav.addObject("user", user);
        return mav;
    }
    @PostMapping("/reset-password")
    public ResponseEntity<Void> sendResetLink(@RequestParam String email) throws UserPrincipalNotFoundException, MessagingException {
        if(iUser.getUserByEmail(email) == null){
            return ResponseEntity.notFound().build();
        }
        resetPasswordService.sendResetLink(email);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/reset-password/{id}")
    public ResponseEntity<Void> resetPassword(@PathVariable("id") String id, @RequestBody PasswordEntity password){
        try {
            if(iUser.getUserByID(Integer.parseInt(id)) == null){
                return ResponseEntity.notFound().build();
            }
            iUser.resetPassword(id,password.getPassword());
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update")
    public ResponseEntity<User> updateInformation(@RequestBody @NotNull User user){
        User u = iUser.getUserByEmail(user.getEmail());
        try {
            if(u == null){
                return ResponseEntity.notFound().build();
            }
            u = iUser.update(user);
            return ResponseEntity.ok(u);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable("id") String id){
        User u = iUser.getUserByID(Integer.parseInt(id));
        if(u == null){
            return ResponseEntity.notFound().build();
        }
        try {
            iUser.delete(u);
        }catch (Exception e){

        }
        return ResponseEntity.noContent().build();

    }
    @GetMapping("user/")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
        User u = iUser.getUserByEmail(email);
        if(u == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(u);
    }


}
