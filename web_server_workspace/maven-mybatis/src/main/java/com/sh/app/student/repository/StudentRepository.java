package com.sh.app.student.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sh.app.student.entity.Student;

// repository는 DAO랑 같다고 보면 됨
public interface StudentRepository {

	List<Student> findAll(SqlSession session);
	Student findById(SqlSession session, int id);
	int insertStudent(SqlSession session, Student student);
	int updateStudent(SqlSession session, Student student);
}
