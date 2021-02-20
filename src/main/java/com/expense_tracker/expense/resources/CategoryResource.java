package com.expense_tracker.expense.resources;

import com.expense_tracker.expense.services.Category;
import com.expense_tracker.expense.store.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/categories/")
public class CategoryResource {
    @Autowired
    Category category;

    @GetMapping("")
    ResponseEntity<List<CategoryEntity>> getAllCategories(HttpServletRequest request){
        int userId = (Integer) request.getAttribute("userId");
        List<CategoryEntity> categories = category.fetchAllCategories(userId);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<CategoryEntity> addCategory(HttpServletRequest request ,@RequestBody Map<String, Object> categoryMap){
        int userId = (Integer) request.getAttribute("userId");
        String title = (String) categoryMap.get("title");
        String description = (String) categoryMap.get("description");
        CategoryEntity category = this.category.addCategory(userId, title, description);
        return new ResponseEntity(category, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryEntity> getCategoryById(HttpServletRequest request, @PathVariable("id") Integer id){
        int userId = (Integer) request.getAttribute("userId");
        int categoryId = (Integer) id;
        CategoryEntity categoryEntity = category.fetchCategoryById(userId, categoryId);
        return new ResponseEntity(categoryEntity, HttpStatus.OK);
    }
}
