package com.example.forestproject.Controller;

import com.example.forestproject.Dto.Auth.UserIdDto;
import com.example.forestproject.Dto.Auth.UserJoinDto;
import com.example.forestproject.Dto.Auth.UserLoginDto;
import com.example.forestproject.Dto.Auth.UserPwdDto;
import com.example.forestproject.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class AuthController { // 로그인,회원가입

    private final AuthService service;

    // 회원가입
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED) // 201 성공적으로 자원을 생성함
    public void Join(@Valid @RequestBody UserJoinDto userJoinDto) {
        service.join(userJoinDto);
    }

    // 로그인
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public void Login(@Valid @RequestBody UserLoginDto userLoginDto) {
        service.login(userLoginDto);
    }

    // 아이디 중복확인
    @GetMapping("/compare/{userid}")
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public void ExistsUserid(@PathVariable String userid) {
        service.existsuserid(userid);
    }

    // 닉네임 중복확인
    @GetMapping("/compare/{name}")
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public void ExistsName(@PathVariable String name) {
        service.existsname(name);
    }

    //  이메일 중복확인
    @GetMapping("/compare/{email}")
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public void ExistsEmail(@PathVariable String email) {
        service.existsemail(email);
    }

    /*
    // 닉네임 변경
    @PutMapping("/{beforename}/{aftername}")
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public void ChangeName(@PathVariable("beforename")String beforename,
                           @PathVariable("aftername") String aftername) {
        service.changename(beforename, aftername);
    }*/

    // 이메일로 아이디 찾기
    @GetMapping("/find/{email}")
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public UserIdDto FindUserid(@PathVariable String email) {
        return service.finduserid(email);
    }

    // 아이디로 비밀번호 찾기
    @GetMapping("/find/{userid}")
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public UserPwdDto FindPwd(@PathVariable String userid) {
        return service.findpwd(userid);
    }


}
