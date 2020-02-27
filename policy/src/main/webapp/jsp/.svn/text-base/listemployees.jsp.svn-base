<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp" />
<table align="center" width="70%" class="policyTable" cellpadding="0" cellspacing="0">
	<thead>
		<tr>
			<th class="policyTableSub"><b>Sn.</b></td>
			<th class="policyTableSub"><b>Name</b></td>
			<th class="policyTableSub"><b>Role</b></td>
			<th class="policyTableSub"><b>Email Id</b></td>
			<th class="policyTableSub"><b>Phone No.</b></td>
			<th class="policyTableSub"><b>Enabled</b></td>
			<th class="policyTableSub"><b>Update</b></td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${employees}" var="employee" varStatus="status">
			<tr>
				<td class="policyTableData">${status.index+1}</td>
				<td class="policyTableData">${employee.first} ${employee.last}</td>
				<td class="policyTableData">${employee.role}</td>
				<td class="policyTableData">${employee.email}</td>
				<td class="policyTableData">${employee.phone}</td>
				<td class="policyTableData">
				<c:choose>
					<c:when test="${employee.enabled}">
						Yes
					</c:when>
					<c:otherwise>
						No
					</c:otherwise>
				</c:choose>
				</td>
				<td class="policyTableData"><a href="<c:url value="/updateuserpage.do?email=${employee.email}"/>">Update</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="footer.jsp" />