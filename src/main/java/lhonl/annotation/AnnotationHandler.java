package lhonl.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//	自定义注解类

//	@Component正常是需要手动将这个注入进bean中 但是我们这里选择继承
public class AnnotationHandler implements ConstraintValidator<MyAnnotation, Object>{
	//	ConstraintValidator<自定义注解， 注解可以标记在哪种字段上>
	
	//	初始化 暂时没有学习到有什么用处
	@Override
	public void initialize(MyAnnotation constraintAnnotation) {
		System.out.println("init----------");
	}
	
	//	进行校验判断	ConstraintValidatorContext 校验上下文， value传入的值
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		System.out.println(value);
		//	返回校验失败或者成功
		return true;
	}
	
}
