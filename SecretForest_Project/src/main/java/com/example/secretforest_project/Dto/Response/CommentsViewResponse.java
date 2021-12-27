package com.example.secretforest_project.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentsViewResponse {

    @NotBlank
    private Long id;

    @NotBlank
    @Size(max = 500)
    private String comment;

    @NotBlank
    private String writer;


}
