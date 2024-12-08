package com.nickolas.productmanagement.contollers;

import com.nickolas.productmanagement.entities.Category;
import com.nickolas.productmanagement.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories";
    }


    @GetMapping("/new")
    public String showCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "categoryForm";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute Category category) {
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }
}
