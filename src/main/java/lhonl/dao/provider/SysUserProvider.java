package lhonl.dao.provider;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.jdbc.SQL;

import lhonl.dto.FindUserDto;

public class SysUserProvider {
	public String findUserByLogin(String username) {
		return new SQL() {
			{
				SELECT("u.* ,r.roleName");
				FROM("Sys_User u");
				LEFT_OUTER_JOIN("Sys_Role r ON r.id = sru.rid");
				WHERE(" u.username = #{username}");
			}
		}.toString();
	}
	
	public String selectUserById(Integer id) {
		return new SQL() {;
			{
				SELECT("u.* ,r.roleName");
				FROM("Sys_User u");
				LEFT_OUTER_JOIN("Sys_U_R sru ON u.id= sru.uid");
				LEFT_OUTER_JOIN("Sys_Role r ON r.id = sru.rid");
				WHERE("u.id = #{id}");
			}
		}.toString();
	}
//	@Insert("insert into " 
//			+ USERTABLE 
//			+" (username, password, gender, email, remark)"
//			+ " values(#{user.username}, #{user.password}, #{user.gender}, #{user.email}, #{user.remark})"
//			+ " insert into " 
//			+ RoleTABLE 
//			+ " (roleName) values(#{user.roleName})")
	
	public String insertUser(FindUserDto user) {
		return new SQL() {
			{
				INSERT_INTO("Sys_User");
				VALUES("username", "#{username}");
				VALUES("password", "#{password}");
				VALUES("gender", "#{gender}");
				VALUES("email", "#{email}");
				VALUES("remark", "#{remark}");
				INSERT_INTO("Sys_Role");
				VALUES("roleName", "#{roleName}");
				VALUES("uid", "last_insert_id()");
			}
		}.toString();
	}
}
