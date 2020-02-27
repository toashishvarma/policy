package com.intraedge.policy.auditing;

import java.util.Date;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.intraedge.policy.dao.entity.AuditableEntity;

@Aspect
public class AuditingManager {

	@Before(value = "execution(* com.intraedge.policy..*(..)) && @annotation(Audit) && args(auditable)")
	public void updateAuditInfo(AuditableEntity auditable) {
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User){
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (auditable.getCreatedBy() == null) {
				auditable.setCreationTime(new Date());
				auditable.setCreatedBy(user.getUsername());
			}
			auditable.setModificationTime(new Date());
			auditable.setModifiedBy(user.getUsername());
		}
	}
}