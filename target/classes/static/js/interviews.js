$(document).ready(function() {
	$(".notification").click(function() {
		$('.box').toggle();
	});

	$("#accept").click(function() {
		event.preventDefault();
		var result = "accepted";
		interviewResult(result);
	});

	$("#reject").click(function() {
		event.preventDefault();
		var result = "rejected";
		interviewResult(result);
	});
	
	$("#footer-form").submit(function(event) {
		//after submitting the email,it will go to pm-controller /sendmail
		event.preventDefault();
		footer_form_submit();
	});

});




$('#myModal').on('shown.bs.modal', function() {
	$('#myInput').trigger('focus')
})

/* to add panel start*/

$("#panelform").submit(function(event) {

	//stop submit the form, we will post it manually.
	event.preventDefault();

	fire_ajax_submit();

});



function fire_ajax_submit() {

	var form = $("#addpanelmodal").find($("form"));

	// PREPARE FORM DATA
	var formData = {
		panelFor: $("#addpanelmodal").find($("form")).find($("#panelfor")).val(),
		employee: $("#addpanelmodal").find($("form")).find($("#employee")).val(),
	}
	console.log(formData);
	$.ajax({
		type: "POST",
		contentType: "application/json ; charset=utf-8",
		url: "/panel",
		data: JSON.stringify(formData),
		success: function(data) {
			console.log("SUCCESS : ", JSON.stringify(formData));
			$('#close-addpanel').click();
			$('.close').click();
		},
		error: function(e) {
			console.log("ERROR : ", e);

		}
	});
}

/* to add panel end */


/* This is to check if the notification is already seen or not */

$(".details_notification").click(function() {
	var isClicked = false;
	if (isClicked == false) {
		var notificationId = $(this).attr("name");
		console.log(notificationId);
		isClicked = true;
		$.ajax({
			type: 'GET',
			url: '/notification/details',
			data: { id: notificationId },
			dataType: 'json',
			success: function(data) {
				console.log(data);
				if (data.id != null) {
					$('#details').empty();
					$('#details').append("<label>Project Name : </label><span>" + " " + data.name + "</span>",
						"<br><label>Project Location : </label> <span>" + " " + data.location + "</span>",
						"<br><label>Project Created Date : </label> <span>" + " " + data.createdDate + "</span>",
						"<br><label>Project Start Date : </label> <span>" + " " + data.startDate + "</span>",
						"<br><label>Project End Date : </label> <span>" + " " + data.dueEndDate + "</span>",
						"<br><label>Project Pic : </label> <span>" + " " + "<img src=" + "/project-pics/" + data.id + "/" + data.projectPic + " />" + "</span>",
					);
				}
				/* if(data.empId != null){
					$('#details').append("<label>Project Name : </label><span>" +" "+data.name +"</span>" ,
							"<br><label>Project Location : </label> <span>" +" "+data.location +"</span>",
							"<br><label>Project Created Date : </label> <span>" +" "+data.createdDate +"</span>",
							"<br><label>Project Start Date : </label> <span>" +" "+data.startDate +"</span>",
							"<br><label>Project End Date : </label> <span>" +" "+data.dueEndDate +"</span>",
							);
				}
				if(data.jdId != null){
					$('#details').append("<label>Project Name : </label><span>" +" "+data.name +"</span>" ,
							"<br><label>Project Location : </label> <span>" +" "+data.location +"</span>",
							"<br><label>Project Created Date : </label> <span>" +" "+data.createdDate +"</span>",
							"<br><label>Project Start Date : </label> <span>" +" "+data.startDate +"</span>",
							"<br><label>Project End Date : </label> <span>" +" "+data.dueEndDate +"</span>",
							);
				} */
			},
			error: function(data) {
				console.log('error');
				console.log(data);
			}
		});
	}
});


jQuery(document).ready(function($) {
	$('*[data-href]').on('click', function() {
		window.location = $(this).data("href");
	});
});
function show(x, y, z, v) {
	console.log(x);
	console.log(y);
	console.log(z);
	console.log(v);
	$("#candidatename").attr("value", "" + x);
	$("#candidateidhidden").attr("value", "" + y);
	$("#candidatePositionFor").attr("value", "" + z);
	$("#candidateInterviewDate").attr("value", "" + v);
}

function interviewResult(result) {
	var form = $('#feedback_form')[0];
	var data = new FormData(form);
	
	
	if (result == "accepted") {
		alert("Candidate Accepted");
		data.append("result","accepted");
	}
	else {
		alert("Candidate Rejected");
		data.append("result","rejected");
	}

	$.ajax({
		type: 'POST',
		processData: false,
		contentType: false,
		url: url = "/interviewer/registerResult",
		data: data,
		success: function(data) {
			console.log("SUCCESS : " + data);
			$("#scheduled-interviews").load(window.location.href + " #scheduled-interviews");
			$("#closed-interviews").load(window.location.href + " #closed-interviews");
			$('#close-feedback-form').click();
			$('.close').click();
		},
		error: function(e) {
			console.log("ERROR : ", e);

		}
	});
}

function footer_form_submit() {
	var form = $('#footer-form')[0];
	var data = new FormData(form);
	data.append("email", $("#email").val());
	$.ajax({
		type: 'POST',
		processData: false,
        contentType: false,
		url: "/mail/sendMail",
		data: data,
		success: function(data) {
			console.log("SUCCESS : " + data);
			$("#email").val('');
			alert("Thank you for subscribing us...")
		},
		error: function(e) {
			console.log("ERROR : ", e);

		}
	});
}


