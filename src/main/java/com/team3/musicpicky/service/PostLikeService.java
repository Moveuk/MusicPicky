package com.team3.musicpicky.service;

import com.team3.musicpicky.controller.request.PostLikeRequestDto;
import com.team3.musicpicky.controller.response.PostLikeResponseDto;
import com.team3.musicpicky.controller.response.ResponseDto;
import com.team3.musicpicky.domain.Post;
import com.team3.musicpicky.domain.PostLike;
import com.team3.musicpicky.domain.User;
import com.team3.musicpicky.global.error.ErrorCode;
import com.team3.musicpicky.jwt.TokenProvider;
import com.team3.musicpicky.repository.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final TokenProvider tokenProvider;
    private final PostService postService;

    @Transactional
    public ResponseDto<?> like(Long postId, PostLikeRequestDto requestDto,
                               HttpServletRequest request) {
        if(request.getHeader("Refresh-Token")==null) {
            return ResponseDto.fail(ErrorCode.LOGIN_REQUIRED);
        }
        if(request.getHeader("Authorization") == null) {
            return ResponseDto.fail(ErrorCode.LOGIN_REQUIRED);
        }

        User user = validateUser(request);
        if (null == user) {
            return ResponseDto.fail(ErrorCode.INVALID_TOKEN);
        }

        Post post = postService.isPresentPost(requestDto.getPostId());
        if (null == post) {
            return ResponseDto.fail(ErrorCode.POST_NOT_FOUND);
        }

        Optional<PostLike> postLikeChk = postLikeRepository.findByPostIdAndUserId(postId, user.getUserId());

        if (requestDto.getUid() == 0) {

            if (postLikeChk.isPresent()) {
                postLikeChk.get().setIsChecked(true);
            }

            PostLike postLike = PostLike.builder()
                    .post(post)
                    .user(user)
                    .IsChecked(true)
                    .build();

            postLikeRepository.save(postLike);

            post.setCountLike(chkLike(postId));

            return ResponseDto.success(
                    PostLikeResponseDto.builder()
                            .isChecked(postLikeChk.get().getIsChecked())
                            .build()
            );

        }

        postLikeChk.get().setIsChecked(false);
        return ResponseDto.success(
                PostLikeResponseDto.builder()
                        .isChecked(postLikeChk.get().getIsChecked())
                        .build()
        );
    }


    public Long chkLike(Long postId) {
        List<PostLike> postLikeList = postLikeRepository.findAllByIdAndIsChecked(postId, true);

        return (long) postLikeList.size();
    }

    @Transactional
    public User validateUser(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return null;
        }
        return tokenProvider.getMemberFromAuthentication();
    }
}
