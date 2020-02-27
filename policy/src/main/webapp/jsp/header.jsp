<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IntraEdge Policy Management System</title>
<link rel="stylesheet" type="text/css" href="css/policy.css"/>
</head>
<body>

<div align="left"><img src="${pageContext.request.contextPath}/images/IE_Logo.gif"/></div>
<table width="100%" class="headerTable">
<tr>
<td align="left">
<a href="<c:url value='/welcome.do' />" style="{text-decoration:none;}; visited {text-decoration:none;}; hover {text-decoration:underline;}">Home</a>
</td>
<sec:authorize access="hasRole('ROLE_ADMIN')">
<td align="center">
 <a href="${pageContext.request.contextPath}/adduser.do" target="_top">Add User</a>
</td>
<td align="center">
 <a href="${pageContext.request.contextPath}/addpolicy.do" target="_top">Add Policy</a>
</td>
<td align="center">
 <a href="${pageContext.request.contextPath}/listemployees.do" target="_top">View Employees</a>
</td>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_SUPERVISOR')">
<td align="center">
	<a href="${pageContext.request.contextPath}/reports.do" target="_top">Reports</a>
</td>
</sec:authorize>
<td align="right">
<a href="<c:url value='/j_spring_security_logout' />">Logout</a>
</td>
</tr>

</table>
<hr/>

