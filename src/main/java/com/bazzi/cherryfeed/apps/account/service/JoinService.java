package com.bazzi.cherryfeed.apps.account.service;

import com.bazzi.cherryfeed.apps.account.domain.Account;
import com.bazzi.cherryfeed.exception.AppException;
import com.bazzi.cherryfeed.exception.ErrorCode;
import com.bazzi.cherryfeed.apps.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
public class JoinService {
    static int n = 8; // n자리 쿠폰
    static char[] chs = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private final AccountRepository accountRepository;

    //닉네임 중복체크 서비스 로직
    public String duplicationCheckNicknameService(String nickname) {
        // user name 중복 체크
        accountRepository.findByNickname(nickname)
                .ifPresent(user -> {
                    log.error(nickname + "는 이미 있습니다.");
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED, nickname + "는 이미 있습니다.");
                });
        return "닉네임 사용 가능.";
    }

    //연결코드 생성 서비스 로직
    public String createCode() {
        Random rd = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = chs[rd.nextInt(chs.length)];
            sb.append(ch);
        }
        return sb.toString();
    }

    //생성한 연결코드 얻는 로직
    public String getCreateConnectCode() {
        String randomCode = createCode();
        accountRepository.findByConnectCode(randomCode)
                .ifPresent(user -> {
                    log.error(user + "코드와 중복 됩니다.");
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED, user + "랜덤코드 오류 재요청바랍니다.");
                });
        return randomCode;
    }
}
