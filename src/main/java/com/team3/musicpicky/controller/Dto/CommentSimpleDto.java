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
    private String comment;

    @Builder
    public CommentSimpleDto(Comment commentAs) {
        this.userId = commentAs.getUser().getUserId();
        this.comment = commentAs.getComment();
    }

}
