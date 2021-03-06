package com.mimansa.project.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mimansa.project.blog.entities.Category;
import com.mimansa.project.blog.entities.Post;
import com.mimansa.project.blog.entities.User;
import com.mimansa.project.blog.payloads.PostDto;

public interface PostRepo extends JpaRepository<Post, Integer>  {
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);

}
