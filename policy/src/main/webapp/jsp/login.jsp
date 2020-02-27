<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/policy.css"/>
</head>
<body onload='document.f.j_username.focus();'>
	<h3 align="center"><img src="${pageContext.request.contextPath}/images/IE_Logo.gif"/></h3>
	<div align="center">${passwordResetMessage}</div>
	<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
	<div align="center">
	<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST' class="loginForm">
	
		<table>
			<tr>
				<td>Email:</td>
				<td><input type='text' name='j_username' value=''>
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password' />
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input name="submit" type="submit" value="Login" />
				&nbsp;&nbsp;&nbsp;<input name="reset" type="reset" value="Reset" />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><a href="${pageContext.request.contextPath}/forgotPassword.do">Forgot password</a></td>
			</tr>
		</table>

	</form>
	
	</div>
	
</body>
</html>