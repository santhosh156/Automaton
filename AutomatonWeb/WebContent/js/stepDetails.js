
$( document ).ready(function() {
 
	$(".submit-btn").click(function(event) {
		stepJson = {};
		stepJson["step_name"] = $("#step-name").val();
		stepJson["step_type"] = $("#step-type").val();
		stepJson["input_type"] = $("#step-inp-type").val();
		stepJson["output_type"] = $("#step-out-type").val();
		stepJson["attr"] = {};
		console.log(stepJson);
	});

	function createStepJSON(stepJson) {
		jsonObj = [];
		   	$("input[class=form-control]").each(function() {
		
		       	var id = $(this).attr("title");
		       	var email = $(this).val();
		
		       	item = {}
		       	item ["title"] = id;
		       	item ["email"] = email;
		
		       	jsonObj.push(item);
		   });
	}
 
	
	$(".db-action-btn").click(function( event ) {
 		$(".list-group").append("<a href=\"step-details.html\" class=\"list-group-item list-group-item-success btn-primary\">Step - </a>");
     });
 
 	$(".sh-action-btn").click(function( event ) {
 		$(".list-group").append("<a href=\"step-details.html\" class=\"list-group-item list-group-item-success btn btn-success\">Step - </a>");
     });

 	$(".rest-action-btn").click(function( event ) {
 		$(".list-group").append("<a href=\"step-details.html\" class=\"list-group-item list-group-item-success btn btn-info\">Step - </a>");
     });

 	$(".soap-action-btn").click(function( event ) {
 		$(".list-group").append("<a href=\"step-details.html\" class=\"list-group-item list-group-item-success btn btn-warning\">Step - </a>");
     });
});





