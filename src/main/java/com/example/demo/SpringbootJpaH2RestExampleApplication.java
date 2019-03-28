package com.example.demo;

import com.example.demo.entity.Article;
import com.example.demo.entity.User;
import com.example.demo.entity.enumeration.TextColor;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringbootJpaH2RestExampleApplication implements CommandLineRunner{

    @Autowired
    MainService mainService;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaH2RestExampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mainService.clearDB();
        mainService.fillingDB();
    }
}
