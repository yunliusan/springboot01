package lhonl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRole {
	
	private Integer id;			//id
	private Integer uid;
	private String roleName;	//用户所拥有的权限名字
}
