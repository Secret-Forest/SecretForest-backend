package com.example.secretforest_project.Entity.Post;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Post { // 게시글

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 2000)
    private String content;

    @Column(nullable = false, length = 15)
    private String writer;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 1)
    private Integer censorship;

}
