package com.team3.musicpicky.repository;

import com.team3.musicpicky.domain.Comment;
import com.team3.musicpicky.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<List<Comment>> findAllByUser(User user);

}
