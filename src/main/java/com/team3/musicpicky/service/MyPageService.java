package com.team3.musicpicky.service;

import com.team3.musicpicky.controller.response.ResponseDto;
import com.team3.musicpicky.domain.Post;
import com.team3.musicpicky.domain.PostLike;
import com.team3.musicpicky.domain.User;
import com.team3.musicpicky.exception.InvalidValueException;
import com.team3.musicpicky.global.error.ErrorCode;
import com.team3.musicpicky.jwt.TokenProvider;
import com.team3.musicpicky.repository.PostLikeRepository;
import com.team3.musicpicky.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final TokenProvider tokenProvider;
    private final PostRepository postRepository;
//    private final CommentRepository commentRepository;
    private final PostLikeRepository postLikeRepository;

    public ResponseDto<?> getMyPage(HttpServletRequest request) {
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

        List<Post> PostList = postRepository.findAllByUser(user).orElseThrow(
                ()-> new InvalidValueException(ErrorCode.POST_NOT_FOUND));

//        List<Comment> CommentList = commentRepository.findAllByUsername(username);

        List<PostLike> postLikeList = postLikeRepository.findAllByUserAndIsHeart(user, true)
                .orElseThrow(()-> new InvalidValueException(ErrorCode.POST_NOT_FOUND) );

        System.out.println(postLikeList.get(0).getPost());

        List<Post> LikedPostList = new ArrayList<>();
        for(PostLike postLike : postLikeList) {
            Post post = postLike.getPost();

            LikedPostList.add(post);
        }

        HashMap<String,List<?>> MyPageDto = new HashMap<>();

        MyPageDto.put("PostList", PostList);
//        MyPageDto.put("CommentList", CommentList);
        MyPageDto.put("LikedPostList", LikedPostList);

        return ResponseDto.success(MyPageDto);
    }

    @Transactional
    public User validateUser(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return null;
        }
        return tokenProvider.getUserFromAuthentication();
    }

}