package com.example.secretforest_project.Service.Util;

import com.example.secretforest_project.Exception.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MatchesPassword {

    private final PasswordEncoder encoder;

    public void matchesPassword(String matchespassword,String password){
        if (!encoder.matches(matchespassword, password)) {
            // matches(비교할 비밀번호, db에 저장되어 있는 비밀번호)
            throw new ConflictException();
        }
    }

}
