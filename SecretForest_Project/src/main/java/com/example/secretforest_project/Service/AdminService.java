package com.example.secretforest_project.Service;

import com.example.secretforest_project.Dto.Response.*;
import com.example.secretforest_project.Entity.CommentsEntity;
import com.example.secretforest_project.Entity.CommentsRepository;
import com.example.secretforest_project.Entity.PostEntity;
import com.example.secretforest_project.Entity.PostRepository;
import com.example.secretforest_project.Exception.NotFoundException;
import lombok.RequiredArgsConstructor;
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
                .comments_id(commentsEntity.getComments_id())
                .writer(commentsEntity.getWriter())
                .Comment(commentsEntity.getComment())
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
    public PostListResponse showpost() {

        List<PostEntity> postEntityList = postRepository.findAllBy();
        List<PostViewResponse> postViewDtos = new ArrayList<>();

        for (PostEntity postEntity : postEntityList) {
            if(postEntity.getCnsrs() == 1)
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
    public PostListResponse showreportpost() {

        List<PostEntity> postEntityList = postRepository.findAllBy();
        List<PostViewResponse> postViewDtos = new ArrayList<>();

        for (PostEntity postEntity : postEntityList) {
            if(postEntity.getCnsrs() == 2)
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
    public CommentsListResponse showreportcomments() {

        List<CommentsEntity> commentsEntityList = commentsRepository.findAllBy();
        List<CommentsViewResponse> commentsViewResponseList = new ArrayList<>();

        for (CommentsEntity commentsEntity : commentsEntityList) {
            if (commentsEntity.getCnsrs() == 2)
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
