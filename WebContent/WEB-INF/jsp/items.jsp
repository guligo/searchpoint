<%@ include file="/WEB-INF/jsp/commons/taglibs-header.jsp" %>
<%@ page pageEncoding="UTF-8" isELIgnored="false" %>

<script type="text/javascript">   
    function onImageError(image) {
    	image.src = 'css/searchpoint/images/no-photo-available.jpg';
        image.onerror = '';
        return true;
    }

    $(document).ready(function() {
        $("#items").dataTable();
    });
</script>
<div id="items-container" class="indent">
    <div id="navigation">
        <c:forEach items="${navigationSequenceBean.navigationSequence}" var="navigation" varStatus="status">
            <c:choose>
                <c:when test="${navigation.link != null}">
                    <a href="${navigation.link}">
                        ${navigation.name}
                    </a>
                </c:when>
                <c:otherwise>
                    ${navigation.name}
                </c:otherwise>
            </c:choose>            
            <c:if test="${status.count != fn:length(navigationSequenceBean.navigationSequence)}">
                >>
            </c:if>
        </c:forEach>
    </div>
    <table id="items" class="displaytagTable">
        <thead>
	        <tr>
		        <th>Image</th>
		        <th>Name</th>
		        <th>Shop</th>
		        <th>Manufacturer</th>
		        <th>In Stock</th>
		        <th>Price</th>
	        </tr>
        </thead>
        <tbody>
            <c:forEach items="${items}" var="item">
                <tr>
                    <td class="item-image">
                        <c:set var="itemImageUrl" value="${item.imageUrl}" />
                        <c:if test="${empty item.imageUrl}">
                            <c:set var="itemImageUrl" value="empty" />
                        </c:if>
                        <img src="${itemImageUrl}" onerror="onImageError(this);" />
                    </td>
                    <td class="item-name">
                        <strong>${item.name}</strong>
                    </td>
                    <td class="item-company">
                        <a href="${item.company.homepageUrl}">
                            ${item.company.title}
                        </a>
                    </td>
                    <td class="item-manufacturer">
                        <c:choose>
			                <c:when test="${item.manufacturer != null}">
			                    ${item.manufacturer}
			                </c:when>
			                <c:otherwise>
			                    Unknown
			                </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="item-in-stock">
                        ${item.inStock}
                    </td>
                    <td class="item-price">
                        <strong>
			                <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2">
			                    ${item.price}
			                </fmt:formatNumber>
			            </strong>
                        ${item.currency}
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <%--
    <display:table id="items" list="${items}" requestURI="${requestUri}" class="displaytagTable" pagesize="10">
        <display:column class="item-image" title="Image">
            <img src="${items.imageUrl}" onerror="onImageError(this);" />
        </display:column>
        <display:column class="item-name" title="Name" sortable="true">
            <strong>${items.name}</strong>
        </display:column>
        <display:column class="item-company" title="Shop" sortable="true">
            <a href="${items.company.homepageUrl}">
                ${items.company.title}
            </a>
        </display:column>            
        <display:column class="item-manufacturer" title="Manufacturer" sortable="true">
            <c:choose>
                <c:when test="${items.manufacturer != null}">
                    ${items.manufacturer}
                </c:when>
                <c:otherwise>
                    Unknown
                </c:otherwise>
            </c:choose>
        </display:column>
        <display:column class="item-in-stock" title="In Stock" sortable="true" property="inStock" />
        <display:column class="item-price" title="Price" sortable="true">
            <strong>
                <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2">
                    ${items.price}
                </fmt:formatNumber>
            </strong>
            ${items.currency}
        </display:column>
    </display:table>
    --%>
</div>