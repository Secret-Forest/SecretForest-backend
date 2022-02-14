package com.example.secretforest_project.Service;

import com.example.secretforest_project.Dto.Response.CommentListResponse;
import com.example.secretforest_project.Dto.Response.CommentViewResponse;
import com.example.secretforest_project.Entity.Comments.Comment;
import com.example.secretforest_project.Entity.Comments.CommentRepository;
import com.example.secretforest_project.Exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminCommentsService {

    private final CommentRepository commentsRepository;

    // 댓글 검열 통과
    public void commentsok(Long commentId) {

        Comment commentsEntity = commentsRepository.findById(commentId)
                .orElseThrow(NotFoundException::new);

        Comment build = Comment.builder()
                .id(commentsEntity.getId())
                .post(commentsEntity.getPost())
                .writer(commentsEntity.getWriter())
                .comment(commentsEntity.getComment())
                .password(commentsEntity.getPassword())
                .censorship(0)
                .build();

        commentsRepository.save(build);

    }

    // 댓글 검열 삭제
    public void commentsno(Long commentId) {

        Comment commentsEntity = commentsRepository.findById(commentId)
                .orElseThrow(NotFoundException::new);

        commentsRepository.delete(commentsEntity);

    }

    // 댓글 신고 목록
    public CommentListResponse showreportcomments(Pageable page) {

        Page<Comment> commentsEntityList = commentsRepository.findAllByCensorshipOrderByIdDesc(3, page);
        List<CommentViewResponse> commentsViewResponseList = new ArrayList<>();

        for (Comment commentsEntity : commentsEntityList) {
                commentsViewResponseList.add(
                        CommentViewResponse.builder()
                                .id(commentsEntity.getId())
                                .comment(commentsEntity.getComment())
                                .writer(commentsEntity.getWriter())
                                .build()
                );
        }

        return CommentListResponse.builder()
                .commentsViewResponseList(commentsViewResponseList)
                .build();
    }

}
