package com.team3.musicpicky.controller.Dto;


import com.team3.musicpicky.domain.Comment;
import com.team3.musicpicky.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentSimpleDto {

    private Long userId;
    private String username;
    private Long commentId;
    private String comment;

    @Builder
    public CommentSimpleDto(Comment commentAs) {
        this.userId = commentAs.getUser().getUserId();
        this.username = commentAs.getUser().getUsername();
        this.commentId = commentAs.getCommentId();
        this.comment = commentAs.getComment();
    }

}
