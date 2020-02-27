package com.intraedge.policy.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import javax.validation.Constraint;
import javax.validation.Payload;
@Target({ANNOTATION_TYPE, METHOD, FIELD, PARAMETER, CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PolicyPdfValidator.class) 
public @interface ValidPolicyPdf {
	String fileSize() default "31457280";
    String[] supportedFileTypes() default {"pdf"};
    String message() default "File must be a PDF no larger then 30M bytes";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
