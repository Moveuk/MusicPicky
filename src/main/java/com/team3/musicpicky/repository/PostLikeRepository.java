package com.team3.musicpicky.repository;


import com.team3.musicpicky.domain.Post;
import com.team3.musicpicky.domain.PostLike;
import com.team3.musicpicky.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository extends CrudRepository<PostLike, Long> {

    Optional<PostLike> findByPostAndUser(Post post, User user);
    Long countAllByPostAndIsHeart(Post post, Boolean isLike);

}
