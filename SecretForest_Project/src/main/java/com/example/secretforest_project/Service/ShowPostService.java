package com.example.secretforest_project.Service;

import com.example.secretforest_project.Dto.Request.CommentsRequest;
import com.example.secretforest_project.Dto.Response.CommentsPostResponse;
import com.example.secretforest_project.Dto.Response.PostResponse;
import com.example.secretforest_project.Dto.Response.PostListResponse;
import com.example.secretforest_project.Dto.Response.PostViewResponse;
import com.example.secretforest_project.Entity.CommentsEntity;
import com.example.secretforest_project.Entity.PostEntity;
import com.example.secretforest_project.Entity.PostRepository;
import com.example.secretforest_project.Exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ShowPostService {

    private final PostRepository postRepository;

    // 중복코드
    private PostListResponse list(List<PostEntity> postEntityList) {

        List<PostViewResponse> postViewDtos = new ArrayList<>();

        for (PostEntity postEntity : postEntityList) {
            postViewDtos.add(
                    PostViewResponse.builder()
                            .id(postEntity.getId())
                            .title(postEntity.getTitle())
                            .content(postEntity.getContent())
                            .writer(postEntity.getWriter())
                            .build()
            );
        }

        return PostListResponse.builder()
                .postViewDtoList(postViewDtos)
                .build();

    }

    // 제목으로 게시글 찾기
    public PostListResponse findtitle(String title) {

        List<PostEntity> postEntityList = postRepository.findAllByTitleContaining(title);
        return list(postEntityList);

    }

    // 작성자로 게시글 찾기
    public PostListResponse findwriter(String writer) {

        List<PostEntity> postEntityList = postRepository.findAllByWriter(writer);
        return list(postEntityList);

    }

    // main 게시글 보기
    public PostListResponse showmain() {

        List<PostEntity> postEntityList = postRepository.findAllBy();
        return list(postEntityList);
    }

    // 게시글 보기
    public PostResponse showpost(Long postid) {

        PostEntity postEntity = postRepository.findById(postid)
                .orElseThrow(NotFoundException::new);

        List<CommentsEntity> commentsEntityList = postEntity.getCommentsEntities();
        List<CommentsPostResponse> commentsPostResponses = new ArrayList<>();

        for (CommentsEntity commentsEntityeies : commentsEntityList) {
            commentsPostResponses.add(
                    CommentsPostResponse.builder()
                            .id(commentsEntityeies.getId())
                            .writer(commentsEntityeies.getWriter())
                            .comment(commentsEntityeies.getComment())
                            .build()
            );
        }


        return PostResponse.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .writer(postEntity.getWriter())
                .commentsPostResponses(commentsPostResponses)
                .build();

    }

}
