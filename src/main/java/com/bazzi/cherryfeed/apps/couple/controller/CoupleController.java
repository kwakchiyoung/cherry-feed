package com.bazzi.cherryfeed.apps.couple.controller;

import com.bazzi.cherryfeed.apps.couple.service.CoupleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Api(tags = "커플")
@RestController
@RequestMapping(value = "/api/v1/connection", produces = "application/json; charset=utf8")
@RequiredArgsConstructor
public class CoupleController { //토큰이 발급된 대상만 이용가능한 컨트롤러이다.
    private final CoupleService coupleService;

    @ApiOperation(value = "커플연결등록")
    @PostMapping
    public ResponseEntity<String> connecting(Authentication authentication, String connectCode) {
        return ResponseEntity.ok().body(coupleService.createCouple(Long.parseLong(authentication.getName()), connectCode));
    }

    @ApiOperation(value = "커플 해지")
    @DeleteMapping
    public ResponseEntity<String> deleteConnecting(Authentication authentication) {
        coupleService.deleteCouple(Long.parseLong(authentication.getName())); //고객아이디
        return ResponseEntity.ok().body("상태9변경완료.");
    }
}
