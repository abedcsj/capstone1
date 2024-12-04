package com.example.capstone1.post.controller;

import com.example.capstone1.post.dto.PostDto;
import com.example.capstone1.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<String> createPost(@PathVariable Long userId, @RequestBody PostDto.createReq request) {
        postService.createPost(userId, request);
        return ResponseEntity.ok("Post created successfully.");
    }

    @GetMapping("/detail/{postId}")
    public ResponseEntity<PostDto.createRes> detailPost(@PathVariable Long postId) {
        PostDto.createRes postDetail = postService.detailPost(postId);
        return ResponseEntity.ok(postDetail);
    }

    @PatchMapping("/update/{userId}")
    public ResponseEntity<String> updatePost(@PathVariable Long userId, @RequestBody PostDto.updateReq request) {
        postService.updatePost(userId, request);
        return ResponseEntity.ok("Post updated successfully.");
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deletePost(@PathVariable Long userId, @RequestBody PostDto.deleteReq request) {
        postService.deletePost(userId, request);
        return ResponseEntity.ok("Post deleted successfully.");
    }
    @PostMapping("/like/{postId}")
    public ResponseEntity<Integer> likePost(@PathVariable Long postId) {
        int likeCount = postService.likePost(postId);
        return ResponseEntity.ok(likeCount);
    }
    @GetMapping("/list")
    public ResponseEntity<List<PostDto.createRes>> getAllPosts() {
        List<PostDto.createRes> postList = postService.getAllPosts();
        return ResponseEntity.ok(postList);
    }
}
