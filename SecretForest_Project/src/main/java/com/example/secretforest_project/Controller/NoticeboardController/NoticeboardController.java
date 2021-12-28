package com.example.secretforest_project.Controller.NoticeboardController;

import com.example.secretforest_project.Dto.Request.PostRequest;
import com.example.secretforest_project.Dto.Response.PostListResponse;
import com.example.secretforest_project.Service.PostService;
import com.example.secretforest_project.Service.ShowPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RequiredArgsConstructor
@RestController
public class NoticeboardController {

    private final ShowPostService showPostService;
    private final PostService postService;

    // 첫 메인 페이지
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public PostListResponse ShowMain() {
        return showPostService.showmain();
    }

    // 제목으로 게시글 검색
    @GetMapping("/search/title")
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public PostListResponse FindTitle(@PathParam("title") String title) {
        return showPostService.findtitle(title);
    }

    // 작성자로 게시글 검색
    @GetMapping("/search/writer")
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public PostListResponse FindWriter(@PathParam("writer") String writer) {
        return showPostService.findwriter(writer);
    }

    // 게시글 작성
    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED) // 201 성공적으로 자원을 생성함
    public void SavePost(@Valid @RequestBody PostRequest postRequest) {
        postService.savepost(postRequest);
    }

}
