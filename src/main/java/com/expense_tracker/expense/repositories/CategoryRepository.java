package com.expense_tracker.expense.repositories;

import com.expense_tracker.expense.exceptions.EtBadRequestException;
import com.expense_tracker.expense.exceptions.EtResourceNotFoundException;
import com.expense_tracker.expense.store.CategoryEntity;

import java.util.List;


public interface CategoryRepository  {
    List<CategoryEntity> findAll(Integer userId) throws EtResourceNotFoundException;

    CategoryEntity findById(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

    Integer create(Integer userId, String title, String description) throws EtBadRequestException;

    void update(Integer userId, Integer categoryId, CategoryEntity category) throws EtBadRequestException;

    void removeById(Integer userId, Integer categoryId);
}
