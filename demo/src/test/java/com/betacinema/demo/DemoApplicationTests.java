package com.betacinema.demo;

import com.betacinema.demo.entity.User;
import com.betacinema.demo.repository.UserRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Date;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	UserRepository repository;
	@Test
	@Order(1)
	public void saveUser(){
		User user = new User();
		user.setUserName("Vu Truong Huy");
		user.setBalance(BigDecimal.valueOf(10000.0));
		user.setDob(Date.valueOf("2005-12-12"));
		user.setEmail("Huy@gmail.com");
		user.setVip(false);
		user.setPassword("121205");
		repository.save(user);
	}

	@Test
	@Order(2)
	public void getAllUser(){
		repository.findAll();
	}

	@Test
	@Order(3)
	public void getUserByID(){
		repository.findById(502);
	}

	@Test
	@Order(4)
	public void getUserByEmail(){
		repository.findByEmail("quanvmhe160023@fpt.edu.vn");
	}
	@Test
	@Order(5)
	public void deleteUser(){
		User user = new User();
		user.setUserName("Vu Truong Huy");
		user.setBalance(BigDecimal.valueOf(10000.0));
		user.setDob(Date.valueOf("2005-12-12"));
		user.setEmail("Huy@gmail.com");
		user.setVip(false);
		user.setPassword("121205");
		repository.delete(user);
	}
	@Test
	@Order(6)
	public void updateUser(){
		User user = repository.findByEmail("quanvmhe160023@fpt.edu.vn");
		repository.delete(user);
		user.setPassword("quan02082001");
		repository.save(user);
	}
}
