package com.example.demo.controller;



import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.auth.SimpleLoginUser;
import com.example.demo.entity.Word;
import com.example.demo.service.WordService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(path = "/word")
@Slf4j
public class WordController {
	
	private WordService wordService;
	
	@Autowired
	public WordController(WordService wordService) {
		this.wordService = wordService;
	}
	
	@GetMapping
	public String index(
			@ModelAttribute WordForm wordForm,
			Model model,
			@AuthenticationPrincipal SimpleLoginUser loginUser
			) {
		
		//ログインユーザーに紐づくWordを取得。
		List<Word> wordList = wordService.findByUserId(loginUser.getUser().getId());
		model.addAttribute("list", wordList);
		//一覧画面に戻す
		return "word/index";
	}
	
	@GetMapping(path = "/form")
	public String form(
			@ModelAttribute WordForm wordForm,
			Model model
			) {
		//詳細画面に移動する。
	    return "word/form";
	}
	
	@PostMapping(path = "/register")
	public String register(
			@ModelAttribute WordForm wordForm,
			Model model,
			RedirectAttributes redirectAttributes,
			@AuthenticationPrincipal SimpleLoginUser loginUser
			) {
		
		if(Objects.isNull(wordForm.getId())) {
			//新規登録の場合
			Word word = makeWord(wordForm, loginUser.getUser().getId());
			wordService.register(word);
			redirectAttributes.addFlashAttribute("successMessage", "単語の登録が完了しました");
		}else {
			//更新登録の場合
			Optional<Word> wordOpt = wordService.findById(wordForm.getId());
			Word word = new Word();
			if(wordOpt.isPresent()) {
				word = wordOpt.get();
				word.setId(wordForm.getId());
				word.setQuession(wordForm.getQuession());
				word.setAnswer(wordForm.getAnswer());
				log.info("Wordを更新します。id:"+ word.getId());
				wordService.update(word);
				redirectAttributes.addFlashAttribute("successMessage", "単語の更新が完了しました");
			}else {
				log.warn("更新対象のWordが存在しません。id:"+ word.getId());
			}
		}
	    return "redirect:/word/";
	}
	
	@PostMapping(path = "/delete")
	public String delete(
			@ModelAttribute WordForm wordForm,
			Model model,RedirectAttributes redirectAttributes,
			@AuthenticationPrincipal SimpleLoginUser loginUser) {
		
		log.info("Wordを削除します。id:"+ wordForm.getId());
		wordService.delete(wordForm.getId());
	    redirectAttributes.addFlashAttribute("successMessage", "単語の削除が完了しました");
		return "redirect:/word/";
	}
	
	private Word makeWord(WordForm wordForm, long userId) {
		Word word = new Word();
		word.setUserId(userId);
		word.setQuession(wordForm.getQuession());
		word.setAnswer(wordForm.getAnswer());
		//新規登録用にデフォルト値を入れる。
		word.setOkCount((long) 0);
		word.setNgCount((long) 0);
		word.setOkRate((long) 0);
		return word;
	}

}
