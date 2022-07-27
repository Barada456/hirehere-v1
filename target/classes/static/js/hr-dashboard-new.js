function showImg(x) {
	document.getElementById("emp-img").hidden = false;
	document.getElementById("emp-img")
	console.log(x.empImage);
	$("#emp-img").attr("src", "" + x);
}
function hideImg(x) {
	document.getElementById("emp-img").hidden = true;
}
$(document).ready(function() {
	$(".notification").click(function() {
		$('.box').toggle();
	});
	$("#footer-form").submit(function(event) {
		//after submitting the email,it will go to pm-controller /sendmail
		event.preventDefault();
		footer_form_submit();
	});


	setInterval(callcontrol, 2000);
});

/*
call control is used to check whether interviewer have closed any open interview,
if closed,it will be auto updated into hr dashboard without page refresh
*/

function callcontrol() {

	console.log("called");
	$.ajax({
		type: 'GET',
		url: '/interviewer/check',
		success: function(data) {

			console.log(data);
			if (data == true) {
				$("#refreshdiv").load(window.location.href + " #refreshdiv");
			}

		},
		error: function(data) {

			console.log('error');
			console.log(data);
		}
	});
}

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




$(".details_notification").click(function() {
	/* This is to check if the notification is already seen or not */
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
				if (data.jdId != null) {
					$('#details').empty();
					$('#details').append("<span class='badge badge-dark'>Position For : </span><span>" + " " + data.positionFor + "</span>",
						"<br><label class='badge badge-dark'>Qualification : </label><span>" + " " + data.qualification + "</span>",
						"<br><label class='badge badge-dark'>Responsibilities : </label><span>" + " " + data.responsibilities + "</span>",
						"<br><label class='badge badge-dark'>JD Created Date : </label><span>" + " " + data.createdDate + "</span>",
						"<br><label class='badge badge-dark'>Author : </label> <span>" + " " + data.author + "</span>",
						"<br><label class='badge badge-dark'>Domain : </label> <span>" + " " + data.domain + "</span>",
						"<br><label class='badge badge-dark'>Skill Set : </label> <span>" + " " + data.skillSet + "</span>",
					);
				}

				if (data.id != null) {
					$('#details').empty();
					$('#details').append("<label class='badge badge-dark'>Project Name : </label><span>" + " " + data.name + "</span>",
						"<br><label class='badge badge-dark'>Project Location : </label> <span>" + " " + data.location + "</span>",
						"<br><label class='badge badge-dark'>Project Created Date : </label> <span>" + " " + data.createdDate + "</span>",
						"<br><label class='badge badge-dark'>Project Start Date : </label> <span>" + " " + data.startDate + "</span>",
						"<br><label class='badge badge-dark'>Project End Date : </label> <span>" + " " + data.dueEndDate + "</span>",
						"<br><label class='badge badge-dark'>Project Pic : </label> <span>" + " " + "<img src=" + "/project-pics/" + data.id + "/" + data.projectPic + " />" + "</span>",
					);

				}
			},
			error: function(data) {
				console.log('error');
				console.log(data);
			}
		});
	}
});

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