package com.example.forestproject.Dto.Noticeboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    @Size(max = 2000)
    private String content;

    @NotBlank
    private String writer;

    private List<CommentsDto> commentsDtos = new ArrayList<>();

}
