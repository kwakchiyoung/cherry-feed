package com.bazzi.cherryfeed.apps.anvsy.service;

import com.bazzi.cherryfeed.apps.anvsy.domain.Anvsy;
import com.bazzi.cherryfeed.apps.couple.domain.Couple;
import com.bazzi.cherryfeed.apps.account.domain.Account;
import com.bazzi.cherryfeed.apps.anvsy.dto.AnvsyRequestDto;
import com.bazzi.cherryfeed.apps.anvsy.dto.AnvsyResponseDto;
import com.bazzi.cherryfeed.apps.anvsy.repository.AnvsyRepository;
import com.bazzi.cherryfeed.apps.account.repository.AccountRepository;
import com.bazzi.cherryfeed.exception.AppException;
import com.bazzi.cherryfeed.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnvsyService {
    private final AccountRepository accountRepository;
    private final AnvsyRepository anvsyRepository;

    public String createAnvsy(Long id, AnvsyRequestDto anvsyRequestDto) {
        Account fidedUser = accountRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));   //user
        Couple coupleId = fidedUser.getCouple();                      //couple_id(PK)

        Anvsy anvsy = Anvsy.builder()
                .anvsyNm(anvsyRequestDto.getAnvsyNm())
                .anvsyAt(anvsyRequestDto.getAnvsyAt())
                .status(anvsyRequestDto.getStatus())
                .coupleId(coupleId)
                .imgId(anvsyRequestDto.getImgId())
                .build();
        anvsyRepository.save(anvsy);
        return "등록완료.";
    }

    //기념일 수정 서비스로직
    @Transactional
    public String updateAnvsy(Long id, AnvsyRequestDto anvsyRequestDto) {

        String anvsyNm = anvsyRequestDto.getAnvsyNm();   //일정이름
        int status = anvsyRequestDto.getStatus();        //일정상태
        Long imgId = anvsyRequestDto.getImgId();         //이미지아이디
        Date anvsyAt = anvsyRequestDto.getAnvsyAt();     //일정시간

        Anvsy anvsy = anvsyRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        anvsy.updateAnvsy(anvsyNm, imgId, status, anvsyAt);
        return "기념일 수정완료.";
    }

    @Transactional
    public String deleteAnvsy(Long id) {
        anvsyRepository.deleteById(id);
        return "기념일 삭제완료.";
    }

    public List<AnvsyResponseDto> readAnvsy(Long id) {
        Account findedUser = accountRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));//user
        Couple coupleId = findedUser.getCouple();                        //couple_id(PK)

        List<Anvsy> anvsies = anvsyRepository.findByCoupleId(coupleId); // 조회한 일정들을 list에 담는다.

        List<AnvsyResponseDto> anvsyResponseDtoList = new ArrayList<>();// 응답DTO를 담을 리스트를 생성한다.

        for (Anvsy anvsy : anvsies) {
            AnvsyResponseDto dto = AnvsyResponseDto.builder()           //생성한 DTO리스트에 조회한 일정들을 담는다.
                    .id(anvsy.getId())
                    .anvsyNm(anvsy.getAnvsyNm())
                    .anvsyAt(anvsy.getAnvsyAt())
                    .status(anvsy.getStatus())
                    .imgId(anvsy.getImgId())
                    .build();
            anvsyResponseDtoList.add(dto);
        }
        return anvsyResponseDtoList; //조회한 일정을 담은 응답 DTO를 반환한다.
    }
}
