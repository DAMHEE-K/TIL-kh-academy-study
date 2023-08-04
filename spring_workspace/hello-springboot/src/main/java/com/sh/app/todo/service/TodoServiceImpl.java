package com.sh.app.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.app.todo.entity.Todo;
import com.sh.app.todo.repository.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepository;
	
	@Override
	public List<Todo> findAllTodo() {
		return todoRepository.findAllTodo();
	}

	@Override
	public int todoCreate(Todo todo) {
		return todoRepository.todoCreate(todo);
	}

	@Override
	public int deleteTodo(int id) {
		return todoRepository.deleteTodo(id);
	}

	@Override
	public List<Todo> findByIdTodo(String memberId) {
		return todoRepository.findAllByMemberId(memberId);
	}

	@Override
	public int updateTodo(Todo todo) {
		return todoRepository.updateTodo(todo);
	}

	@Override
	public int insertTodo(Todo todo) {
		return todoRepository.insertTodo(todo);
	}
	
}
