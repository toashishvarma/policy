<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<jsp:include page="header.jsp" />
<s:form commandName="employee"
	action="${pageContext.request.contextPath}/${action}" method="post">
	<div style="color: red" align="center">
		${errorMessage}
		<s:errors path="*" element="div" />
	</div>
	<div style="color: green" align="center">${successMessage}</div>
	<div align="center">
		<table class="policyTable" cellpadding="0" cellspacing="0">
			<tr>
				<th colspan="2" align="center" class="policyTableSub">
				<c:choose>
						<c:when test="${update}">
    		Update User
    	</c:when>
						<c:otherwise>
    		Add User
    	</c:otherwise>
					</c:choose>
				<th>
			</tr>
			<tr>
				<td class="policyTableSub">Email</td>
				<td class="policyTableData"><c:choose>

						<c:when test="${update}">
							${employee.email}
							<s:hidden path="email"/>
						</c:when>
						<c:otherwise>
							<s:input name="email" path="email" />
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td class="policyTableSub">First Name</td>
				<td class="policyTableData"><s:input name="first" path="first" /></td>
			</tr>
			<tr>
				<td class="policyTableSub">Last Name</td>
				<td class="policyTableData"><s:input name="last" path="last" /></td>
			</tr>
			<tr>
				<td class="policyTableSub">Phone</td>
				<td class="policyTableData"><s:input name="phone" path="phone" /></td>
			</tr>
			<tr>
				<td class="policyTableSub">Enabled</td>
				<td class="policyTableData"><s:checkbox name="enabled"
						path="enabled" /></td>
			</tr>
			<tr>
				<td colspan="2" class="policyTableData" align="center"><input
					type="submit" value="Submit" /></td>
			</tr>
		</table>
	</div>
</s:form>
<jsp:include page="footer.jsp" />