package com.team3.musicpicky.controller;

import com.team3.musicpicky.controller.request.CommentRequestDto;
import com.team3.musicpicky.controller.response.ResponseDto;
import com.team3.musicpicky.domain.UserDetailsImpl;
import com.team3.musicpicky.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping({"/api/posts/{postId}/comments"})
    public ResponseEntity<ResponseDto> createComment(@PathVariable Long postId,
                                                     @RequestBody CommentRequestDto commentRequestDto,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {

        commentRequestDto.setUser(userDetails.getUser());

        return new ResponseEntity<>(
                ResponseDto.success(commentService.createComment(commentRequestDto, postId)),
                HttpStatus.valueOf(HttpStatus.OK.value()));
    }
}
