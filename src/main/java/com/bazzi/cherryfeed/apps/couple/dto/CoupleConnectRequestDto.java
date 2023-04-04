package com.bazzi.cherryfeed.apps.couple.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "커플 연결 모델")
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class CoupleConnectRequestDto {
    @ApiModelProperty("연결시 사용하는 코드")
    private String connectCode;
}
