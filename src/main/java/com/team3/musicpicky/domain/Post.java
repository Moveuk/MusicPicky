package com.team3.musicpicky.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @Column(nullable = false)
    private Long likeCnt;

    @JsonIgnore
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> commentList;

    @Getter
    @AllArgsConstructor
    public enum Genre {
        HIPHOP("HipHop"),
        BALLAD("Ballad"),
        ROCK("Rock"),
        DANCE("Dance"),
        ETC("Etc");

        private String genre;
    }

    public void update(String title, User user, String artist, Genre genre, String content, String imageUrl, String videoUrl) {
        this.title = title;
        this.user = user;
        this.artist = artist;
        this.genre = genre;
        this.content = content;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
    }

    public void count(Long likeCnt) {
        this.likeCnt = likeCnt;
    }
}
