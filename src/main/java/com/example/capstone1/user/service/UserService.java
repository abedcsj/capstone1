package com.example.capstone1.user.service;

import com.example.capstone1.post.dto.PostDto;
import com.example.capstone1.post.entity.Post;
import com.example.capstone1.post.repository.PostRepository;
import com.example.capstone1.user.dto.UserDto;
import com.example.capstone1.user.entity.User;
import com.example.capstone1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;


    public void createUser(UserDto.createReq userReq) {
        User user = new User();
        user.setUsername(userReq.getUsername());
        user.setEmail(userReq.getEmail());
        user.setPassword(userReq.getPassword());
        userRepository.save(user);
    }

    public void updateUser(UserDto.createReq userReq) {
        Optional<User> userOptional = userRepository.findById(userReq.getId());
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found with ID: " + userReq.getId());
        }
        User user = userOptional.get();
        user.setUsername(userReq.getUsername());
        user.setEmail(userReq.getEmail());
        user.setPassword(userReq.getPassword());
        userRepository.save(user);
    }


    public UserDto.detailRes detail(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
        User user = userOptional.get();
        return new UserDto.detailRes(
                user.getUsername(),
                user.getEmail(),
                PostDto.createRes.postList(user.getPost())
        );
    }



    public List<PostDto.createReq> list(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }

        List<Post> userPosts = postRepository.findByUserId(userId);
        return userPosts.stream()
                .map(post -> new PostDto.createReq(post.getTitle(), post.getContent()))
                .collect(Collectors.toList());
    }
}
