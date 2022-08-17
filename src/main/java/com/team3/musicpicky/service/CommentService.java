package com.team3.musicpicky.service;

import com.team3.musicpicky.controller.request.CommentRequestDto;
import com.team3.musicpicky.domain.Comment;
import com.team3.musicpicky.domain.Post;
import com.team3.musicpicky.exception.InvalidValueException;
import com.team3.musicpicky.global.error.ErrorCode;
import com.team3.musicpicky.repository.CommentRepository;
import com.team3.musicpicky.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public Comment createComment(CommentRequestDto commentRequestDto, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new InvalidValueException(ErrorCode.POST_NOT_FOUND));

        return commentRepository.save(
                Comment.builder()
                        .user(commentRequestDto.getUser())
                        .comment(commentRequestDto.getComment())
                        .build());
    }

    public Comment updateComment(CommentRequestDto commentRequestDto, Long postId, Long commentId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new InvalidValueException(ErrorCode.POST_NOT_FOUND));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new InvalidValueException(ErrorCode.COMMENT_NOT_FOUND));

        Long writerId = comment.getUser().getUserId();

        if (Objects.equals(writerId, userId)) {
            comment.updateComment(commentRequestDto);
        } else {
            throw new InvalidValueException(ErrorCode.COMMENT_UNAUTHORIZED);
        }
        return comment;
    }

    public String deleteComment(Long postId, Long commentId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new InvalidValueException(ErrorCode.POST_NOT_FOUND));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new InvalidValueException(ErrorCode.COMMENT_NOT_FOUND));

        Long writerId = comment.getUser().getUserId();

        if (Objects.equals(writerId, userId)) {
            commentRepository.deleteById(commentId);
        } else {
            throw new InvalidValueException(ErrorCode.COMMENT_UNAUTHORIZED);
        }
        return "댓글 삭제";
    }
}