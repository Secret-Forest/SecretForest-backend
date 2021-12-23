package com.example.forestproject.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn
    @JsonManagedReference
    private PostEntity comments_id;
}
