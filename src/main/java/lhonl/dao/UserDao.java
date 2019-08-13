package lhonl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import lhonl.dto.AddUserDto;
import lhonl.dto.LoginUserDto;
import lhonl.dto.UpdateUserDto;
import lhonl.entity.SysRole;
import lhonl.entity.SysUser;

public interface UserDao {

	final String USERTABLE = "Sys_User";
	final String ROLENAME = "Sys_Role";
	
	//	id查找
	/*
	 * 		@Result(column="id",property="id", id=true)
	 * 	坑点
	 * 		这一行如果不标注id的话  所取到的结果id都是null值 望注意 
	 * 
	 */
	@Select("select * from " + USERTABLE + " where id = #{id}")
	@Results(id= "userMap", value= {
				@Result(column="id",property="id", id=true),
				@Result(column="id", property="roles", many=@Many(
							select="lhonl.dao.UserDao.selectRolesById"))
	})
	SysUser selectUserById(Integer id);
	
	//	根据uid查找角色
	@Select("select * from " + ROLENAME +" where uid = #{id}")
	List<SysRole> selectRolesById(Integer id);
	
	//	用户插入 属性为AddUserDto中所设置的 在这里 insert函数返回的是一个int值 意义为受影响行数的值
    
	@Insert("insert into " + USERTABLE  + "(username, password, gender, email, remark) values(#{user.username}, #{user.password}, #{user.gender}, #{user.email}, #{user.remark})")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "user.id")
	int insertUser(@Param("user")AddUserDto user);
    
    //	角色插入 属性为sysRole中所设置的
    @Options(useGeneratedKeys = true, keyColumn = "id")
    @Insert("insert into " + ROLENAME + "(uid, roleName) values(#{uid}, #{roleName})")
    void insertRoleByUserId(SysRole role);
    
    @Delete("delete from " +USERTABLE+ " where id = #{id}")
    void deleteUserById(Integer id);
    
    @Delete("delete from " +ROLENAME+ " where uid = #{uid}")
    void deleteRoleByUid(@Param("uid")Integer id);
    
    @Update("update " +USERTABLE+ " set username = #{username}, password = #{password}, gender =#{gender} ,email = #{email}, remark = #{remark} where id = #{id}")
	void updateUser(UpdateUserDto user);
    
    @Update("update " +ROLENAME+ " set roleName = #{roleName} where uid = #{uid}")
    void updateRoleByUid(Integer uid);
    
    @Select("select * from " + USERTABLE)
    @ResultMap("userMap")
    List<SysUser> selectUserAll();
    
    @Select("select * from " + USERTABLE + " where username = #{username}")
    @ResultMap("userMap")
    SysUser selectUserByUserName(LoginUserDto user);
    
}
