package com.example.forestproject.Service;

import com.example.forestproject.Dto.Auth.UserIdDto;
import com.example.forestproject.Dto.Auth.UserJoinDto;
import com.example.forestproject.Dto.Auth.UserLoginDto;
import com.example.forestproject.Dto.Auth.UserPwdDto;
import com.example.forestproject.Entity.UserEntity;
import com.example.forestproject.Entity.UserRepository;
import com.example.forestproject.Exception.ConflictException;
import com.example.forestproject.Exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    // 회원가입
    public void join(UserJoinDto userjoinDto) {

        UserEntity userEntity = UserEntity.builder()
                .userid(userjoinDto.getUserid())
                .pwd(passwordEncoder.encode(userjoinDto.getPwd()))
                .email(userjoinDto.getEmail())
                .name(userjoinDto.getName())
                .build();

        userRepository.save(userEntity);

    }

    //로그인
    public void login(UserLoginDto userloginDto) {

        UserEntity userEntity = userRepository.findByUserid(userloginDto.getUserid())
                .orElseThrow(NotFoundException::new);

        if (!passwordEncoder.matches(userloginDto.getUserid(), userEntity.getPwd())) {
            throw new NotFoundException();
        }

    }

    // 아이디 중복확인
    public void existsuserid(String userid) {

        if (userRepository.existsByUserid(userid)) {
            throw new ConflictException();
        }

    }

    // 닉네임 중복확인
    public void existsname(String name) {

        if (userRepository.existsByName(name)) {
            throw new ConflictException();
        }

    }

    // 이메일 중복확인
    public void existsemail(String email) {

        if (userRepository.existsByEmail(email)) {
            throw new ConflictException();
        }

    }

    // 이메일로 아이디 찾기
    public UserIdDto finduserid(String email) {

        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(NotFoundException::new);

        UserIdDto useridDto = UserIdDto.builder()
                .userid(userEntity.getUserid())
                .build();

        return useridDto;

    }

    // 아이디로 비밀번호 찾기
    public UserPwdDto findpwd(String userid) {

        UserEntity userEntity = userRepository.findByUserid(userid)
                .orElseThrow(NotFoundException::new);

        UserPwdDto userPwdDto = UserPwdDto.builder()
                .pwd(userEntity.getPwd())
                .build();

        return userPwdDto;

    }

    // 밑에꺼 다 토큰으로 해야하는 거ㅠㅠ 로그인 되어 있는 유저를 판단할때 토큰으로 판단하기!!
    /*
    // 닉네임 변경
    public void changename(String beforename, String aftername) {

        UserEntity userEntity = userRepository.findByName(beforename)
                .orElseThrow(NotFoundException::new);

        UserEntity build = UserEntity.builder()
                .id(userEntity.getId())
                .name(aftername)
                .pwd(userEntity.getPwd())
                .userid(userEntity.getUserid())
                .email(userEntity.getEmail())
                .build();

        userRepository.save(build);

    }

    // 비밀번호 변경
    public void changepwd(String before, String aftername) {

        UserEntity userEntity = userRepository.findByName(beforename)
                .orElseThrow(NotFoundException::new);

        UserEntity build = UserEntity.builder()
                .id(userEntity.getId())
                .name(aftername)
                .pwd(userEntity.getPwd())
                .userid(userEntity.getUserid())
                .email(userEntity.getEmail())
                .build();

        userRepository.save(build);

    }
     */

}
