function loginFunction(){

	//this should be serverside
	if((($("#userName").val() == "Adam")) && ($("#userPassword").val() == "qwerty")){
		localStorage.setItem("name", $("#userName").val());
	}else{
		localStorage.clear();
		alert("Login failed!");
	}
}

function registerFunction(){
	localStorage.setItem("name", $("#reguserName").val());
}

function logoutFunction(){
	localStorage.removeItem("name");
}
