package com.ccunix.hospital.common.utils.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {
    private boolean require = false;
    @Override
    public void initialize(IsMobile constraintAnnotation) {
        require = constraintAnnotation.require();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(require){
            return ValidateUtil.isMobile(value);
        }
        return false;
    }
}
