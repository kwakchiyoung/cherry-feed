package com.bazzi.cherryfeed.apps.account.domain;

import com.bazzi.cherryfeed.apps.common.BaseEntity;
import com.bazzi.cherryfeed.apps.couple.domain.Couple;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity //JPA에서 관리할때 JPA가 사용하는 객체를 의미할때
@AllArgsConstructor
@Getter
@NoArgsConstructor //public 또는 protected 의 기본 생성자가 필수이다. 기본 생성자를 꼭 넣어야 한다. //JPA기반 필수임 프록시 기술 쓸때 피룡함 나중에.
@Builder //해당 클래스에 해당하는 엔티티 객체를 만들 때 빌더 패턴을 이용해서 만들 수 있도록 지정해주는 어노테이션이다.
//@Table(name="user") 이렇게 하면 테이블 명을 따로 지정해준다. 없으면 클레스명으로 만들어진다.
public class Account extends BaseEntity {
    @Id //PK라는걸 알려주는
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에서 값을 증가시키는 전략 IDENTITY -MySQL auto increment
    private Long id; //유저 아이디
    private String userName;       //이름
    private String pw;             //비밀번호 varchar(30)
    private String email;          //이메일 varchar(50)
    private String nickname;       //닉네임 varchar(16)
    private String birth;          //생년월일 date
    private String socialProvider; //social_provider varchar(16)
    private String socialId;       //social_id varchar(16)
    private Boolean isTerms;        //이용약관(개인정보) boolean
    private String connectCode;    //고유연결코드 varchar(16)
    private Long imgId;            //프로필사진 varchar(16)
    private String introduce;      //소개글 varchar(16)
    private String link;           //링크 varchar(16)
    private Boolean isOpen;        //계정 비공개 여부 boolean
    private String status;         //상태 varchar(16)
    private String phone;          //휴대폰번호 varchar(20)
    private String gender;         //성별 varchar(16)
    private String role;           //권한 USER_ROLE,ADMIN_ROLE,BANNED_ROLE
    //private String withdrawalDetailId; //서비스탈퇴이력아이디
    @OneToOne
    @JoinColumn(name = "withdrawal_detail_id") //서비스탈퇴이력아이디
    private WithdrawalDetail withdrawalDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "couple_id")
    private Couple couple; //couple_id

    public void updateUserCoupleId(Couple id) {
        couple = id;
    }

    public void updateUserWithdrawal(WithdrawalDetail withdrawalDetailId, String status) {
        this.withdrawalDetailId = withdrawalDetailId;
        this.status = status;
    }

    public void updateUserInfo(String introduce, String link, Boolean isOpen, Long imgId, Boolean isTerms, String phone, String nickname) {
        this.introduce = introduce;
        this.link = link;
        this.isOpen = isOpen;
        this.imgId = imgId;
        this.isTerms = isTerms;
        this.phone = phone;
        this.nickname = nickname;
    }
}
