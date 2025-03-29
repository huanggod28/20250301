package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.TodoDao;
import com.example.model.TodoDto;

@Service
public class TodoService {
	
	@Autowired
	private TodoDao todoDao;
	
	public void addTodo() {
		TodoDto dto = new TodoDto();
		dto.setTitle("吃飯");
		dto.setDueDate(new Date());
		dto.setStatus(0);
		todoDao.add(dto);
	}
	
	public List<TodoDto> findAll() {
		List<TodoDto> dtos = todoDao.findAll();
		return dtos;
	}
	
	public List<TodoDto> findByTitle() {
		List<TodoDto> dtos = todoDao.findByTitle("菜");
		return dtos;
	}
}
