package lhonl.interceptor;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lhonl.annotation.LoginRequired;
import lhonl.bean.ResponseCode;
import lhonl.entity.SysRole;
import lhonl.entity.SysUser;
import lhonl.exception.RequestException;

public class AuthenticationInterception extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("lin123");
		boolean flag = false;
	 	// 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        
        //	通过注解的方式标明那些方法是需要拦截的
        
        // ①:START 方法注解级拦截器
        HandlerMethod handlerMethod = (HandlerMethod) handler;                
        Method method = handlerMethod.getMethod();
        
        // 判断接口是否需要登录 看方法上是否有@LoginRequired注解
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        
        // 有 @LoginRequired 注解，需要认证
        if (methodAnnotation != null) {
            // 这写你拦截需要干的事儿，比如取缓存，SESSION，权限判断等  

        	SysUser user = (SysUser) request.getSession().getAttribute("userSession");
        	
        	for(SysRole role : user.getRoles()){
        		if(role.getRoleName().equals("ROLE_ADMIN")) {
        			flag = true;
        			break;
        		}		
        	}
        	if(!flag) {
        		throw new RequestException(ResponseCode.FAIL_ROLE); 
        	}
        }else {
        	flag=true;
        }
		return flag;
	}
}
