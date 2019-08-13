package lhonl.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import lhonl.bean.ResponseResult;
import lhonl.dto.AddUserDto;
import lhonl.dto.LoginUserDto;
import lhonl.dto.PageUserDto;
import lhonl.dto.UpdateUserDto;

@Component
public interface UserService {
	
	// 实现
	public ResponseResult<?> insertUser(AddUserDto user);
	// 实现
	public ResponseResult<?> selectUserById(Integer id);

	public ResponseResult<?> selectUsersByPage(PageUserDto pageUser);

	public ResponseResult<?> deleteUserById(Integer id);

	public ResponseResult<?> updateUser(UpdateUserDto updateUser, Integer id);
	
	public ResponseResult<?> login(LoginUserDto user, HttpSession session);
}
