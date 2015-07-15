<%@ include file="/WEB-INF/jsp/commons/taglibs-header.jsp" %>
<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page isErrorPage="true" %>
<%@ page import="java.io.StringWriter" %>
<%@ page import="java.io.PrintWriter"%>

<html>
    <head> 
        <title>
            SearchPoint | Error Page
        </title>
        <link type="text/css" rel="stylesheet" href="css/displaytag/displaytag.css" />
        <link type="text/css" rel="stylesheet" href="css/yellow/style.css" />
        <link type="text/css" rel="stylesheet" href="css/yellow/layout.css" />        
        <link type="text/css" rel="stylesheet" href="css/searchpoint/searchpoint.css" />
        <script type="text/javascript" src="js/yellow/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="js/yellow/cufon-yui.js"></script>
        <script type="text/javascript" src="js/yellow/Swis721_Md_BT_400.font.js"></script>
        <script type="text/javascript" src="js/yellow/Swis721_Hv_BT_400.font.js"></script>
        <script type="text/javascript" src="js/yellow/cufon-replace.js"></script>
        <!--[if lt IE 7]>
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
                                <div class="wrapper">
                                    <div id="stack-trace">                     
		                                <!-- content  -->           
		                                <%
							                StringWriter stringWriter = new StringWriter();
							                PrintWriter printWriter = new PrintWriter(stringWriter);
							                exception.printStackTrace(printWriter);
							                out.print(stringWriter);                    
							                stringWriter.close();
							                printWriter.close();
							            %>
							            <br />
							            <br />
							            <div id="home-link">
							                <a href="home.html">Home</a>
							            </div>
						            </div>
					            </div>
                            </div>                    
                        </div>
                    </div>
                </div>
            </div>
        </div>                  
    </body>    
</html>