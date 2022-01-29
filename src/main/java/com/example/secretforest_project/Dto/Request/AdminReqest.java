package com.example.secretforest_project.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminReqest {

    @NotBlank
    @Size(min = 1, max = 10)
    private String accountId;

    @NotBlank
    @Size(min = 6, max = 20)
    private String pwd;

}
