package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Word;
import com.example.demo.repository.WordRepository;
import com.example.demo.service.WordService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WordServiceImpl implements WordService {
	
	private WordRepository wordRepository;
	
	@Autowired
	public WordServiceImpl(WordRepository wordRepository) {
		this.wordRepository = wordRepository;
	}
	
	@Override
	public Optional<Word> findById(Long id) {
		return wordRepository.findById(id);
	}
	
	@Override
	public List<Word> findByUserId(Long userId) {
		return wordRepository.findByUserId(userId);
	}
	
	@Override
	public void register(Word word) {
		wordRepository.save(word);
		
	}
	
	@Override
	public void update(Word word) {
		wordRepository.save(word);
	}
	
	@Override
	public void delete(Long id) {
		
		wordRepository.deleteById(id);
		
	}

}
