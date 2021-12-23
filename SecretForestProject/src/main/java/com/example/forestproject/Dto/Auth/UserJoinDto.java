package com.example.forestproject.Dto.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinDto {

    @NotBlank
    @Size(min = 2, max = 30)
    private String userid;

    @NotBlank
    @Size(min = 8, max = 20)
    private String pwd;

    @NotBlank
    @Size(max = 15)
    private String name;

    @NotBlank
    private String email;

}
