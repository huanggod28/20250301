package com.example.dao;

import java.util.List;

import com.example.model.TodoDto;

public interface TodoDao {

	//create
	void add(TodoDto tododto);
	//read
	public List<TodoDto> findAll();
	public List<TodoDto> findByTitle(String title);
	public List<TodoDto> findByTitle2(String title, String temp);
	//update
	void update(TodoDto tododto);
	//delete
	void del(Long id);
}
