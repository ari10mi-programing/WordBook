package com.example.demo.controller;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordForm {
	private Long id;
	
	@NotNull
	private String quession;
	
	@NotNull
	private String answer;

}
