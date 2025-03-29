package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.service.TodoService;

@Controller
public class TodoController {
	
	@Autowired
	private TodoService todoservice;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/add")
	public String addTodo() {
		todoservice.addTodo();
		return "index";
	} 
	
	@GetMapping("/all")
	public String findAllTodo() {
		todoservice.findAll();
		return "index";
	}
	
	@GetMapping("/title")
	public String findByTitle() {
		todoservice.findByTitle();
		return "index";
	}
}
