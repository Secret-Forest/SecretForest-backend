package com.example.forestproject.Dto.Noticeboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostViewListDto {

    private List<PostViewResponse> postViewDtoList;

}
