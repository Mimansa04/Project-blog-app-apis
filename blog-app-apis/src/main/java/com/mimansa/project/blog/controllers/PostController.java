package com.mimansa.project.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mimansa.project.blog.config.AppConstants;
import com.mimansa.project.blog.entities.Post;
import com.mimansa.project.blog.payloads.ApiResponse;
import com.mimansa.project.blog.payloads.PostDto;
import com.mimansa.project.blog.payloads.PostResponse;
import com.mimansa.project.blog.services.FileService;
import com.mimansa.project.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	//add a post
	@PostMapping("/users/{userId}/category/{categoryId}/posts/create")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			@PathVariable("userId") Integer userId,
			@PathVariable("categoryId") Integer categoryId){

		PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	}

	// get all posts added by a particular user
	@GetMapping("/users/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){

		List<PostDto> postDtos = this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);

	}
	

	// Get all posts under a particular category
	@GetMapping("/categories/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){

		List<PostDto> postDtos = this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);

	}

	/**	@GetMapping("/posts/all")
	public ResponseEntity<List<PostDto>> getAllPosts(
			@RequestParam(value="pageNumber",defaultValue="0", required = false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="10", required = false) Integer pageSize
			){

		List<PostDto>  postDtos = this.postService.getAllPosts(pageNumber,pageSize);
		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);

	}
	 **/

	// Get list of all posts, we can sort the results, paginated.
	@GetMapping("/posts/all")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue=AppConstants.SORT_DIR, required = false) String sortDir
			){

		PostResponse postResponse = this.postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

	}

	// fetch a particular post
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){

		PostDto postDto = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);

	}

	//Delete a particular post
	@DeleteMapping("/posts/delete/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){

		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully", true),HttpStatus.OK); 

	}

	// Update a particular post
	@PutMapping("/posts/update/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){

		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);

	}
	
	// Fetch list of all posts as per a search keyword
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(
			@PathVariable String keywords
			){
		List<PostDto> postDtos = this.postService.searchPosts(keywords);
		
		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
	}
	
	// Upload image
	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException{
		
		PostDto postDto = this.postService.getPostById(postId);
		String fileName= this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}
	
	
	//Service the image
	@GetMapping(value="/posts/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response) throws IOException{
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}


}
