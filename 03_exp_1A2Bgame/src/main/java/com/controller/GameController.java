package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.ResultBean;
import com.service.Gameservice;
	
@Controller
public class GameController {
	@Autowired
	private Gameservice gameservice;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/newGame")
	public String newGame() {
		System.out.println("newGame");
		gameservice.getAnswer();
		return "index";
	}
	
	@GetMapping("/guess")
	public String guessAnswer(@RequestParam("guessNum") String guessNumber, Model model) {
		System.out.println("guess: "+guessNumber);
		gameservice.checkAnswer(guessNumber);
		List<ResultBean> historyList = gameservice.getHistory();
		model.addAttribute("history", historyList);
		return "index";
	}
	
	@GetMapping("/testSession")
	public String testSession() {
		gameservice.testSession();
		return "index";
	}
}