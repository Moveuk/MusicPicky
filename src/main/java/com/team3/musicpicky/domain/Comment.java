package com.team3.musicpicky.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team3.musicpicky.controller.request.CommentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@NoArgsConstructor
@Getter
@Entity
@Builder
@AllArgsConstructor
public class Comment extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long commentId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_postId")
    private Post post;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_userId")
    private User user;

    @Column(nullable = false)
    private String comment;

    public void updateComment(CommentRequestDto commentRequestDto) {this.comment = commentRequestDto.getComment(); }
}
