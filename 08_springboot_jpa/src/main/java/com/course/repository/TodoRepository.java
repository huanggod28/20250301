package com.course.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.course.entity.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
	
	// SQL : select * from todo where title = ?
		public List<TodoEntity> findByTitle(String title);
		
		List<TodoEntity> findByDueDate(Date dueDate);
		
		// SQL : select * from todo where title = ? and status = ?
		List<TodoEntity> findByTitleAndStatus(String title, Integer status);
		
		// SQL語句：select * from todo where due_date > ?
		List<TodoEntity> findByDueDateGreaterThan(Date dueDate);
		
		// SQL語句：select * from todo where title like ?
		List<TodoEntity> findByTitleLike(String keyword);
		
		// SQL語句：select * from todo where title like '關鍵字%'
		List<TodoEntity> findByTitleStartingWith(String title);

		// SQL語句：select * from todo where title like '%關鍵字'
		List<TodoEntity> findByTitleEndingWith(String title);
		
		// SQL語句：select * from todo where due_date between ? and ?
		List<TodoEntity> findByDueDateBetween(Date startDate, Date endDate);
		
		// SQL語句：select * from todo where id in (?, ?, ?);
		List<TodoEntity> findByIdIn(List<Long> ids);
		
		// SQL語句：select * from todo order by title;
		List<TodoEntity> findAllByOrderByTitleDesc();
		
		// SQL語句：select count(*) from todo where status = ?;
		Integer countByStatus(Integer status);
		
		//jpql
//		@Query(value = "select t from TodoEntity t where t.title = ?1 and t.status = ?2")
//		@Query(value = "select t from TodoEntity t where t.title = :title and t.status = :status")
		@Query(nativeQuery = true , value = "select * from todo t where t.status = :status and t.title = :title")
		List<TodoEntity> findByCondition(String title, Integer status);
}
