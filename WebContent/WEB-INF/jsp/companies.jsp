<%@ include file="/WEB-INF/jsp/commons/taglibs-header.jsp" %>
<%@ page pageEncoding="UTF-8" isELIgnored="false" %>

<div id="content">
    <div class="indent">
        <div class="wrapper">
            <div class="box-1 no-indent">
                <div class="left">
                    <div class="right">
                        <div id="global-companies-container" class="inner-2">
                            <h2>
                                <strong><img src="css/yellow/images/icon-1.gif" alt="" />Data Providing Shops</strong>
                            </h2>
                            <table cellpadding="0" cellspacing="0">
	                            <c:forEach items="${keys}" var="key">
	                                <tr class="key-row">
	                                    <td class="key">  
	                                        <strong>${key}</strong>
	                                    </td>
	                                    <td class="back-to-top">
	                                        <a href="#">[Back to top]</a>
	                                    </td>
	                                </tr>
	                                <tr class="data-row">
	                                    <td class="data-row-cell" colspan="2">
	                                        <c:choose>
	                                            <c:when test="${!empty companies[key]}">
	                                                <table>
	                                                    <c:set var="diff" value="${fn:length(companies[key]) / 3}" />
				                                        <c:forEach begin="0" end="${diff}" varStatus="status">
				                                            <tr>
				                                                <td align="left">				                                                    
				                                                    <a href="${companies[key][status.count - 1].homepageUrl}">
				                                                        <img src="css/searchpoint/icons/tag-orange.png">${companies[key][status.count - 1].title}
				                                                    </a>
				                                                </td>
				                                                <td align="left">
				                                                    <c:if test="${!empty companies[key][status.count + diff]}">
					                                                    <a href="${companies[key][status.count + diff].homepageUrl}">
	                                                                        <img src="css/searchpoint/icons/tag-orange.png">${companies[key][status.count + diff].title}
	                                                                    </a>
                                                                    </c:if>                              
				                                                </td>
				                                                <td align="left">
				                                                    <c:if test="${!empty companies[key][status.count + 1 + 2 * diff]}"> 
					                                                    <a href="${companies[key][status.count + 1 + 2 * diff].homepageUrl}">
	                                                                        <img src="css/searchpoint/icons/tag-orange.png">${companies[key][status.count + 1 + 2 * diff].title}
	                                                                    </a>
                                                                    </c:if>
				                                                </td>
				                                            </tr>
				                                        </c:forEach>
			                                        </table>
	                                            </c:when>
	                                            <c:otherwise>
	                                                <span class="empty">
	                                                    <c:out value="<Empty>" />
	                                                </span>
	                                            </c:otherwise>
	                                        </c:choose>
	                                    </td>
	                                </tr>
	                            </c:forEach>
                            </table>
                        </div>                      
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>