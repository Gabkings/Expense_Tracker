package com.expense_tracker.expense.services;

import com.expense_tracker.expense.exceptions.EtBadRequestException;
import com.expense_tracker.expense.exceptions.EtResourceNotFoundException;
import com.expense_tracker.expense.store.CategoryEntity;

import java.util.List;

public interface Category {
    List<CategoryEntity> fetchAllCategories(Integer userId);

    CategoryEntity fetchCategoryById(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

    CategoryEntity addCategory(Integer userId, String title, String description) throws EtBadRequestException;

    void updateCategory(Integer userId, Integer categoryId, CategoryEntity category) throws EtBadRequestException;

    void removeCategoryWithAllTransactions(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

}
