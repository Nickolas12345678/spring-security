package com.nickolas.productmanagement.services;

import com.nickolas.productmanagement.entities.Category;
import com.nickolas.productmanagement.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Зберегти категорію
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
}
