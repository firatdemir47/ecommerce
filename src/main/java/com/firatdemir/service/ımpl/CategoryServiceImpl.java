package com.firatdemir.service.ımpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.firatdemir.dto.CategoryDto;
import com.firatdemir.model.Category;
import com.firatdemir.model.Product;
import com.firatdemir.repository.CategoryRepository;
import com.firatdemir.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public CategoryDto creatCategory(CategoryDto dto) {

		Category category = toEntity(dto);
		Category saved = categoryRepository.save(category);
		return todDto(saved);
	}

	@Override
	public CategoryDto getCategoryByID(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Kategori Bulunamadı,id" + id));
		return todDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategories() {

		return categoryRepository.findAll().stream().map(this::todDto).collect(Collectors.toList());
	}

	@Override
	public CategoryDto updaCategory(Long id, CategoryDto dto) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Kategori bulunamadı, id: " + id));

		category.setName(dto.getName());
		Category updated = categoryRepository.save(category);

		return todDto(updated);
	}

	@Override
	public void deleteCategory(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Kategori bulunamadı, id: " + id));
		categoryRepository.delete(category);

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

	private Category toEntity(CategoryDto dto) {
		Category category = new Category();
		category.setId(dto.getId());
		category.setName(dto.getName());

		return category;
	}

}
