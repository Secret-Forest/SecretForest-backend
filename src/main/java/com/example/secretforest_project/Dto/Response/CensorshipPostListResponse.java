package com.example.secretforest_project.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CensorshipPostListResponse {

    private List<CensorshipPostResponse> censorshipPostResponses;

}
