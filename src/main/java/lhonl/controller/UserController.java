package lhonl.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lhonl.annotation.LoginRequired;
import lhonl.bean.ResponseResult;
import lhonl.dto.AddUserDto;
import lhonl.dto.PageUserDto;
import lhonl.dto.UpdateUserDto;
import lhonl.service.UserService;

@SuppressWarnings("rawtypes")

@RequestMapping("/user")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@LoginRequired
	@GetMapping("/{id}")
	public ResponseResult findUserById(@PathVariable Integer id) {
		return userService.selectUserById(id);		
	}
	
	@LoginRequired
	@PostMapping
	public ResponseResult addUser(@Valid @RequestBody AddUserDto user, BindingResult rs) {
		
		if(rs.hasErrors()){
			return new ResponseResult<>(505, rs.getFieldError().getDefaultMessage());
		}else {
			return userService.insertUser(user);		
		}		
	}
	
	@LoginRequired
	@DeleteMapping("/{id}")
	public ResponseResult removeUser(@PathVariable Integer id) {
		return userService.deleteUserById(id);		
	}
	
	@LoginRequired
	@PutMapping("/{id}")
	public ResponseResult updateUser(@RequestBody UpdateUserDto user, @PathVariable Integer id) {
		return userService.updateUser(user, id);		
	}
	
	@GetMapping
	public ResponseResult findUserByPage(@RequestBody PageUserDto pageUser) {
		return userService.selectUsersByPage(pageUser);
		
	}
}