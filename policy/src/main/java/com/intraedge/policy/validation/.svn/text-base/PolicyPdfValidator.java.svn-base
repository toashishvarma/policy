package com.intraedge.policy.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class PolicyPdfValidator implements
		ConstraintValidator<ValidPolicyPdf, CommonsMultipartFile> {

	private String fileSize = "31457280"; // 30M-bytes
	private String[] supportedFileTypes = { "PDF" };

	@Override
	public void initialize(ValidPolicyPdf constraintAnnotation) {
		fileSize = constraintAnnotation.fileSize();
		supportedFileTypes = constraintAnnotation.supportedFileTypes();
	}

	@Override
	public boolean isValid(CommonsMultipartFile file,
			ConstraintValidatorContext ctx) {
		if(file.getSize()==0){
			return false;
		}
		if(file.getOriginalFilename().length()==0){
			return false;
		}
		return validateExtensionType(file) && validateFileSize(file);
	}

	private boolean validateExtensionType(CommonsMultipartFile value){
		int dotPos = value.getOriginalFilename().lastIndexOf(".");
		boolean result = false;
		if (dotPos != -1) {
			String extension = value.getOriginalFilename()
					.substring(dotPos + 1);

			final List<String> supportedExtensions = Arrays
					.asList(supportedFileTypes);

			for (String supportedExtension : supportedExtensions) {
				if (extension.equalsIgnoreCase(supportedExtension)) {
					result = true;
					break;
				}
			}

		}
		return result;
	}

	private boolean validateFileSize(CommonsMultipartFile value) {
		boolean result = false;
		if (value != null) {
			if (value.getSize() != 0
					&& value.getSize() <= Long.valueOf(fileSize)) {
				result = true;
			}
		}
		return result;
	}

}
