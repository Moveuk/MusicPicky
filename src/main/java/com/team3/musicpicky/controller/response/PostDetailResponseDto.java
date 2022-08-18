package com.team3.musicpicky.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.team3.musicpicky.controller.Dto.CommentDto;
import com.team3.musicpicky.controller.Dto.CommentSimpleDto;
import com.team3.musicpicky.controller.Dto.UserDto;
import com.team3.musicpicky.domain.Post;
import com.team3.musicpicky.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class PostDetailResponseDto {

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

    private List<CommentSimpleDto> commentListSimple;

    private Long likeCnt;
    private Long uid;
    @Builder
    public PostDetailResponseDto(Post post, Long uid) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.user = post.getUser();
        this.artist = post.getArtist();
        this.genre = post.getGenre();
        this.content = post.getContent();
        this.imageUrl = post.getImageUrl();
        this.videoUrl = post.getVideoUrl();
        this.likeCnt = post.getLikeCnt();
        this.uid = uid;
        this.commentListSimple = post.getCommentList().stream().map(comment -> CommentSimpleDto.builder().commentAs(comment).build()).collect(Collectors.toList());
    }
}