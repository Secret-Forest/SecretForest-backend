package com.example.secretforest_project.Service;

import com.example.secretforest_project.Dto.Request.CommentRequest;
import com.example.secretforest_project.Dto.Request.CommentUpdateRequest;
import com.example.secretforest_project.Dto.Request.PasswordRequest;
import com.example.secretforest_project.Entity.Comments.Comment;
import com.example.secretforest_project.Entity.Comments.CommentRepository;
import com.example.secretforest_project.Entity.Post.Post;
import com.example.secretforest_project.Entity.Post.PostRepository;
import com.example.secretforest_project.Exception.NotFoundException;
import com.example.secretforest_project.Service.Util.MatchesPassword;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CommentsService {

    private final PasswordEncoder encoder;

    private final PostRepository postRepository;
    private final CommentRepository commentsRepository;

    private final MatchesPassword matchesPassword;

    // 댓글 저장
    public void sevecomments(Long postId, CommentRequest commentsRequest) {

        Post postEntity = postRepository.findById(postId)
                .orElseThrow(NotFoundException::new);

        commentsRepository.save(
                Comment.builder()
                        .post(postEntity)
                        .writer(commentsRequest.getWriter())
                        .comment(commentsRequest.getComment())
                        .password(encoder.encode(commentsRequest.getPassword()))
                        .censorship(0)
                        .build()
        );

    }

    public void match(Long commentId, PasswordRequest passwordRequest) {

        Comment commentsEntity = commentsRepository.findById(commentId)
                .orElseThrow(NotFoundException::new);

        matchesPassword.matchesPassword(passwordRequest.getPassword(), commentsEntity.getPassword());
    }

    // 댓글 수정
    public void updatecomments(Long commentId, CommentUpdateRequest commentsUpdateRequest) {

        Comment commentsEntity = commentsRepository.findById(commentId)
                .orElseThrow(NotFoundException::new);

        commentsRepository.save(
                Comment.builder()
                        .id(commentsEntity.getId())
                        .post(commentsEntity.getPost())
                        .writer(commentsEntity.getWriter())
                        .comment(commentsUpdateRequest.getComment())
                        .password(commentsEntity.getPassword())
                        .censorship(0)
                        .build()
        );

    }

    // 댓글 삭제
    public void delcomments(PasswordRequest pwdRequest, Long commentId) {

        Comment commentsEntity = commentsRepository.findById(commentId)
                .orElseThrow(NotFoundException::new);

        matchesPassword.matchesPassword(pwdRequest.getPassword(), commentsEntity.getPassword());

        commentsRepository.delete(commentsEntity);

    }

    // 댓글 신고
    public void reportcomments(Long commentId) {

        Comment commentsEntity = commentsRepository.findById(commentId)
                .orElseThrow(NotFoundException::new);

        commentsRepository.save(
                Comment.builder()
                        .id(commentsEntity.getId())
                        .post(commentsEntity.getPost())
                        .writer(commentsEntity.getWriter())
                        .comment(commentsEntity.getComment())
                        .password(commentsEntity.getPassword())
                        .censorship(3)
                        .build()

        );

    }


}
