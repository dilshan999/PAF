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
	var status = validateFundsForm();
	if (status != true)
	{
	$("#alertError").text(status);
	$("#alertError").show();
	return;
	}
	
	// If valid------------------------
	var type = ($("#hidFundIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
	{
	url : "FundsAPI",
	type : type,
	data : $("#formFunds").serialize(),
	dataType : "text",
	complete : function(response, status)
	{
	onFundSaveComplete(response.responseText, status);
	}
	});
});


function onFundSaveComplete(response, status)
{
	if (status == "success")
	{
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
	$("#alertSuccess").text("Successfully saved.");
	$("#alertSuccess").show();
	$("#divFundsGrid").html(resultSet.data);
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
	$("#hidFundIDSave").val("");
	$("#formFunds")[0].reset();
}


$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidFundIDSave").val($(this).data("fundid"));
	$("#admin_ID").val($(this).closest("tr").find('td:eq(1)').text());
	$("#product_ID").val($(this).closest("tr").find('td:eq(2)').text());
	$("#funds").val($(this).closest("tr").find('td:eq(3)').text());
	
})


$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
	url : "FundsAPI",
	type : "DELETE",
	data : "fund_ID=" + $(this).data("fundid"),
	dataType : "text",
	complete : function(response, status)
	{
	onFundDeleteComplete(response.responseText, status);
	}
	});
})


function onFundDeleteComplete(response, status)
{
	if (status == "success")
	{
	var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
		$("#alertSuccess").text("Successfully deleted.");
		$("#alertSuccess").show();
		$("#divFundsGrid").html(resultSet.data);
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
function validateFundsForm()
{
// Admin ID
	if ($("#admin_ID").val().trim() == "")
	{
	return "Insert Admin ID.";
	}
// Product ID
	if ($("#product_ID").val().trim() == "")
	{
	return "Insert Product ID.";
	}
// Fund 
if ($("#funds").val().trim() == "")
{
return "Insert Funds.";
}

// is numerical value
var tmpFunds = $("#funds").val().trim();
if (!$.isNumeric(tmpFunds))
{
return "Insert a numerical value for Funds.";
}

// convert to decimal 
$("#funds").val(parseFloat(tmpFunds).toFixed(2));


return true;
}