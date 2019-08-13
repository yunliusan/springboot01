package lhonl.dto;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import lhonl.entity.SysRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUserDto {
	
	@NotEmpty(message = "用户名称不能为空")
	private String username;
	@NotEmpty(message = "用户密码不能为空")
	private String password;
	
	//根据角色完成授权 /有根据资源进行授权的
	private List<SysRole> roles;	//用户将拥有的角色名称
}
