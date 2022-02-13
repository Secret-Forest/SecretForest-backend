package com.example.secretforest_project.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CensorshipPostResponse {

    @NotBlank
    private Long id;

    @NotBlank
    private Integer censorship;

    @NotBlank
    private String title;

    @NotBlank
    private String writer;

}
