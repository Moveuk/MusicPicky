package com.team3.musicpicky.service;

import com.team3.musicpicky.controller.response.ResponseDto;
import com.team3.musicpicky.domain.Post;
import com.team3.musicpicky.domain.User;
import com.team3.musicpicky.global.error.ErrorCode;
import com.team3.musicpicky.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
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
        String username = user.getUsername();

        List<Post> PostList = new ArrayList<>();
        List<Comment> CommentList = new ArrayList<>();
        List<Post> LikedPostList = new ArrayList<>();

        PostList = postRepository.findAllByUsername(username);
        CommentList = commentRepository.findAllByUsername(username);

        List<Long> postLikeList = postLikeRepository.findAllByUser(user);

        for(PostLike postLike : postLikeList) {
            Long postId = postLike.getPost();

            Post post = postRepository.findById(postId).orElseThrow(
                    ()-> new IllegalArgumentException("게시글이 없습니다.")
            );

            LikedPostList.add(post);
        }

        HashMap<String,List<?>> MyPageDto = new HashMap<>();

        MyPageDto.put("PostList", PostList);
        MyPageDto.put("CommentList", CommentList);
        MyPageDto.put("LikedPostList", LikedPostList);

        return ResponseDto.success(MyPageDto);
    }

}