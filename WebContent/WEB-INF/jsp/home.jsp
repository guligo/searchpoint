<%@ include file="/WEB-INF/jsp/commons/taglibs-header.jsp" %>
<%@ page pageEncoding="UTF-8" isELIgnored="false" %>

<div id="content">
    <div class="indent">
        <div class="wrapper">
            <div class="box">
                <div class="inner">
                    <div class="wrapper">
                        <form:form id="search-form" action="search.html" commandName="searchBean" method="get">
                            <div class="wrapper">                                
                                <table>
                                    <spring:hasBindErrors name="searchBean">                                               
	                                    <tr>	                                        
	                                        <td>
	                                            <form:errors path="query" cssClass="error" cssStyle="font-style: normal;" />
	                                        </td>
	                                        <td>
	                                            <form:errors path="min" cssClass="error" cssStyle="font-style: normal;" />
	                                        </td>
	                                        <td>
	                                            <form:errors path="max" cssClass="error" cssStyle="font-style: normal;" />
	                                        </td>
	                                    </tr>
                                    </spring:hasBindErrors> 
                                    <tr>
                                        <td class="search-label">I'm looking for</td>
                                        <td class="search-label">Min price</td>
                                        <td class="search-label">Max price</td>
                                    </tr>                                    
                                    <tr>
                                        <td>
                                            <label class="search-field">
                                                <form:input path="query" />
                                            </label>
                                        </td>
                                        <td>
                                            <label class="price-field">
                                                <form:input path="min" />
                                            </label>
                                        </td>
                                        <td>
                                            <label class="price-field">
                                                <form:input path="max" />
                                            </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3" class="submit-button-cell">
                                            <input type="submit" value="" id="search-submit" />
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
            <div class="box-1">
                <div class="left">
                    <div class="right">
                        <div class="inner">
                            <div class="wrapper">
                                <div style="width: 100%; text-align: center;">
	                                <object width="700" height="700" type="application/x-shockwave-flash" data="flash/h2flashtagcloud/text_and_image_cloud.swf" id="cloud" style="visibility: visible; margin: -125px;" >
							            <param name="movie" value="flash/h2flashtagcloud/text_and_image_cloud.swf" />
							            <param name="wmode" value="transparent">
							            <param name="menu" value="false">
							            <param name="quality" value="best">
							            <param name="flashvars" value="cloud_data=cloud.html&amp;tcolor=0x999999&amp;tcolor2=0x666666&amp;hicolor=0x494949&amp;tspeed=150&amp;fontFace=Times New Roman">
							        </object>
						        </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>            
        </div>
    </div>    
</div>