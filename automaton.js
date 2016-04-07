
$( document ).ready(function() {
	 var chart;
	 var stepDescription = ["st=>start","e=>end: End"];
	 var steps =[];
	 var stepCount =1 ;
	 $("#soap-form").hide();
	 $("#rest-form").hide();
	 $("#shell-form").hide();
	 $("#database-form").hide();
	 $("#conf-tab").hide();
	 $("#exec-tab").hide();

	 $("#automaton,#home-menu").click(function(event) {
		 $("#conf-tab").hide();
		 $("#exec-tab").hide();
		 $("header").show();
	 });
	 
	 $("#conf-menu,#create-wf-btn").click(function(event) {
		 $("header").hide();
		 $("#exec-tab").hide();
		 $("#conf-tab").show();
	 });
	 
	 $("#exec-menu").click(function(event) {
		 $("header").hide();
		 $("#conf-tab").hide();
		 $("#exec-tab").show();
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

	$('#workflow-name').on('input', function() {
		var input=$(this);
		var is_name=input.val();
		if(is_name){input.removeClass("invalid").addClass("valid");}
		else{input.removeClass("valid").addClass("invalid");}
	});
	
	$("#wf-cancel-btn").click(function(event) {
		$(".list-group").hide();
		$("#shell-step-btn").hide();
		$("#rest-step-btn").hide();
		$("#soap-step-btn").hide();
		chart.clean();
	});
	
	$("#wf-save-btn").click(function(event) {
		alert("Workflow: "+$("#workflow-name").val());
		
	});
 
	$("#database").click(function(event) {
		$("#steps").hide();
		$("#database-form").show();
		$("#soap-form").hide();
		$("#rest-form").hide();
		$("#shell-form").hide();
	});
	$("#soap").click(function(event) {
		$("#steps").hide();
		$("#database-form").hide();
		$("#soap-form").show();
		$("#rest-form").hide();
		$("#shell-form").hide();
	});
	$("#rest").click(function(event) {
		$("#steps").hide();
		$("#database-form").hide();
		$("#soap-form").hide();
		$("#rest-form").show();
		$("#shell-form").hide();
	});
	$("#shell").click(function(event) {
		$("#steps").hide();
		$("#database-form").hide();
		$("#soap-form").hide();
		$("#rest-form").hide();
		$("#shell-form").show();
	});
	
	$("#db-save-btn").click(function( event ) {
		var step_name = $("#db-step-name").val();
		$("#db-step-name").val('');
		$("#database-form").hide();
		$("#steps").show();
		$(".list-group").append("<a href=\"#\" id=\"db-step-btn\"class=\"list-group-item list-group-item-success btn btn-primary btn-xl\">"+step_name+"</a>");
 		addStep(stepCount,step_name);
     });
	 
 	$("#shell-save-btn").click(function( event ) {
 		var step_name = $("#shell-step-name").val();
		$("#shell-step-name").val('');
		$("#shell-form").hide();
		$("#steps").show();
		$(".list-group").append("<a href=\"#\" id=\"shell-step-btn\"class=\"list-group-item list-group-item-success btn btn-warning\">"+step_name+"</a>");
 		addStep(stepCount,step_name);
     });

 	$("#rest-save-btn").click(function( event ) {
 		var step_name = $("#rest-step-name").val();
		$("#rest-step-name").val('');
		$("#rest-form").hide();
		$("#steps").show();
		$(".list-group").append("<a href=\"#\" id=\"rest-step-btn\"class=\"list-group-item list-group-item-success btn btn-info\">"+step_name+"</a>");
 		addStep(stepCount,step_name);
     });

 	$("#soap-save-btn").click(function( event ) {
 		var step_name = $("#soap-step-name").val();
		$("#soap-step-name").val('');
		$("#soap-form").hide();
		$("#steps").show();
		$(".list-group").append("<a href=\"#\" id=\"soap-step-btn\"class=\"list-group-item list-group-item-success btn btn-success\">"+step_name+"</a>");
 		addStep(stepCount,step_name);
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
               'fill': 'white'
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
				addStep(stepCount,currentEntry.val());
			}).on('click', '.btn-remove', function(e) {
		$(this).parents('.entry:first').remove();
		e.preventDefault();
		return false;
	});

});





