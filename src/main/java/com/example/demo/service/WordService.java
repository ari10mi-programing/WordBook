package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Word;

public interface WordService {
	Optional<Word> findById(Long id);
	List<Word> findByUserId(Long userId);
	void register(Word word);
	void update(Word word);
	void delete(Long id);
}
