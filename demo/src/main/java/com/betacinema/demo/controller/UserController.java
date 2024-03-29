package com.betacinema.demo.controller;

import com.betacinema.demo.entity.User;
import com.betacinema.demo.service.Authorize;
import com.betacinema.demo.service.IUser;
import com.betacinema.demo.service.ResetPasswordService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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

    private Authorize authorize = new Authorize();


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
    @GetMapping("/test")
    public String test(Model model){

        model.addAttribute("msg","hi");
        return "test";
    }
    @GetMapping("/user-update")
    public ModelAndView userUpdate(HttpSession session){
        if(!authorize.checkLogin(session)){
            return new ModelAndView("redirect:/login");
        }
        ModelAndView mav = new ModelAndView("Setting.html");
        User u = new User();
        mav.addObject("user", u);
        mav.addObject("flag", null);
        return mav;
    }
    @PostMapping("/user-update")
    public ModelAndView userUpdate(HttpSession session, @ModelAttribute("user") User user, HttpServletRequest request) {
        authorize.checkLogin(session);
        User u = (User)session.getAttribute("login");
        String newPwd = request.getParameter("newPwd");
        ModelAndView mav = new ModelAndView("Setting.html");
        System.out.println(user.getUserName());
        if(user.getUserName().trim().isBlank() || user.getUserName() == null){
            System.out.println("name null");
            if(u.getPassword().equals(user.getPassword()) && user.getPassword().matches("[a-zA-Z0-9]+")){
                u.setPassword(newPwd);
                iUser.update(u);
                u = iUser.getUserByEmail(u.getEmail());
                session.removeAttribute("login");
                session.setAttribute("login", u);
                mav.addObject("flag", "Change success");
            }else{
                mav.addObject("flag", "Something gone wrong, try again");
            }
        }else{
            System.out.println("name not null");
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
        }
        return mav;
    }
    @GetMapping("/user-premium")
    public ModelAndView userPremium(HttpSession session){
        if(!authorize.checkLogin(session)){
            return new ModelAndView("redirect:/login");
        }
        ModelAndView mav = new ModelAndView("Premium.html");
        mav.addObject("user", session.getAttribute("login"));
        return mav;
    }
    @PostMapping("/user-premium")
    public ModelAndView userPremium(HttpSession session, HttpServletRequest request){
        if(!authorize.checkLogin(session)){
            return new ModelAndView("redirect:/login");
        }
        double money = Double.parseDouble(request.getParameter("m1"));
        User u = (User)session.getAttribute("login");
        if(money > u.getBalance().doubleValue()){
            ModelAndView mav = new ModelAndView("redirect:/user-premium");
            mav.addObject("flag", "Don't have enough money");
            return mav;
        }else{
            Double balance = u.getBalance().doubleValue() - money;
            BigDecimal m = BigDecimal.valueOf(balance);
            u = iUser.update(u, true, m);
            session.removeAttribute("login");
            session.setAttribute("login", u);
            return new ModelAndView("redirect:/user-profile");
        }
    }
    @GetMapping("/user-profile")
    public ModelAndView userProfile(HttpSession session){
        if(!authorize.checkLogin(session)){
            return new ModelAndView("redirect:/login");
        }
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
    @GetMapping("/change-password")
    public ModelAndView changePwd(HttpSession session){
        if(!authorize.checkLogin(session)){
            return new ModelAndView("redirect:/login");
        }
        ModelAndView mav = new ModelAndView("ChangePwd.html");
        mav.addObject("flag", null);
        return mav;
    }
    @PostMapping("/change-password")
    public ModelAndView changePwd(HttpServletRequest request, HttpSession session){
        if(!authorize.checkLogin(session)){
            return new ModelAndView("redirect:/login");
        }
        String newPwd = request.getParameter("newPwd");
        String renewPwd = request.getParameter("renewPwd");
        if(newPwd.equals(renewPwd) && (!newPwd.trim().isBlank() && newPwd != null)){
            User u = (User)session.getAttribute("login");
            u.setPassword(newPwd);
            iUser.update(u);
            return new ModelAndView("redirect:/");
        }
        ModelAndView mav = new ModelAndView("ChangePwd.html");
        mav.addObject("flag", "Something gone wrong, try again");
        return mav;
    }
    @GetMapping("/reset-password")
    public ModelAndView resetPwd(){
        ModelAndView mav = new ModelAndView("ForgotPwd.html");
        mav.addObject("user", new User());
        mav.addObject("flag", null);
        return mav;
    }
    @PostMapping("/reset-password")
    public ModelAndView sendResetLink(@ModelAttribute("user") User user, HttpSession session) throws UserPrincipalNotFoundException, MessagingException {
        ModelAndView mav = new ModelAndView();
        if(iUser.getUserByEmail(user.getEmail()) == null){
            mav.addObject("flag", "Email not found");
            mav.setViewName("ForgotPwd.html");
            return mav;
        }
        resetPasswordService.sendResetLink(user.getEmail(), iUser.getUserByEmail(user.getEmail()));
        mav.setViewName("Login.html");
        session.setAttribute("flag", true);
        return mav;
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
