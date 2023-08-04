package com.sh.app.todo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sh.app.todo.entity.Todo;

@Mapper
public interface TodoRepository {

	@Select("select * from todo")
	public List<Todo> findAllTodo();

	@Insert("insert into todo values(seq_todo_id.nextval, #{memberId}, #{todo}, default, null)")
	public int todoCreate(Todo todo);

	@Delete("delete from todo where id = #{id}")
	public int deleteTodo(int id);
	
	@Select("select * from todo where member_id = #{memberId} order by completed_at nulls first, id")
	public List<Todo> findAllByMemberId(String memberId);

	@Update("update todo set completed_at = #{completedAt, jdbcType=DATE} where id = #{id} and member_id = #{memberId}")
	int updateTodo(Todo todo);

	@Insert("insert into todo (id, member_id, todo) values (seq_todo_id.nextval, #{memberId}, #{todo})")
	public int insertTodo(Todo todo);
	
}
