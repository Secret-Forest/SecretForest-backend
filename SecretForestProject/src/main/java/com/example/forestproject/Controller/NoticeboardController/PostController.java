package com.example.forestproject.Controller.NoticeboardController;

import com.example.forestproject.Dto.Noticeboard.CommentsDto;
import com.example.forestproject.Dto.Noticeboard.PostDto;
import com.example.forestproject.Dto.Noticeboard.PostResponse;
import com.example.forestproject.Dto.Noticeboard.PostViewListDto;
import com.example.forestproject.Service.CommentsService;
import com.example.forestproject.Service.PostService;
import com.example.forestproject.Service.ShowPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RequestMapping("/{postid}")
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
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public void UpdatePost(@PathVariable("postid") Long postid,
                           @Valid @RequestBody PostDto postDto) {
        postService.updatepost(postid, postDto);
    }

    // 게시글 삭제
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public void DelPost(@PathVariable("postid") Long postid,
                           @Valid @RequestBody PostDto postDto) {
        postService.delpost(postid, postDto);
    }

    // 댓글 작성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201 성공적으로 자원을 생성함
    public void SeveConnents(@PathVariable("postid") Long postid,
                              @Valid @RequestBody CommentsDto commentsDto) {
        commentsService.seveconnents(postid, commentsDto);
    }

    // 댓글 수정
    @PutMapping("/{commentsid}")
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public void UpdateConnents(@PathVariable("postid") Long postid,
                                       @PathVariable("commentsid") Long commentsid,
                                       @Valid @RequestBody CommentsDto commentsDto) {
        commentsService.updateconnents(postid, commentsid, commentsDto);
    }

    // 댓글 삭제
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public void DelConnents(@PathVariable("postid") Long postid,
                                @Valid @RequestBody CommentsDto commentsDto) {
        commentsService.delconnents(postid, commentsDto);
    }

}
