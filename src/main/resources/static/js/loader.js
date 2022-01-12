$('a').on("click",function(){
	var page = $(this).attr('href');
	var id= $(this).attr('id');

	if(page != "#"){
		$("#mainpage_container").load(page);
		if(page == "register.html"){
			$("#login-bs-modal").modal('hide');
		}

		if(page=="table.html") {
			if(typeof id !== 'undefined' ) {
				$('body').attr('id',id+"_body")
				$(initializeTable); //calls when document is ready
			} else {
				//this is an error
				prompt("Nie podano identyfikatora linku tabeli. Proszę poinformować administratora o błędzie");
				$("#content").load("home.html");
			}

		} else {
			if ($('body').attr('id').length>0) {
				$('body').removeAttr('id');
			}
		}
		return false;
	}
});


function loadFunction() {
	$("#mainpage_container").load("home.html"); //comment to debug
	if(localStorage.getItem("name")){
		document.getElementById("loginName").innerHTML = localStorage.getItem("name");
		$("#loginName").attr("data-bs-target", "#logout-modal");
	}

	return false;
}

function chooseCategory() {
	var page = $(this).attr('href');
	$("#content").load(page);
	return false;
}


//Add Hover effect to menus
jQuery('ul.nav li.dropdown').hover(function() {
	jQuery(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn();
}, function() {
	jQuery(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut();
});

