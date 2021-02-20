package com.expense_tracker.expense.services;

import com.expense_tracker.expense.exceptions.EtAuthException;
import com.expense_tracker.expense.repositories.UserRepository;
import com.expense_tracker.expense.store.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public User validate_user(String email, String password) throws EtAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email != null)
            email.toLowerCase();
            if(!pattern.matcher(email).matches())
                throw new EtAuthException("Please enter a valid email");
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User register_user(String firstName, String lastName, String email, String password) throws EtAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email != null)
            email.toLowerCase();
            if(!pattern.matcher(email).matches())
                throw new EtAuthException("Please enter a valid email");

        Integer count = userRepository.getCountByEmail(email);
        if(count > 0)
            throw new EtAuthException("Email already in use");

        Integer user_id = userRepository.create(firstName, lastName, email, password);
        return  userRepository.findById(user_id);
    }
}
