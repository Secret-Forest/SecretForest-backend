package com.example.forestproject.Service;

import com.example.forestproject.Dto.Noticeboard.*;
import com.example.forestproject.Entity.CommentsEntity;
import com.example.forestproject.Entity.PostEntity;
import com.example.forestproject.Entity.PostRepository;
import com.example.forestproject.Exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ShowPostService {

    private final PostRepository postRepository;

    // 중복코드
    private PostViewListDto list(List<PostEntity> postEntityList) {

        List<PostViewResponse> postViewDtos = new ArrayList<>();

        for (PostEntity postEntity : postEntityList) {
            postViewDtos.add(
                    PostViewResponse.builder()
                            .id(postEntity.getId())
                            .title(postEntity.getTitle())
                            .content(postEntity.getContent())
                            .writer(postEntity.getWriter())
                            .build()
            );
        }

        return PostViewListDto.builder()
                .postViewDtoList(postViewDtos)
                .build();

    }

    // 제목으로 게시글 찾기
    public PostViewListDto findtitle(String title) {

        List<PostEntity> postEntityList = postRepository.findAllByTitleLike(title);
        return list(postEntityList);

    }

    // 작성자로 게시글 찾기
    public PostViewListDto findwriter(String writer) {

        List<PostEntity> postEntityList = postRepository.findAllByWriter(writer);
        return list(postEntityList);

    }

    // main 게시글 보기
    public PostViewListDto showmain() {

        List<PostEntity> postEntityList = postRepository.findAllBy();
        return list(postEntityList);
    }

    // 자신의 게시글 보기 (임시) => 토큰
    // 이 코드 검색 코드랑 같은데 엄연히 다른거임 검색은 모두가 접근 할 수 있지만 이건 자신만 접근 가능하다구...(토큰 필요)
    public PostViewListDto showmy(String writer) {

        List<PostEntity> postEntityList = postRepository.findAllByWriter(writer);
        return list(postEntityList);

    }

    // 게시글 보기
    public PostResponse showpost(Long postid) {

        PostEntity postEntity = postRepository.findById(postid)
                .orElseThrow(NotFoundException::new);

        List<CommentsEntity> commentsEntityList = postEntity.getCommentsEntities();
        List<CommentsDto> commentsDtos = new ArrayList<>();

        for (CommentsEntity commentsEntityeies : commentsEntityList) {
            commentsDtos.add(
                    CommentsDto.builder()
                            .writer(commentsEntityeies.getWriter())
                            .Comment(commentsEntityeies.getComment())
                            .build()
            );
        }


        return PostResponse.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .writer(postEntity.getWriter())
                .commentsDtos(commentsDtos)
                .build();

    }

}
