package com.team3.musicpicky.repository;

import com.team3.musicpicky.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
