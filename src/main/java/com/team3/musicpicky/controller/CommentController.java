package com.team3.musicpicky.controller;

import com.team3.musicpicky.controller.request.CommentRequestDto;
import com.team3.musicpicky.controller.response.ResponseDto;
import com.team3.musicpicky.domain.UserDetailsImpl;
import com.team3.musicpicky.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


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

    @PutMapping({"/api/posts/{postId}/comments/{commentId}"})
    public ResponseEntity<ResponseDto> updateComment(@PathVariable(name="postId") Long postId,
                                                     @PathVariable(name="commentId") Long commentId,
                                                     @RequestBody CommentRequestDto commentRequestDto,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long userId = userDetails.getUser().getUserId();

        return new ResponseEntity<>(
                ResponseDto.success(commentService.updateComment(commentRequestDto, postId, commentId, userId)),
                HttpStatus.valueOf(HttpStatus.OK.value()));
    }

    @DeleteMapping({"/api/posts/{postId}/comments/{commentId}"})
    public ResponseEntity<ResponseDto> deleteComment(@PathVariable("postId") Long postId,
                                                     @PathVariable("commentId") Long commentId,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long userId = userDetails.getUser().getUserId();

        return new ResponseEntity<>(
                ResponseDto.success(commentService.deleteComment(postId, commentId, userId)), HttpStatus.valueOf(HttpStatus.OK.value()));
    }
}
