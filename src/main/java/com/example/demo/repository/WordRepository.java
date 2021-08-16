package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
	
	@Query("SELECT a FROM Word a WHERE :userId = a.userId ")
    List<Word> findByUserId(
            @Param("userId") Long userId);

}
