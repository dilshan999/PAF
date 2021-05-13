$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
	$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

//SAVE
$(document).on("click", "#btnSave", function(event)
	{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateProjectsForm();
	if (status != true)
	{
	$("#alertError").text(status);
	$("#alertError").show();
	return;
	}
	
	// If valid------------------------
	var type = ($("#hidProjectIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
	{
	url : "ProjectsAPI",
	type : type,
	data : $("#formProject").serialize(),
	dataType : "text",
	complete : function(response, status)
	{
	onProjectSaveComplete(response.responseText, status);
	}
	});
});


function onProjectSaveComplete(response, status)
{
	if (status == "success")
	{
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
	$("#alertSuccess").text("Successfully saved.");
	$("#alertSuccess").show();
	$("#divProjectsGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error")
	{
	$("#alertError").text(resultSet.data);
	$("#alertError").show();
	}
	} else if (status == "error")
	{
	$("#alertError").text("Error while saving.");
	$("#alertError").show();
	} else
	{
	$("#alertError").text("Unknown error while saving..");
	$("#alertError").show();
	}
	$("#hidProjectIDSave").val("");
	$("#formProject")[0].reset();
}


$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidProjectIDSave").val($(this).data("projectid"));
	$("#projectName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#researcherID").val($(this).closest("tr").find('td:eq(2)').text());
	$("#description").val($(this).closest("tr").find('td:eq(3)').text());
	$("#price").val($(this).closest("tr").find('td:eq(4)').text());
	$("#email").val($(this).closest("tr").find('td:eq(5)').text());
	$("#phone").val($(this).closest("tr").find('td:eq(6)').text());
})


$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
	url : "ProjectsAPI",
	type : "DELETE",
	data : "projectID=" + $(this).data("projectid"),
	dataType : "text",
	complete : function(response, status)
	{
	onProjectDeleteComplete(response.responseText, status);
	}
	});
})


function onProjectDeleteComplete(response, status)
{
	if (status == "success")
	{
	var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
		$("#alertSuccess").text("Successfully deleted.");
		$("#alertSuccess").show();
		$("#divProjectsGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error")
		{
		$("#alertError").text(resultSet.data);
		$("#alertError").show();
		}
	} 
	else if (status == "error")
	{
	$("#alertError").text("Error while deleting.");
	$("#alertError").show();
	} 
	else
	{
	$("#alertError").text("Unknown error while deleting..");
	$("#alertError").show();
	}
}





// CLIENT-MODEL================================================================
function validateProjectsForm()
{
// Name
	if ($("#projectName").val().trim() == "")
	{
	return "Insert Project Name.";
	}
// Researcher ID
	if ($("#researcherID").val().trim() == "")
	{
	return "Insert Researcher ID.";
}

// Description
if ($("#description").val().trim() == "")
{
return "Insert Description.";
}

// price -------------------------------
if ($("#price").val().trim() == "")
{
return "Insert Price.";
}

// is numerical value
var tmpPrice = $("#price").val().trim();
if (!$.isNumeric(tmpPrice))
{
return "Insert a numerical value for Price.";
}

// convert to decimal price
$("#price").val(parseFloat(tmpPrice).toFixed(2));

// Email -------------------------------
if ($("#email").val().trim() == "")
{
return "Insert Email.";
}

// Phone
if ($("#phone").val().trim() == "")
{
return "Insert Phone.";
}

return true;
}