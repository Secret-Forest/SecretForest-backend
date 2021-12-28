package com.example.secretforest_project.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
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

    @Column(nullable = false, length = 15)
    private String writer;

    @Column(nullable = false, length = 20)
    private String pwd;

    @Column(nullable = false, length = 1)
    private Integer cnsrs; // censorship(검열)

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "comments_id")
    @JsonBackReference
    private List<CommentsEntity> commentsEntities = new ArrayList<>();

}
