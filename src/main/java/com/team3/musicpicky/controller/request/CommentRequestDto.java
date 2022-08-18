package com.team3.musicpicky.controller.request;

import com.team3.musicpicky.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CommentRequestDto {

    private User user;

    @NotBlank
    private String comment;

    public String getComment() {return this.comment;}
}
