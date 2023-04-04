package com.bazzi.cherryfeed.apps.calendar.repository;

import com.bazzi.cherryfeed.apps.couple.domain.Couple;
import com.bazzi.cherryfeed.apps.calendar.domain.CoupleCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoupleCalendarRepository extends JpaRepository<CoupleCalendar, Long> {
    List<CoupleCalendar> findByCoupleId(Couple couple);
}
