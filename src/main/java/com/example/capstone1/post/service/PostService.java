package com.example.capstone1.post.service;

import com.example.capstone1.post.dto.PostDto;
import com.example.capstone1.post.entity.Post;
import com.example.capstone1.post.repository.PostRepository;
import com.example.capstone1.user.entity.User;
import com.example.capstone1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with ID: " + postId));
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
    }

    public void createPost(Long userId, PostDto.createReq req) {
        User user = findUserById(userId);
        Post post = Post.toEntity(req.getTitle(), req.getContent(), user);
        postRepository.save(post);
    }

    public PostDto.createRes detailPost(Long postId) {
        Post post = findPostById(postId);
        return new PostDto.createRes(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getLikeCount()
        );
    }

    public void updatePost(Long userId, PostDto.updateReq req) {
        Post post = findPostById(req.getPostId());
        if (!post.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("User is not the owner of the post.");
        }
        post.setTitle(req.getTitle());
        post.setContent(req.getContent());
        postRepository.save(post);
    }

    public void deletePost(Long userId, PostDto.deleteReq req) {
        User user = findUserById(userId);
        Post post = findPostById(req.getPostId());
        if (!post.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("User is not the owner of the post.");
        }
        postRepository.delete(post);
    }

    public int likePost(Long postId) {
        Post post = findPostById(postId);
        post.setLikeCount(post.getLikeCount() + 1);
        postRepository.save(post);
        return post.getLikeCount();
    }
    public List<PostDto.createRes> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return PostDto.createRes.postList(posts);
    }
    public List<PostDto.createRes> searchPostsByTitle(String title) {
        List<Post> posts = postRepository.findByTitleContaining(title);
        return PostDto.createRes.postList(posts);
    }

}

