package com.betacinema.demo;

import com.betacinema.demo.entity.User;
import com.betacinema.demo.repository.UserRepository;
import com.betacinema.demo.service.IUser;
import com.betacinema.demo.service.PaypalService;
import com.betacinema.demo.service.ResetPasswordService;
import com.paypal.api.payments.Payment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
class DemoApplicationTests {
	@Test
	void contextLoads() {
	}
	@Autowired
	UserRepository repository;
	@Autowired
	IUser iuser;
	@Autowired
	ResetPasswordService resetPasswordService;
	@Autowired
	PaypalService service;
	public static final String SUCCESS_URL = "pay/success";
	public static final String CANCEL_URL = "pay/cancel";
	@Test
	@Order(1)
	public void saveUser() throws Exception {
		User user = new User();
		user.setUserName("Vu Truong Huy");
		user.setBalance(BigDecimal.valueOf(10000.0));
		user.setEmail("Huy@gmail.com");
		user.setVip(false);
		user.setPassword("121205");
		iuser.addUser(user);
		Assertions.assertEquals(iuser.getUserByEmail("Huy@gmail.com").getUserID(), user.getUserID());
	}
	@Test
	public void testUpdateBalance(){
		User user = iuser.getUserByEmail("Huy@gmail.com");
		iuser.update(user, BigDecimal.valueOf(100.0));
	}
	@Test
	public void testUpdateBalanceFail(){
		User user = new User();
		user.setUserName("Vu Truong Huy");
		user.setBalance(BigDecimal.valueOf(10000.0));
		user.setEmail("HuyAAAA@gmail.com");
		user.setVip(false);
		user.setPassword("121205");
		Assertions.assertTrue(iuser.update(user, BigDecimal.valueOf(1000.0)) == null);
	}
	@Test
	public void testUpdateVIP(){
		User u = new User();
		u = iuser.getUserByEmail("Huy@gmail.com");
		Assertions.assertTrue(iuser.update(u, BigDecimal.valueOf(10.000)) != null);
	}
	@Test
	public void testUpdateVIPFail(){
		User user = new User();
		user.setUserName("Vu Truong Huy");
		user.setBalance(BigDecimal.valueOf(10000.0));
		user.setEmail("HuyAAAA@gmail.com");
		user.setVip(false);
		user.setPassword("121205");
		Assertions.assertTrue(iuser.update(user) == null);
	}
	@Test
	@Order(1)
	public void saveUser_duplicateEmail(){
		User user = new User();
		user.setUserName("Vu Truong Huy");
		user.setBalance(BigDecimal.valueOf(10000.0));
		user.setEmail("quanvmhe160023@fpt.edu.vn");
		user.setVip(false);
		user.setPassword("121205");
		Assertions.assertThrows(java.lang.RuntimeException.class, ()-> iuser.addUser(user));
	}

	@Test
	@Order(2)
	public void getAllUser(){
		List<User> users = iuser.getAllUser();
		Assertions.assertNotNull(users);
	}

	@Test
	@Order(3)
	public void getUserByID(){
		iuser.getUserByID(3053);
	}

	@Test
	@Order(4)
	public void getUserByEmail(){
		repository.findByEmail("quanvmhe160023@fpt.edu.vn");
	}
	@Test
	@Order(5)
	public void deleteUser() throws Exception {
		User user = iuser.getUserByEmail("Huy@gmail.com");
		iuser.delete(user);
	}
	@Test
	@Order(6)
	public void updateUser(){
		User user = repository.findByEmail("quanvmhe160023@fpt.edu.vn");
		repository.delete(user);
		user.setPassword("quan02082001");
		repository.save(user);
	}
	@Test
	@Order(5)
	public void deleteUser_userNotExist(){
		User user = new User();
		user.setUserName("Vu Truong Huy");
		user.setBalance(BigDecimal.valueOf(10000.0));
		user.setEmail("Hy@gmail.com");
		user.setVip(false);
		user.setPassword("121205");
		Assertions.assertThrows(RuntimeException.class,()-> iuser.delete(user));
	}
	@Test
	public void getUserNotExist(){
		iuser.getUserByID(1);
	}

	@Test
	public void testSendMail(){
		try {
			resetPasswordService.sendResetLink("quanvmhe160023@fpt.edu.vn", new User());
		}catch (Exception e){

		}
	}
	@Test
	public void testSendMail_NotFound(){
		User user = new User();
		user.setUserName("Vu Truong Huy");
		user.setBalance(BigDecimal.valueOf(10000.0));
		user.setEmail("h@gmail.com");
		user.setVip(false);
		user.setPassword("121205");
		Assertions.assertThrows(UserPrincipalNotFoundException.class, ()->resetPasswordService.sendResetLink("h@gmail.com", user));
	}
	@Test
	public void testResetPassword(){
		User user = repository.findByEmail("quanvmhe160023@fpt.edu.vn");
		String id = String.valueOf(user.getUserID());
		iuser.resetPassword(id, "quan12345");
	}
	@Test
	public void testResetPasswordNotFound(){
		Assertions.assertThrows(NoSuchElementException.class, ()->iuser.resetPassword("1", "quan12345"));
	}
	@Test
	public void testLogin(){
		String email = "quanvmhe160023@fpt.edu.vn";
		String password = "quan12345";
		User user = iuser.getUserByEmail(email);
		Assertions.assertTrue(user != null && user.getPassword().equals(password));
	}
	@Test
	public void testLoginWrongEmail(){
		String email = "quan@gmail.com";
		User user = iuser.getUserByEmail(email);
		Assertions.assertTrue(user == null);
	}
	@Test
	public void testLoginWrongPassword(){
		String email = "quanvmhe160023@fpt.edu.vn";
		String password = "quan02082001";
		User user = iuser.getUserByEmail(email);
		Assertions.assertFalse(!user.getPassword().equals(password));
	}
	@Test
	public void testUpdate(){
		User user = iuser.getUserByEmail("HuyTomm@gmail.com");
		user.setUserName("Huy Tomm");
		user.setEmail("HuyTomm@gmail.com");
		iuser.update(user);
	}
	@Test
	public void testUpdateNotFound(){
		Assertions.assertThrows(RuntimeException.class, ()->iuser.update(new User(1, "Vu Minh Quan", BigDecimal.valueOf(0.0), true, "q@gmail.com","quan12345")));
	}
	@Test
	public void testUpdateEmail(){
		User user = iuser.getUserByEmail("quanvmhe160023@fpt.edu.vn");
	}
	@Test
	public void testCreatePaymentError() {
		int i = 0;
		try {
			com.betacinema.demo.entity.Order order = new com.betacinema.demo.entity.Order();
			Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
					order.getIntent(), order.getDescription(), "http://localhost:8081/" + CANCEL_URL,
					"http://localhost:8081/" + SUCCESS_URL);
		}catch (Exception e){
			i++;
			Assertions.assertTrue(i == 1);
		}
	}
	@Test
	public void testCreatePayment(){
		try {
			com.betacinema.demo.entity.Order order = new com.betacinema.demo.entity.Order(100.0, "USD", "paypal", "sale", "test");
			Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
					order.getIntent(), order.getDescription(), "http://localhost:8081/" + CANCEL_URL,
					"http://localhost:8081/" + SUCCESS_URL);
		}catch (Exception e){

		}
	}

}
