package com.example.secretforest_project.Controller.AdminController;

import com.example.secretforest_project.Dto.Response.PostResponse;
import com.example.secretforest_project.Service.ShowPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@RequiredArgsConstructor
@RestController
public class AdminController {

    private final ShowPostService showPostService;

    // 게시글 보기
    @GetMapping("/board/{postid}")
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public PostResponse ShowPost(@PathVariable("postid") Long postid) {
        return showPostService.showpost(postid);
    }

}
