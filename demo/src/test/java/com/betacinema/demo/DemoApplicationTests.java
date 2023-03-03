package com.betacinema.demo;

import com.betacinema.demo.entity.User;
import com.betacinema.demo.repository.UserRepository;
import com.betacinema.demo.service.IUser;
import com.betacinema.demo.service.ResetPasswordService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.sql.Date;
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


	@Test
	@Order(1)
	public void saveUser() throws Exception {
		User user = new User();
		user.setUserName("Vu Truong Huy");
		user.setBalance(BigDecimal.valueOf(10000.0));
		user.setDob(Date.valueOf("2005-12-12"));
		user.setEmail("Huy@gmail.com");
		user.setVip(false);
		user.setPassword("121205");
		iuser.addUser(user);
		Assertions.assertEquals(iuser.getUserByEmail("Huy@gmail.com").getUserID(), user.getUserID());
	}
	@Test
	@Order(1)
	public void saveUser_duplicateEmail(){
		User user = new User();
		user.setUserName("Vu Truong Huy");
		user.setBalance(BigDecimal.valueOf(10000.0));
		user.setDob(Date.valueOf("2005-12-12"));
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
		user.setDob(Date.valueOf("2005-12-12"));
		user.setEmail("Hy@gmail.com");
		user.setVip(false);
		user.setPassword("121205");
		Assertions.assertThrows(RuntimeException.class,()-> iuser.delete(user));
	}
	@Test
	public void getUserNotExist(){
		iuser.getUserByID(1);
	}

//	@Test
//	public void testSendMail(){
//		try {
//			resetPasswordService.sendResetLink("quanvmhe160023@fpt.edu.vn");
//		}catch (Exception e){
//
//		}
//	}
	@Test
	public void testSendMail_NotFound(){
		Assertions.assertThrows(UserPrincipalNotFoundException.class, ()->resetPasswordService.sendResetLink("h@gmail.com"));
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
		User user = iuser.getUserByEmail("HuyTommm@gmail.com");
		user.setUserName("Huy Tomm");
		user.setEmail("HuyTomm@gmail.com");
		iuser.update(user);
	}
	@Test
	public void testUpdateNotFound(){
		Assertions.assertThrows(RuntimeException.class, ()->iuser.update(new User(1, "Vu Minh Quan", Date.valueOf("2001-08-02"), BigDecimal.valueOf(0.0), true, "q@gmail.com","quan12345")));
	}
	@Test
	public void testUpdateEmail(){
		User user = iuser.getUserByEmail("quanvmhe160023@fpt.edu.vn");
		Assertions.assertThrows(RuntimeException.class,()-> iuser.update(user));
	}
}
