$(document).ready(function() {

	$(".notification").click(function() {
		$('.box').toggle();
	});

	allpanel();
});
/* to show all panels start */

function allpanel() {
	$.ajax({
		type: 'GET',
		url: '/panels',
		dataType: 'json',
		success: function(data) {
			console.log(data);

			for (let i = 0; i < data.length; ++i) {

				var panelName = data[i].panelFor;

				var panelId = data[i].id;

				$('#panel-body').append("<div class='panel mb-0 text-center'><a href='/interviewer/interviews/"+ data[i].id+"'><label style='font-weight:bold'>" + data[i].panelFor + "&nbsp;&nbsp;</label></a> </div> ");
				
			}


		},
		error: function(data) {
			console.log('error');
			console.log(data);
		}
	});
}

/* to show all panels end */


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
});
$('#myModal').on('shown.bs.modal', function() {
	$('#myInput').trigger('focus')
})

$("#footer-form").submit(function(event) {
		//after submitting the email,it will go to pm-controller /sendmail
		event.preventDefault();
		footer_form_submit();
	});


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
var isClicked = false;
$(".details_notification").click(function() {
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