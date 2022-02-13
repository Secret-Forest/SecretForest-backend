package com.example.secretforest_project.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    @NotBlank
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String writer;

    private List<CommentsPostResponse> commentsPostResponses = new ArrayList<>();

}
