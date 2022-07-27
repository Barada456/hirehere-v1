$(document).ready(function() {
	/* to toogle notification box - start */

	$(".notification").click(function() {
		$('.box').toggle();
	});

	$("#footer-form").submit(function(event) {
		//after submitting the email,it will go to pm-controller /sendmail
		event.preventDefault();
		footer_form_submit();
	});



	/* to toogle notification box - End */

	/* To show project details as popup from notification  start*/

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
	/* To show project details as popup from notification End*/



	/* for Demand Accept Start*/
	$(".btn-success").click(function(ev) {
		ev.preventDefault();

		var form = $(this).parent().parent();
		var value = form.find($("input")).val();
		var url = '/ceo/acceptdemand';

		$.ajax({
			type: "POST",
			url: url,
			data: value,
			contentType: 'text/plain',
			success: function(data) {
				var id = "#jdstatus" + data.jdId;
				$("#accept").attr("disabled", "true");
				$(id).html("<i class='fas fa-check-circle' style='color:green'></i>");
				$("#reject").attr("disabled", "true");
				$("#suggest").attr("disabled", "true");
				alert("Demand Approved Successfully");
			},
			error: function(data) {
				alert("Something Went Wrong");
			}
		});
	});

	/* for Demand Accept End*/



	/* for Demand Reject Start*/

	$(".btn-danger").click(function(ev) {
		ev.preventDefault();

		var form = $(this).parent().parent();
		var value = form.find($("input")).val();
		var url = '/ceo/rejectdemand';

		$.ajax({
			type: "POST",
			url: url,
			data: value,
			contentType: 'text/plain',
			success: function(data) {
				$("#accept").attr("disabled", "true");
				var id = "#jdstatus" + data.jdId;
				$("#reject").attr("disabled", "true");
				$(id).html('<i class="fas fa-times-circle" style="color:red"></i>');
				$("#suggest").attr("disabled", "true");
				alert("Demand rejected Successfully");
			},
			error: function(data) {
				alert("Something Went Wrong");
			}
		});
	});

	/* for Demand Reject End*/
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