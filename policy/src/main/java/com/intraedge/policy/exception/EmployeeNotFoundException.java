package com.intraedge.policy.exception;
/**
 * Wrapper for exception
 * @author SujayB
 *
 */
public class EmployeeNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1590964965999430529L;
	/**
	 * 
	 */
	private Exception e;

	@Override
	public Throwable getCause() {
		return e;
	}

	public EmployeeNotFoundException(Exception e) {
		this.e = e;
	}
	
	public EmployeeNotFoundException(String message) {
		this.e = new RuntimeException(message);
	}

	@Override
	public String getMessage() {
		return "Policy system exception caused by : "+e.getMessage();
	}
}
