package com.team3.musicpicky.controller.Dto;

import com.team3.musicpicky.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class CommentDto {

    private Long commentId;
    private UserDto userDto;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public CommentDto(Comment commentAs, UserDto userDto) {
        this.commentId = commentAs.getCommentId();
        this.userDto = userDto;
        this.comment = commentAs.getComment();
        this.createdAt = commentAs.getCreatedAt();
        this.modifiedAt = commentAs.getModifiedAt();
    }
}
