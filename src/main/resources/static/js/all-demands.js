$(document).ready(function() {
	var urlForAccept = "/ceo/acceptdemand";
	var urlForReject = "/ceo/rejectdemand";
	var urlForSuggest = "/ceo/suggestdemand";
	$('#accept').click(function() {
		$('#form_id').attr('action', urlForAccept);
	});
	$('#reject').click(function() {
		$('#form_id').attr('action', urlForReject);
	});
	$('#suggest').click(function() {
		$('#form_id').attr('action', urlForSuggest);
	});
});
