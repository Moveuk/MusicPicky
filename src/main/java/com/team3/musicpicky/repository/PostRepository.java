package com.team3.musicpicky.repository;

import com.team3.musicpicky.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
