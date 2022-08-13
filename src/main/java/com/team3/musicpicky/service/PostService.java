package com.team3.musicpicky.service;

import com.team3.musicpicky.controller.request.CreatePostRequestDto;
import com.team3.musicpicky.controller.response.ResponseDto;
import com.team3.musicpicky.domain.Post;
import com.team3.musicpicky.repository.PostRepository;
import com.team3.musicpicky.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final S3Service s3Service;

    public ResponseEntity<ResponseDto> createPost(CreatePostRequestDto createPostRequestDto) {

        String imageUrl = null;

        if (Objects.nonNull(createPostRequestDto.getImageFile())) {
            imageUrl = s3Service.uploadImage(createPostRequestDto.getImageFile());
        }

        return new ResponseEntity<>(ResponseDto.success(
                postRepository.save(
                        Post.builder()
                                .title(createPostRequestDto.getTitle())
                                .user(createPostRequestDto.getUser())
                                .artist(createPostRequestDto.getArtist())
                                .genre(createPostRequestDto.getGenre())
                                .content(createPostRequestDto.getContent())
                                .imageUrl(imageUrl)
                                .videoUrl(createPostRequestDto.getVideoUrl())
                                .build())), HttpStatus.OK);
    }
}
