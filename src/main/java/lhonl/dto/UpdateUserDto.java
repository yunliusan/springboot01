package lhonl.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import lhonl.annotation.MyAnnotation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
	
	private Integer id;
	
	@NotNull(message = "用户名称不能为空")
	private String username;
	
	@NotNull(message = "用户密码不能为空")
	private String password;
	
	private String gender;
	
	@Email(message = "请输入正确的邮箱号码")
	private String email;
	
	@MyAnnotation
	private String remark;
	
	private List<String> roleName;
	
}