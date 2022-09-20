package provider.common.annotation;

import java.lang.annotation.*;

/**
 * @program: erukeribbon
 * @description: Aop注解
 * @author:
 * @create: 2022-05-27 17:25
 **/
@Target({ElementType.TYPE, ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AopAnnotation {
}
