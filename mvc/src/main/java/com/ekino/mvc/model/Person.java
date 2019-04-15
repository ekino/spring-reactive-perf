package com.ekino.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

	@Id
	private Long id;
	@Column
	private String firstname;
	@Column
	private String lastname;
	@Column
	private int age;
}
