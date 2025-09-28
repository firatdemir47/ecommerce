package com.firatdemir.service;

import java.util.List;

import com.firatdemir.dto.CategoryDto;

public interface CategoryService {

	CategoryDto creatCategory(CategoryDto dto);

	CategoryDto getCategoryByID(Long id);

	List<CategoryDto> getAllCategories();

	CategoryDto updaCategory(Long id, CategoryDto dto);

	void deleteCategory(Long id);
}
