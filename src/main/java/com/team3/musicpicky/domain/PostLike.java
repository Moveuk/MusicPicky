package com.team3.musicpicky.domain;

import jdk.jfr.BooleanFlag;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Builder @Setter
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false)
    private Boolean isLike;
}

