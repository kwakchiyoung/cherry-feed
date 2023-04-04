package com.bazzi.cherryfeed.apps.anvsy.repository;

import com.bazzi.cherryfeed.apps.anvsy.domain.Anvsy;
import com.bazzi.cherryfeed.apps.couple.domain.Couple;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnvsyRepository extends JpaRepository<Anvsy, Long> {
    List<Anvsy> findByCoupleId(Couple couple);
}
