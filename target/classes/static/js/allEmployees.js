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