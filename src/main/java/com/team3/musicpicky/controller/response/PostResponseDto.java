package com.team3.musicpicky.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.team3.musicpicky.domain.Post;
import com.team3.musicpicky.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class PostResponseDto {

    @NotBlank
    private Long postId;

    @NotBlank
    private String title;

    @NotBlank
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

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

//    private String countLike; // 좋아요 기능 추가 예정

    @Builder
    public PostResponseDto(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.user = post.getUser();
        this.artist = post.getArtist();
        this.genre = post.getGenre();
        this.content = post.getContent();
        this.imageUrl = post.getImageUrl();
        this.videoUrl = post.getVideoUrl();
    }
}
