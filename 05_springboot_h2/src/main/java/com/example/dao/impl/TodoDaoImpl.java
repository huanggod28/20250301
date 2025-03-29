package com.example.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.dao.TodoDao;
import com.example.model.TodoDto;

@Repository
public class TodoDaoImpl implements TodoDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void add(TodoDto tododto) {
		String sql="insert into todo(title,duedate,status) values(?,  ?, ?)";
		jdbcTemplate.update(sql,tododto.getTitle(),tododto.getDueDate(),tododto.getStatus());
	}

	@Override
	public void update(TodoDto tododto) {
		String sql="update todo set title=?, duedate=?, status=? where id=?";
		jdbcTemplate.update(sql,tododto.getTitle(),tododto.getDueDate(),tododto.getStatus(),tododto.getId());
	}

	@Override
	public void del(Long id) {
		String sql="delete from todo where id=?";
		jdbcTemplate.update(sql,id);
	}

	@Override
	public List<TodoDto> findAll() {
		String sql = "SELECT * FROM TODO";
		
		RowMapper<TodoDto> rowMapper = new RowMapper<>() {

			@Override
			public TodoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				TodoDto dto = new TodoDto();
				dto.setId(rs.getLong("ID"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setDueDate(rs.getDate("DUEDATE"));
				dto.setStatus(rs.getInt("STATUS"));
				
				return dto;
			}
		};
		List<TodoDto> dtoList = jdbcTemplate.query(sql, rowMapper);
		return dtoList;
	}

	@Override
	public List<TodoDto> findByTitle(String title) {
		StringBuilder sb=new StringBuilder();
		sb.append("select ");
		sb.append("* ");
		sb.append("from todo ");
		sb.append("where title like ? ");
		RowMapper<TodoDto> rowMapper = (rs, num) -> {
	        TodoDto dto = new TodoDto();
	        dto.setId(rs.getLong("id"));
	        dto.setTitle(rs.getString("title"));
	        dto.setDueDate(rs.getDate("dueDate"));
	        dto.setStatus(rs.getInt("status"));
	        return dto;
		};
		List<TodoDto> dtoList = jdbcTemplate.query(sb.toString(), rowMapper, "%" + title + "%");
		
		return dtoList;
	}
	
	@Override
	public List<TodoDto> findByTitle2(String title, String temp) {

		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ");
		sb.append(" * ");
		sb.append(" FROM TODO ");
		sb.append(" WHERE 1 = 1 "); 	
		
		if(title != null && title.isBlank()) {
			sb.append("and title = ?");
		}
		if(temp != null && temp.isBlank()) {
			sb.append("and temp = ?");
		}
		
		RowMapper<TodoDto> rowMapper = (rs, num) -> {
	        TodoDto dto = new TodoDto();
	        dto.setId(rs.getLong("id"));
	        dto.setTitle(rs.getString("title"));
	        dto.setDueDate(rs.getDate("dueDate"));
	        dto.setStatus(rs.getInt("status"));
	        return dto;
		};
		List<TodoDto> dtoList = jdbcTemplate.query(sb.toString(), rowMapper, "%" + title + "%");
		
		return dtoList;
	}

}
