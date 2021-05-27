package com.example.transfer.service.repository;

import com.example.transfer.service.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Balance> findByAccountId(String accountId);
}
