package com.example.forestproject.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 2, max = 30)
    private String userid;

    @Column(nullable = false)
    @Size(min = 8, max = 20)
    private String pwd;

    @Column(nullable = false, length = 15)
    private String name;

    @Column(nullable = false)
    private String email;

}
