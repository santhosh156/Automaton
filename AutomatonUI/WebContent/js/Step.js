function step(){
	
}

function databaseStep(hostName, url, userName, password){
	this.hostName = hostName;
	this.url = url;
	this.userName = userName;
	this.password = password;
	this.step;
}

function shellStep(hostName,script, userName, password) {
	this.hostName;
	this.script;
	this.userName;
	this.password;
	this.step;
}

function soapStep(endpoint, xmlString) {
	this.endpoint;
	this.xmlString;
	this.step;
	
	function print() {
		alert(this.endpoint);
		alert(this.xmlString);
	}
}

function restStep(url, requestString) {
	this.url;
	this.requestString;
	this.step;
}

databaseStep.prototype= Object.create(step.prototype);
shellStep.prototype= Object.create(step.prototype);
soapStep.prototype= Object.create(step.prototype);
restStep.prototype= Object.create(step.prototype);