
		$(document).ready(function () {

			$(".notification").click(function () {
				$('.box').toggle();
			});

			allpanel();

			/* to add panel start*/

			$("#panelform").submit(function (event) {

				//stop submit the form, we will post it manually.
				event.preventDefault();

				fire_ajax_submit();

			});


			function fire_ajax_submit() {

				var form = $("#addpanelmodal").find($("form"));

				// PREPARE FORM DATA
				var formData = {
					panelFor: form.find($("#panelfor")).val(),
					employee: form.find($("#employee")).val(),
				}
				console.log(formData);
				$.ajax({
					type: "POST",
					contentType: "application/json ; charset=utf-8",
					url: "/panel",
					data: JSON.stringify(formData),
					success: function (data) {
						console.log("SUCCESS : ", JSON.stringify(formData));
						/*close add panel div automatically*/
						$('#close-addpanel').click();
						$('.close').click();
						/* update all panel div after adding new panel  */
						$("#panel-body").load(window.location.href + " #panel-body");
						alert("New Panel Added");
						allpanel();
					},
					error: function (e) {
						console.log("ERROR : ", e);

					}
				});
			}

			/* to add panel end */

		});

		/* to show all panels start */

		function allpanel() {
			$.ajax({
				type: 'GET',
				url: '/panels',
				dataType: 'json',
				success: function (data) {
					console.log(data);

					for (let i = 0; i < data.length; ++i) {
						
						var panelName= data[i].panelFor;
						
						var panelId= data[i].id;
						
						$('#panel-body').append("<div class='panel mb-0 text-center'><label style='font-weight: bold;'>" + data[i].panelFor + "&nbsp;&nbsp;</label>" + "<button class='btn-success float-right' type='submit' onclick='assignPanel(`" +panelName + "`,`" +panelId + "`)'> Assign </button></br> </div> ");
					}

					
				},
				error: function (data) {
					console.log('error');
					console.log(data);
				}
			});
		}

		/* to show all panels end */

		
		
		/* to assign panel for interview scheduling */
		function assignPanel(x,y) {
			console.log(x);
			console.log(y);
			$("#assigned-panel").attr("value", "" + x);
			$("#assigned-panel-hidden").attr("value", "" + y);
		}
		

		/* to submit interview schedule form */
		$("#schedule-form").submit(function (event) {

			//stop submit the form, we will post it manually.
			event.preventDefault();
			schudele_form_submit();

		});

		function schudele_form_submit() {

			//var form = $("#schedule-card").find($("#schedule-form"));

			var form1 = $('#schedule-form')[0];

			var data = new FormData(form1);
			// PREPARE FORM DATA
			data.append("CV", $('#candidateCV')[0].files[0]);
			$.ajax({
				type: 'POST',
				contentType: false,
				cache: false,
				processData: false,
				enctype: 'multipart/form-data',
				url: "/hr/schedule",
				data: data,
				success: function (data) {
					console.log("SUCCESS : " + data);
					$('#panelReset').click();
				},
				error: function (e) {
					console.log("ERROR : ", e);

				}
			});
		}


		function showImg(x) {
			document.getElementById("emp-img").hidden = false;
			document.getElementById("emp-img")
			console.log(x.empImage);
			$("#emp-img").attr("src", "" + x);
		}
		function hideImg(x) {
			document.getElementById("emp-img").hidden = true;
		}

		$('#myModal').on('shown.bs.modal', function () {
			$('#myInput').trigger('focus')
		})




		$(".details_notification").click(function () {
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
                        success: function (data) {
                            console.log(data);
                           if(data.jdId!= null){
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
                        error: function (data) {
                            console.log('error');
                            console.log(data);
                        }
                    });
                }
            });