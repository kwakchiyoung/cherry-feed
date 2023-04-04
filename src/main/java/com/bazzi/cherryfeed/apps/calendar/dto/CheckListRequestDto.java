package com.bazzi.cherryfeed.apps.calendar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CheckListRequestDto {
    private CheckListRequestDto() {
        throw new IllegalStateException("CheckListRequestDto");
    }

    @ApiModel(value = "체크리스트등록모델")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        @ApiModelProperty(value = "내용(체크리스트)")
        private String content;
        @ApiModelProperty(value = "체크유무 true,false")
        private boolean isFinish;
    }

    @ApiModel(value = "체크리스트응답모델")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        @ApiModelProperty(value = "체크리스트 아이디")
        private Long id;
        @ApiModelProperty(value = "내용(체크리스트)")
        private String content;
        @ApiModelProperty(value = "체크유무 true,false")
        private Boolean isFinish;

    }

    @ApiModel(value = "체크리스트수정요청모델")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        @ApiModelProperty(value = "체크리스트 아이디")
        private Long id;
        @ApiModelProperty(value = "내용(체크리스트)")
        private String content;
        @ApiModelProperty(value = "체크유무 true,false")
        private Boolean isFinish;
    }
}
