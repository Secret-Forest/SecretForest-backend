package com.example.secretforest_project.Service;

import com.example.secretforest_project.Dto.Request.CommentsRequest;
import com.example.secretforest_project.Dto.Request.CommentsUpdateRequest;
import com.example.secretforest_project.Dto.Request.PwdRequest;
import com.example.secretforest_project.Entity.Comments.Comments;
import com.example.secretforest_project.Entity.Comments.CommentsRepository;
import com.example.secretforest_project.Entity.Post.Post;
import com.example.secretforest_project.Entity.Post.PostRepository;
import com.example.secretforest_project.Exception.ConflictException;
import com.example.secretforest_project.Exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CommentsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final PostRepository postRepository;
    private final CommentsRepository commentsRepository;

    // 댓글 저장
    public void sevecomments(Long postid, CommentsRequest commentsRequest) {

        Post postEntity = postRepository.findById(postid)
                .orElseThrow(NotFoundException::new);

        Comments commentsEntity = Comments.builder()
                .post(postEntity)
                .writer(commentsRequest.getWriter())
                .comment(commentsRequest.getComment())
                .pwd(passwordEncoder.encode(commentsRequest.getPwd()))
                .cnsrs(0)
                .build();

        commentsRepository.save(commentsEntity);

    }

    // 댓글 수정
    public void updatecomments(Long commentsid, CommentsUpdateRequest commentsUpdateRequest) {

        Comments commentsEntity = commentsRepository.findById(commentsid)
                .orElseThrow(NotFoundException::new);

        if (!passwordEncoder.matches(commentsUpdateRequest.getPwd(), commentsEntity.getPwd())) {
            // matches(비교할 비밀번호, db에 저장되어 있는 비밀번호)
            throw new ConflictException();
        }

        Comments build = Comments.builder()
                .id(commentsEntity.getId())
                .post(commentsEntity.getPost())
                .writer(commentsEntity.getWriter())
                .comment(commentsUpdateRequest.getComment())
                .pwd(commentsEntity.getPwd())
                .cnsrs(0)
                .build();

        commentsRepository.save(build);

    }

    // 댓글 삭제
    public void delcomments(PwdRequest pwdRequest, Long commentsid) {

        Comments commentsEntity = commentsRepository.findById(commentsid)
                .orElseThrow(NotFoundException::new);

        if (!passwordEncoder.matches(pwdRequest.getPwd(), commentsEntity.getPwd())) {
            // matches(비교할 비밀번호, db에 저장되어 있는 비밀번호)
            throw new ConflictException();
        }

        commentsRepository.delete(commentsEntity);

    }

    // 댓글 신고
    public void reportcomments(Long comments_id) {

        Comments commentsEntity = commentsRepository.findById(comments_id)
                .orElseThrow(NotFoundException::new);

        Comments build = Comments.builder()
                .id(commentsEntity.getId())
                .post(commentsEntity.getPost())
                .writer(commentsEntity.getWriter())
                .comment(commentsEntity.getComment())
                .pwd(commentsEntity.getPwd())
                .cnsrs(3)
                .build();

        commentsRepository.save(build);

    }


}
