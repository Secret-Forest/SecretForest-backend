package com.example.secretforest_project.Service;

import com.example.secretforest_project.Dto.Response.CommentsPostResponse;
import com.example.secretforest_project.Dto.Response.PostResponse;
import com.example.secretforest_project.Dto.Response.PostListResponse;
import com.example.secretforest_project.Dto.Response.PostViewResponse;
import com.example.secretforest_project.Entity.Comments.CommentsEntity;
import com.example.secretforest_project.Entity.Comments.CommentsRepository;
import com.example.secretforest_project.Entity.Post.PostEntity;
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
public class ShowPostService {

    private final PostRepository postRepository;
    private final CommentsRepository commentsRepository;

    // 중복코드
    private PostListResponse list(Page<PostEntity> postEntityList) {

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
    public PostListResponse findtitle(String title, Pageable page) {

        Page<PostEntity> postEntityList = postRepository.findAllByTitleContainingOrderByTitle(title, page);
        return list(postEntityList);

    }

    // 작성자로 게시글 찾기
    public PostListResponse findwriter(String writer, Pageable page) {

        Page<PostEntity> postEntityList = postRepository.findAllByWriterOrderByWriter(writer, page);
        return list(postEntityList);

    }

    // main 게시글 보기
    public PostListResponse showmain(Pageable page) {

        Page<PostEntity> postEntityList = postRepository.findAllByOrderByIdDesc(page);
        return list(postEntityList);
    }

    // 게시글 보기
    public PostResponse showpost(Long postid) {

        PostEntity postEntity = postRepository.findById(postid)
                .orElseThrow(NotFoundException::new);

        List<CommentsEntity> commentsEntityList = commentsRepository.findAllByPost(postEntity);
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
