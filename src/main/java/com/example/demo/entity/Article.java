package com.example.demo.entity;

import com.example.demo.entity.enumeration.TextColor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "articles")
public class Article extends BaseEntity{

    private String text;

    @Enumerated
    private TextColor color;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private User author;

    public Article(String text, TextColor color) {
        this.text = text;
        this.color = color;
    }

    public Article(String text, TextColor color, User author) {
        this.text = text;
        this.color = color;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Article{" +
                "text='" + text + '\'' +
                ", color=" + color +
                ", author=" + author.getName() +
                '}';
    }
}
