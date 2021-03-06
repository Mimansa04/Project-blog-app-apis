package com.mimansa.project.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mimansa.project.blog.payloads.ApiResponse;
import com.mimansa.project.blog.payloads.CategoryDto;
import com.mimansa.project.blog.services.CategoryService;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService catService;
	
	// add a new category
	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		
		CategoryDto createCategory = this.catService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);		
		
	}
	
	// update an existing category
	@PutMapping("/update/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId){
		
		CategoryDto udpateCategory =  this.catService.updateCategory(categoryDto,catId);
		return new ResponseEntity<CategoryDto>(udpateCategory, HttpStatus.OK);		
		
	}
	
	//Delete a category
	@DeleteMapping("/delete/{catId}")	
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){

		this.catService.deleteCategory(catId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);		

	}
	
	// fetch details of a particular category
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){

		CategoryDto catDto = this.catService.getCategory(catId);
		return new ResponseEntity<CategoryDto>(catDto,HttpStatus.OK);
	}
	
	//List of all categories
	@GetMapping("/allCategories")
	public ResponseEntity<List<CategoryDto>> getCategories(){
		
		List<CategoryDto> catDtos =this.catService.getCategories();
		
		return ResponseEntity.ok(catDtos);
	}

	

}
