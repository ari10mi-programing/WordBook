package com.example.demo.service.impl;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.Word;
import com.example.demo.repository.WordRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("WordServiceImplの単体テスト")
public class WordServiceImplTest {
	
	@Mock
	private WordRepository wordRepository;
	
	@InjectMocks
	private WordServiceImpl wordServiceImpl;
	
	@Test
	@DisplayName("ユーザーに紐づく単語全件取得で0件の場合のテスト")
	public void testFindByUserIdReturnEmptyList() {
		
		//動作定義
		List<Word> list = new ArrayList<Word>();
		Long userId = (long) 1;
		when(wordRepository.findByUserId(userId)).thenReturn(list);
		
		//実行
		List<Word> actualList = wordServiceImpl.findByUserId((long) 1);
		
		//検証
		verify(wordRepository, times(1)).findByUserId((long) 1);
		assertEquals(0, actualList.size());
	}
	
	
}
