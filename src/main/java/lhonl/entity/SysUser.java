package lhonl.entity;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;				//id
	private String username;		//用户名
	private String password;		//密码
	private String gender;
	private String email;
	private String remark;
	
	private List<String> roleNames;
	
	//根据角色完成授权 /有根据资源进行授权的
	private List<SysRole> roles;	//用户将拥有的角色名称
	
	
}
