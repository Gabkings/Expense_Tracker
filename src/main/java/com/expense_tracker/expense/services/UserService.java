package com.expense_tracker.expense.services;

import com.expense_tracker.expense.exceptions.EtAuthException;
import com.expense_tracker.expense.store.User;

public interface UserService {

   User validate_user(String email, String password) throws EtAuthException;

   User register_user(String firstName, String lastName, String email, String password) throws EtAuthException;
}
