package com.example.secretforest_project.Controller.AdminController;

import com.example.secretforest_project.Dto.Response.CommentsListResponse;
import com.example.secretforest_project.Dto.Response.PostListResponse;
import com.example.secretforest_project.Service.AdminService;
import com.example.secretforest_project.Service.CommentsService;
import com.example.secretforest_project.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/report")
@RequiredArgsConstructor
@RestController
public class ReportController {

    private final AdminService adminService;
    private final CommentsService commentsService;
    private final PostService postService;

    // 게시글 신고
    @PutMapping("post/{postid}")
    @ResponseStatus(HttpStatus.OK)
    public void ReportPost (@PathVariable("postid") Long post_id) {
        postService.reportpost(post_id);
    }

    // 댓글 신고
    @PutMapping("comments/{commentsid}")
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
    public PostListResponse ShowReportPost(Pageable page) {
        return adminService.showreportpost(page);
    }

    // 신고된 댓글 목록
    @GetMapping("/comments")
    @ResponseStatus(HttpStatus.OK)
    public CommentsListResponse ShowReportComments(Pageable page) {
        return adminService.showreportcomments(page);
    }

}
