package com.firatdemir.service.ımpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.firatdemir.dto.CategoryDto;
import com.firatdemir.model.Category;
import com.firatdemir.model.Product;
import com.firatdemir.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Override
	public CategoryDto creatCategory(CategoryDto dto) {

		return null;
	}

	@Override
	public CategoryDto getCategoryByID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDto updaCategory(Long id, CategoryDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCategory(Long id) {
		// TODO Auto-generated method stub

	}

	private CategoryDto todDto(Category entity) {
		CategoryDto dto = new CategoryDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());

		if (entity.getProducts() != null) {
			dto.setProductsIds(entity.getProducts().stream().map(Product::getId).collect(Collectors.toList()));

		}
		return dto;
	}

}
