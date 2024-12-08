package com.nickolas.productmanagement.contollers;

import com.nickolas.productmanagement.entities.Product;
import com.nickolas.productmanagement.services.CategoryService;
import com.nickolas.productmanagement.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public String listProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(required = false) Long categoryId, @RequestParam(defaultValue = "asc") String priceOrder, @RequestParam(required = false) Double minPrice, @RequestParam(required = false) Double maxPrice, Model model) {
        if (page < 0) page = 0;
        if (size <= 0) size = 5;
        Page<Product> productPage = productService.getFilteredProducts(page, size, categoryId, priceOrder, minPrice, maxPrice);
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("priceOrder", priceOrder);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);

        return "products";
    }


    @GetMapping("/category/{categoryId}")
    public String listProductsByCategory(@PathVariable Long categoryId, @RequestParam(defaultValue = "0") int page, Model model) {
        List<Product> products = productService.getProductsByCategory(categoryId);
        model.addAttribute("products", products);
        return "products";
    }


    @GetMapping("/new")
    public String showProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "productForm";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }
}
