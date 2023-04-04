package com.bazzi.cherryfeed.apps.account.service;

import com.bazzi.cherryfeed.apps.account.domain.Account;
import com.bazzi.cherryfeed.apps.account.domain.WithdrawalDetail;
import com.bazzi.cherryfeed.apps.account.dto.*;
import com.bazzi.cherryfeed.exception.AppException;
import com.bazzi.cherryfeed.exception.ErrorCode;
import com.bazzi.cherryfeed.apps.account.repository.AccountRepository;
import com.bazzi.cherryfeed.apps.account.repository.WithdrawalDetailRepository;
import com.bazzi.cherryfeed.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final AccountRepository accountRepository;
    private final WithdrawalDetailRepository withdrawalDetailRepository;

    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.token.secret}") //application.yml에
    private String key;
    private Long expireTimeMs = 1000 * 60 * 60l; //1시간

    public String join(AccountDto.Create userJoinRequestDto) {
        String email = userJoinRequestDto.getEmail();    //로그인 이메일
        String password = userJoinRequestDto.getPassword(); //비밀번호
        // user name 중복 체크
        accountRepository.findByEmail(email)
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED, email + "는 이미 있습니다.");
                });
        // 저장
        Account user = Account.builder()
                .email(email)
                .pw(encoder.encode(password)) //인코딩
                .userName(userJoinRequestDto.getUserName()) //회원이름
                .nickname(userJoinRequestDto.getNickname()) //닉네임
                .birth(userJoinRequestDto.getBirth())       //생년월일
                .isTerms(userJoinRequestDto.getIsTerms())   //이용약관
                .phone(userJoinRequestDto.getPhone())       //휴대폰번호
                .gender(userJoinRequestDto.getGender())     //성별
                .connectCode(userJoinRequestDto.getConnectCode())
                .imgId(userJoinRequestDto.getImgId())
                .status("1")
                .build();
        accountRepository.save(user);
        return "회원가입이 성공하였습니다.";
    }

    public String login(String email, String password) {
        // userName없음
        Account selectedUser = accountRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, email + " 이 없습니다."));

        // password틀림
        log.info("selectedPw:{} pw:{}", selectedUser.getPw(), password);
        if (!encoder.matches(password, selectedUser.getPw())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘못 입력 헀습니다.");
        }

        // 맞았을경우
        // 앞에서 Exception안나면 토큰 발행
        return JwtTokenUtil.createToken(selectedUser.getId(), key, expireTimeMs); //발행하는 토큰
    }

    @Transactional
    public String withdrawal(Long id, AccountDto.Delete withdrawalRequestDto) {
        Account user = accountRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        WithdrawalDetail withdrawalDetail = WithdrawalDetail.builder()
                .status(withdrawalRequestDto.getStatus())
                .content(withdrawalRequestDto.getContent())
                .createdById(user)
                .build();
        withdrawalDetailRepository.save(withdrawalDetail);
        user.updateUserWithdrawal(withdrawalDetail, "9");
        return "회원탈퇴 완료.";
    }

    public AccountDto.Response readUser(Long id) {
        Account user = accountRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        AccountDto.Response userInfoResponseDto = AccountDto.Response.builder()
                .id(user.getId())
                .isOpen(user.getIsOpen())
                .introduce(user.getIntroduce())
                .link(user.getLink())
                .coupleId(user.getCouple().getId())
                .birth(user.getBirth())
                .connectCode(user.getConnectCode())
                .email(user.getEmail())
                .gender(user.getGender())
                .imgId(user.getImgId())
                .isTerms(user.getIsTerms())
                .link(user.getLink())
                .phone(user.getPhone())
                .build();
        return userInfoResponseDto;
    }

    @Transactional
    public String userUpdate(Long id, AccountDto.Update userRequestUpdateDto) {
        Account user = accountRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        user.updateUserInfo(
                userRequestUpdateDto.getIntroduce(),
                userRequestUpdateDto.getLink(),
                userRequestUpdateDto.getIsOpen(),
                userRequestUpdateDto.getImgId(),
                userRequestUpdateDto.getIsTerms(),
                userRequestUpdateDto.getPhone(),
                userRequestUpdateDto.getNickname()
        );
        return "업데이트 완료.";
    }
}
