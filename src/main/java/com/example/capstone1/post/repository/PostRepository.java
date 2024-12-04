package com.example.capstone1.post.repository;


import com.example.capstone1.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post>findByUserId(Long userid);
}
