package com.example.transfer.service.service;

import com.example.transfer.service.exception.BadRequestException;
import com.example.transfer.service.exception.NotFoundException;
import com.example.transfer.service.model.Balance;
import com.example.transfer.service.model.Transaction;
import com.example.transfer.service.repository.BalanceRepository;
import com.example.transfer.service.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public Balance getBalance(String accountId){
        return balanceRepository.findByAccountId(accountId).orElseThrow(()->new NotFoundException("Account with id does not exist!"));
    }

    public List<Balance> getAllBalances(){
        return balanceRepository.findAll();
    }

    public Balance createAccount(String name){
        return balanceRepository.save(new Balance(name));
    }

    @Transactional
    public Transaction credit(String accountId, long amount) {
        Balance balance = getBalance(accountId);
        long balAmt = balance.getBalance();
        balance.setBalance(balAmt+amount);
        balanceRepository.save(balance);
        return transactionRepository.save(new Transaction(accountId, amount, Transaction.Type.CREDIT));
    }

    @Transactional
    public Transaction debit(String accountId, long amount) {
        Balance balance = getBalance(accountId);
        long balAmt = balance.getBalance();
        if(amount>balAmt){
            throw new BadRequestException("Insufficient balance!");
        }
        balance.setBalance(balAmt-amount);
        balanceRepository.save(balance);
        return transactionRepository.save(new Transaction(accountId, amount, Transaction.Type.DEBIT));
    }
}
