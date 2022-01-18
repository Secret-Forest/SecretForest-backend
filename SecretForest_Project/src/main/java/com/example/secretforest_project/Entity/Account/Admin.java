package com.example.secretforest_project.Entity.Account;

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

    @Column(name = "account_Id",nullable = false, length = 10)
    private String aminId;

    @Column(name = "account_pwd",nullable = false)
    private String pwd;

}
