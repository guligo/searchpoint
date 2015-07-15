<%@ include file="/WEB-INF/jsp/commons/taglibs-header.jsp" %>
<%@ page pageEncoding="UTF-8" isELIgnored="false" %>

<script type="text/javascript">
	$(document).ready(function() {
		$("#file").change(function() {
	        $("#filename").val($("#file").val());
	    });
		$("input:not(.ignore)").tooltip({
            position: "center right",
            offset: [0, 10],
            effect: "fade"
        });
	}); 	
</script>
<form:form commandName="data" method="post" enctype="multipart/form-data" action="submit-data-upload.html?id=${param.id}" onsubmit="showDataUploadLoader();">
    <table cellpadding="10" cellspacing="10">    
        <tr>
            <td><form:radiobutton path="useUrl" value="false" class="ignore" /></td>
            <td class="label">
                File Upload (XML):
            </td>
            <td>
                <form:input path="file" type="file" cssClass="standard ignore" />
                <div class="file-upload">
                    <input id="filename" type="text" class="standard ignore" />
                    <input type="button" value="Browse" class="custom-button" />
                </div>
            </td>            
        </tr>        
        <c:if test="${data.xml != null}">
	        <tr>
	            <td colspan="2">&nbsp;</td>
	            <td>
	                <a href="show-data.html?id=${data.id}" class="terms-of-use-link" target="blank">
	                   ${data.xmlFileName}
	                </a>
	            </td>
	        </tr>
        </c:if>
        <%-- error rendering --%>
        <c:set var="error">
            <form:errors path="file" />
        </c:set>
        <c:if test="${not empty error}">
            <tr><td>&nbsp;</td><td>&nbsp;</td><td class="error">(${error})</td></tr>
        </c:if>
        <%-- END! error rendering --%>
        <tr>
            <td><form:radiobutton path="useUrl" value="true" class="ignore" /></td>
            <td class="label">Remote File (URL):</td>
            <td>
                <form:input path="url" cssClass="standard" />
            </td>            
        </tr>
        <%-- error rendering --%>
        <c:set var="error">
            <form:errors path="url" />
        </c:set>
        <c:if test="${not empty error}">
            <tr><td>&nbsp;</td><td>&nbsp;</td><td class="error">(${error})</td></tr>
        </c:if>
        <%-- END! error rendering --%>
        <tr>
            <td colspan="2">&nbsp;</td>
            <td>
	            <input class="custom-button ignore" type="submit" value="Save" />
	            <input class="custom-button ignore" type="button" value="Cancel" onclick="window.location = 'user-companies.html';" />
            </td>
        </tr>
    </table>
</form:form>
                           