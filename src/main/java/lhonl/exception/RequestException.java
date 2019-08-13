package lhonl.exception;

import java.io.Serializable;

import lhonl.bean.ResponseCode;

public class RequestException extends RuntimeException implements Serializable{
	
	/*		
	 * 		统一回复成功/失败消息 我们实现了三个类RequestException（异常）ResponseResult（成功）ResponseCode（封装错误代码信息）
	 * 
	 * 		这里只处理自定义异常 读取枚举类（自我理解 就是有参构造器 并实现枚举类中的参数的读取 /不能进行设置 所以只有get函数）
	 * 
	 * 		实现自定义异常的实现需要定义全局异常处理器
	 * */
	private static final long serialVersionUID = 1L;
	
	//	封装错误代码信息
	private final ResponseCode responseCode;
	
	public RequestException(ResponseCode responseCode) {
		this.responseCode = responseCode;		
	}
	
	public ResponseCode getResponseCode() {		
		return responseCode;
	}
}
