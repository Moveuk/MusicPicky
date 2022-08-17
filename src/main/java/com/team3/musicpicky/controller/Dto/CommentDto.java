package com.team3.musicpicky.controller.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.team3.musicpicky.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentDto {

    private Long commentId;
    private UserDto userDto;
    private String comment;
    private Long postId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;

    public CommentDto(Comment commentAs, UserDto userDto) {
        this.commentId = commentAs.getCommentId();
        this.userDto = userDto;
        this.comment = commentAs.getComment();
        this.createdAt = commentAs.getCreatedAt();
        this.modifiedAt = commentAs.getModifiedAt();
        this.postId = commentAs.getPost().getPostId();
    }
}
