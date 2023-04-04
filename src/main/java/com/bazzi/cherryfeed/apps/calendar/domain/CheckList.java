package com.bazzi.cherryfeed.apps.calendar.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor //public 또는 protected 의 기본 생성자가 필수이다. 기본 생성자를 꼭 넣어야 한다. //JPA기반 필수임 프록시 기술 쓸때 피룡함 나중에.
@Builder //해당 클래스에 해당하는 엔티티 객체를 만들 때 빌더 패턴을 이용해서 만들 수 있도록 지정해주는 어노테이션이다.
public class CheckList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에서 값을 증가시키는 전략 IDENTITY -MySQL auto increment
    private Long id;             //체크리스트 아이디
    //private Long calendarId;     //일정고유ID
    private String content;      //내용(체크리스트)
    private Boolean isFinish;    //체크유무 true,false
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id")  //일정고유ID
    private CoupleCalendar calendar;

    public void updateCheckList(String content, Boolean isFinish) {
        this.content = content;
        this.isFinish = isFinish;
    }
}
