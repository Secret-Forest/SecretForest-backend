package com.example.forestproject.Dto.Auth;

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
public class UserPwdDto {

    @NotBlank
    @Size(min = 8, max = 20)
    private String pwd;

}
