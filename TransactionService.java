package com.ii.app.services.interfaces;

import com.ii.app.dto.in.TransactionIn;
import com.ii.app.dto.out.TransactionOut;
import com.ii.app.models.Transaction;

import java.util.List;

public interface TransactionService {
    TransactionOut create(TransactionIn transactionDTO,String email);
    
    // WithdrawOut withdraw (WithdrawIn withdrawIn);

    List<TransactionOut> findAll();

    List<TransactionOut> findAllByBankAccountId(Long bankAccountId);
}