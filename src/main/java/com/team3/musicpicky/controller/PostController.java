package com.team3.musicpicky.controller;

import com.team3.musicpicky.controller.request.CreatePostRequestDto;
import com.team3.musicpicky.controller.request.UpdatePostRequestDto;
import com.team3.musicpicky.controller.response.ResponseDto;
import com.team3.musicpicky.domain.Post;
import com.team3.musicpicky.domain.UserDetailsImpl;
import com.team3.musicpicky.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping(path = "/api/posts")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<ResponseDto> createPost(CreatePostRequestDto createPostRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        createPostRequestDto.setUser(userDetails.getUser());

        return new ResponseEntity<>(
                ResponseDto.success(postService.createPost(createPostRequestDto)), HttpStatus.valueOf(HttpStatus.OK.value()));
    }

    @GetMapping(path = "/{postId}")
    public ResponseEntity<ResponseDto> getPost(@PathVariable Long postId) {
        return new ResponseEntity<>(
                ResponseDto.success(postService.getPost(postId)), HttpStatus.valueOf(HttpStatus.OK.value()));
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getPostList() {
        return new ResponseEntity<>(
                ResponseDto.success(postService.getPostList()), HttpStatus.valueOf(HttpStatus.OK.value()));
    }

    @GetMapping(params = "genre")
    public ResponseEntity<ResponseDto> getPostListByGenre(@RequestParam Post.Genre genre) {
        return new ResponseEntity<>(
                ResponseDto.success(postService.getPostListByGenre(genre)), HttpStatus.valueOf(HttpStatus.OK.value()));
    }

    @PutMapping(path = "/{postId}")
    public ResponseEntity<ResponseDto> updatePost(@PathVariable Long postId, UpdatePostRequestDto updatePostRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        updatePostRequestDto.setUser(userDetails.getUser());
        return new ResponseEntity<>(
                ResponseDto.success(postService.updatePost(postId, updatePostRequestDto)), HttpStatus.valueOf(HttpStatus.OK.value()));
    }

}
