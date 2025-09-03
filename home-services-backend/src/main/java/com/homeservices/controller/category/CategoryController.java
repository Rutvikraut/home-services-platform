package com.homeservices.controller.category;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeservices.dto.request.CategoryRequestDto;
import com.homeservices.dto.request.ProvidedServiceRequestDto;
import com.homeservices.dto.response.CategoryResponseDTO;
import com.homeservices.entities.ProvidedService;
import com.homeservices.service.category.CategoryService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
	private final CategoryService categoryService; 
	
	@GetMapping
	public ResponseEntity<?> getAllCategories() {
		return ResponseEntity.ok(categoryService.getAllCategories());
	}
	
	@PostMapping
    public ResponseEntity<?> addNewCategory(@RequestBody CategoryRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addNewCategory(dto));
    }

	@GetMapping("/{categoryId}")
	public ResponseEntity<?> getCategoryById(@PathVariable Long categoryId){
		return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
	}
	
	@GetMapping("/{categoryId}/services")
	public ResponseEntity<List<ProvidedService>> getServicesByCategoryId(@PathVariable Long categoryId){
		return ResponseEntity.ok(categoryService.getServicesByCategoryId(categoryId));
	}
		 
	@PutMapping("/{categoryId}")
	public ResponseEntity<?> updateCategory(@PathVariable Long categoryId,@RequestBody CategoryRequestDto dto){
		return ResponseEntity.ok(categoryService.updateCategory(categoryId,dto));
	}
	

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId){
		return ResponseEntity.ok(categoryService.deleteCatgory(categoryId));
	}
	
	@GetMapping("/{categoryId}/partners")
	public ResponseEntity<?> getPartnersByCategoryId(@PathVariable Long categoryId){
		System.out.println(categoryId);
		return ResponseEntity.ok(categoryService.getPartnersByCategoryId(categoryId));
	}
}
