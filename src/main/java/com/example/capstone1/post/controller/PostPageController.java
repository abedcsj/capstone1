package com.example.capstone1.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostPageController {
    @GetMapping("/post")
    public String getPostPage() {
        return "post";
    }
}
