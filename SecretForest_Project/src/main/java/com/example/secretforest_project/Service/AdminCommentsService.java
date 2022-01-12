package com.example.secretforest_project.Service;

import com.example.secretforest_project.Dto.Response.CommentsListResponse;
import com.example.secretforest_project.Dto.Response.CommentsViewResponse;
import com.example.secretforest_project.Entity.Comments.CommentsEntity;
import com.example.secretforest_project.Entity.Comments.CommentsRepository;
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

    private final CommentsRepository commentsRepository;

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

    // 댓글 신고 목록
    public CommentsListResponse showreportcomments(Pageable page) {

        Page<CommentsEntity> commentsEntityList = commentsRepository.findAllByCnsrsOrderByIdDesc(3, page);
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
