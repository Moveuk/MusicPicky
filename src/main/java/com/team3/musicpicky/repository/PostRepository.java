package com.team3.musicpicky.repository;

import com.team3.musicpicky.domain.Post;
import com.team3.musicpicky.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<List<Post>> findAllByGenreOrderByPostIdDesc(Post.Genre genre);
    Optional<List<Post>> findAllByUser(User user);
}
