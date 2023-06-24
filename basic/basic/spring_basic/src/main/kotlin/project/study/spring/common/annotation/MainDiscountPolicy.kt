package project.study.spring.common.annotation

import org.springframework.beans.factory.annotation.Qualifier
import java.lang.annotation.Inherited

@MustBeDocumented
@Qualifier("mainDiscountPolicy")
@Inherited
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.CLASS,
    AnnotationTarget.ANNOTATION_CLASS
)
@Retention(AnnotationRetention.RUNTIME)
annotation class MainDiscountPolicy
