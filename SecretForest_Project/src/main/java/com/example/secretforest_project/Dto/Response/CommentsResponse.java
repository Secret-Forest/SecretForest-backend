package com.example.secretforest_project.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentsResponse {

    @NotBlank
    private Long id;

    @NotBlank
    @Size(max = 500)
    private String comment;

    @NotBlank
    private String writer;

    @NotBlank
    @Size(min = 6, max = 20)
    private String pwd;

    @NotBlank
    @Size(max = 1)
    private Integer cnsrs; // censorship(검열)



}
