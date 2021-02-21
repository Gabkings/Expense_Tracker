package com.expense_tracker.expense.repositories;

import com.expense_tracker.expense.exceptions.EtBadRequestException;
import com.expense_tracker.expense.exceptions.EtResourceNotFoundException;
import com.expense_tracker.expense.store.Transaction;

import java.util.List;

public interface TransactionRepo {
    List<Transaction> findAll(Integer userId, Integer categoryId);

    Transaction findById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;

    Integer create(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate) throws EtBadRequestException;

    void update(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction) throws EtBadRequestException;

    void removeById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;
}
