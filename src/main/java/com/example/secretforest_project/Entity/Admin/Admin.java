package com.example.secretforest_project.Entity.Admin;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin_Id",nullable = false, length = 10)
    private String aminId;

    @Column(nullable = false)
    private String password;

}
