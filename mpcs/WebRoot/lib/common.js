$(function(){
	$('#checkAll').click(function(){
		$('input[name="checkBoxId"]').attr('checked',$(this).checked());
	});
});