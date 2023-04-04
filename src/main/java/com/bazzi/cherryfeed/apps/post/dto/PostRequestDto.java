package com.bazzi.cherryfeed.apps.post.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "게시물등록요청모델")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDto {
    @ApiModelProperty(value = "게시물제목")
    private String postNm;
    @ApiModelProperty(value = "게시물 내용")
    private String postContent;
    @ApiModelProperty(value = "장소")
    private String location;
    @ApiModelProperty(value = "이미지 아이디")
    private Long imgId;
    @ApiModelProperty(value = "캘린더 아이디")
    private Long calendarId;
}
