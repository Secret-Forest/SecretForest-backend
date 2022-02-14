package com.example.secretforest_project.Controller.NoticeboardController;

import com.example.secretforest_project.Dto.Request.PostUpdateRequest;
import com.example.secretforest_project.Dto.Request.PasswordRequest;
import com.example.secretforest_project.Dto.Response.PostResponse;
import com.example.secretforest_project.Service.PostService;
import com.example.secretforest_project.Service.ShowPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;


@RequiredArgsConstructor
@RequestMapping("/board/{postid}")
@RestController
public class PostController {

    private final PostService postService;
    private final ShowPostService showPostService;

    // 게시글 보기
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PostResponse ShowPost(@PathVariable("postid") Long postId) {
        return showPostService.showpost(postId);
    }

    // 게시글 수정
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void UpdatePost(@PathVariable("postid") Long postId,
                           @Valid @RequestBody PostUpdateRequest postUpdateRequest) {
        postService.updatepost(postId, postUpdateRequest);
    }

    // 게시글 삭제
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DelPost(@PathVariable("postid") Long postId,
                        @Valid @RequestBody PasswordRequest pwdRequest) {
        postService.delpost(postId, pwdRequest);
    }

}
