  /**
   *  HAMES ALERT SCRIPT
   */

  //A "stack" controls the direction and position
  // of a notification. Here we create an array w
  // with several custom stacks that we use later
  var Stacks = {
    stack_top_right: {
      "dir1": "down",
      "dir2": "left",
      "push": "top",
      "spacing1": 10,
      "spacing2": 10
    },
    stack_bar_top: {
      "dir1": "down",
      "dir2": "right",
      "push": "top",
      "spacing1": 0,
      "spacing2": 0
    },
    stack_context: {
      "dir1": "down",
      "dir2": "left",
      "context": $("#stack-context")
    },
 };
  
var SuccessAlert = (function () {
	this.handleSuccess = function(data){
		this.successBarTop(data.message);
	},
	this.successBarTop = function(data) {
		new PNotify({
	        title: 'SAVED',
	        text: data.successDescription,
	        shadow: "",
	        opacity: 1,
	        addclass: "stack_bar_top",
	        type: "success",
	        stack: Stacks["stack_bar_top"],
	        width: findWidth("stack_bar_top"),
	        delay: 1500
        }); 
	},
	this.findWidth = function(noteStack){
		if (noteStack == "stack_bar_top") {
          return "100%";
        }
        if (noteStack == "stack_bar_bottom") {
          return "70%";
        } else {
          return "290px";
        }
	};
	return this;
})();

var ErrorAlert = (function(){
	this.handleError = function(data){
		if(data.errorSubCode == "401"){
			this.errorBarTop(data);
		}else if(data.errorSubCode = "422"){
			this.handleValidationError(data);
		}
	},
	this.errorBarTop = function(data){
		new PNotify({
	        title: data.errorCode,
	        text: data.errorDescription,
	        shadow: "",
	        opacity: 1,
	        addclass: "stack_bar_top",
	        type: "danger",
	        stack: Stacks["stack_bar_top"],
	        width: findWidth("stack_bar_top"),
	        delay: 5000
        }); 
	},
	this.handleValidationError = function(data){
		var errors = data.errorDescription.split(",");
		$.each(errors,function(i,error){
			new PNotify({
		        title: "VALIDATION",
		        text: error,
		        shadow: "",
		        opacity: 1,
		        addclass: "stack_top_right",
		        type: "danger",
		        stack: Stacks["stack_top_right"],
		        width: findWidth("stack_top_right"),
		        delay: 3000
	        });
		});
		
	};
	
	return this;
})();