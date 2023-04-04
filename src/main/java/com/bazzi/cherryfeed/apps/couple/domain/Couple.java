package com.bazzi.cherryfeed.apps.couple.domain;

import com.bazzi.cherryfeed.apps.account.domain.Account;
import com.bazzi.cherryfeed.apps.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor //public 또는 protected 의 기본 생성자가 필수이다. 기본 생성자를 꼭 넣어야 한다. //JPA기반 필수임 프록시 기술 쓸때 피룡함 나중에.
@Builder //해당 클래스에 해당하는 엔티티 객체를 만들 때 빌더 패턴을 이용해서 만들 수 있도록 지정해주는 어노테이션이다.
public class Couple extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에서 값을 증가시키는 전략 IDENTITY -MySQL auto increment
    private Long id;             //커플아이디
    private String coupleName;   //커플이름
    private int stts;            //커플 상태 1등록 9해지

    //자신이 Member의 team에 매핑되어 있으므로 team으로 설정해준 것 입니다.
    //연관관계의 주인을 설정할 때 주인을 따로 설정하는 것이 아니고 자신이 이 연관관계의 주인이 아님을 설정해줘야 합니다.
    @OneToMany(mappedBy = "couple")  //FK가 없는쪽에 mappedBy를 써주는것이 좋다.
    private List<Account> users = new ArrayList<>();

    public void updateCoupleStts(int stts) {
        this.stts = stts;
    }

}
