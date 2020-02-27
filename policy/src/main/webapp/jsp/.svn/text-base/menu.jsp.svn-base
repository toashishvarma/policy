<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"/>




<table align="center" width="70%" class="policyTable" cellpadding="0" cellspacing="0">
	<tr>
	<th class="policyTableSub">Sr. No.</th>
		<th class="policyTableSub">Policy Name</th><th class="policyTableSub">Policy Description</th><th class="policyTableSub">Approval Status</th>
		<th class="policyTableSub">Acceptance Status</th>
		<th class="policyTableSub">Created By</th>
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
    		<c:when test="${policy.enabled}">
    			Approved
    		</c:when>
    		<c:otherwise>
    			Draft
    		</c:otherwise>
    		</c:choose>
    	</td>
    	<td class="policyTableData">
    	<c:choose>
    		<c:when test="${policy.enabled}">
	    		<c:choose>
    				<c:when test="${policy.accepted}">
    					Accepted
    				</c:when>
    				<c:otherwise>
    					Not Accepted
    				</c:otherwise>
    			</c:choose>
    		</c:when>
    		<c:otherwise>
    				NA
    		</c:otherwise>
    	</c:choose>
    	</td>
    	<td class="policyTableData">
    		${policy.createdBy}	
    	</td>
    </tr>
	</c:forEach>
</table>

<jsp:include page="footer.jsp"/>