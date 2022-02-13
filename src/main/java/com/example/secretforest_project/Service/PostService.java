package com.example.secretforest_project.Service;

import com.example.secretforest_project.Dto.Request.PostRequest;
import com.example.secretforest_project.Dto.Request.PostUpdateRequest;
import com.example.secretforest_project.Dto.Request.PasswordRequest;
import com.example.secretforest_project.Entity.Post.Post;
import com.example.secretforest_project.Entity.Post.PostRepository;
import com.example.secretforest_project.Exception.ConflictException;
import com.example.secretforest_project.Exception.NotFoundException;
import com.example.secretforest_project.Service.Util.MatchesPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PasswordEncoder encoder;

    private final PostRepository postRepository;

    private final MatchesPassword matchesPassword;

    // 게시글 저장
    public void savepost(PostRequest postRequest) {

        Post postEntity = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .writer(postRequest.getWriter())
                .password(encoder.encode(postRequest.getPassword()))
                .censorship(1)
                .build();

        postRepository.save(postEntity);

    }

    // 게시글 수정
    public void updatepost(Long post_id, PostUpdateRequest postUpdateRequest) {

        Post postEntity = postRepository.findById(post_id)
                .orElseThrow(NotFoundException::new);

        matchesPassword.matchesPassword(postUpdateRequest.getPassword(), postEntity.getPassword());

        Post build = Post.builder()
                .id(postEntity.getId())
                .title(postUpdateRequest.getTitle())
                .content(postUpdateRequest.getContent())
                .writer(postEntity.getWriter())
                .password(postEntity.getPassword())
                .censorship(2)
                .build();

        postRepository.save(build);

    }

    // 게시글 삭제
    public void delpost(Long post_id, PasswordRequest pwdRequest) {

        Post postEntity = postRepository.findById(post_id)
                .orElseThrow(ConflictException::new);

        matchesPassword.matchesPassword(pwdRequest.getPassword(), postEntity.getPassword());

        postRepository.delete(postEntity);

    }

    // 게시글 신고
    public void reportpost(Long post_id) {

        Post postEntity = postRepository.findById(post_id)
                .orElseThrow(NotFoundException::new);

        Post build = Post.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .writer(postEntity.getWriter())
                .password(postEntity.getPassword())
                .censorship(3)
                .build();

        postRepository.save(build);

    }

}
