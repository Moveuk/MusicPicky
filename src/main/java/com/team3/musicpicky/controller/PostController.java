package com.team3.musicpicky.controller;

import com.team3.musicpicky.controller.request.CreatePostRequestDto;
import com.team3.musicpicky.controller.response.ResponseDto;
import com.team3.musicpicky.domain.UserDetailsImpl;
import com.team3.musicpicky.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping(path = "/api/posts")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<ResponseDto> createPost(CreatePostRequestDto createPostRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return postService.createPost(createPostRequestDto);
    }

}
