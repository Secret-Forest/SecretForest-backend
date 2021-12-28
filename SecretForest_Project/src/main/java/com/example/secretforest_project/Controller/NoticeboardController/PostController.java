package com.example.secretforest_project.Controller.NoticeboardController;

import com.example.secretforest_project.Dto.Request.CommentsRequest;
import com.example.secretforest_project.Dto.Request.PostRequest;
import com.example.secretforest_project.Dto.Request.PostUpdateRequest;
import com.example.secretforest_project.Dto.Request.PwdRequest;
import com.example.secretforest_project.Dto.Response.PostResponse;
import com.example.secretforest_project.Service.CommentsService;
import com.example.secretforest_project.Service.PostService;
import com.example.secretforest_project.Service.ShowPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RequestMapping("/post/{postid}")
@RestController
public class PostController {

    private final PostService postService;
    private final ShowPostService showPostService;
    private final CommentsService commentsService;

    // 게시글 보기
    @GetMapping
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public PostResponse ShowPost(@PathVariable("postid") Long postid) {
        return showPostService.showpost(postid);
    }

    // 게시글 수정
    @PutMapping
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public void UpdatePost(@PathVariable("postid") Long post_id,
                           @Valid @RequestBody PostUpdateRequest postUpdateRequest) {
        postService.updatepost(post_id, postUpdateRequest);
    }

    // 게시글 삭제
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public void DelPost(@PathVariable("postid") Long post_id,
                        @Valid @RequestBody PwdRequest pwdRequest) {
        postService.delpost(post_id, pwdRequest);
    }

    // 댓글 작성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201 성공적으로 자원을 생성함
    public void SeveComments(@PathVariable("postid") Long post_id,
                              @Valid @RequestBody CommentsRequest commentsRequest) {
        commentsService.sevecomments(post_id, commentsRequest);
    }

}
