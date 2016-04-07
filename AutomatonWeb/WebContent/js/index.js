$( document ).ready(function() {
 
	$(".db-action-btn").click(function( event ) {
 		$(".list-group").append("<a href=\"step-details.html\" class=\"list-group-item list-group-item-success\">Step - </a>");
     });

	$(".submit-btn").click(function(event) {

		$.getJSON( "json/sample.json", function( data ) {
			$("#step-name").val(data.workflow.step.name);
			$("#step-type").val(data.workflow.step.type);
			$("#step-inp-type").val(data.workflow.step.input_type);
			$("#step-out-type").val(data.workflow.step.Output_type);
			$("#hostname").val(data.workflow.step.db_details.host_name);
			$("#port").val(data.workflow.step.db_details.port);
			$("#username").val(data.workflow.step.db_details.username);
			$("#pwd").val(data.workflow.step.db_details.password);
			$("#query-txt").val(data.workflow.step.db_details.Query);
		});
		
	});
 

});
