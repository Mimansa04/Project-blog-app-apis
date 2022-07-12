package com.mimansa.project.blog.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="user_name", nullable=false, length=100)
	private String name;
	private String email;
	private String password;
	private String about;
	
	@OneToMany(mappedBy ="user",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Post> post = new ArrayList<>();

}
