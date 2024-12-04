package com.example.capstone1.post.dto;

import com.example.capstone1.post.entity.Post;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

public class PostDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    public static class createReq {
        private String title;
        private String content;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class deleteReq {
        private Long postId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createRes {
        private Long postId;
        private String title;
        private String content;
        private int likeCount;

        public static List<createRes> postList(List<Post> posts) {
            List<createRes> resList = new ArrayList<>();
            for (Post post : posts) {
                createRes res = new createRes(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getLikeCount()
                );
                resList.add(res);
            }
            return resList;
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class updateReq {
        private Long postId;
        private String title;
        private String content;
    }
}