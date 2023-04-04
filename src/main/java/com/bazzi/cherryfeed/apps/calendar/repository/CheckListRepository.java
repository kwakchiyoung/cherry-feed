package com.bazzi.cherryfeed.apps.calendar.repository;

import com.bazzi.cherryfeed.apps.calendar.domain.CheckList;
import com.bazzi.cherryfeed.apps.calendar.domain.CoupleCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckListRepository extends JpaRepository<CheckList, Long> {
    List<CheckList> findByCalendarId(CoupleCalendar calendar);
}
