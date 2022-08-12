package com.team3.musicpicky.controller;

import com.team3.musicpicky.controller.request.LoginRequestDto;
import com.team3.musicpicky.controller.request.UserRequestDto;
import com.team3.musicpicky.controller.response.ResponseDto;
import com.team3.musicpicky.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @RequestMapping(value = "/api/users/signup", method = RequestMethod.POST)
    public ResponseDto<?> signup(@RequestBody @Valid UserRequestDto requestDto) {
        return userService.createMember(requestDto);
    }

    @RequestMapping(value = "/api/users/login", method = RequestMethod.POST)
    public ResponseDto<?> login(@RequestBody @Valid LoginRequestDto requestDto,
                                HttpServletResponse response
    ) {
        return userService.login(requestDto, response);
    }

//  @RequestMapping(value = "/api/auth/member/reissue", method = RequestMethod.POST)
//  public ResponseDto<?> reissue(HttpServletRequest request, HttpServletResponse response) {
//    return memberService.reissue(request, response);
//  }

    @RequestMapping(value = "/api/auth/users/logout", method = RequestMethod.POST)
    public ResponseDto<?> logout(HttpServletRequest request) {
        return userService.logout(request);
    }
}
