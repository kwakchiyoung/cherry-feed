package com.bazzi.cherryfeed.apps.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //JPA에서 관리할때 JPA가 사용하는 객체를 의미할때
@AllArgsConstructor
@Getter
@NoArgsConstructor //public 또는 protected 의 기본 생성자가 필수이다. 기본 생성자를 꼭 넣어야 한다. //JPA기반 필수임 프록시 기술 쓸때 피룡함 나중에.
@Builder //해당 클래스에 해당하는 엔티티 객체를 만들 때 빌더 패턴을 이용해서 만들 수 있도록 지정해주는 어노테이션이다.
public class SocialLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            //소셜로그인 테이블 아이디
    private Long createdById;   //유저아이디
    private boolean kakao;      //카카오 true,false
    private boolean apple;      //애플  true,false
    private boolean google;     //구글  true,false
}
