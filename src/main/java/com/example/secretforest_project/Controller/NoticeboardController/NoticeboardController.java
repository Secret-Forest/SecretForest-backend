package com.example.secretforest_project.Controller.NoticeboardController;

import com.example.secretforest_project.Dto.Request.CommentsRequest;
import com.example.secretforest_project.Dto.Request.PostRequest;
import com.example.secretforest_project.Dto.Response.PostListResponse;
import com.example.secretforest_project.Service.CommentsService;
import com.example.secretforest_project.Service.PostService;
import com.example.secretforest_project.Service.ShowPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
public class NoticeboardController {

    private final ShowPostService showPostService;
    private final PostService postService;
    private final CommentsService commentsService;

    // 첫 메인 페이지
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public PostListResponse ShowMain(Pageable page) {
        return showPostService.showmain(page);
    }

    // 제목으로 게시글 검색
    @GetMapping("/search/title")
    @ResponseStatus(HttpStatus.OK)
    public PostListResponse FindTitle(@RequestParam(value = "title") String title, Pageable page) {
        return showPostService.findtitle(title, page);
    }

    // 작성자로 게시글 검색
    @GetMapping("/search/writer")
    @ResponseStatus(HttpStatus.OK)
    public PostListResponse FindWriter(@RequestParam(value = "writer") String writer, Pageable page) {
        return showPostService.findwriter(writer, page);
    }

    // 게시글 작성
    @PostMapping("/board")
    @ResponseStatus(HttpStatus.CREATED)
    public void SavePost(@Valid @RequestBody PostRequest postRequest) {
        postService.savepost(postRequest);
    }

    // 댓글 작성
    @PostMapping("comments/{postid}")
    @ResponseStatus(HttpStatus.CREATED)
    public void SeveComments(@PathVariable("postid") Long post_id,
                             @Valid @RequestBody CommentsRequest commentsRequest) {
        commentsService.sevecomments(post_id, commentsRequest);
    }

    // 게시글 신고
    @PutMapping("/report/board/{postid}")
    @ResponseStatus(HttpStatus.CREATED)
    public void ReportPost (@PathVariable("postid") Long post_id) {
        postService.reportpost(post_id);
    }

    // 댓글 신고
    @PutMapping("/report/comments/{commentsid}")
    @ResponseStatus(HttpStatus.CREATED)
    public void ReportComments (@PathVariable("commentsid") Long comments_id) {
        commentsService.reportcomments(comments_id);
    }

}
