package com.example.secretforest_project.Controller.AdminController;

import com.example.secretforest_project.Dto.Response.CommentsListResponse;
import com.example.secretforest_project.Dto.Response.PostListResponse;
import com.example.secretforest_project.Service.AdminCommentsService;
import com.example.secretforest_project.Service.AdminPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/admin/report")
@RequiredArgsConstructor
@RestController
public class ReportController {

    private final AdminCommentsService adminCommentsService;
    private final AdminPostService adminPostService;

    // 댓글 검열 - 통과
    @PutMapping("/{commentid}/pass")
    @ResponseStatus(HttpStatus.CREATED)
    public void CommentsOk(@PathVariable("commentid") Long commentId) {
        adminCommentsService.commentsok(commentId);
    }

    // 댓글 검열 - 삭제
    @DeleteMapping("/{commentid}/elmnt")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void CommentsNo(@PathVariable("commentid") Long commentId) {
        adminCommentsService.commentsno(commentId);
    }

    // 신고된 게시물 목록
    @GetMapping("/board")
    @ResponseStatus(HttpStatus.OK)
    public PostListResponse ShowReportPost(Pageable page) {
        return adminPostService.showreportpost(page);
    }

    // 신고된 댓글 목록
    @GetMapping("/comment")
    @ResponseStatus(HttpStatus.OK)
    public CommentsListResponse ShowReportComments(Pageable page) {
        return adminCommentsService.showreportcomments(page);
    }

}
