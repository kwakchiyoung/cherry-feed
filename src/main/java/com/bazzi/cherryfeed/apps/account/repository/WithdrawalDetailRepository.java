package com.bazzi.cherryfeed.apps.account.repository;

import com.bazzi.cherryfeed.apps.account.domain.WithdrawalDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalDetailRepository extends JpaRepository<WithdrawalDetail, Long> {
}
