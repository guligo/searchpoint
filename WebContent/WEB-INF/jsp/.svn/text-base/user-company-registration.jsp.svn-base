<%@ include file="/WEB-INF/jsp/commons/taglibs-header.jsp" %>
<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>

<script type="text/javascript">
    $(document).ready(function() {
        $(":input:not(.ignore)").tooltip({
            position: "center right",
            offset: [0, 10],
            effect: "fade"
        });                
    });    
</script>

<%-- set variables --%>
<c:set var="mode">
    <tiles:getAsString name="mode" />
</c:set>
<c:set var="ADD" value="add" />
<c:set var="EDIT" value="edit" />

<!-- set url and button names -->
<c:if test="${mode == ADD}">
    <c:set var="url" value="submit-add-user-company.html" />
    <c:set var="submit" value="Register" />
</c:if>
<c:if test="${mode == EDIT}">
    <c:set var="url" value="submit-edit-user-company.html" />
    <c:set var="submit" value="Save" />
</c:if>

<%-- render --%>
<form:form id="user-company-registration-form" commandName="company" method="post" action="${url}">
    <c:if test="${mode == EDIT}">
        <form:hidden path="id" />
        <form:hidden path="title" />
    </c:if>
    <table cellpadding="10" cellspacing="10">
        <c:choose>
            <c:when test="${mode == ADD}">        
		        <tr>
		            <td class="label"><font color="red">*</font> Title:</td>
		            <td>
		                <form:input path="title" cssClass="standard" title="Enter the title of your e-shop" />
		            </td>		            
		        </tr>
		        <%-- error rendering --%>
		        <c:set var="error">
		            <form:errors path="title" />
		        </c:set>
		        <c:if test="${not empty error}">
		            <tr><td>&nbsp;</td><td class="error">(${error})</td></tr>
		        </c:if>
		        <%-- END! error rendering --%>
            </c:when>
            <c:otherwise>
                <tr>
                    <td class="label"><font color="red">*</font> Title:</td>
                    <td>
                        ${company.title}                        
                    </td>                                        
                </tr>                
            </c:otherwise>
        </c:choose>
        <tr>
            <td class="label"><font color="red">*</font> Homepage (URL):</td>
            <td>
                <form:input path="homepageUrl" cssClass="standard" title="Entry the home-page URL of your e-shop" />
            </td>                      
        </tr>
        <%-- error rendering --%>
        <c:set var="error">
            <form:errors path="homepageUrl" />
        </c:set>
        <c:if test="${not empty error}">
            <tr><td>&nbsp;</td><td class="error">(${error})</td></tr>
        </c:if>
        <%-- END! error rendering --%>
        <tr>            
            <td class="label"><font color="red">*</font> Logo (URL):</td>            
            <td>
                <form:input path="logoUrl" cssClass="standard" title="Enter the URL for image logo of your e-shop" />
            </td>
        </tr>
        <%-- error rendering --%>
        <c:set var="error">
            <form:errors path="logoUrl" />
        </c:set>
        <c:if test="${not empty error}">
            <tr><td>&nbsp;</td><td class="error">(${error})</td></tr>
        </c:if>
        <%-- END! error rendering --%>
        <%-- let's don't use it for now --%>
        <%--
        <tr>
            <td class="label supported-countries-label"><font color="red">*</font> Supported Countries:</td>
            <td>
                <select multiple="multiple" class="standard supported-countries-field">
                    <c:forEach items="${countries}" var="country">
                        <option>${country}</option>
                    </c:forEach>                                       
                </select>
            </td>            
        </tr>
        --%>        
        <tr>
            <td>&nbsp;</td>
            <td>
                <%-- <input id="register-submit" type="submit" value="" /> --%>
                <input class="custom-button ignore" type="submit" value="${submit}" />
                <input class="custom-button ignore" type="button" value="Cancel" onclick="window.location = 'user-companies.html';" />
            </td>
        </tr>
    </table>    
</form:form>