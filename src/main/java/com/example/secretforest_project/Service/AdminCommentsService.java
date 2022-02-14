package com.example.secretforest_project.Service;

import com.example.secretforest_project.Dto.Response.CommentsListResponse;
import com.example.secretforest_project.Dto.Response.CommentsViewResponse;
import com.example.secretforest_project.Entity.Comments.Comments;
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
    public void commentsok(Long commentId) {

        Comments commentsEntity = commentsRepository.findById(commentId)
                .orElseThrow(NotFoundException::new);

        Comments build = Comments.builder()
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

        Comments commentsEntity = commentsRepository.findById(commentId)
                .orElseThrow(NotFoundException::new);

        commentsRepository.delete(commentsEntity);

    }

    // 댓글 신고 목록
    public CommentsListResponse showreportcomments(Pageable page) {

        Page<Comments> commentsEntityList = commentsRepository.findAllByCensorshipOrderByIdDesc(3, page);
        List<CommentsViewResponse> commentsViewResponseList = new ArrayList<>();

        for (Comments commentsEntity : commentsEntityList) {
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
