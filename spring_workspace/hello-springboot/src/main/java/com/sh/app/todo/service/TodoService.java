package com.sh.app.todo.service;

import java.util.List;

import com.sh.app.todo.entity.Todo;

public interface TodoService {

	List<Todo> findAllTodo();

	int todoCreate(Todo todo);

	int deleteTodo(int id);

	List<Todo> findByIdTodo(String memberId);

	int updateTodo(Todo todo);

	int insertTodo(Todo todo);

}
