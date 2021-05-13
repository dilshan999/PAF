<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@page import = "com.Projects" %>
      
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Projects handling</title>


<link rel = "stylesheet" href = "Views/bootstrap.min.css">
<script src = "Components/jquery-3.6.0.min.js"></script>
<script src = "Components/projects.js"></script>



</head>
<body>

<div class = "container"> 
	<div class="row">
		<div class="col">

		<h1>Projects</h1>
		
	<form id="formProject" name="formProject"  >
	<!--	Project ID:
		<input id="projectID" name="projectID" type="text" class="form-control form-control-sm"><br> -->
		Project Name:
		<input id="projectName" name="projectName" type="text" class="form-control form-control-sm"><br>
		Researcher ID:
		<input id="researcherID" name="researcherID" type="text" class="form-control form-control-sm"><br> 
		Description:
		<input id="description" name="description" type="text" class="form-control form-control-sm"><br>
		Price:
		<input id="price" name="price" type="text" class="form-control form-control-sm"><br>
		Email:
		<input id="email" name="email" type="text" class="form-control form-control-sm"><br>
		Phone:
		<input id="phone" name="phone" type="text" class="form-control form-control-sm"><br>
		
		
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidProjectIDSave" name="hidProjectIDSave" value="">
	</form>
    
    <div id="alertSuccess" class="alert alert-success"></div>
     <div id="alertError" class="alert alert-danger"></div>
    
    <br>
	<div id="divProjectsGrid">
	<%
	Projects projectObj = new Projects();
		out.print(projectObj.readProjects());
	%>
	</div>

<br>


</div>
</div>
</div>


</body>
</html>