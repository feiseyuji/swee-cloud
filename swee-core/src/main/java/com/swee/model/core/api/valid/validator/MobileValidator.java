package com.swee.model.core.api.valid.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.swee.model.core.api.valid.annotation.Mobile;

/**
 * “手机号”校验器
 *
 * @author Deolin
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {

    @Override
    public void initialize(Mobile constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        // 11位数字，以1开头
        return value.length() == 11 && value.startsWith("1");
    }

}
