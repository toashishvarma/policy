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
<body onload='document.f.email.focus();'>
	<h3 align="center"><img src="${pageContext.request.contextPath}/images/IE_Logo.gif"/></h3>
	<div align="center">
	<table width="29%"><tr><td align="left"><a href="${pageContext.request.contextPath}/login.do">Login</a></td><td>&nbsp;</td></tr></table>
</div>
	<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
	<div align="center">
	${passwordResetMessage}
	<form name='f' action="${pageContext.request.contextPath}/resetPassword.do"
		method='POST' class="loginForm">
	
		<table>
			<tr>
				<td>Email:</td>
				<td><input type='text' name='email'>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input name="submit" type="submit" value="submit" />
				</td>
			</tr>
		</table>

	</form>
	
	</div>
	
</body>
</html>