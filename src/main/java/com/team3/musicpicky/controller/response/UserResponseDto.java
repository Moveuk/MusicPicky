package com.team3.musicpicky.controller.response;

import com.team3.musicpicky.controller.request.TokenDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String username;
    private TokenDto token;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
