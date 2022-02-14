package com.example.secretforest_project.Controller.NoticeboardController;

import com.example.secretforest_project.Dto.Request.CommentUpdateRequest;
import com.example.secretforest_project.Dto.Request.PasswordRequest;
import com.example.secretforest_project.Service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/comment/{commentid}")
@RestController
public class CommentController {

    private final CommentsService commentsService;

    // 댓글 수정
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void UpdateComments (@PathVariable("commentid") Long commentId,
                                @Valid @RequestBody CommentUpdateRequest commentsUpdateRequest) {
        commentsService.updatecomments(commentId, commentsUpdateRequest);
    }

    // 댓글 삭제
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DelComments(@PathVariable("commentid") Long commentId,
                            @Valid @RequestBody PasswordRequest pwdRequest) {
        commentsService.delcomments(pwdRequest, commentId);
    }

}
