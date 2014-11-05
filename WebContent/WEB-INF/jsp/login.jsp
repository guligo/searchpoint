<%@ include file="/WEB-INF/jsp/commons/taglibs-header.jsp" %>
<%@ page pageEncoding="UTF-8" isELIgnored="false" %>

<script type="text/javascript">
    $(document).ready(function() {
        $("#loginForm :input").tooltip({
            position: "center right",
            offset: [0, 10],
            effect: "fade"
        });        
    });    
</script>
<form:form id="loginForm" commandName="user" method="post" action="submit-login.html">
    <table cellpadding="10" cellspacing="10">
        <tr>
            <td class="label">E-mail:</td>
            <td>
                <form:input path="email" cssClass="standard" title="E-mail provided by you during the registration" />
            </td>
        </tr>
        <%-- error rendering --%>
        <c:set var="error">
            <form:errors path="email" />
        </c:set>
        <c:if test="${not empty error}">
            <tr><td>&nbsp;</td><td class="error">(${error})</td></tr>
        </c:if>
        <%-- END! error rendering --%>
        <tr>
            <td class="label">Password:</td>
            <td>
                <form:password path="password" cssClass="standard" title="Password provided by you during the registration" />
            </td>
        </tr>
        <%-- error rendering --%>
        <c:set var="error">
            <form:errors path="password" />
        </c:set>
        <c:if test="${not empty error}">
            <tr><td>&nbsp;</td><td class="error">(${error})</td></tr>
        </c:if>
        <%-- END! error rendering --%>    
        <tr>
            <td>&nbsp;</td>
            <td>
		        <%-- <input id="login-submit" type="submit" value="" /> --%>
		        <input class="custom-button" type="submit" value="Login" />
		    </td>
        </tr>
    </table>
</form:form>