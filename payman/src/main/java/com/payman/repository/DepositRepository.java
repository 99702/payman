package com.payman.repository;

import com.payman.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    @Query(value = "select * from deposit d where d.from_fk_account=:accountId  order by d.created_at desc", nativeQuery = true)
    List<Deposit>  getAllDepositFrom(Long accountId);

}