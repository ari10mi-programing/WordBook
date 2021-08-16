package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "word")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"quession", "answer"})
public class Word {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  
	  @Column(name="user_id", nullable = false)
	  private Long userId;
	  
	  @Column(name = "quession", length = 120, nullable = false)
	  private String quession;
	  
	  @Column(name = "answer", length = 120, nullable = false)
	  private String answer;
	  
	  @Column(name="ok_count", nullable = false)
	  private Long okCount;
	  
	  @Column(name="ng_count", nullable = false)
	  private Long ngCount;
	  
	  @Column(name="ok_rate", nullable = false)
	  private Long okRate;

}
