package com.mimansa.project.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mimansa.project.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
