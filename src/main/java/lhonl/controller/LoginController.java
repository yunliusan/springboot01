package lhonl.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lhonl.bean.ResponseResult;
import lhonl.dto.LoginUserDto;
import lhonl.service.UserService;

@SuppressWarnings("rawtypes")

@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String loginTest() {
		return "login";	
	}
	
	@PostMapping("/login")
	public ResponseResult login(@Valid @RequestBody LoginUserDto user, BindingResult rs, HttpSession session) {

		if(rs.hasErrors()) {
			return new ResponseResult(505, rs.getFieldError().getDefaultMessage());
		}		
		return userService.login(user, session);	
	}
	
	@RequestMapping("/logout")
	public ResponseResult noLogin(HttpSession session) {
		session.invalidate();
		return new ResponseResult();	
	}
}
