package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.entity.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public interface MainService {

    public List<User> userAdd(String name, int age);

    public List<Article> articleAdd(String text, String color, int userId);

    public List<User> findArticlesByColor(String color);

    public List<User> findAllUsersOlderSomeAge(int age);

    public void showUniqueNames();

    public HashSet<String> showUniqueNamesWithManyArticles();

    public void clearDB();

    public void fillingDB();
}
