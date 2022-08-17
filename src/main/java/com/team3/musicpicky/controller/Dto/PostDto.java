package com.team3.musicpicky.controller.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.team3.musicpicky.domain.Post;
import com.team3.musicpicky.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostDto {

    @NotBlank
    private Long postId;

    @NotBlank
    private String title;

    @NotBlank
    private UserDto userDto;

    @NotBlank
    private String artist;

    @NotBlank
    private Post.Genre genre;

    @NotBlank
    private String content;

    @NotBlank
    private String imageUrl;

    @NotBlank
    private String videoUrl;

    private Long likeCnt;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;


    public PostDto(Post post, UserDto userDto) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.userDto = userDto;
        this.artist = post.getArtist();
        this.genre = post.getGenre();
        this.content = post.getContent();
        this.imageUrl = post.getImageUrl();
        this.videoUrl = post.getVideoUrl();
        this.likeCnt = post.getLikeCnt();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}