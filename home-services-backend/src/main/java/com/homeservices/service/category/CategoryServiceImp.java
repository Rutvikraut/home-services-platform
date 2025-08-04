package com.homeservices.service.category;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.homeservices.custom_exceptions.ApiException;
import com.homeservices.custom_exceptions.ResourceNotFoundException;
import com.homeservices.dao.CategoryRepository;
import com.homeservices.dto.request.CategoryRequestDto;
import com.homeservices.dto.response.ApiResponse;
import com.homeservices.dto.response.CategoryResponseDTO;
import com.homeservices.entities.Category;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CategoryServiceImp implements CategoryService{
	private final CategoryRepository categoryRepository;
	private final ModelMapper modelMapper;
	
	@Override
	public List<CategoryResponseDTO> getAllCategories() {
		
		return categoryRepository
				.findAll()
				.stream()
				.map(category -> 
				      modelMapper.map(category, CategoryResponseDTO.class))
				.toList();
	}
	
	
	@Override
	public ApiResponse addNewCategory(CategoryRequestDto dto) {
		if(categoryRepository.existsByName(dto.getName()))
			throw new ApiException("Duplicate Category name ");
		Category category = modelMapper.map(dto, Category.class);
		Category saved = categoryRepository.save(category);
		return new ApiResponse("Category Added " + saved.getId());
	}
	
	
	@Override
	public CategoryResponseDTO getCategoryById(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with ID = " + categoryId));
		return modelMapper.map(category, CategoryResponseDTO.class);
	}
	
	
	@Override
	public ApiResponse updateCategory(Long categoryId, CategoryRequestDto dto) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with ID " + categoryId));
		modelMapper.map(dto,category);
		return new ApiResponse("Category updated successfully");
	}
	
	
	@Override
	public ApiResponse deleteCatgory(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
			.orElseThrow(() -> new ResourceNotFoundException("Category not found by id " + categoryId));
			
		categoryRepository.delete(category);
		return new ApiResponse("Category deleted by Id");
	}
}
