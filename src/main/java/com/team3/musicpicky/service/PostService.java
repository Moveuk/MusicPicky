package com.team3.musicpicky.service;

import com.team3.musicpicky.controller.request.CreatePostRequestDto;
import com.team3.musicpicky.controller.response.ResponseDto;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    public ResponseDto<?> createPost(CreatePostRequestDto createPostRequestDto) {

        return ResponseDto.success(null);
    }
}
