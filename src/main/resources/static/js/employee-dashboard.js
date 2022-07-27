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

				$('#panel-body').append("<div class='mb-0 text-center'><a href='/interviewer/interviews/"+ data[i].id+"'><label style='font-weight:bold'>" + data[i].panelFor + "</label></a> </div> ");
				
			}


		},
		error: function(data) {
			console.log('error');
			console.log(data);
		}
	});
}