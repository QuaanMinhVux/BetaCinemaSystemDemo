package com.example.filmproject;

import com.example.filmproject.repositories.FilmRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FilmProjectApplicationTests {

    @Autowired

    private FilmRepository repository;
    @Test
    void contextLoads() {
        repository.findAll();
    }
    @Test
    void contextLoads2() {
        repository.getAllByYear(1);
    }
    @Test
    void contextLoads3() {
        repository.getAllByName("Long");
    }
    @Test
    void contextLoads4() {
        repository.getAllByCategory(1);
    }
    @Test
    void getAllSize() {
        int exp = 5;
        int result = repository.findAll().size();
        Assertions.assertEquals(exp,result);
    }
    @Test
    void getAllByName() {
        int exp = 0;
        int result = repository.getAllByName("jj").size();
        Assertions.assertEquals(exp,result);
    }
    @Test
    void getAllByName2() {
        int exp = 0;
        int result = repository.getAllByName("kk").size();
        Assertions.assertEquals(exp,result);
    }
    @Test
    void getAllByName3() {
        int exp = 0;
        int result = repository.getAllByName("-1").size();
        Assertions.assertEquals(exp,result);
    }
    @Test
    void getAllByName4() {
        int exp = 1;
        int result = repository.getAllByName("Cars").size();
        Assertions.assertEquals(exp,result);
    }
    @Test
    void getAllByName5() {
        int exp = 1;
        int result = repository.getAllByName("Havard").size();
        Assertions.assertEquals(exp,result);
    }
    @Test
    void getAllByYear() {
        int exp = 1;
        int result = repository.getAllByYear(2006).size();
        Assertions.assertEquals(exp,result);
    }
    @Test
    void getAllByYear2() {
        int exp = 0;
        int result = repository.getAllByYear(9999).size();
        Assertions.assertEquals(exp,result);
    }
    @Test
    void getAllByYear3() {
        int exp = 1;
        int result = repository.getAllByYear(2023).size();
        Assertions.assertEquals(exp,result);
    }
    @Test
    void getAllByYear4() {
        int exp = 2;
        int result = repository.getAllByYear(2002).size();
        Assertions.assertEquals(exp,result);
    }
    @Test
    void getAllByYear5() {
        int exp = 0;
        int result = repository.getAllByYear(-1).size();
        Assertions.assertEquals(exp,result);
    }
    @Test
    void getAllByCate() {
        int exp = 1;
        int result = repository.getAllByCategory(1).size();
        Assertions.assertEquals(exp,result);
    }
    @Test
    void getAllByCate1() {
        int exp = 1;
        int result = repository.getAllByCategory(2).size();
        Assertions.assertEquals(exp,result);
    }
    @Test
    void getAllByCate3() {
        int exp = 1;
        int result = repository.getAllByCategory(3).size();
        Assertions.assertEquals(exp,result);
    }
    @Test
    void getAllByCate4() {
        int exp = 1;
        int result = repository.getAllByCategory(4).size();
        Assertions.assertEquals(exp,result);
    }
    @Test
    void getAllByCate5() {
        int exp = 1;
        int result = repository.getAllByCategory(5).size();
        Assertions.assertEquals(exp,result);
    }

}
