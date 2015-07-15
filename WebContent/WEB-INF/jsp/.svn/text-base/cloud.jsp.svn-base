<%@ include file="/WEB-INF/jsp/commons/taglibs-header.jsp" %>
<%@ page pageEncoding="UTF-8" isELIgnored="false" %>

<tags>
    <c:set var="fontSize" value="25" />
    <c:if test="${!empty searches}">
        <c:set var="maxCount" value="${searches[0].count}" />
    </c:if>
    <c:forEach items="${searches}" var="search">
        <a href="search.html?query=${search.word}&min=&max=" rel="tag" style="font-size: ${search.count * fontSize / maxCount}px;" target="_parent">
            ${search.word}
        </a>
    </c:forEach>    
</tags>