package com.team3.musicpicky.service;

import com.team3.musicpicky.controller.request.CreatePostRequestDto;
import com.team3.musicpicky.controller.request.UpdatePostRequestDto;
import com.team3.musicpicky.controller.response.PostResponseDto;
import com.team3.musicpicky.domain.Post;
import com.team3.musicpicky.exception.InvalidValueException;
import com.team3.musicpicky.global.error.ErrorCode;
import com.team3.musicpicky.repository.PostRepository;
import com.team3.musicpicky.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

        // Dto 팩토리 메서드로 리팩토링
        return postRepository.save(createPostRequestDto.postFrom(imageUrl));
    }

    public PostResponseDto getPost(Long postId) {
        // 포스트 아이디로 조회.
        Post post = postRepository.findById(postId).orElseThrow(()-> new InvalidValueException(ErrorCode.POST_NOT_FOUND));
        return PostResponseDto.builder()
                .post(post)
                .build();
    }

    public List<PostResponseDto> getPostList() {
        // 모든 포스트 목록 조회.
        return postRepository.findAll(Sort.by(Sort.Direction.DESC,"postId"))
                .stream()
                .map(post -> PostResponseDto.builder().post(post).build())
                .collect(Collectors.toList());
    }

    public List<PostResponseDto> getPostListByGenre(Post.Genre genre) {
        // 포스트 장르를 기준으로 목록 조회.
        return postRepository.findAllByGenreOrderByPostIdDesc(genre)
                .orElseThrow(() ->
                        new InvalidValueException(ErrorCode.POST_NOT_FOUND))
                .stream()
                .map(post -> PostResponseDto.builder().post(post).build())
                .collect(Collectors.toList());
    }

    @Transactional
    public Post updatePost(Long postId, UpdatePostRequestDto updatePostRequestDto) {

        String imageUrl = null;

        Post post = postRepository.findById(postId).orElseThrow(() -> new InvalidValueException(ErrorCode.POST_NOT_FOUND));

        //새로운 사진이 들어왔으면 새로운 파일을 넣고 기존 파일을 삭제함.
        if (Objects.nonNull(updatePostRequestDto.getImageFile())) {
            // 새로운 이미지 s3 버킷 저장
            imageUrl = s3Service.uploadImage(updatePostRequestDto.getImageFile());
            // 기존 이미지 삭제 (내부에 Url->filename으로 분리 로직 존재)
            s3Service.deleteObjectByImageUrl(post.getImageUrl());
        } else {
            // 사진이 null값이면 기존 사진을 그대로 사용함.
            imageUrl = post.getImageUrl();
        }

        post.update(
                updatePostRequestDto.getTitle(),
                updatePostRequestDto.getUser(),
                updatePostRequestDto.getArtist(),
                updatePostRequestDto.getGenre(),
                updatePostRequestDto.getContent(),
                imageUrl,
                updatePostRequestDto.getVideoUrl()
        );

        return post;
    }
}
