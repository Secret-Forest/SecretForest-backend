package com.example.secretforest_project.Service;

import com.example.secretforest_project.Dto.Request.PostRequest;
import com.example.secretforest_project.Dto.Request.PostUpdateRequest;
import com.example.secretforest_project.Dto.Request.PasswordRequest;
import com.example.secretforest_project.Entity.Post.Post;
import com.example.secretforest_project.Entity.Post.PostRepository;
import com.example.secretforest_project.Exception.NotFoundException;
import com.example.secretforest_project.Service.Util.MatchesPassword;
import lombok.RequiredArgsConstructor;
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

        postRepository.save(
                Post.builder()
                        .title(postRequest.getTitle())
                        .content(postRequest.getContent())
                        .writer(postRequest.getWriter())
                        .password(encoder.encode(postRequest.getPassword()))
                        .censorship(1)
                        .build()
        );

    }

    public void match(Long postId, PasswordRequest passwordRequest) {

        Post postEntity = postRepository.findById(postId)
                .orElseThrow(NotFoundException::new);

        matchesPassword.matchesPassword(passwordRequest.getPassword(), postEntity.getPassword());
    }

    // 게시글 수정
    public void updatepost(Long postId, PostUpdateRequest postUpdateRequest) {

        Post postEntity = postRepository.findById(postId)
                .orElseThrow(NotFoundException::new);

        postRepository.save(
                Post.builder()
                        .id(postEntity.getId())
                        .title(postUpdateRequest.getTitle())
                        .content(postUpdateRequest.getContent())
                        .writer(postEntity.getWriter())
                        .password(postEntity.getPassword())
                        .censorship(2)
                        .build()
        );

    }

    // 게시글 삭제
    public void delpost(Long postId, PasswordRequest pwdRequest) {

        Post postEntity = postRepository.findById(postId)
                .orElseThrow(NotFoundException::new);

        matchesPassword.matchesPassword(pwdRequest.getPassword(), postEntity.getPassword());

        postRepository.delete(postEntity);

    }

    // 게시글 신고
    public void reportpost(Long postId) {

        Post postEntity = postRepository.findById(postId)
                .orElseThrow(NotFoundException::new);

        postRepository.save(
                Post.builder()
                        .id(postEntity.getId())
                        .title(postEntity.getTitle())
                        .content(postEntity.getContent())
                        .writer(postEntity.getWriter())
                        .password(postEntity.getPassword())
                        .censorship(3)
                        .build()
        );

    }

}
