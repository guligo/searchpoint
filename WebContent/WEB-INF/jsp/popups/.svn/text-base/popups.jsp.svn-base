<%-- this page contains global pop-ups which might occur on any page --%>
<%-- terms pop-up --%>
<script type="text/javascript">
    var showTermsPopup = function() {
        $('#termsPopup').dialog({width: 800, modal: true, resizable: false, buttons: [
            {
                text: 'Close',
                click: function() {
                    $(this).dialog('close');
                }
            }
        ]});
    };
</script>
<div id="termsPopup" title="Terms of Use" class="popup">
    <jsp:include page="/WEB-INF/jsp/popups/terms.jsp" />
</div>

<%-- contacts pop-up --%>
<script type="text/javascript">
    var showContactsPopup = function() {
        $('#contactsPopup').dialog({width: 550, modal: true, resizable: false, buttons: [
            {
                text: 'Close',
                click: function() {
                    $(this).dialog('close');
                }
            }
        ]});
    };
</script>
<div id="contactsPopup" title="Contacts" class="popup">
    <jsp:include page="/WEB-INF/jsp/popups/contacts.jsp" />
</div>

<%-- data upload loader pop-up --%>
<script type="text/javascript">
    var showDataUploadLoader = function() {
        $('#dataUploadLoader').dialog({width: 60, height: 150, modal: true, resizable: false});
        $('.ui-dialog-titlebar').hide();        
    };
</script>
<!--[if IE]>
<script type="text/javascript">
    var showDataUploadLoader = function() {
        $('#dataUploadLoader').dialog({width: 60, height: 150, modal: true, resizable: false});
        $('#dataUploadLoader').html(
            'This might take a while<br/>' +
            '<img src="css/searchpoint/images/loader.gif" width="53" height="53" />'
        );
        $('.ui-dialog-titlebar').hide();        
    };
</script>
<![endif]-->
<div id="dataUploadLoader" class="popup">
    This might take a while<br/>
    <img src="css/searchpoint/images/loader.gif" width="53" height="53" />
</div>