package com.example.capstone1.user.dto;

import com.example.capstone1.post.dto.PostDto;
import com.example.capstone1.post.entity.Post;
import com.example.capstone1.user.entity.User;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserDto {


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createReq {
        private Long id;
        private String username;
        private String email;
        private String password;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class detailReq{
        private Long id;
        private String username;
        private List<Post> posts;

    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class detailRes {
        private String username;
        private String email;
        private List<PostDto.createRes> posts;
    }


}
