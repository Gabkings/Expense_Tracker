package com.expense_tracker.expense.services;

import com.expense_tracker.expense.exceptions.EtBadRequestException;
import com.expense_tracker.expense.exceptions.EtResourceNotFoundException;
import com.expense_tracker.expense.repositories.CategoryRepository;
import com.expense_tracker.expense.store.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryImpl implements Category{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> fetchAllCategories(Integer userId) {
        return categoryRepository.findAll(userId);
    }

    @Override
    public CategoryEntity fetchCategoryById(Integer userId, Integer categoryId) throws EtResourceNotFoundException {
        return categoryRepository.findById(userId, categoryId);

    }

    @Override
    public CategoryEntity addCategory(Integer userId, String title, String description) throws EtBadRequestException {
        int categoryId = categoryRepository.create(userId,title, description);
        return categoryRepository.findById(userId,categoryId);
    }

    @Override
    public void updateCategory(Integer userId, Integer categoryId, CategoryEntity category) throws EtBadRequestException {
        categoryRepository.update(userId, categoryId, category);
    }

    @Override
    public void removeCategoryWithAllTransactions(Integer userId, Integer categoryId) throws EtResourceNotFoundException {
        categoryRepository.removeById(userId, categoryId);
    }
}
