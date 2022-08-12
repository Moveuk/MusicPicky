package com.team3.musicpicky.controller;

import com.team3.musicpicky.controller.request.CreatePostRequestDto;
import com.team3.musicpicky.controller.response.ResponseDto;
import com.team3.musicpicky.domain.UserDetailsImpl;
import com.team3.musicpicky.service.PostService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(path = "/api/posts")
@RestController
public class PostController {

    PostService postService;

    @PostMapping
    public ResponseDto<?> createPost(CreatePostRequestDto createPostRequestDto, MultipartFile imageFile, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        createPostRequestDto.setUser(userDetails.getUser());

        return null;
    }

}
