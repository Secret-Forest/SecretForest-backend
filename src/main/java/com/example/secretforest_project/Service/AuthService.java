package com.example.secretforest_project.Service;

import com.example.secretforest_project.Dto.JwtToken;
import com.example.secretforest_project.Dto.Request.AdminReqest;
import com.example.secretforest_project.Dto.Request.PasswordRequest;
import com.example.secretforest_project.Entity.Admin.Admin;
import com.example.secretforest_project.Entity.Admin.AdminRepository;
import com.example.secretforest_project.Entity.RefreshToken.RefreshToken;
import com.example.secretforest_project.Entity.RefreshToken.RefreshTokenRepository;
import com.example.secretforest_project.Exception.NotFoundException;
import com.example.secretforest_project.Exception.UnauthorizedException;
import com.example.secretforest_project.Jwt.JwtTokenProvider;
import com.example.secretforest_project.Service.Util.MatchesPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final MatchesPassword matchesPassword;
    private final AdminRepository adminRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtTokenProvider jwtTokenProvider;

    // 로그인
    public JwtToken login (AdminReqest adminReqest) {

        Admin admin = adminRepository.findByAminId(adminReqest.getAdminId())
                .orElseThrow(NotFoundException::new);

        matchesPassword.matchesPassword(adminReqest.getPassword(), admin.getPassword());

        // 토큰 발행
        return JwtToken.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(admin.getAminId()))
                .refreshToken(jwtTokenProvider.getRefreshToken(admin.getAminId()))
                .build();

    }

    // 토큰 재발급
    @Transactional
    public JwtToken tokenRefresh(JwtToken jwtToken) {

        Authentication authentication = jwtTokenProvider.getAuthentication(jwtToken.getAccessToken());

        RefreshToken refreshToken = refreshTokenRepository.findById(authentication.getName())
                .orElseThrow(NotFoundException::new);

        if (!refreshToken.getRefreshToken().equals(jwtToken.getRefreshToken())) {
            throw new UnauthorizedException();
        }

        JwtToken token = JwtToken.builder()
                .refreshToken(refreshToken.getRefreshToken())
                .accessToken(jwtTokenProvider.generateAccessToken(authentication.getName()))
                .build();

        RefreshToken newRefreshToken = refreshToken.update(token.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        return token;

    }



}
