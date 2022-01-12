package com.example.secretforest_project.Service;

import com.example.secretforest_project.Dto.Response.*;
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
public class AdminService {

    private final PostRepository postRepository;
    private final CommentsRepository commentsRepository;

    // 게시글 검열 통과
    public void postok(Long post_id) {

        PostEntity postEntity = postRepository.findById(post_id)
                .orElseThrow(NotFoundException::new);

        PostEntity build = PostEntity.builder()
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

        PostEntity postEntity = postRepository.findById(post_id)
                .orElseThrow(NotFoundException::new);

        postRepository.delete(postEntity);

    }

    // 댓글 검열 통과
    public void commentsok(Long comments_id) {

        CommentsEntity commentsEntity = commentsRepository.findById(comments_id)
                .orElseThrow(NotFoundException::new);

        CommentsEntity build = CommentsEntity.builder()
                .id(commentsEntity.getId())
                .post(commentsEntity.getPost())
                .writer(commentsEntity.getWriter())
                .comment(commentsEntity.getComment())
                .pwd(commentsEntity.getPwd())
                .cnsrs(0)
                .build();

        commentsRepository.save(build);

    }

    // 댓글 검열 삭제
    public void commentsno(Long comments_id) {

        CommentsEntity commentsEntity = commentsRepository.findById(comments_id)
                .orElseThrow(NotFoundException::new);

        commentsRepository.delete(commentsEntity);

    }

    // 게시글 검열 목록
    public PostListResponse showpost(Pageable page) {

        Page<PostEntity> postEntityList = postRepository.findAllByCnsrsOrderByIdDesc(1, page);
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

    // 게시글 신고 목록
    public PostListResponse showreportpost(Pageable page) {

        Page<PostEntity> postEntityList = postRepository.findAllByCnsrsOrderByIdDesc(2, page);
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

    // 댓글 신고 목록
    public CommentsListResponse showreportcomments(Pageable page) {

        Page<CommentsEntity> commentsEntityList = commentsRepository.findAllByCnsrsOrderByIdDesc(2, page);
        List<CommentsViewResponse> commentsViewResponseList = new ArrayList<>();

        for (CommentsEntity commentsEntity : commentsEntityList) {
                commentsViewResponseList.add(
                        CommentsViewResponse.builder()
                                .id(commentsEntity.getId())
                                .comment(commentsEntity.getComment())
                                .writer(commentsEntity.getWriter())
                                .build()
                );
        }

        return CommentsListResponse.builder()
                .commentsViewResponseList(commentsViewResponseList)
                .build();
    }

}
