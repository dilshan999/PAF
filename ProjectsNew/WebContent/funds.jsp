<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import = "com.Funds" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Funds handling</title>

<link rel = "stylesheet" href = "Views/bootstrap.min.css">
<script src = "Components/jquery-3.6.0.min.js"></script>
<script src = "Components/funds.js"></script>

</head>
<body>

<div class = "container"> 
	<div class="row">
		<div class="col">

		<h1>Funds</h1>
		
	<form id="formFunds" name="formFunds"  >
		Admin ID:
		<input id="admin_ID" name="admin_ID" type="text" class="form-control form-control-sm"><br>
		Product ID:
		<input id="product_ID" name="product_ID" type="text" class="form-control form-control-sm"><br> 
		Funds:
		<input id="funds" name="funds" type="text" class="form-control form-control-sm"><br>
		
		
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidFundIDSave" name="hidFundIDSave" value="">
	</form>
    
    <div id="alertSuccess" class="alert alert-success"></div>
     <div id="alertError" class="alert alert-danger"></div>
    
    <br>
	<div id="divFundsGrid">
	<%
	Funds fundObj = new Funds();
		out.print(fundObj.readFunds());
	%>
	</div>

<br>


</div>
</div>
</div>

</body>
</html>