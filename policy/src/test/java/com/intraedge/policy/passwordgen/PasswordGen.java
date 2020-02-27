package com.intraedge.policy.passwordgen;

import org.junit.Test;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class PasswordGen {
	@Test
	public void genPass(){
		Md5PasswordEncoder enc = new Md5PasswordEncoder();
		System.out.println(enc.encodePassword("Password1", null));
	}
}
