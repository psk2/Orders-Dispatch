package com.yathi.EnumValidations;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BrickTypeValidatorImpl implements ConstraintValidator<BrickTypeValidator, String> {

    public void initialize(BrickTypeValidator enumValidator)
    {
        // used only if your annotation has attributes
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }

    public boolean isValid(Character brickType, ConstraintValidatorContext constraintContext)
    {
        // Bean Validation specification recommends to consider null values as
        // being valid. If null is not a valid value for an element, it should
        // be annotated with @NotNull explicitly.
        if (brickType == null)
        {
            return true;
        }
        if (brickType.equals("CLAY") || brickType.equals("CEMENT") || brickType.equals("SAND"))
            return true;

        else
        {
            return false;
        }

    }

}