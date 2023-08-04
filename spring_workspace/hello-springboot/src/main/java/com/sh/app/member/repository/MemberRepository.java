package com.sh.app.member.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sh.app.member.dto.MemberCreateDto;
import com.sh.app.member.entity.Member;
import com.sh.app.member.entity.MemberDetails;

@Mapper
public interface MemberRepository {
	
	@Insert("insert into member values (#{memberId}, #{password}, #{name}, #{birthday, jdbcType=DATE}, #{email, jdbcType=VARCHAR}, default)")
	int insertMember(MemberCreateDto member);

	@Select("select * from member where member_id = #{memberId}")
	Member findMemberById(String memberId);

	
	MemberDetails loadUserByUsername(String username);
	// username이지만 PK값을 명시하는것 이기 때문에 member_id라고 생각하면 됨

	@Update("update member set birthday = #{birthday}, email = #{email}, name = #{name} where member_id= #{memberId}")
	int memberUpdate(Member member);
}
