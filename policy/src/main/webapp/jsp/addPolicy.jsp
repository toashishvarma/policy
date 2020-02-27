<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.jsp"/>
<!-- addPolicy.jsp starts here -->
<s:form commandName="createPolicyRequest" action="${pageContext.request.contextPath}/createpolicy.do" method="post" enctype="multipart/form-data">
    <div style="color: red" align="center">
        <s:errors path="policy.name" cssClass="errorblock" element="div" />
        <s:errors path="policy.description" cssClass="errorblock" element="div" />
        <s:errors path="policy.fileData" cssClass="errorblock" element="div" />
    </div>
    <div style="color: green" align="center">
		${successMessage}
    </div>
	<div align="center"> 
    <table class="policyTable" cellpadding="0" cellspacing="0">
    <tr><th colspan="2" align="center" class="policyTableSub">Add Policy<th></tr>
        <tr><td class="policyTableSub">Policy Name</td><td class="policyTableData"><s:input name="policyName" path="policy.name"/></td></tr>
        <tr><td class="policyTableSub">Description</td><td class="policyTableData"><s:input name="description" path="policy.description"/></td></tr>
       <tr><td class="policyTableSub">Policy File</td><td class="policyTableData"><s:input type="file" path="policy.fileData"/></td></tr>
    <tr><td colspan="2" class="policyTableData" align="center"><input type="submit" value="Submit"/></td></tr>
    </table>
    </div>
</s:form>
<!-- addPolicy.jsp ends here -->
<jsp:include page="footer.jsp"/>
</html>