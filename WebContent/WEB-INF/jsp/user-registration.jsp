<%@ include file="/WEB-INF/jsp/commons/taglibs-header.jsp" %>
<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>

<script type="text/javascript">
    $(document).ready(function() {
        $("#userRegistrationForm :input:not(.ignore):not(#agreeCheckBox)").tooltip({
            position: "center right",
            offset: [0, 10],
            effect: "fade"
        });        
        $("#agreeCheckBox").tooltip({
            position: "center right",
            offset: [0, 300],
            effect: "fade"
        });
    });    
</script>
<form:form id="userRegistrationForm" commandName="user" method="post" action="submit-user-registration.html">    
    <table cellpadding="10" cellspacing="10">
        <tr>
            <td class="label"><font color="red">*</font> E-mail:</td>
            <td>                         
                <form:input path="email" cssClass="standard" title="Enter your e-mail (5 to 30 characters)" />
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
            <td class="label"><font color="red">*</font> Password:</td>
            <td>
                <form:password path="password" cssClass="standard" title="Enter preffered password (5 to 30 characters)" />
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
            <td class="label"><font color="red">*</font> Confirm:</td>
            <td>
                <form:password path="confirm" cssClass="standard" title="Repeat preffered password" maxlength="30" />
            </td>
        </tr>        
        <tr>
            <td class="label large-field-label"><font color="red">*</font> Verification:</td>
            <td>
                <%
                    ReCaptcha c = ReCaptchaFactory.newReCaptcha("6LepCcUSAAAAAJII-f8UOfl0EVe9zjj_i-_XGGdl", "6LepCcUSAAAAANy8pusO7G-yLkVGh0EtsweKt1WE", false);
                    out.print(c.createRecaptchaHtml(null, null));
                %>
            </td>            
        </tr>
         <%-- error rendering --%>
        <c:set var="error">
            <form:errors path="captcha" />
        </c:set>
        <c:if test="${not empty error}">
            <tr><td>&nbsp;</td><td class="error">(${error})</td></tr>
        </c:if>
        <%-- END! error rendering --%>
        <tr>
            <td>&nbsp;</td>
            <td>
                <form:checkbox id="agreeCheckBox" path="agree" title="Check the box if you have read 'Terms of Use'" />                
                I do agree with the <a href="javascript:showTermsPopup();" class="terms-of-use-link">Terms of Use</a>.
            </td>            
        </tr>
         <%-- error rendering --%>
        <c:set var="error">
            <form:errors path="agree" />
        </c:set>
        <c:if test="${not empty error}">
            <tr><td>&nbsp;</td><td class="error">(${error})</td></tr>
        </c:if>
        <%-- END! error rendering --%>
        <tr>
            <td>&nbsp;</td>
            <td>
                <%-- <input id="register-submit" type="submit" value="" /> --%>
                <input class="custom-button ignore" type="submit" value="Register" />
                <input class="custom-button ignore" type="button" value="Cancel" onclick="window.location = 'login.html';" />
            </td>
        </tr>
    </table>
</form:form>