
$( document ).ready(function() {
	 var chart;
	 var stepDescription = ["st=>start","e=>end: End"];
	 var steps =[];
	 var stepCount =1 ;
	 $("#soap-form").hide();
	 $("#rest-form").hide();
	 $("#shell-form").hide();
	 $("#database-form").hide();
	$(".step_wf_menu").click(function(event) {
		window.location.href="index.html"
		
	});
 
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
 
	$("#database").click(function(event) {
		$("#database-form").show();
		addStep(stepCount,"Database Operation");
	});
	$("#soap").click(function(event) {
		$("#soap-form").show();
		addStep(stepCount,"Soap Operation");
	});
	$("#rest").click(function(event) {
		$("#rest-form").show();
		addStep(stepCount,"Rest Operation");
	});
	$("#shell").click(function(event) {
		$("#shell-form").show();
		addStep(stepCount,"Shell Operation");
	});
	
	$(".db-action-btn").click(function( event ) {
 		$(".list-group").append("<a href=\"#\" id=\"db-step-btn\" class=\"list-group-item list-group-item-success btn btn-primary \">Step - </a>");
 		addStep(stepCount,"Database Operation");
     });

	
	 
 	$(".sh-action-btn").click(function( event ) {
 		$(".list-group").append("<a href=\"#\" id=\"shell-step-btn\"class=\"list-group-item list-group-item-success btn btn-success\">Step - </a>");
 		addStep(stepCount,"Shell Script");
     });



 	$(".rest-action-btn").click(function( event ) {
 		$(".list-group").append("<a href=\"#\" id=\"soap-step-btn\"class=\"list-group-item list-group-item-success btn btn-info\">Step - </a>");
 		addStep(stepCount,"Rest Webservice");
     });

 	$(".soap-action-btn").click(function( event ) {
 		$(".list-group").append("<a href=\"#\" id=\"rest-step-btn\"class=\"list-group-item list-group-item-success btn btn-warning\">Step - </a>");
 		addStep(stepCount,"Soap service");
     });

 	$(document).on('click','a#db-step-btn',function(){
  		window.location.href = "step-details.html";
  		sleep()
  		alert($("#step-type").val("2"));
	});

	$(document).on('click','a.shell-step-btn',function(){
  		window.location.href = "step-details.html";
	});

 	$("#shell-step").click(function(event) {
 		$("#step-attr1-label").val = "Hostname";
 		$("#step-attr2-label").val = "Port";
 		$("#step-attr3-label").val = "Username";
 		$("#step-attr4-label").val = "Password";
 		$("#step-attr5-label").val = "Script(with Path)";
 		$("#step-name").val = "XXXXXX";
 		$("#step-attr1-label").val = "XXXXX";
 		$("#step-attr2-label").val = "Port";
 		$("#step-attr3-label").val = "Username";
 		$("#step-attr4-label").val = "Password";
 		$("#step-attr5-label").val = "Query below/File Input";
 	});
 
 	function addStep(number,stepName) {
 		alert("addStep");
 		addStepDescription(number, stepName);
 		var stepFlow = addStepFlow();
 		var code="";
 		for (var index= 0; index < stepDescription.length; index++)
 			code =code+"\n"+stepDescription[index];
 		code= code+"\n"+stepFlow;
 		draw(code);
 		stepCount++;
 	}
 	
 	
 	
 	function addStepDescription(number, stepName) {
 		steps.push("step"+number);
 		stepDescription.push("step"+number+"=>operation: "+stepName);
 	}
 	
 	function addStepFlow() {
 		var stepFlow;
 		var previousStep;
 		for	(var index = 0; index < steps.length; index++) {
 		    if(index == 0)
 		    	stepFlow ="st->"+steps[index];
 		    else
 		    	stepFlow= stepFlow+"\n"+previousStep+"->"+steps[index];
 		    previousStep = steps[index];
 		}
 		stepFlow = stepFlow+"\n"+previousStep+"->e";
 		return stepFlow;
 	}
 	
 	function draw(code) {
 		
         if (chart) {
           chart.clean();
         }
         alert(code);
         chart = flowchart.parse(code);
         chart.drawSVG('canvas', {
           // 'x': 30,
           // 'y': 50,
           'line-width': 3,
           'line-length': 50,
           'text-margin': 10,
           'font-size': 14,
           'font': 'normal',
           'font-family': 'Helvetica',
           'font-weight': 'normal',
           'font-color': 'black',
           'line-color': 'black',
           'element-color': 'black',
           'fill': 'white',
           'yes-text': 'yes',
           'no-text': 'no',
           'arrow-end': 'block',
           'scale': 1,
           'symbols': {
             'start': {
               'font-color': 'red',
               'element-color': 'green',
               'fill': 'yellow'
             },
             'end':{
               'class': 'end-element'
             }
           },
           'flowstate' : {
             'past' : { 'fill' : '#CCCCCC', 'font-size' : 12},
             'current' : {'fill' : 'yellow', 'font-color' : 'red', 'font-weight' : 'bold'},
             'future' : { 'fill' : '#FFFF99'},
             'request' : { 'fill' : 'blue'},
             'invalid': {'fill' : '#444444'},
             'approved' : { 'fill' : '#58C4A3', 'font-size' : 12, 'yes-text' : 'APPROVED', 'no-text' : 'n/a' },
             'rejected' : { 'fill' : '#C45879', 'font-size' : 12, 'yes-text' : 'n/a', 'no-text' : 'REJECTED' }
           }
         });

 };
 
 
 $(document)
	.on('click','.btn-add',function(e) {
				e.preventDefault();
				var controlForm = $('.controls form:first'), currentEntry = $(
						this).parents('.entry:first'), newEntry = $(
						currentEntry.clone()).appendTo(controlForm);
				newEntry.find('input').val('');
				controlForm
						.find('.entry:not(:last) .btn-add')
						.removeClass('btn-add')
						.addClass('btn-remove')
						.removeClass('btn-success')
						.addClass('btn-danger')
						.html(
								'<span class="glyphicon glyphicon-minus"></span>');
				alert(currentEntry);
				addStep(stepCount,currentEntry.val());
			}).on('click', '.btn-remove', function(e) {
		$(this).parents('.entry:first').remove();
		e.preventDefault();
		return false;
	});

});





