package com.example.forestproject.Dto.Noticeboard;

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
public class CommentsDto {

    @NotBlank
    @Size(max = 500)
    private String Comment;

    @NotBlank
    private String writer;

}
