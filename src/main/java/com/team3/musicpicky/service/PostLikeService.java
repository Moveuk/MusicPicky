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
import com.team3.musicpicky.repository.PostRepository;
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
    private final PostRepository postRepository;

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

        Post post = isPresentPost(postId);
        if (null == post) {
            return ResponseDto.fail(ErrorCode.POST_NOT_FOUND);
        }


        Optional<PostLike> postLikeChk = postLikeRepository.findByPostAndUser(post, user);

        if (requestDto.getUid() == 0) {

            if (postLikeChk.isEmpty()) {
                PostLike postLike = PostLike.builder()
                        .post(post)
                        .user(user)
                        .isLike(true)
                        .build();

                postLikeRepository.save(postLike);


            } else {
                postLikeChk.get().setIsLike(true);
            }

            post.setLikeCnt(chkLike(post));

            return ResponseDto.success(
                    PostLikeResponseDto.builder()
                            .isLike(true)
                            .build()
            );
        }

        postLikeChk.ifPresent(postLike -> postLike.setIsLike(false));
        post.setLikeCnt(chkLike(post));
        return ResponseDto.success(
                PostLikeResponseDto.builder()
                        .isLike(false)
                        .build()
        );
    }


    public Long chkLike(Post post) {
        List<PostLike> postLikeList = postLikeRepository.findAllByPostAndIsLike(post, true);

        return (long) postLikeList.size();
    }

    @Transactional
    public User validateUser(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return null;
        }
        return tokenProvider.getUserFromAuthentication();
    }

    @Transactional(readOnly = true)
    public Post isPresentPost(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        return optionalPost.orElse(null);

    }
}
