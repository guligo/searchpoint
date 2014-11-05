<%@ include file="/WEB-INF/jsp/commons/taglibs-header.jsp" %>
<%@ page pageEncoding="UTF-8" isELIgnored="false" %>

<!-- header -->
<div id="header" class="sub-menu">
    <div id="menu">
        <div id="left">
            <div id="right">
                <c:set var="subTab">
                    <tiles:getAsString name="subTab" />
                </c:set>
                <ul>                    
                    <c:if test="${empty sessionScope['user']}">
                        <li class="${subTab == 3 ? 'extra act last' : 'last'}">
                            <a href="login.html">Login</a>
                        </li>
                    </c:if>
                    <c:if test="${empty sessionScope['user']}">
	                    <li class="${subTab == 2 ? 'extra act' : ''}">
	                        <a href="user-registration.html">Registration</a>
	                    </li>
                    </c:if>                                       
                    <c:if test="${!empty sessionScope['user']}">
                        <li class="${subTab == 5 ? 'extra act last' : 'last'}">
                            <a href="user-companies.html">My Shops</a>
                        </li>
                    </c:if>
                    <c:if test="${!empty sessionScope['user']}">
                        <li class="${subTab == 4 ? 'extra act' : ''}">
                            <a href="add-user-company.html">Add Shop</a>
                        </li>
                    </c:if>
                    <li class="${subTab == 1 ? 'extra act' : ''}">
                        <a href="info.html">Info</a>
                    </li>                    
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- content -->
<c:set var="wrap">
    <tiles:getAsString name="wrap" />
</c:set>
<c:choose>
    <c:when test="${wrap == true}">
        <div id="content">
		    <div class="indent">
		        <div class="wrapper">
		            <div class="box-1 no-indent">
		                <div class="left">
		                    <div class="right">
		                        <div class="inner-2">
		                            <div class="wrapper">
		                                <div id="content">
		                                    <tiles:insertAttribute name="content" />
		                                </div>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
    </c:when>
    <c:otherwise>
         <tiles:insertAttribute name="content" />
    </c:otherwise>
</c:choose>