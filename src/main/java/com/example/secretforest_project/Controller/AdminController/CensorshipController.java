package com.example.secretforest_project.Controller.AdminController;

import com.example.secretforest_project.Dto.Response.PostListResponse;
import com.example.secretforest_project.Dto.Response.PostViewResponse;
import com.example.secretforest_project.Service.AdminPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/admin/Censorship")
@RequiredArgsConstructor
@RestController
public class CensorshipController {

    private final AdminPostService adminPostService;

    // 게시글 검열 - 통과
    @PutMapping("/{postid}/pass")
    @ResponseStatus(HttpStatus.CREATED)
    public void PostOk(@PathVariable("postid") Long post_id) {
        adminPostService.postok(post_id);
    }

    // 게시글 검열 - 삭제
    @DeleteMapping("/{postid}/elmnt")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void PostNo(@PathVariable("postid") Long post_id) {
        adminPostService.postno(post_id);
    }

    // 검열해야하는 게시물 목록
    @GetMapping("/board")
    @ResponseStatus(HttpStatus.OK)
    public PostListResponse ShowPost(Pageable page) {
        return adminPostService.showpost(page);
    }

}
