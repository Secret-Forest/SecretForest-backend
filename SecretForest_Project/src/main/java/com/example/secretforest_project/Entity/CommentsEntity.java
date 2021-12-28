package com.example.secretforest_project.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class CommentsEntity { // 댓글

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String Comment;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false, length = 60)
    private String pwd;

    @Column(nullable = false, length = 1)
    private Integer cnsrs; // censorship(검열)

    @ManyToOne
    @JoinColumn
    @JsonManagedReference
    private PostEntity comments_id;
}
