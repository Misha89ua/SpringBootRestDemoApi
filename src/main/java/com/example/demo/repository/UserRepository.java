package com.example.demo.repository;

import com.example.demo.entity.Article;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM Users u WHERE u.age > ?1", nativeQuery = true)
    List<User> findAllUsersOlderSomeAge(int age);


    @Query(value = "SELECT u.* FROM Users u JOIN Articles a on a.color = ?1 and u.id = a.user_id", nativeQuery = true)
    List<User> findArticlesByColor(int color);

    @Query(value = "SELECT a.user_id FROM Articles a GROUP BY a.user_id HAVING count(*)>2;", nativeQuery = true)
    List<Integer> findUserIDWithManyArticles();

}
