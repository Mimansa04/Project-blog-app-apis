package com.mimansa.project.blog.services;

import java.util.List;

import com.mimansa.project.blog.entities.Post;
import com.mimansa.project.blog.payloads.PostDto;
import com.mimansa.project.blog.payloads.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	//List<PostDto> getAllPosts()
	//List<PostDto> getAllPosts(Integer pageNumber, Integer pageSize);
	
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	PostDto getPostById(Integer postId);
	
	List<PostDto>  getPostsByCategory(Integer categoryId);
	
	List<PostDto>  getPostsByUser(Integer userI);
	
	List<PostDto> searchPosts(String keyword);
	
	

}
