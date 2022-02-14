package com.example.secretforest_project.Controller.AdminController;

import com.example.secretforest_project.Dto.JwtToken;
import com.example.secretforest_project.Dto.Request.AdminReqest;
import com.example.secretforest_project.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;

@RequestMapping("/admin/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    // 로그인
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtToken login(@Valid @RequestBody AdminReqest reqest) {
        return authService.login(reqest);
    }

    // 토큰 재발급
    @PutMapping("/reissue")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtToken reissue(@Valid @RequestBody JwtToken jwtToken) {
        return authService.tokenRefresh(jwtToken);
    }

}
