package lhonl.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import lhonl.bean.ResponseCode;
import lhonl.bean.ResponseResult;

//	@ControllerAdvice 声明这是一个自定义异常处理类

@ControllerAdvice
public class ExceptionHandler {
	
	/*
	 * 		自定义异常处理
	 */
	
	@SuppressWarnings("rawtypes")
	//	@ExceptionHandler(自定义异常类.class) 声明自定义的异常
	@org.springframework.web.bind.annotation.ExceptionHandler(RequestException.class)
	@ResponseBody
	public ResponseResult reponseHandler(HttpServletRequest request, RequestException ex) {	
		
		//	返回自定义统一结果
		return new ResponseResult(ex.getResponseCode().getStateCode(), ex.getResponseCode().getMsg());	 				 	
	}	
	
	/*
	 * 		其他异常处理
	 */
//	@SuppressWarnings("rawtypes")
//	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
//	@ResponseBody
//	public ResponseResult exceptionHandler(HttpServletRequest request, Exception ex) {	
//		
//		return new ResponseResult(ResponseCode.NETWORK_BAD.getStateCode(), ResponseCode.NETWORK_BAD.getMsg());	 				 	
//	}	
}
