package com.team3.musicpicky.service;

import com.team3.musicpicky.controller.request.CreatePostRequestDto;
import com.team3.musicpicky.controller.response.PostResponseDto;
import com.team3.musicpicky.domain.Post;
import com.team3.musicpicky.exception.InvalidValueException;
import com.team3.musicpicky.global.error.ErrorCode;
import com.team3.musicpicky.repository.PostRepository;
import com.team3.musicpicky.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final S3Service s3Service;

    public Post createPost(CreatePostRequestDto createPostRequestDto) {

        String imageUrl = null;

        if (Objects.nonNull(createPostRequestDto.getImageFile())) {
            imageUrl = s3Service.uploadImage(createPostRequestDto.getImageFile());
        }

        return postRepository.save(
                        Post.builder()
                                .title(createPostRequestDto.getTitle())
                                .user(createPostRequestDto.getUser())
                                .artist(createPostRequestDto.getArtist())
                                .genre(createPostRequestDto.getGenre())
                                .content(createPostRequestDto.getContent())
                                .imageUrl(imageUrl)
                                .videoUrl(createPostRequestDto.getVideoUrl())
                                .build());
    }

    public PostResponseDto getPost(Long postId) {
        // 포스트 아이디로 조회.
        Post post = postRepository.findById(postId).orElseThrow(()-> new InvalidValueException(ErrorCode.POST_NOT_FOUND));
        return PostResponseDto.builder()
                        .post(post)
                        .imageUrl(post.getImageUrl()).build();
    }
}
