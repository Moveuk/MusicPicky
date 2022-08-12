package com.team3.musicpicky.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_userId")
    private User user;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = false)
    private Genre genre;

    @Column(nullable = false)
    @Lob
    private String content;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String videoUrl;

    @Getter
    @AllArgsConstructor
    public enum Genre {
        HIPHOP("HipHop"),
        BALLAD("Ballad"),
        ROCK("Rock"),
        DANCE("Dance"),
        ETC("Etc"),
        ;

        private String genre;
    }
}
