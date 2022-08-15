package com.team3.musicpicky.repository;


import com.team3.musicpicky.domain.Post;
import com.team3.musicpicky.domain.PostLike;
import com.team3.musicpicky.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Optional<PostLike> findByPostAndUser(Post post, User user);
    List<PostLike> findAllByPost(Long postId);
    List<PostLike> findAllByPostAndChecked(Post post, Boolean checked);

}
