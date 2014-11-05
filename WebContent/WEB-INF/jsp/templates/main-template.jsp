<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/commons/taglibs-header.jsp" %>
<%@ page pageEncoding="UTF-8" isELIgnored="false" %>

<html>
    <head> 
        <!--[if IE]>
        <meta http-equiv="Page-Enter" content="blendTrans(duration=0)" />
        <meta http-equiv="Page-Exit" content="blendTrans(duration=0)" />
        <![endif]-->
    
        <title>
            <tiles:getAsString name="title" />
        </title>
        <link type="text/css" rel="stylesheet" href="css/jquery/smoothness/jquery-ui-1.8.12.custom.css" />
        <link type="text/css" rel="stylesheet" href="css/displaytag/displaytag.css" />
        <link type="text/css" rel="stylesheet" href="css/yellow/style.css" />
        <link type="text/css" rel="stylesheet" href="css/yellow/layout.css" />        
        <link type="text/css" rel="stylesheet" href="css/searchpoint/searchpoint.css" /> 
        <link type="text/css" rel="stylesheet" href="css/shjs/sh_style.css" /> 

        <script type="text/javascript" src="js/yellow/cufon-yui.js"></script>
        <script type="text/javascript" src="js/yellow/Swis721_Md_BT_400.font.js"></script>
        <script type="text/javascript" src="js/yellow/Swis721_Hv_BT_400.font.js"></script>
        <script type="text/javascript" src="js/yellow/cufon-replace.js"></script>
        <script type="text/javascript" src="js/jquery/jquery-1.6.js"></script>
        <script type="text/javascript" src="js/jquery/jquery-ui-1.8.12.custom.min.js"></script>
        <script type="text/javascript" src="js/shjs/sh_main.js"></script>
        <script type="text/javascript" src="js/shjs/sh_xml.js"></script>
        <script type="text/javascript" src="js/dataTables/jquery.dataTables.js"></script>
        <!-- get source code -->
        <script src="http://cdn.jquerytools.org/1.2.5/tiny/jquery.tools.min.js"></script>
        <script src="http://cdn.jquerytools.org/1.2.5/form/jquery.tools.min.js"></script>
		<!--[if IE]>
            <link type="text/css" rel="stylesheet" href="css/yellow/ie.css" />
            <link type="text/css" rel="stylesheet" href="css/searchpoint/searchpoint-ie.css" />
		<![endif]-->
		<script type="text/javascript">
		    $(document).ready(function() {
		    	Cufon.now();
            });		   
		</script>
    </head>
    <body id="page1">        
        <div id="main-tail-top">
            <div id="main-tail-bot">
			    <div id="main-tail-ver">
                    <div id="main-bg-top">
                        <div id="main-bg-bot">
                            <div id="main">                            
                                <!-- header -->
					            <div id="header"> <a href="index.html"><img src="css/searchpoint/images/findforit-logo.png" id="logo" alt="" /></a>
					                <ul id="navi">
					                    <c:if test="${!empty sessionScope['user']}">
                                            <li>
                                                <div id="welcome-container">
                                                    Welcome, <b>${sessionScope['user'].email}</b>!
                                                </div>
                                            </li>                                            
                                        </c:if>
                                        <%--           
                                        <li id="navi-0"><a href="#">Share</a></li>
                                        --%>
					                    <li id="navi-2"><a href="javascript:showContactsPopup();">Contacts</a></li>
					                    <%--
					                    <li id="navi-3"><a href="#">Location</a></li>
					                    <li id="navi-4"><a href="#">Language</a></li>
					                    --%>
					                    <c:if test="${empty sessionScope['user']}">
                                            <li id="navi-5"><a href="login.html">Login</a></li>   
                                        </c:if>
					                    <c:if test="${!empty sessionScope['user']}">
                                            <li id="navi-5"><a href="logout.html">Logout</a></li>   
					                    </c:if>  
					                </ul>					                
						            <div id="menu">
							            <div id="left">
                                            <div id="right">
                                                <c:set var="tab">
                                                    <tiles:getAsString name="tab" />
                                                </c:set>
							                    <ul>
                                                    <li class="${tab == 1 ? 'extra act' : ''}">
                                                        <a href="home.html"><span>Search</span></a>
                                                    </li>
                                                    <li class="${tab == 2 ? 'extra act' : ''}">
							                            <a href="categories.html">Catalogue</a>
                                                    </li>
							                        <li class="${tab == 3 ? 'extra act' : ''}">
							                            <a href="companies.html">Shops</a>
							                        </li>
                                                    <li class="${tab == 5 ? 'extra act last' : 'last'}">
                                                        <a href="info.html">Advertising</a>
                                                    </li>
							                    </ul>
                                            </div>
							            </div>
						            </div>
					            </div>
					            <!-- content  -->           
					            <div id="content">
					                <tiles:insertAttribute name="content" />
					            </div>
                                <!-- footer -->
                                <div id="footer">
                                    <div class="inner">
                                        <div class="wrapper">
                                            <ul>
                                                <li><a href="home.html">Search</a></li>
                                                <li><a href="categories.html">Catalogue</a></li>
                                                <li><a href="companies.html">Shops</a></li>
                                                <li><a href="login.html">Advertising</a></li>
                                            </ul>
                                            <p>Cool Company &copy; 2011 <a href="javascript:showTermsPopup();">Terms of Use</a></p>
                                        </div>
                                    </div>
                                </div>                                
			                </div>			          
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/jsp/popups/popups.jsp" />		
    </body>
</html>