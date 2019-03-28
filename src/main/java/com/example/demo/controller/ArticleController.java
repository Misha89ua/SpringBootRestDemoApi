package com.example.demo.controller;

import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private MainService mainService;
    private ArticleRepository articleRepository;

    @Autowired
    public ArticleController(MainService mainService, ArticleRepository articleRepository) {
        this.mainService = mainService;
        this.articleRepository = articleRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllUsers(){
        return new ResponseEntity<>(articleRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    List<Article> articleAdd(@RequestParam(name = "text") String text,
                             @RequestParam(name = "color") String color,
                             @RequestParam(name = "user_id") int userId){
        return mainService.articleAdd(text, color, userId);
    }

}
