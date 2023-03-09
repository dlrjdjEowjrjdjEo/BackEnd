package com.medic.jpa;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "recommend_one")
	private String recommendOne;
		
	@Column(name = "recommend_three")
	private String recommendThree;
	
	@Column(name = "recommend_two")
	private String recommendTwo;
}

