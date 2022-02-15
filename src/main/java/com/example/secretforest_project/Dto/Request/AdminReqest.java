package com.example.secretforest_project.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminReqest {

    @NotBlank
    private String adminId;

    @NotBlank
    private String password;

}
