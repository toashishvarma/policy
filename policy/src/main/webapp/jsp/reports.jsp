<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="header.jsp" />
<!-- report page start -->
	<table align="center" width="70%" class="policyTable" cellpadding="0" cellspacing="0">
		<tr>
			<th align="center" class="policyTableSub">
				Sr. No.
			</th>
			<th align="center" class="policyTableSub">
				Report
			</th>
			<th align="center" class="policyTableSub">
				PDF
			</th>
			<th align="center" class="policyTableSub">
				XLS
			</th>
		</tr>
		<tr>
			<td class="policyTableSub" align="center">1</td>
			<td class="policyTableSub">Policy Acceptance By Employees</td>
			<td class="policyTableSub"><a href="${pageContext.request.contextPath}/download/pdf.do">PDF</td>
			<td class="policyTableSub"><a href="${pageContext.request.contextPath}/download/xls.do">XLS</a></td>
		</tr>
		
	</table>
<!-- report page end -->
<jsp:include page="footer.jsp" />