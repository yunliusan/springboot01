package lhonl.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

import lhonl.entity.SysUser;
import lhonl.bean.ResponseCode;
import lhonl.bean.ResponseResult;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		

//        System.out.println(request.getCharacterEncoding());	//	请求的编码格式
//        System.out.println(request.getMethod());			//	请求方法
//        System.out.println(request.getSession());			//	
//        System.out.println(request.getServletPath());		
//        System.out.println(request.getServerName());		//	服务器名字
//        System.out.println(request.getRequestURI());		//	请求路径（不包含服务器名字）
//        System.out.println(request.getRequestedSessionId());//	空？

        boolean flag = false;
        
        final String[] IGNORE_URI = {"/login"};
        String requestPath = request.getRequestURI();
        for(String s:IGNORE_URI) {        	
        	if(requestPath.contains(s)) {
        		flag = true;
        		break;
        	}       	        	
        }
        
        if(!flag) {
        	SysUser user = (SysUser) request.getSession().getAttribute("userSession");
        	
        	if(user == null) {
        		    
        		//	听说可以抛异常 不知道可以不
        		response.setContentType("application/json;charset=UTF-8");
        		response.getWriter().write(objectMapper.writeValueAsString(
        					new ResponseResult<Object>(ResponseCode.NOT_SING_IN.getStateCode(), ResponseCode.NOT_SING_IN.getMsg())));
        		return flag;
        		
        	}else {
        		flag = true;
        	}
        }
        return flag;
	}

}
