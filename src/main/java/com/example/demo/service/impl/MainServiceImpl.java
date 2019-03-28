package com.example.demo.service.impl;

import com.example.demo.entity.Article;
import com.example.demo.entity.User;
import com.example.demo.entity.enumeration.TextColor;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class MainServiceImpl implements MainService {

    private UserRepository userRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public MainServiceImpl(UserRepository userRepository, ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public void clearDB() {
        userRepository.deleteAll();
        articleRepository.deleteAll();
    }

    @Override
    @Transactional
    public void fillingDB() {
        User mykhailo = new User("Mykhailo", 29);
        User taras = new User("Taras", 55);
        User ivan = new User("Ivan", 45);
        User mykola = new User("Mykola", 55);
        User mykhailo_2 = new User("Mykhailo", 55);
        User oksana = new User("Oksana", 25);
        User lilya = new User("Lilya", 67);
        User taras_2 = new User("Taras", 99);
        User ira = new User("Ira", 33);
        User sofi = new User("Sofi", 19);

        Article article_1 = new Article("Some text - 1", TextColor.BLACK, mykhailo);
        Article article_2 = new Article("Some text - 2", TextColor.GREEN, mykhailo);
        Article article_3 = new Article("Some text - 3", TextColor.RED, sofi);
        Article article_4 = new Article("Some text - 4", TextColor.RED, oksana);
        Article article_5 = new Article("Some text - 5", TextColor.GREEN, mykhailo_2);
        Article article_6 = new Article("Some text - 6", TextColor.BLUE, sofi);
        Article article_7 = new Article("Some text - 7", TextColor.BLACK, mykhailo);
        Article article_8 = new Article("Some text - 8", TextColor.BLACK, ivan);
        Article article_9 = new Article("Some text - 9", TextColor.GREEN, taras);
        Article article_10 = new Article("Some text - 10", TextColor.RED, mykhailo);

        mykhailo.setArticles(new ArrayList<Article>(){{
            add(article_1);
            add(article_2);
            add(article_7);
            add(article_10);
        }});
        sofi.setArticles(new ArrayList<Article>(){{
            add(article_3);
            add(article_6);
        }});
        oksana.setArticles(new ArrayList<Article>(){{
            add(article_4);
        }});
        mykhailo_2.setArticles(new ArrayList<Article>(){{
            add(article_5);
        }});
        ivan.setArticles(new ArrayList<Article>(){{
            add(article_8);
        }});
        taras.setArticles(new ArrayList<Article>(){{
            add(article_9);
        }});

        userRepository.save(mykhailo);
        userRepository.save(taras);
        userRepository.save(ivan);
        userRepository.save(mykola);
        userRepository.save(mykhailo_2);
        userRepository.save(oksana);
        userRepository.save(lilya);
        userRepository.save(taras_2);
        userRepository.save(ira);
        userRepository.save(sofi);
    }

    @Override
    public List<User> findAllUsersOlderSomeAge(int age) {
        System.out.println("AGE = " + age);
        return userRepository.findAllUsersOlderSomeAge(age);
    }

    @Override
    public void showUniqueNames() {
    }

    @Override
    public HashSet<String> showUniqueNamesWithManyArticles() {
        List<Integer> userIdList = new ArrayList<>(userRepository.findUserIDWithManyArticles());
        HashSet<String> userNames = new HashSet<>();
        for (Integer userId : userIdList) {
            User user = userRepository.findById(userId).get();
            userNames.add(user.getName());
        }
        return userNames;
    }

    @Override
    public List<User> findArticlesByColor(String color) {
        System.out.println("1111");
        List<User> userList = new ArrayList<>();
        userList = userRepository.findArticlesByColor(TextColor.valueOf(color).ordinal());
        System.out.println("2222");

        for (User user : userList) {
            System.out.println("#1");
            Collection<Article>articleList = new ArrayList<>();
            articleList = user.getArticles();

            Iterator<Article> articleIterator = articleList.iterator();
            while (articleIterator.hasNext()){
                Article article = articleIterator.next();
                if (article.getColor().ordinal() != TextColor.valueOf(color).ordinal()){
                    articleIterator.remove();
                }
            }
        }
        return userList;
    }

    @Override
    @Transactional
    public List<User> userAdd(String name, int age) {
        User user = new User(name,age);
        userRepository.save(user);
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public List<Article> articleAdd(String text, String color, int userId) {
        User user = userRepository.findById(userId).get();
        Article article = new Article(text, TextColor.valueOf(color),user);
        user.getArticles().add(article);
        userRepository.save(user);
        return articleRepository.findAll();
    }
}
