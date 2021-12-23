package com.example.forestproject.Service;

import com.example.forestproject.Dto.Noticeboard.CommentsDto;
import com.example.forestproject.Entity.CommentsEntity;
import com.example.forestproject.Entity.PostEntity;
import com.example.forestproject.Entity.PostRepository;
import com.example.forestproject.Exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CommentsService {

    private final PostRepository postRepository;

    // 댓글 저장
    public void seveconnents(Long postid, CommentsDto commentsDto) {

        PostEntity postEntity = postRepository.findById(postid)
                .orElseThrow(NotFoundException::new);

        List<CommentsEntity> commentsEntityList = postEntity.getCommentsEntities();
        commentsEntityList.add(
                CommentsEntity.builder()
                        .writer(commentsDto.getWriter())
                        .Comment(commentsDto.getComment())
                        .build()
        );

        PostEntity build = PostEntity.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .writer(postEntity.getWriter())
                .commentsEntities(commentsEntityList)
                .build();

        postRepository.save(build);

    }

    // 댓글 수정
    public void updateconnents(Long postid,Long commentsid, CommentsDto commentsDto) {

        PostEntity postEntity = postRepository.findById(postid)
                .orElseThrow(NotFoundException::new);

        List<CommentsEntity> commentsEntityList = postEntity.getCommentsEntities();
        commentsEntityList.set(Math.toIntExact(commentsid),
                CommentsEntity.builder()
                        .writer(commentsDto.getWriter())
                        .Comment(commentsDto.getComment())
                        .build()
        );

        PostEntity build = PostEntity.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .writer(postEntity.getWriter())
                .commentsEntities(commentsEntityList)
                .build();

        postRepository.save(build);

    }

    // 댓글 삭제
    public void delconnents(Long postid, CommentsDto commentsDto) {

        PostEntity postEntity = postRepository.findById(postid)
                .orElseThrow(NotFoundException::new);

        List<CommentsEntity> commentsEntityList = postEntity.getCommentsEntities();
        commentsEntityList.remove(
                CommentsEntity.builder()
                        .writer(commentsDto.getWriter())
                        .Comment(commentsDto.getComment())
                        .build()
        );

        PostEntity build = PostEntity.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .writer(postEntity.getWriter())
                .commentsEntities(commentsEntityList)
                .build();


        postRepository.save(build);

    }

}
