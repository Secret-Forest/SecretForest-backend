package com.example.secretforest_project.Controller.AdminController;

import com.example.secretforest_project.Dto.Response.CommentsListResponse;
import com.example.secretforest_project.Dto.Response.PostListResponse;
import com.example.secretforest_project.Service.AdminCommentsService;
import com.example.secretforest_project.Service.AdminPostService;
import com.example.secretforest_project.Service.CommentsService;
import com.example.secretforest_project.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/report")
@RequiredArgsConstructor
@RestController
public class ReportController {

    private final AdminCommentsService adminCommentsService;
    private final AdminPostService adminPostService;

    // 댓글 검열 - 통과
    @PutMapping("/{commentsid}/pass")
    @ResponseStatus(HttpStatus.OK)
    public void CommentsOk(@PathVariable("commentsid") Long comments_id) {
        adminCommentsService.commentsok(comments_id);
    }

    // 댓글 검열 - 삭제
    @DeleteMapping("/{commentsid}/elmnt")
    @ResponseStatus(HttpStatus.OK)
    public void CommentsNo(@PathVariable("commentsid") Long comments_id) {
        adminCommentsService.commentsno(comments_id);
    }

    // 신고된 게시물 목록
    @GetMapping("/board")
    @ResponseStatus(HttpStatus.OK)
    public PostListResponse ShowReportPost(Pageable page) {
        return adminPostService.showreportpost(page);
    }

    // 신고된 댓글 목록
    @GetMapping("/comments")
    @ResponseStatus(HttpStatus.OK)
    public CommentsListResponse ShowReportComments(Pageable page) {
        return adminCommentsService.showreportcomments(page);
    }

}
