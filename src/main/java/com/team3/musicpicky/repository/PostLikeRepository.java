package com.team3.musicpicky.repository;

import com.team3.musicpicky.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Optional<PostLike> findByPostIdAndUserId(Long postId, Long userId);
    List<PostLike> findAllByPostId(Long postId);
    List<PostLike> findAllByIdAndIsChecked(Long postId, Boolean IsChecked);
}
