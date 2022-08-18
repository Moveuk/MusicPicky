package com.team3.musicpicky.controller.request;

import com.team3.musicpicky.domain.Post;
import com.team3.musicpicky.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdatePostRequestDto {

    @NotBlank
    private String title;

    private User user;

    @NotBlank
    private String artist;

    @NotBlank
    private Post.Genre genre;

    @NotBlank
    private String content;

    private MultipartFile imageFile;

    @NotBlank
    private String videoUrl;
}
