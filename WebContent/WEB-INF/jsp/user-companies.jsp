<%@ include file="/WEB-INF/jsp/commons/taglibs-header.jsp" %>
<%@ page pageEncoding="UTF-8" isELIgnored="false" %>

<script type="text/javascript">
	function onImageError(image) {
		image.parentNode.parentNode.innerHTML = "No image";
	    // image.src = 'css/searchpoint/images/no-image-available.png';
	    // image.onerror = '';
	    return true;
	}
    
	$(document).ready(function() {
        $("#companies").dataTable();
    });
	
    var showDeleteConfirmationPopup = function(id) {
        $('#deleteConfirmationPopup').dialog({width: 500, resizable: false, buttons: [
            {
                text: 'Ok',
                click: function() {
                    window.location.href = 'delete-user-company.html?id=' + id;
                }
            },
            {
                text: 'Cancel',
                click: function() {
                    $(this).dialog('close');
                }
            }
        ]});
    };
</script>
<div id="deleteConfirmationPopup" title="Delete Shop" class="popup">Are you sure you want to delete this shop?</div>

<div id="companies-container" class="indent">
    <table id="companies" class="displaytagTable">
        <thead>
	        <tr>
	            <th class="company-title">Title</th>
	            <th class="company-data">Data</th>
	            <th class="company-logo">Logo</th>
	            <th class="company-items">Items</th>
	            <th class="company-status">Status</th>
	            <th class="company-actions">Actions</th>
	        </tr>
        </thead>
        <tbody>
	        <c:forEach items="${companies}" var="company">
		        <tr>
		            <td class="company-title">
		                <a href="${company.homepageUrl}">${company.title}</a>
		            </td>
		            <td class="company-data">
		                <c:choose>
			                <c:when test="${company.data.useUrl == null}">
			                    No items data from this shop
			                </c:when>
			                <c:when test="${company.data.useUrl == false && company.data.xml != null}">
			                    Uses data from file
			                    <a href="show-data.html?id=${company.data.id}" target="blank">                        
			                        <img src="css/searchpoint/icons/link.png" />
			                    </a>
			                </c:when>
			                <c:when test="${company.data.useUrl == true && company.data.url != null}">
			                    Uses data from URL
			                    <a href="${company.data.url}" target="blank">                        
			                        <img src="css/searchpoint/icons/link.png" />
			                    </a>
			                </c:when>
			                <c:otherwise>
			                    Error
			                </c:otherwise>
			            </c:choose>
		            </td>
		            <td class="company-logo">
		                <a href="${company.logoUrl}">
	                        <img src="${company.logoUrl}" onerror="onImageError(this);" />
	                    </a>
		            </td>
		            <td class="company-items">
		                <a href="company-items.html?id=${company.id}">
	                        <strong>${fn:length(company.items)}</strong>
	                    </a>
		            </td>
		            <td class="company-status">
		                <c:if test="${company.status == 'COMPANY_STATUS_OK'}">
			                <img src="css/searchpoint/icons/ok.png" class="icon" title="Company is successfully registred and data are feeded" />
			            </c:if>
			            <c:if test="${company.status == 'COMPANY_STATUS_ERROR'}">
			                <img src="css/searchpoint/icons/error.png" class="icon" title="Some error occured during data processing" />
			            </c:if>
			            <c:if test="${company.status == 'COMPANY_STATUS_DISABLED'}">
			                <img src="css/searchpoint/icons/disabled.png" class="icon" title="Company has been disabled" />
			            </c:if>
			            <c:if test="${company.status == 'COMPANY_STATUS_PENDING'}">
			                <img src="css/searchpoint/icons/pending.png" class="icon" title="Company is pending on being approved by administrators" />
			            </c:if>
		            </td>
		            <td class="company-actions">
		                <a href="edit-user-company.html?id=${company.id}">
			                <img src="css/searchpoint/icons/edit.png" class="icon" title="Edit" />
			            </a>
			            <a href="javascript:showDeleteConfirmationPopup(${company.id});">
			                <img src="css/searchpoint/icons/delete.png" class="icon" title="Delete" />
			            </a>
			            <a href="data-upload.html?id=${company.id}">
			                <img src="css/searchpoint/icons/data.png" class="icon" title="Data Upload" />
			            </a>
		            </td>
		        </tr>
	        </c:forEach>
        </tbody>
    </table>
 
    <%--
    <display:table id="companies" list="${companies}" requestURI="user-companies.html" class="displaytagTable" pagesize="10">
        <display:column class="company-title" title="Title" sortable="true">
            <a href="${companies.homepageUrl}">${companies.title}</a>
        </display:column>        
        <display:column class="company-data" title="Data">
            <c:choose>
                <c:when test="${companies.data.useUrl == null}">
                    No items data from this shop
                </c:when>
                <c:when test="${companies.data.useUrl == false && companies.data.xml != null}">
                    Uses data from file
                    <a href="show-data.html?id=${companies.data.id}" target="blank">                        
                        <img src="css/searchpoint/icons/link.png" />
                    </a>
                </c:when>
                <c:when test="${companies.data.useUrl == true && companies.data.url != null}">
                    Uses data from URL
                    <a href="${companies.data.url}" target="blank">                        
                        <img src="css/searchpoint/icons/link.png" />
                    </a>
                </c:when>
                <c:otherwise>
                    Error
                </c:otherwise>
            </c:choose>
        </display:column>
        <display:column class="company-logo" title="Logo">
            <a href="${companies.logoUrl}">
                <img src="aaa" onerror="onImageError(this);" />
            </a>
        </display:column>
        <display:column class="company-items" title="Items" sortable="true">
            <a href="company-items.html?id=${companies.id}">
                <strong>${fn:length(companies.items)}</strong>
            </a>
        </display:column>
        <display:column class="company-status" title="Status">            
            <c:if test="${companies.status == 'COMPANY_STATUS_OK'}">
                <img src="css/searchpoint/icons/ok.png" class="icon" title="Company is successfully registred and data are feeded" />
            </c:if>
            <c:if test="${companies.status == 'COMPANY_STATUS_ERROR'}">
                <img src="css/searchpoint/icons/error.png" class="icon" title="Some error occured during data processing" />
            </c:if>
            <c:if test="${companies.status == 'COMPANY_STATUS_DISABLED'}">
                <img src="css/searchpoint/icons/disabled.png" class="icon" title="Company has been disabled" />
            </c:if>
            <c:if test="${companies.status == 'COMPANY_STATUS_PENDING'}">
                <img src="css/searchpoint/icons/pending.png" class="icon" title="Company is pending on being approved by administrators" />
            </c:if>
        </display:column>
        <display:column class="company-action" title="Actions">
            <a href="edit-user-company.html?id=${companies.id}">
                <img src="css/searchpoint/icons/edit.png" class="icon" title="Edit" />
            </a>
            <a href="javascript:showDeleteConfirmationPopup(${companies.id});">
                <img src="css/searchpoint/icons/delete.png" class="icon" title="Delete" />
            </a>
            <a href="data-upload.html?id=${companies.id}">
                <img src="css/searchpoint/icons/data.png" class="icon" title="Data Upload" />
            </a>
        </display:column>
    </display:table>
    --%>
</div>