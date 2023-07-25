package com.sh.app.student.service;

import static com.sh.app.common.SqlSessionUtils.*;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sh.app.student.entity.Student;
import com.sh.app.student.repository.StudentRepository;
import com.sh.app.student.repository.StudentRepositoryImpl;

public class StudentServiceImpl implements StudentService {
	private final StudentRepository studentRepository = new StudentRepositoryImpl();
	
	@Override
	public List<Student> findAll() {
		SqlSession session = getSqlSession(); // connection 대신 session
		List<Student> students = studentRepository.findAll(session);
		session.close();
		return students;
	}
	
	@Override
	public Student findById(int id) {
		SqlSession session = getSqlSession();
		Student student = studentRepository.findById(session, id);
		session.close();
		return student;
	}
	
	
	@Override
	public int insertStudent(Student student) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			// dml 요청
			result = studentRepository.insertStudent(session, student);
			session.commit();
		} catch(Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
		return result;
	}
	
	@Override
	public int updateStudent(Student student) {
		SqlSession session = getSqlSession();
		int result = 0;
		try {
			result = studentRepository.updateStudent(session, student);
			session.commit();
		} catch(Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
		return result;
	}
}
