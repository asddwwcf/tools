package com.mine.tool.common.util.valid;

import com.mine.tool.common.exception.BaseRuntimeException;
import com.mine.tool.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 功能 :
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidUtils {
    /**
     * 需要校验的对象
     **/
    public static <T> void valid(T obj, Class<?>... groups) {
        ValidatorFactory factory = Validation.byProvider(HibernateValidator.class).configure()
                .failFast(false)
                .buildValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(obj, groups);
        if (violations != null && !violations.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            violations.forEach(error -> builder.append(error.getMessage()));
            throw new BaseRuntimeException(ErrorCode.SYSTEM_ERROR, builder.toString());
        }
    }

    /**
     * 需要校验的对象
     **/
    public static <T> void fastValid(T obj, Class<?>... groups) {
        ValidatorFactory factory = Validation.byProvider(HibernateValidator.class).configure()
                .failFast(true)
                .buildValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(obj, groups);
        if (violations != null && !violations.isEmpty()) {
            throw new BaseRuntimeException(ErrorCode.SYSTEM_ERROR, violations.iterator().next().getMessage());
        }
    }
}
