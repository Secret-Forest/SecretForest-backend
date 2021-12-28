package com.example.secretforest_project.Controller.AdminController;

import com.example.secretforest_project.Dto.Response.CommentsListResponse;
import com.example.secretforest_project.Dto.Response.PostListResponse;
import com.example.secretforest_project.Service.AdminService;
import com.example.secretforest_project.Service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/report")
@RequiredArgsConstructor
@RestController
public class ReportController {

    private final AdminService adminService;
    private final CommentsService commentsService;

    // 게시글 신고
    @PutMapping("/{postid}")
    @ResponseStatus(HttpStatus.OK)
    public void ReportPost (@PathVariable("postid") Long post_id) {
        commentsService.reportcomments(post_id);
    }

    // 댓글 신고
    @PutMapping("/{commentsid}")
    @ResponseStatus(HttpStatus.OK)
    public void ReportComments (@PathVariable("commentsid") Long comments_id) {
        commentsService.reportcomments(comments_id);
    }

    // 댓글 검열 - 통과
    @PutMapping("/{commentsid}/pass")
    @ResponseStatus(HttpStatus.OK)
    public void CommentsOk(@PathVariable("commentsid") Long comments_id) {
        adminService.commentsok(comments_id);
    }

    // 댓글 검열 - 삭제
    @DeleteMapping("/{commentsid}/elmnt")
    @ResponseStatus(HttpStatus.OK)
    public void CommentsNo(@PathVariable("commentsid") Long comments_id) {
        adminService.commentsno(comments_id);
    }


    // 신고된 게시물 목록
    @GetMapping("/post")
    @ResponseStatus(HttpStatus.OK)
    public PostListResponse ShowReportPost() {
        return adminService.showreportpost();
    }

    // 신고된 댓글 목록
    @GetMapping("/comments")
    @ResponseStatus(HttpStatus.OK)
    public CommentsListResponse ShowReportComments() {
        return adminService.showreportcomments();
    }

}
