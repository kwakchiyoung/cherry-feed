package com.bazzi.cherryfeed.apps.post.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@ApiModel(value = "게시판조회응답모델")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponseDto {
    @ApiModelProperty(value = "게시물 아이디")
    private Long id;
    @ApiModelProperty(value = "이미지 아이디")
    private Long imgId;
    @ApiModelProperty(value = "장소")
    private String location;
    @ApiModelProperty(value = "등록시간")
    private LocalDateTime postAt;
    @ApiModelProperty(value = "게시물 내용")
    private String postContent;
    @ApiModelProperty(value = "게시물 제목")
    private String postNm;
    @ApiModelProperty(value = "조회수")
    private Long postView;
    @ApiModelProperty(value = "스크랩 수")
    private Long scrap;
}
