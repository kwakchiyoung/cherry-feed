package com.bazzi.cherryfeed.apps.couple.repository;

import com.bazzi.cherryfeed.apps.couple.domain.Couple;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoupleRepository extends JpaRepository<Couple, Long> {
}
