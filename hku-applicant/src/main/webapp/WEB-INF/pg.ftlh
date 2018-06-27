<html>
<script language="JavaScript">

function redirect() {
   document.DataForm.submit();
}
</script>
<body bgcolor="#FFFFFF" onload="redirect()">

<form action="https://ujpg.jtetbwkl.com.hk/PayPage/payPage.jsp" method="POST" name="DataForm">
<h1 class="apphead">Redirect to JETCO Payment Gateway</h1>
<input type="hidden" name="DO" value=${DO} />
</form>
</body>
</html>