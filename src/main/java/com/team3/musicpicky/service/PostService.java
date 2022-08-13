package com.team3.musicpicky.service;

import com.team3.musicpicky.controller.request.CreatePostRequestDto;
import com.team3.musicpicky.controller.response.ResponseDto;
import com.team3.musicpicky.domain.Post;
import com.team3.musicpicky.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public ResponseEntity<ResponseDto> createPost(CreatePostRequestDto createPostRequestDto) {

        return new ResponseEntity<>(ResponseDto.success(
                postRepository.save(
                        Post.builder()
                                .title(createPostRequestDto.getTitle())
                                .user(createPostRequestDto.getUser())
                                .artist(createPostRequestDto.getArtist())
                                .genre(createPostRequestDto.getGenre())
                                .imageUrl("test")
                                .videoUrl(createPostRequestDto.getVideoUrl())
                                .build())), HttpStatus.OK);
    }
}
