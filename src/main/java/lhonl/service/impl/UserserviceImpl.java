package lhonl.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import lhonl.bean.ResponseCode;
import lhonl.bean.ResponseResult;
import lhonl.dao.UserDao;
import lhonl.dto.AddUserDto;
import lhonl.dto.LoginUserDto;
import lhonl.dto.PageUserDto;
import lhonl.dto.UpdateUserDto;
import lhonl.entity.SysRole;
import lhonl.entity.SysUser;
import lhonl.exception.RequestException;
import lhonl.service.UserService;

@CacheConfig(cacheNames="user")
@Service
public class UserserviceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	final static int PAGESIZE = 2;
	
	//	登录 @Param loginUserDto
	@Cacheable(key="'login_'.concat(#user.username)")
	public ResponseResult<?> login(LoginUserDto user, HttpSession session) {
		
		SysUser userTemp = userDao.selectUserByUserName(user);
		
		if(userTemp == null) {
			throw new RequestException(ResponseCode.FAIL_USERNAME);
		}
		
		if(!userTemp.getPassword().equals(user.getPassword())) {
			throw new RequestException(ResponseCode.FAIL_PASSWORD);
		}
		
		session.setAttribute("userSession", userTemp);
		
		PageHelper.startPage(0, 4);
		List<SysUser> userResult = userDao.selectUserAll();

		return new ResponseResult<Object>(userResult);
	}
	
	//	查询	@Param id
	@Cacheable(key= "'users_'+#id")
	public ResponseResult<?> selectUserById(Integer id) {
		
		SysUser user = userDao.selectUserById(id);
		System.out.println(user);
		if(user == null) {
			throw new RequestException(ResponseCode.FAIL_USERNAME);
		}		
//		//	这里可能看了会乱 我这里不想将SysRole类返回给前台 就将roleName合成list集合 
//		ArrayList<String> roleList = new ArrayList<>();		
//		
//		List<SysRole> roles = userRemp.getRoles();
//		for(SysRole sysRole : roles) {
//			roleList.add(sysRole.getRoleName());
//		}
//		FindUserDto user = FindUserDto.builder()
//							.username(userRemp.getUsername())
//							.password(userRemp.getPassword())
//							.gender(userRemp.getGender())
//							.email(userRemp.getEmail())
//							.remark(userRemp.getRemark())
//							.roleName(roleList)
//							.build();
		
		return new ResponseResult<Object>(user);
	}
	
	
	//	插入	@Param AddUserDto
	@CachePut
	public ResponseResult<?> insertUser(AddUserDto user) {	
		
		SysUser userRemp = userDao.selectUserById(user.getId());
		if(userRemp != null) {
			throw new RequestException(ResponseCode.FAIL_USERNAME);
		}
		
		ArrayList<String> roles = (ArrayList<String>) user.getRoleName();
		
//		我们在dao层中使用@Options注解的时候  它会自动将id值注入回实体类中 （user），这时候用get方法取就完事了 
		userDao.insertUser(user);
		
		//	这里取得是新插入Sys_User表数据的id
		int uid = user.getId();
		
		//	分多次写入Sys_role表中
		for (int i = 0; i < roles.size(); i++) {
			SysRole role = SysRole.builder()
							.uid(uid)
							.roleName(roles.get(i))
							.build();
			
			userDao.insertRoleByUserId(role);
		}		
		return new ResponseResult<Object>();
	}
	
	//	删除	@Param id 
	@CacheEvict(key="'user_'+#id")
	public ResponseResult<?> deleteUserById(Integer id) {
		
		SysUser userRemp = userDao.selectUserById(id);
		if(userRemp == null) {
			throw new RequestException(ResponseCode.FAIL_USERNAME);
		}
		userDao.deleteUserById(id);
		userDao.deleteRoleByUid(id);
		return new ResponseResult<Object>();
	}
	
	//	更新	@Param id	UpdateUser
	
	@CachePut(key="'user_'+#id")
	public ResponseResult<?> updateUser(UpdateUserDto user, Integer id) {
		
		SysUser userRemp = userDao.selectUserById(id);
		if(userRemp == null) {
			throw new RequestException(ResponseCode.FAIL_USERNAME);
		}
		user.setId(id);
		
		userDao.updateUser(user);
		
		if(user.getRoleName()!= null) {
			userDao.updateRoleByUid(id);
		}
		return new ResponseResult<Object>();
	}	
	
	@Cacheable(key="'user_'+#user.page+#user.size")
	public ResponseResult<?> selectUsersByPage(PageUserDto user) {
		
		List<SysUser> userResult = userDao.selectUserAll();
		PageHelper.startPage(user.getPage(),user.getSize());
		
		return new ResponseResult<Object>(userResult);
	}
}
