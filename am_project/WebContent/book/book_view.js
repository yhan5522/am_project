
// window.load는 페이지 로딩 후 실행
window.onload = function() {
	var action = document.form1.action.value;

	if(action=="edit") {
		document.getElementById("insert").disabled=true;
		// document.getElementById("update").disabled=false;
		// document.getElementById("delete").disabled=false;
	} else if(action=="add") {
		// document.getElementById("insert").disabled=false;
		document.getElementById("update").disabled=true;
		document.getElementById("delete").disabled=true;
	}
} 

function insertcheck() {
	// post방식
	document.form1.action.value="insert";
	document.form1.submit();
}

function updatecheck() {
	// post방식
	document.form1.action.value="update";
	document.form1.submit();
}

function deletecheck() {
	result = confirm("정말로 삭제하시겠습니까 ?");

	if(result == true){
		
		// post방식
		document.form1.action.value="delete";
		document.form1.submit();
	}
	else
		return;
}

function backcheck() {
	// post방식
	document.form1.action.value="list";
	document.form1.submit();
	// history.go(-1);
	
}
