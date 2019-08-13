package lhonl.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseResult<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/*		
	 * 		回复信息主体
	 * 
	 */
	
	private Integer stateCode;
	private String msg;
	
	private T data;
	
	/*
	 * 		@AllArgsConstructor 默认只提供全部已定义属性的构造器 不会创建其他的参数的构造器 
	 * 		如有三个属性 只会自动配置一个包含是三个参数的构造器 如果想要只有两个参数的构造器 只能自己构建
	 * 
	 */
	public ResponseResult() {
		this.stateCode = ResponseCode.OK.getStateCode();
		this.msg = ResponseCode.OK.getMsg();
	}
	public ResponseResult(Integer stateCode, String msg){
		
		this.stateCode = stateCode;
		this.msg = msg;
	}
	public ResponseResult(T data) {
		this.stateCode = ResponseCode.OK.getStateCode();
		this.msg = ResponseCode.OK.getMsg();
		this.data  = data;
	}
}
