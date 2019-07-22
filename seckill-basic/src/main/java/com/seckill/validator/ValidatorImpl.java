package com.seckill.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidatorImpl implements InitializingBean {

    private Validator validator;

    public ValidationResult validate(Object bean) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);
        if (constraintViolationSet.size() > 0) {
            result.setError(true);
            constraintViolationSet.forEach(e -> {
                String errorMsg = e.getMessage();
                String propertyName = e.getPropertyPath().toString();
                result.getErrorMsgMap().put(propertyName, errorMsg);
            });
        }
        return result;
    }

    /**
     * bean初始化完成后调用
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
