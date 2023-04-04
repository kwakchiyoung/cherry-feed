package com.bazzi.cherryfeed.apps.anvsy.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel(value = "기념일등록응답모델")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnvsyResponseDto {
    @ApiModelProperty(value = "기념일아이디")
    private Long id;
    @ApiModelProperty(value = "기념일 이름")
    private String anvsyNm;
    @ApiModelProperty(value = "기념일 날짜")
    private Date anvsyAt;
    @ApiModelProperty(value = "이미지아이디")
    private Long imgId;
    @ApiModelProperty(value = "상태값 - 1:반복,2:목표")
    private int status;
}
