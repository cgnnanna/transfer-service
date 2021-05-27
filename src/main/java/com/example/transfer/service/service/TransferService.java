package com.example.transfer.service.service;

import com.example.transfer.service.dto.TransferDto;
import com.example.transfer.service.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransferService {

    @Autowired
    private BalanceService balanceService;

    @Transactional
    public Transaction transfer(TransferDto transferDto) {
        Transaction debit = balanceService.debit(transferDto.getFromAccount(), transferDto.getAmount());
        Transaction credit = balanceService.credit(transferDto.getToAccount(), transferDto.getAmount());
        return debit;
    }
}
