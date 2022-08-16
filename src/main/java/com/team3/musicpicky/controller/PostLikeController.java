package com.team3.musicpicky.controller;

import com.team3.musicpicky.controller.request.PostLikeRequestDto;
import com.team3.musicpicky.controller.response.ResponseDto;
import com.team3.musicpicky.domain.UserDetailsImpl;
import com.team3.musicpicky.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping("/api/likes/{postId}")
    public ResponseDto<?> like(@PathVariable Long postId, @RequestBody PostLikeRequestDto requestDto,
                               HttpServletRequest request) {

        return postLikeService.like(postId, requestDto, request);
    }
}
