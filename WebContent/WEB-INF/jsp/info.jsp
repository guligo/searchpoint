<%@ include file="/WEB-INF/jsp/commons/taglibs-header.jsp" %>
<%@ page pageEncoding="UTF-8" isELIgnored="false" %>

<script type="text/javascript">
    $(document).ready(function() {
        sh_highlightDocument();
    });     
</script>
<div>
    <h2>
        <img src="css/yellow/images/icon-1.gif" />Information for Retailers
    </h2>
    <p>
        If you want your best offer products to be available on this site, just <a href="user-registration.html">create
        your account</a>, register e-shop in our system (you can register up to 8 shops). After registration of shop you
        will need to provide some data about your products in XML format by uploading simple text file or specifying remote
        URL. Snippet below shows the only valid XML format that is accepted by the system. 
    </p>
    <pre class="sh_xml" style="font-size: 1.2em; width: 98%; border: 1px dashed #cccccc; padding: 5px; background-color: #ffffcc;">
&lt;?xml version=&quot;1.0&quot; encoding=&quot;utf-8&quot;?&gt;
&lt;root&gt;
    &lt;item&gt;
        &lt;name>Apple iPhone 4 16GB&lt;/name&gt;
        &lt;link>http://some.eshop.commons/apple-iphone&lt;/link&gt;
        &lt;price>650.00 GBP&lt;/price&gt;
        &lt;image>http://some.eshop.commons/apple-iphone/image.png&lt;/image&gt;
        &lt;category>Cell Phones & Smartphones&lt;/category&gt;
        &lt;in_stock>1&lt;/in_stock&gt;
    &lt;/item&gt;
    &lt;item&gt;
        ...
    &lt;/item&gt;
    ...
&lt;/root&gt;</pre>
</div>