package com.bazzi.cherryfeed.apps.calendar.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

public class CalendarRequestDto {
    private CalendarRequestDto() {
        throw new IllegalStateException("CalendarRequestDto");
    }

    @ApiModel(value = "캘린더등록모델")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        @ApiModelProperty(value = "참여자아이디1")
        private Long partiId1;      //참여자아이디1
        @ApiModelProperty(value = "참여자아이디2")
        private Long partiId2;      //참여자아이디2
        @ApiModelProperty(value = "캘린더 제목")
        private String title;       //제목
        @ApiModelProperty(value = "종일 여부")
        private Boolean isAllDay;  //종일 여부
        @ApiModelProperty(value = "시작일자")
        private Date startAt;       //시작일자
        @ApiModelProperty(value = "종료일자")
        private Date endAt;         //종료일자
        @ApiModelProperty(value = "이미지 아이디")
        private Long imgId;         //이미지아이디
        @ApiModelProperty(value = "장소")
        private String location;    //장소
        @ApiModelProperty(value = "진행 상황")
        private String status;      //진행상황
        @ApiModelProperty(value = "내용(다이어리)")
        private String content;     //내용(다이어리)
        @ApiModelProperty(value = "알람일자")
        private Date alarmAt;       //알림일자
        @ApiModelProperty(value = "유형 - 계획1,일정2")
        private int type;          //유형 - 계획1,일정2
        @ApiModelProperty(value = "체크리스트")
        private List<CheckListRequestDto.Create> checkList;
    }

    @ApiModel(value = "캘린더조회응답모델")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        @ApiModelProperty(value = "일정 아이디")
        private Long id;
        @ApiModelProperty(value = "제목")
        private String title;
        @ApiModelProperty(value = "종일 여부")
        private boolean isAllDay;
        @ApiModelProperty(value = "시작일자")
        private Date startAt;
        @ApiModelProperty(value = "종료일자")
        private Date endAt;
        @ApiModelProperty(value = "이미지아이디")
        private Long imgId;
        @ApiModelProperty(value = "장소")
        private String location;
        @ApiModelProperty(value = "진행상황")
        private String status;
        @ApiModelProperty(value = "내용(다이어리)")
        private String content;
        @ApiModelProperty(value = "알림일자")
        private Date alarmAt;
        @ApiModelProperty(value = "유형 - 계획1,일정2")
        private int type;
        @ApiModelProperty(value = "체크리스트")
        private List<CheckListRequestDto.Response> checkList;
    }

    @ApiModel(value = "캘린더등록모델")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        @ApiModelProperty(value = "참여자아이디1")
        private Long partiId1;
        @ApiModelProperty(value = "참여자아이디2")
        private Long partiId2;
        @ApiModelProperty(value = "제목")
        private String title;
        @ApiModelProperty(value = "종일")
        private Boolean isAllDay;
        @ApiModelProperty(value = "시작일자")
        private Date startAt;
        @ApiModelProperty(value = "종료일자")
        private Date endAt;
        @ApiModelProperty(value = "이미지아이디")
        private Long imgId;
        @ApiModelProperty(value = "장소")
        private String location;
        @ApiModelProperty(value = "진행상황")
        private String status;
        @ApiModelProperty(value = "내용(다이어리)")
        private String content;
        @ApiModelProperty(value = "알림일자")
        private Date alarmAt;
        @ApiModelProperty(value = "유형 - 계획1,일정2")
        private int type;
        @ApiModelProperty(value = "체크리스트")
        private List<CheckListRequestDto.Update> checkList;
    }
}
