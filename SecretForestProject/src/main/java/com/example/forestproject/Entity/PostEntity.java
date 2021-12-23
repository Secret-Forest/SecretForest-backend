package com.example.forestproject.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class PostEntity { // 게시글

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 2000)
    private String content;

    @Column(nullable = false)
    private String writer;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "comments_id")
    @JsonBackReference
    private List<CommentsEntity> commentsEntities = new ArrayList<>();

}
