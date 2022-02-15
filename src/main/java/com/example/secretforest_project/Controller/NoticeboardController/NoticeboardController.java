package com.example.secretforest_project.Controller.NoticeboardController;

import com.example.secretforest_project.Dto.Request.CommentRequest;
import com.example.secretforest_project.Dto.Request.PasswordRequest;
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
    @PostMapping("comment/{postid}")
    @ResponseStatus(HttpStatus.CREATED)
    public void SeveComments(@PathVariable("postid") Long postId,
                             @Valid @RequestBody CommentRequest commentsRequest) {
        commentsService.sevecomments(postId, commentsRequest);
    }

    // 게시글 패스워도 검증
    @GetMapping("/match/{postid}")
    @ResponseStatus(HttpStatus.OK)
    public void matchesPostPassword(@PathVariable("postid") Long postId,
                                   @RequestBody @Valid PasswordRequest passwordRequest) {
        postService.match(postId,passwordRequest);
    }

    // 댓글 패스워도 검증
    @GetMapping("/match/{commentid}")
    @ResponseStatus(HttpStatus.OK)
    public void matchesCommentPassword(@PathVariable("commentid") Long commentId,
                                   @RequestBody @Valid PasswordRequest passwordRequest) {
        commentsService.match(commentId, passwordRequest);
    }

    // 게시글 신고
    @PutMapping("/report/board/{postid}")
    @ResponseStatus(HttpStatus.CREATED)
    public void ReportPost (@PathVariable("postid") Long postId) {
        postService.reportpost(postId);
    }

    // 댓글 신고
    @PutMapping("/report/comments/{commentid}")
    @ResponseStatus(HttpStatus.CREATED)
    public void ReportComments (@PathVariable("commentid") Long commentId) {
        commentsService.reportcomments(commentId);
    }

}
