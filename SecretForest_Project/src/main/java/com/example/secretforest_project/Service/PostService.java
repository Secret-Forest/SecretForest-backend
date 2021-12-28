package com.example.secretforest_project.Service;

import com.example.secretforest_project.Dto.Request.PostRequest;
import com.example.secretforest_project.Dto.Request.PostUpdateRequest;
import com.example.secretforest_project.Entity.PostEntity;
import com.example.secretforest_project.Entity.PostRepository;
import com.example.secretforest_project.Exception.ConflictException;
import com.example.secretforest_project.Exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final PostRepository postRepository;

    // 게시글 저장
    public void savepost(PostRequest postRequest) {

        PostEntity postEntity = PostEntity.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .writer(postRequest.getWriter())
                .pwd(passwordEncoder.encode(postRequest.getPwd()))
                .cnsrs(1)
                .build();

        postRepository.save(postEntity);

    }

    // 게시글 수정
    public void updatepost(Long post_id, PostUpdateRequest postUpdateRequest) {

        PostEntity postEntity = postRepository.findById(post_id)
                .orElseThrow(NotFoundException::new);

        if (!passwordEncoder.matches(postUpdateRequest.getPwd(), postEntity.getPwd())) {
            // matches(비교할 비밀번호, db에 저장되어 있는 비밀번호)
            throw new ConflictException();
        }

        PostEntity build = PostEntity.builder()
                .id(postEntity.getId())
                .title(postUpdateRequest.getTitle())
                .content(postUpdateRequest.getContent())
                .writer(postEntity.getWriter())
                .pwd(postEntity.getPwd())
                .cnsrs(1)
                .build();

        postRepository.save(build);

    }

    // 게시글 삭제
    public void delpost(Long post_id, String pwd) {

        PostEntity postEntity = postRepository.findById(post_id)
                .orElseThrow(ConflictException::new);

        if (!passwordEncoder.matches(pwd, postEntity.getPwd())) {
            // matches(비교할 비밀번호, db에 저장되어 있는 비밀번호)
            throw new ConflictException();
        }

        postRepository.delete(postEntity);

    }

    // 게시글 신고
    public void reportpost(Long post_id) {

        PostEntity postEntity = postRepository.findById(post_id)
                .orElseThrow(NotFoundException::new);

        PostEntity build = PostEntity.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .writer(postEntity.getWriter())
                .pwd(postEntity.getPwd())
                .cnsrs(2)
                .build();

        postRepository.save(build);

    }

}
