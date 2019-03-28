package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private MainService mainService;
    private UserRepository userRepository;

    @Autowired
    public UserController(MainService mainService, UserRepository userRepository) {
        this.mainService = mainService;
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    List<User> articleAdd(@RequestParam(name = "name") String name,
                          @RequestParam(name = "age") int age){
        return mainService.userAdd(name, age);
    }

    @PostMapping("/older")
    public Collection<User> findAllUsersOlderSomeAge(@RequestParam(name = "age") int age){
    return userRepository.findAllUsersOlderSomeAge(age);
    }

    @PostMapping("/articleTextColor")
    public List<User>findAllUserWithArtByTextColor(@RequestParam(name = "color") String color){
        return mainService.findArticlesByColor(color);
    }

    @GetMapping("/uniqueName")
    public HashSet<String> showUniqueNames(){
        return mainService.showUniqueNamesWithManyArticles();
    }
}
