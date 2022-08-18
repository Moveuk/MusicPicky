package com.team3.musicpicky.service;

import com.team3.musicpicky.controller.Dto.CommentDto;
import com.team3.musicpicky.controller.Dto.MyPageCommentDto;
import com.team3.musicpicky.controller.Dto.PostDto;
import com.team3.musicpicky.controller.Dto.UserDto;
import com.team3.musicpicky.controller.response.ResponseDto;
import com.team3.musicpicky.domain.*;
import com.team3.musicpicky.exception.InvalidValueException;
import com.team3.musicpicky.global.error.ErrorCode;
import com.team3.musicpicky.jwt.TokenProvider;
import com.team3.musicpicky.repository.CommentRepository;
import com.team3.musicpicky.repository.PostLikeRepository;
import com.team3.musicpicky.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final TokenProvider tokenProvider;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostLikeRepository postLikeRepository;

    @Transactional
    public ResponseDto<?> getMyPage(HttpServletRequest request) {
        if (request.getHeader("Refresh-Token") == null) {
            return ResponseDto.fail(ErrorCode.LOGIN_REQUIRED);
        }
        if (request.getHeader("Authorization") == null) {
            return ResponseDto.fail(ErrorCode.LOGIN_REQUIRED);
        }

        User user = validateUser(request);
        if (null == user) {
            return ResponseDto.fail(ErrorCode.INVALID_TOKEN);
        }

        List<PostDto> PostList = getPostDto(user);
        List<MyPageCommentDto> CommentList = getMyPageCommentDto(user);
        List<PostDto> LikedPostList = getLikedPostDto(user);

        HashMap<String, List<?>> myPage = new HashMap<>();
        myPage.put("PostList", PostList);
        myPage.put("CommentList", CommentList);
        myPage.put("LikedPostList", LikedPostList);
        System.out.println(myPage);
        return ResponseDto.success(myPage);

    }
//        //내가 작성한 게시글 찾기
//        List<Post> PostList = postRepository.findAllByUser(user).orElseThrow(
//                ()-> new InvalidValueException(ErrorCode.POST_NOT_FOUND));
//
//        //내가 작성한 댓글 찾기
//        List<Comment> CommentList = commentRepository.findAllByUser(user).orElseThrow(
//                ()-> new InvalidValueException(ErrorCode.POST_NOT_FOUND));
//
//        //내가 좋아요한 게시글 찾기
//        List<PostLike> postLikeList = postLikeRepository.findAllByUserAndIsHeart(user, true)
//                .orElseThrow(()-> new InvalidValueException(ErrorCode.POST_NOT_FOUND) );
//
//        List<Post> LikedPostList = new ArrayList<>();
//        for(PostLike postLike : postLikeList) {
//            Post post = postLike.getPost();
//
//            LikedPostList.add(post);
//        }

    @Transactional
    public User validateUser(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return null;
        }
        return tokenProvider.getUserFromAuthentication();
    }

    //내가 작성한 게시물 dto로 받아오기
    @Transactional
    public List<PostDto> getPostDto(User user) {
        UserDto userDto = new UserDto(user);
        List<Post> PostList = postRepository.findAllByUser(user).orElseThrow(
                ()-> new InvalidValueException(ErrorCode.POST_NOT_FOUND));

        return PostList.stream()
                .map(post -> new PostDto(post, userDto))
                .collect(Collectors.toList());
    }

    //내가 작성한 댓글 dto로 받아오기
    @Transactional
    public List<MyPageCommentDto> getMyPageCommentDto(User user) {
        UserDto userDto = new UserDto(user);
        List<Comment> CommentList = commentRepository.findAllByUser(user).orElseThrow(
                ()-> new InvalidValueException(ErrorCode.POST_NOT_FOUND));

        return  CommentList.stream()
                .map(comment -> new MyPageCommentDto(comment, userDto))
                .collect(Collectors.toList());
    }

    //내가 좋아요한 게시글 dto로 받아오기
    @Transactional
    public List<PostDto> getLikedPostDto(User user) {
        UserDto userDto = new UserDto(user);
        List<PostLike> postLikeList = postLikeRepository.findAllByUserAndIsHeart(user, true)
                .orElseThrow(()-> new InvalidValueException(ErrorCode.POST_NOT_FOUND) );

        List<Post> LikedPostList = new ArrayList<>();
        for(PostLike postLike : postLikeList) {
            Post post = postLike.getPost();

            LikedPostList.add(post);
        }

        return LikedPostList.stream()
                .map(post -> new PostDto(post, userDto))
                .collect(Collectors.toList());
    }


}