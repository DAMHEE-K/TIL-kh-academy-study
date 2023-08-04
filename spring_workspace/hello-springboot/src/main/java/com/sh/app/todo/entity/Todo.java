package com.sh.app.todo.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Todo {
	int id;
	String memberId;
	String todo;
	LocalDateTime createdAt;
	LocalDateTime completedAt;
}
