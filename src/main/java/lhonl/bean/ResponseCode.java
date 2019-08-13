package lhonl.bean;

//@AllArgsConstructor
public enum ResponseCode {
	
	/*
	 * 		lombok中好像不能在枚举类中使用@Data注解 枚举类中也需要和普通类一样
	 * 
	 * 		枚举类不用实例化
	 */
	OK(200, "操作成功"),
	FAIL(300,"操作失败"),
	FAIL_USERNAME(330, "用户不存在"),

	FAIL_PASSWORD(337, "密码错误"),
	FAIL_ROLE(340, "权限不够"),
    SIGN_IN_FAIL(320,"登录失败"),
    LOGOUT_OK(201,"注销登录成功"),
    LOGOUT_FAIL(309,"注销登录失败"),
    
    SIGN_IN_OK(201, "登录成功"),
    SIGN_IN_NAME_FAIL(301, "用户丢失"),
    SIGN_IN_PASS_FAIL(302, "密码错误"),
    SIGN_IN_INPUT_FAIL(310,"账号或密码错误"),       
    SING_IN_INPUT_EMPTY(303,"账户和密码均不能为空"),
    
    
    NOT_SING_IN(304,"用户未登录或身份异常"),
	
	NETWORK_BAD(444, "服务器异常");

	
	private Integer stateCode;	
	private String msg;
	
	ResponseCode(Integer stateCode, String msg){
		this.msg = msg;
		this.stateCode = stateCode;
	}
	public Integer getStateCode() {
		return stateCode;
	}
	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
		
	
	
	
				
}
