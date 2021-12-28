package com.example.secretforest_project.Dto.Request;

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
public class CommentsUpdateRequest {

    @NotBlank
    @Size(max = 500)
    private String Comment;

    @NotBlank
    @Size(min = 6, max = 20)
    private String pwd;

}
