package com.clothingstore.ClothingStore.service;

import com.clothingstore.ClothingStore.entity.Category;
import com.clothingstore.ClothingStore.exception.ResourceNotFoundException;
import com.clothingstore.ClothingStore.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {

        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {

        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id: " + id
                        )
                );
    }

    public Category updateCategory(Long id, Category category) {

        Category existingCategory = getCategoryById(id);

        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());

        return categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long id) {

        Category category = getCategoryById(id);

        categoryRepository.delete(category);
    }
}