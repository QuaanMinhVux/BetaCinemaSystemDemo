package com.betacinema.demo.controller;

import com.betacinema.demo.entity.Order;
import com.betacinema.demo.entity.User;
import com.betacinema.demo.service.Authorize;
import com.betacinema.demo.service.IUser;
import com.betacinema.demo.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;


@RestController
    public class PaymentController {
    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private PaypalService service;
    @Autowired
    IUser iUser;
    Authorize authorize = new Authorize();

    @GetMapping("/pay")
    public ModelAndView payment(HttpSession session){
        if(!authorize.checkLogin(session)){
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("Error");
    }
    @PostMapping("/pay")
    public ModelAndView payment(HttpServletRequest request, HttpSession session) {
        if(!authorize.checkLogin(session)){
            return new ModelAndView("redirect:/login");
        }
        try {
            Order order = new Order();
            double price = Double.parseDouble(request.getParameter("money"));
            order.setPrice(price);
            order.setCurrency("USD");
            order.setDescription("");
            order.setMethod("paypal");
            order.setIntent("Sale");
            Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
                    order.getIntent(), order.getDescription(), "http://localhost:8081/" + CANCEL_URL,
                    "http://localhost:8081/" + SUCCESS_URL);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    session.setAttribute("money", price);
                    User u = (User) session.getAttribute("login");
                    Double money = (Double)session.getAttribute("money") + u.getBalance().doubleValue();
                    System.out.println(session.getAttribute("money"));
                    return new ModelAndView("redirect:"+link.getHref());
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return new ModelAndView("redirect:/");
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public ModelAndView successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, HttpSession session) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                User u = (User)session.getAttribute("login");
                Double money = (Double)session.getAttribute("money") + u.getBalance().doubleValue();
                BigDecimal balance = BigDecimal.valueOf(money);
                iUser.update(u, balance);
                u.setBalance(balance);
                session.removeAttribute("login");
                session.setAttribute("login", u);
                return new ModelAndView("redirect:/user-profile");
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return new ModelAndView("redirect:/");
    }

}

