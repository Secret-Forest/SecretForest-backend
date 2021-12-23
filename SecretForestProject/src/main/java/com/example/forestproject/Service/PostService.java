package com.example.forestproject.Service;

import com.example.forestproject.Dto.Noticeboard.PostDto;
import com.example.forestproject.Entity.PostEntity;
import com.example.forestproject.Entity.PostRepository;
import com.example.forestproject.Exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    // 게시글 저장
    public void savepost(PostDto postDto) {

        PostEntity postEntity = PostEntity.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .writer(postDto.getWriter())
                .build();

        postRepository.save(postEntity);

    }

    // 게시글 수정
    public void updatepost(Long postid, PostDto postDto) {

        PostEntity postEntity = postRepository.findById(postid)
                .orElseThrow(NotFoundException::new);

        PostEntity build = PostEntity.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .writer(postEntity.getWriter())
                .build();

        postRepository.save(build);

    }

    // 게시글 삭제
    public void delpost(Long postid, PostDto postDto) {

        PostEntity postEntity = postRepository.findById(postid)
                .orElseThrow(NotFoundException::new);

        postRepository.delete(postEntity);

    }

}
