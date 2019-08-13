package lhonl.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy= AnnotationHandler.class)			//	用那个处理这个自定义注解的校验逻辑类
//当你需要对实体类的属性做校验的时候会用到@Constraint 不需要这不用这个注解

@Target(ElementType.FIELD)									//	注解在哪里的意思
@Retention(RetentionPolicy.RUNTIME) 						//	保留的意思 retention   运行时保留
public @interface MyAnnotation {
	
	String message() default "";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
