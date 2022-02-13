package com.example.secretforest_project.Controller.NoticeboardController;

import com.example.secretforest_project.Dto.Request.CommentsUpdateRequest;
import com.example.secretforest_project.Dto.Request.PasswordRequest;
import com.example.secretforest_project.Service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/comments/{commentsid}")
@RestController
public class CommentsController {

    private final CommentsService commentsService;

    // 댓글 수정
    @PutMapping
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public void UpdateComments (@PathVariable("commentsid") Long comments_id,
                                @Valid @RequestBody CommentsUpdateRequest commentsUpdateRequest) {
        commentsService.updatecomments(comments_id, commentsUpdateRequest);
    }

    // 댓글 삭제
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK) // 200 요청을 정상적으로 처리함
    public void DelComments(@PathVariable("commentsid") Long comments_id,
                            @Valid @RequestBody PasswordRequest pwdRequest) {
        commentsService.delcomments(pwdRequest, comments_id);
    }

}
