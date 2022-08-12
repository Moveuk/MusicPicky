package com.team3.musicpicky.repository;

import com.team3.musicpicky.domain.RefreshToken;
import com.team3.musicpicky.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByUser(User user);
}
