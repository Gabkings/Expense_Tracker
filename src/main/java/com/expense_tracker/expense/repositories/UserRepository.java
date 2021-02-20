package com.expense_tracker.expense.repositories;

import com.expense_tracker.expense.exceptions.EtAuthException;
import com.expense_tracker.expense.store.User;

public interface UserRepository {
    //Return generated user id
    Integer create(String firstName, String lastName, String email, String password) throws EtAuthException;

    //Return by id
    User findByEmailAndPassword(String email, String password) throws EtAuthException;
    //Count occurences of email in db
    Integer getCountByEmail(String email);
    //return user associated with certain id
    User findById(Integer userId);
}
