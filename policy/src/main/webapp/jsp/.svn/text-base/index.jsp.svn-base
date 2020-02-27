<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp"/>
<p>
	Welcome to IntraEdge Policy management portal.
</p>
<br/>
<div>
<table align="center" width="70%" class="policyTable" cellpadding="0" cellspacing="0">
	<tr>
	<th class="policyTableSub">Sr. No.</th>
		<th class="policyTableSub">Policy Name</th><th class="policyTableSub">Policy Description</th><th class="policyTableSub">Status</th>
	</tr>
	<c:forEach items="${policies}" var="policy" varStatus="status">
	<tr>
	<td class="policyTableData">${status.index+1}</td>
	<td class="policyTableData">
    	<a href="${pageContext.request.contextPath}/showpolicy.do?policyName=${policy.name}">${policy.name}</a><br/>
    	</td>
    	<td class="policyTableData">
    		${policy.description}	
    	</td>
    	<td class="policyTableData">
    		<c:choose>
    		<c:when test="${policy.accepted}">
    			Accepted
    		</c:when>
    		<c:otherwise>
    			Not Accepted
    		</c:otherwise>
    		</c:choose>
    	</td>
    </tr>
	</c:forEach>
</table>
</div>

<jsp:include page="footer.jsp"/>