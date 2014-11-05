<%@ include file="/WEB-INF/jsp/commons/taglibs-header.jsp" %>
<%@ page pageEncoding="UTF-8" isELIgnored="false" %>

<div id="content" class="categories-container">
    <div class="indent">
		<div class="wrapper">
		    <div class="box-1 no-indent">
		        <div class="left">
		            <div class="right">
		                <div class="inner-2">
		                    <div>		                        
		                        <c:set var="itemsPerRow" value="3" />		                        
		                        <table>                     
									<c:forEach items="${categories}" var="category" varStatus="status">
									    <c:if test="${(status.count  - 1) % itemsPerRow == 0}">
                                            <tr>
                                        </c:if>
                                        <td class="category-cell">                        		                                		                                    
                                            <table>
                                                <tr>
                                                    <td>                                                                                               
                                                        <a href="items.html?id=${category.id}" class="emp-category-link">
                                                            <img src="${category.imageUrl}" /><c:out value="${category.name}" />
                                                        </a>                                         
                                                    </td>
                                                </tr>
                                                <c:forEach items="${category.children}" var="child">
                                                    <tr>
                                                        <td>                                                
                                                            <a href="items.html?id=${child.id}" class="category-link">
                                                                <img src="css/searchpoint/icons/tag-orange.png">${child.name} <b>(${fn:length(child.items)})</b>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>                                                                        
                                            </table>			                                    
                                        </td>		                                    
		                                <c:if test="${(status.count  - 1) % itemsPerRow == (itemsPerRow - 1)}">
                                            </tr>
                                            <tr>
                                                <td class="spacer" colspan="3">
                                                    &nbsp;
                                                </td>
                                            </tr>
                                        </c:if>            	                                	                              
	                                </c:forEach>
                                </table>
                            </div>					
						</div>						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>