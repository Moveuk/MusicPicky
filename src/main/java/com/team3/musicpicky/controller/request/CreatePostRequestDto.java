package com.team3.musicpicky.controller.request;

import com.team3.musicpicky.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostRequestDto {

    @NotBlank
    private String title;

    private User user;

    @NotBlank
    private String artist;

    @NotBlank
    private String genre;

    @NotBlank
    private String content;

    private MultipartFile imageFile;

    @NotBlank
    private String videoUrl;
}
