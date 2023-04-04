package com.bazzi.cherryfeed.apps.anvsy.controller;

import com.bazzi.cherryfeed.apps.anvsy.dto.AnvsyRequestDto;
import com.bazzi.cherryfeed.apps.anvsy.dto.AnvsyResponseDto;
import com.bazzi.cherryfeed.apps.anvsy.service.AnvsyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "기념일")
@RestController
@RequestMapping(value = "/api/v1/anvsy", produces = "application/json; charset=utf8")
@RequiredArgsConstructor
public class AnvsyController {

    private final AnvsyService anvsyService;

    @ApiOperation(value = "기념일조회")
    @GetMapping
    public ResponseEntity<List<AnvsyResponseDto>> readAnvsy(Authentication authentication) {
        //토큰에서 꺼낸 아이디를 서비스에 보내 기념일 데이터를 받아온다.
        return ResponseEntity.ok().body(anvsyService.readAnvsy(Long.parseLong(authentication.getName())));
    }

    @ApiOperation(value = "기념일등록")
    @PostMapping
    public ResponseEntity<String> createAnvsy(Authentication authentication, @RequestBody AnvsyRequestDto anvsyRequestDto) {
        //(토큰에서 꺼낸 아이디,dto)를 서비스로 보내 응답메세지를 받아온다.
        return ResponseEntity.ok().body(anvsyService.createAnvsy(Long.parseLong(authentication.getName()), anvsyRequestDto));
    }

    @ApiOperation(value = "기념일수정")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAnvsy(@PathVariable Long id, @RequestBody AnvsyRequestDto anvsyRequestDto) {
        //기념일 수정 후 응답메세지를 받아온다.
        return ResponseEntity.ok().body(anvsyService.updateAnvsy(id, anvsyRequestDto));
    }

    @ApiOperation(value = "기념일삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnvsy(@PathVariable Long id) {
        //기념일 삭제 후 응답메세지를 받아온다.
        return ResponseEntity.ok().body(anvsyService.deleteAnvsy(id));
    }
}
