package com.sh.app.student.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sh.app.student.entity.Student;

public class StudentRepositoryImpl implements StudentRepository {

	@Override
	public List<Student> findAll(SqlSession session) {
		// namespace.id
		return session.selectList("student.findAll");
	}

	@Override
	public Student findById(SqlSession session, int id) {
		return session.selectOne("student.findById", id);
	}

	@Override
	public int insertStudent(SqlSession session, Student student) {
		return session.insert("student.insertStudent", student);
	}

	@Override
	public int updateStudent(SqlSession session, Student student) {
		return session.update("student.updateStudent", student);
	}
	
}
