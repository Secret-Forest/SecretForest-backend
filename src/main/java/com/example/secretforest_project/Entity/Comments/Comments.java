package com.example.secretforest_project.Entity.Comments;

import com.example.secretforest_project.Entity.Post.Post;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Comments { // 댓글

    @Id
    @Column(name = "comments_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String comment;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 1)
    private Integer censorship;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
