<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change password</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/policy.css"/>
</head>
<body>
	<div align="center"><img src="${pageContext.request.contextPath}/images/IE_Logo.gif"/></div>
	<div align="center">
	<s:form commandName="changePassword" action="${pageContext.request.contextPath}/changePassword.do" method="post" class="loginForm">
	
	<table>
		<tr>
			<td>Old Password:</td><td><s:input name="oldPassword" type="password" path="oldPassword"/></td>
		</tr>
		<tr>
			<td>New Password:</td><td><s:input  name="newPassword" type="password" path="newPassword"/></td>
		</tr>
		<tr>
			<td>Repeat New Password:</td><td><s:input  name="retypedPassword" type="password" path="retypedPassword"/></td>
		</tr>
		<tr><td colspan="2" align="center"><input type="submit" value="submit"/>&nbsp;<input type="reset" /></td></tr>
	</table>
		${passwordError}
		<s:errors path="oldPassword" cssClass="errorblock" element="div" />
	</s:form>
		
	</div>
</body>
</html>