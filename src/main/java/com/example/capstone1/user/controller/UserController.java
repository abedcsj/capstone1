package com.example.capstone1.user.controller;

import com.example.capstone1.post.dto.PostDto;
import com.example.capstone1.user.dto.UserDto;
import com.example.capstone1.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;


    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDto.createReq userReq) {
        try {
            userService.createUser(userReq);
            return ResponseEntity.ok("User created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating user: " + e.getMessage());
        }
    }

    @PatchMapping
    public ResponseEntity<String> updateUser(@RequestBody UserDto.createReq userReq) {
        try {
            userService.updateUser(userReq);
            return ResponseEntity.ok("User updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found or invalid data: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating user: " + e.getMessage());
        }
    }

    @GetMapping("/detail/{userId}")
    public ResponseEntity<?> detailUser(@PathVariable Long userId) {
        try {
            UserDto.detailRes userDetail = userService.detail(userId);
            return ResponseEntity.ok(userDetail);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with ID: " + userId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving user details: " + e.getMessage());
        }
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<?> list(@PathVariable Long userId) {
        try {
            List<PostDto.createReq> postList = userService.list(userId);
            return ResponseEntity.ok(postList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No posts found for user with ID: " + userId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving user posts: " + e.getMessage());
        }
    }
}
