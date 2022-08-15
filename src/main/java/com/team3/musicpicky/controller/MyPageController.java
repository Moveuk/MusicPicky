package com.team3.musicpicky.controller;

import com.team3.musicpicky.controller.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("/api/mypage")
    public ResponseDto<?> myPage(HttpServletRequest request){
        return myPageService.getMyPage(request);
    }
}