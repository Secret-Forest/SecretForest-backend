package com.example.secretforest_project.Service;

import com.example.secretforest_project.Dto.Response.CnsrsPostListResponse;
import com.example.secretforest_project.Dto.Response.CnsrsPostResponse;
import com.example.secretforest_project.Dto.Response.PostListResponse;
import com.example.secretforest_project.Dto.Response.PostViewResponse;
import com.example.secretforest_project.Entity.Post.Post;
import com.example.secretforest_project.Entity.Post.PostRepository;
import com.example.secretforest_project.Exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminPostService {

    private final PostRepository postRepository;

    // 게시글 검열 통과
    public void postok(Long post_id) {

        Post postEntity = postRepository.findById(post_id)
                .orElseThrow(NotFoundException::new);

        Post build = Post.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .writer(postEntity.getWriter())
                .pwd(postEntity.getPwd())
                .cnsrs(0)
                .build();

        postRepository.save(build);

    }

    // 게시글 검열 삭제
    public void postno(Long post_id) {

        Post postEntity = postRepository.findById(post_id)
                .orElseThrow(NotFoundException::new);

        postRepository.delete(postEntity);

    }

    // 게시글 검열 목록
    public CnsrsPostListResponse showpost(Pageable page) {

        Page<Post> postEntityList = postRepository.findAllByCnsrsBetweenOrderByIdDesc(1,2, page);
        List<CnsrsPostResponse> cnsrsPostResponses = new ArrayList<>();

        for (Post postEntity : postEntityList) {
            cnsrsPostResponses.add(
                    CnsrsPostResponse.builder()
                            .id(postEntity.getId())
                            .cnsrs(postEntity.getCnsrs())
                            .title(postEntity.getTitle())
                            .writer(postEntity.getWriter())
                            .build()
            );
        }

        return CnsrsPostListResponse.builder()
                .cnsrsPostResponses(cnsrsPostResponses)
                .build();

    }

    // 게시글 신고 목록
    public PostListResponse showreportpost(Pageable page) {

        Page<Post> postEntityList = postRepository.findAllByCnsrsOrderByIdDesc(3, page);
        List<PostViewResponse> postViewDtos = new ArrayList<>();

        for (Post postEntity : postEntityList) {
                postViewDtos.add(
                        PostViewResponse.builder()
                                .id(postEntity.getId())
                                .title(postEntity.getTitle())
                                .writer(postEntity.getWriter())
                                .build()
                );
        }

        return PostListResponse.builder()
                .postViewDtoList(postViewDtos)
                .build();
    }

}
