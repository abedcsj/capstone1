package com.example.capstone1.user.entity;

import com.example.capstone1.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String username;

    @Setter
    @Column(nullable = false)
    private String email;

    @Setter
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy="user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Post> post;
}

