package com.example.forestproject.Controller.NoticeboardController;

import com.example.forestproject.Dto.Noticeboard.PostDto;
import com.example.forestproject.Dto.Noticeboard.PostViewListDto;
import com.example.forestproject.Service.PostService;
import com.example.forestproject.Service.ShowPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class NoticeboardController {

    private final PostService postService;
    private final ShowPostService showPostService;

    // 첫 메인 페이지
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public PostViewListDto ShowMain() {
        return showPostService.showmain();
    }

    /*
    // 자신의 게시글 보기
    @GetMapping("/mypage")
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public PostViewListDto ShowMy() {
        return showPostService.showmy();
    }
     */

    // 제목으로 게시글 검색
    @GetMapping("/{title}")
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public PostViewListDto FindTitle(@PathVariable String title) {
        return showPostService.findtitle(title);
    }

    // 작성자로 게시글 검색
    @GetMapping("/{writer}")
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public PostViewListDto FindWriter(@PathVariable String writer) {
        return showPostService.findwriter(writer);
    }

    // 게시글 작성
    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED) // 201 성공적으로 자원을 생성함
    public void SavePost(@Valid @RequestBody PostDto postDto) {
        postService.savepost(postDto);
    }

}
