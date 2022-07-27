
$(function() {
	function c() {
		p();
		var e = h();
		var r = 0;
		var u = false;
		l.empty();
		while (!u) {
			if (s[r] == e[0].weekday) {
				u = true;
			} else {
				l.append('<div class="blank"></div>');
				r++;
			}
		}
		for (var c = 0; c < 42 - r; c++) {
			if (c >= e.length) {
				l.append('<div class="blank"></div>');
			} else {
				var v = e[c].day;
				var m = g(new Date(t, n - 1, v)) ? '<div class="today">' : "<div>";
				l.append(m + "" + v + "</div>");
			}
		}
		var y = o[n - 1];
		a.css("background-color", y)
			.find("h1")
			.text(i[n - 1] + " " + t);
		f.find("div").css("color", y);
		l.find(".today").css("background-color", y);
		d();
	}
	function h() {
		var e = [];
		for (var r = 1; r < v(t, n) + 1; r++) {
			e.push({ day: r, weekday: s[m(t, n, r)] });
		}
		return e;
	}
	function p() {
		f.empty();
		for (var e = 0; e < 7; e++) {
			f.append("<div>" + s[e].substring(0, 3) + "</div>");
		}
	}
	function d() {
		var t;
		var n = $("#calendar").css("width", e + "px");
		n.find((t = "#calendar_weekdays, #calendar_content"))
			.css("width", e + "px")
			.find("div")
			.css({
				width: e / 7 + "px",
				height: e / 7 + "px",
				"line-height": e / 7 + "px",
			});
		n.find("#calendar_header")
			.css({ height: e * (1 / 7) + "px" })
			.find('i[class^="icone-favron"]')
			.css("line-height", e * (1 / 7) + "px");
	}
	function v(e, t) {
		return new Date(e, t, 0).getDate();
	}
	function m(e, t, n) {
		return new Date(e, t - 1, n).getDay();
	}
	function g(e) {
		return y(new Date()) == y(e);
	}
	function y(e) {
		return e.getFullYear() + "/" + (e.getMonth() + 1) + "/" + e.getDate();
	}
	function b() {
		var e = new Date();
		t = e.getFullYear();
		n = e.getMonth() + 1;
	}
	var e = 480;
	var t = 2013;
	var n = 9;
	var r = [];
	var i = [
		"JANUARY",
		"FEBRUARY",
		"MARCH",
		"APRIL",
		"MAY",
		"JUNE",
		"JULY",
		"AUGUST",
		"SEPTEMBER",
		"OCTOBER",
		"NOVEMBER",
		"DECEMBER",
	];
	var s = [
		"Sunday",
		"Monday",
		"Tuesday",
		"Wednesday",
		"Thursday",
		"Friday",
		"Saturday",
	];
	var o = [
		"#16a085",
		"#1abc9c",
		"#c0392b",
		"#27ae60",
		"#FF6860",
		"#f39c12",
		"#f1c40f",
		"#e67e22",
		"#2ecc71",
		"#e74c3c",
		"#d35400",
		"#2c3e50",
	];
	var u = $("#calendar");
	var a = u.find("#calendar_header");
	var f = u.find("#calendar_weekdays");
	var l = u.find("#calendar_content");
	b();
	c();
	a.find('i[class^="icon-chevron"]').on("click", function() {
		var e = $(this);
		var r = function(e) {
			n = e == "next" ? n + 1 : n - 1;
			if (n < 1) {
				n = 12;
				t--;
			} else if (n > 12) {
				n = 1;
				t++;
			}
			c();
		};
		if (e.attr("class").indexOf("left") != -1) {
			r("previous");
		} else {
			r("next");
		}
	});
});



/*  $(document).ready(function()
{
	let flag=0;
	let count=0;
   
	$("#calendar_content").find("div").click(function(){
		flag++;
		if(flag%2==0)
			{
			$(this).css({"background-color": "white"});
			console.log(flag);
			}
	    
		else{
		   
		  
		  let  icon = '<i class="fa fa-phone fa-2x" aria-hidden="true" ></i>';
			$(this).css({"background-color": "red", "border":"black"});
			/* //if(count==0 )
			 { */

// $(this).append(icon);

// }
//count++;
// cache.delete();
// console.log(count);





// }
// });
//}); 
// */








$('document').ready(function() {
	$("#calendar_content").find("div").on('click', function() {
		console.log($(this).text());
		if ($(this).text() != "") {
			if ($(this).css("background-color") == "rgb(255, 0, 0)") {
				$(this).css("background-color", "");
				console.log("color present");
				$(this).children("span").remove();
			}
			else {
				$(this).css("background-color", "red");
				console.log("color not present");
				console.log($(this).css("background-color"));
				let icon = '<span class="icon"><i class="fa fa-phone" aria-hidden="true"></i></span>';
				$(this).append(icon);
			}
		}
	})
});


/*  var elementClicked;
 $("#calendar_content").find("div").click(function(){
	elementClicked = true;
 });
 if( elementClicked != true ) {
	 alert("element not clicked");
 }else{
	 alert("element clicked");
 }
  */











