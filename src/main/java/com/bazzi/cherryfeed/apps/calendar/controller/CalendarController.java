package com.bazzi.cherryfeed.apps.calendar.controller;

import com.bazzi.cherryfeed.apps.calendar.dto.CalendarRequestDto;
import com.bazzi.cherryfeed.apps.calendar.service.CoupleCalendarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "캘린더")
@RestController
@RequestMapping(value = "/api/v1/calender", produces = "application/json; charset=utf8")
@RequiredArgsConstructor
public class CalendarController {
    private final CoupleCalendarService coupleCalendarService;

    @ApiOperation(value = "캘린더등록")
    @PostMapping
    public ResponseEntity<String> createCalendar(Authentication authentication, @RequestBody CalendarRequestDto.Create calendarRequestDto) {
        //(고객id,dto)를 캘린더 등록서비스에 보낸 후 응답메세지를 가져온다.
        return ResponseEntity.ok().body(coupleCalendarService.createCalendar(Long.parseLong(authentication.getName()), calendarRequestDto));
    }

    @ApiOperation(value = "캘린더조회")
    @GetMapping
    public ResponseEntity<List<CalendarRequestDto.Response>> readCalendar(Authentication authentication) {
        //고객아이디를 캘린더조회 서비스에 넘긴 후 조회데이터를 가져온다.
        return ResponseEntity.ok().body(coupleCalendarService.readCalendar(Long.parseLong(authentication.getName())));
    }

    @ApiOperation(value = "캘린더수정")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCalendar(@PathVariable Long id, @RequestBody CalendarRequestDto.Update calendarUpdateRequestDto) {
        //캘린더 아이디와 dto를 캘린더수정 서비스에 보낸 후 응답메세지를 가져온다.
        return ResponseEntity.ok().body(coupleCalendarService.updateCalendar(id, calendarUpdateRequestDto));
    }

    @ApiOperation(value = "캘린더삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> updateCalendar(@PathVariable Long id) {
        //캘린더 아이디를 캘린더 삭제 서비스에 보낸 후 응답메세지를 가져온다.
        return ResponseEntity.ok().body(coupleCalendarService.deleteCalendar(id));
    }
}
