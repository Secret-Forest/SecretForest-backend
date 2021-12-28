package com.example.secretforest_project.Controller.AdminController;

import com.example.secretforest_project.Dto.Response.CommentsListResponse;
import com.example.secretforest_project.Dto.Response.PostListResponse;
import com.example.secretforest_project.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/cnsrs")
@RequiredArgsConstructor
@RestController
public class CnsrsController {

    private final AdminService adminService;

    // 게시글 검열 - 통과
    @PutMapping("/{postid}/pass")
    @ResponseStatus(HttpStatus.OK)
    public void PostOk(@PathVariable("postid") Long post_id) {
        adminService.postok(post_id);
    }

    // 게시글 검열 - 삭제
    @DeleteMapping("/{postid}/elmnt")
    @ResponseStatus(HttpStatus.OK)
    public void PostNo(@PathVariable("postid") Long post_id) {
        adminService.postno(post_id);
    }

    // 검열해야하는 게시물 목록
    @GetMapping("/post")
    @ResponseStatus(HttpStatus.OK)
    public PostListResponse ShowPost() {
        return adminService.showpost();
    }

}
