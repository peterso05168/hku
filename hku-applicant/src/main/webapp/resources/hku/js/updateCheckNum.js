$(function() {
	var inputNum = "";
	$("div>input").change(function() {
		checkNum = 1;
	});
	$("select").change(function() {
		checkNum = 1;
	});
	$("div>span>input").change(function() {
		var preNum = $(this).val();
		var nextNum = $(this).parent("span").next().val();
		if (preNum != '' && nextNum != '' && preNum != nextNum) {
			checkNum = 1;
		}
	});
});