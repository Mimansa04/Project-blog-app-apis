package com.mimansa.project.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mimansa.project.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer
>  {

}
