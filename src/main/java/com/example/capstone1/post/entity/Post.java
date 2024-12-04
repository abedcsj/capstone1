package com.example.capstone1.post.entity;


import com.example.capstone1.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private int likeCount=0;

    public static Post toEntity(String title, String content, User user) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setUser(user);
        post.setLikeCount(0);
        return post;
    }
}
