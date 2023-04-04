package com.bazzi.cherryfeed.apps.account.controller;

import com.bazzi.cherryfeed.apps.account.dto.*;
import com.bazzi.cherryfeed.apps.account.service.JoinService;
import com.bazzi.cherryfeed.apps.account.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Api(tags = "회원가입/로그인")
@RestController
@RequestMapping(value = "/api/v1/users", produces = "application/json; charset=utf8") //자꾸 한글깨지길래 utf-8변환함
@RequiredArgsConstructor
public class AccountController {
    private final UserService userService;
    private final JoinService joinService;

    @ApiOperation(value = "회원가입", notes = "회원가입을 진행한다. 회원가입이 성공하면 200응답과 함께 회원가입이 성공하였습니다.가 반환됨.")
    @PostMapping("/join") // URI=  api/v1/users/join - post요청시 회원가입 진행
    public ResponseEntity<String> join(@RequestBody AccountDto.Create userJoinRequestDto) {
        return ResponseEntity.ok().body(userService.join(userJoinRequestDto));
    }

    @ApiOperation(value = "로그인", notes = "로그인을 진행한다. 회원가입이 성공하면 200응답과 함께 바디에 토큰이 발급됨")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AccountDto.Login dto) {
        return ResponseEntity.ok().body(userService.login(dto.getEmail(), dto.getPw())); //로그인 완료 후 반환되는 토큰
    }

    @ApiOperation(value = "닉네임중복조회", notes = "회원가입절차에서 닉네임조회기능. 사용가능하면 200응답과 함께 닉네임 사용 가능.가 반환됨.")
    @GetMapping("/duplicationcheck/nickname")
    public ResponseEntity<String> duplicationcheckNickname(
            @RequestParam(name = "nickname", required = true) String nickname) {
        return ResponseEntity.ok().body(joinService.duplicationCheckNicknameService(nickname));
    }

    @ApiOperation(value = "연결코드생성", notes = "랜덤으로 8자리가 생성된다 저장기능X. 응답으로 200과 코드가 전달됨.")
    @GetMapping("/create/connectcode")
    public ResponseEntity<String> createConnectCode() {
        return ResponseEntity.ok().body(joinService.getCreateConnectCode()); //생성된 랜덤 코드 반환
    }


    @ApiOperation(value = "회원탈퇴", notes = "회원탈퇴. 탈퇴시 탈퇴이력이 남고, 회원테이블의 상태가 9로 변경된다.")
    @PostMapping("/withdrawal") // URI=  api/v1/users/join - post요청시 회원가입 진행
    public ResponseEntity<String> withdrawal(
            Authentication authentication, @RequestBody AccountDto.Delete withdrawalRequestDto) {
        // (토큰에서 꺼낸id,Dto) 서비스에 보내 응닶메세지를 받아온다.
        return ResponseEntity.ok().body(userService.withdrawal(Long.parseLong(authentication.getName()), withdrawalRequestDto));
    }

    @ApiOperation(value = "회원정보조회")
    @GetMapping
    public ResponseEntity<AccountDto.Response> readUser(Authentication authentication) {
        //토큰에서 꺼낸 아이디를 서비스에 보내 응답데이터를 받아온다.
        return ResponseEntity.ok().body(userService.readUser(Long.parseLong(authentication.getName())));
    }

    @ApiOperation(value = "회원정보수정")
    @PutMapping
    public ResponseEntity<String> updateUser(Authentication authentication, @RequestBody AccountDto.Update userRequestUpdateDto) {
        //(토큰에서 꺼낸 아이디,dto)를 서비스로 보내 응답메세지를 받아온다.
        return ResponseEntity.ok().body(userService.userUpdate(Long.parseLong(authentication.getName()), userRequestUpdateDto));
    }
}