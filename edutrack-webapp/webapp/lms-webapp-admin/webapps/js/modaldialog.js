/*******************************************************************************
	모달박스 생성 스크립트

	-- 전제조건 jquery.js, bootstrap.js 가 로딩 되어야 한다.

	사용법:
	1. 모달박스 객체를 생성한다.
		var modalboxname = new ModalBox(title, onload, onclose, attribute);
			modalboxname:	생성된 모달 객체
			title:		타이틀
			onload:		모달박스를 표시할때 실행할 함수
			onclose:	모달박스를 닫을때 실행할 함수
			attribute;	속성
				width: 		박스 넓이
				height: 	박스 높이

		ex) var modalboxname = new ModalBox("타이틀", "", "", "width=300,height=200");

	2. 모달박스에 내용을 추가한다.
		modalboxname.addContents("박스에 내용을 추가합니다.<br>");

	3. 모달창을 화면에 표시한다.
		modalboxname.show()

	4. 모달박스 내용을 모두 지운다.
		modalboxname.clear();

	4. 모달박스를 닫는다.
		modalboxname.close();
*******************************************************************************/
/**
 * ModalDialog를 표현하는 기본 클래스
 */
$M.ModalDialog = function(option) {
	this.data = {
		"modalid"	: "",
		"title"		: "",
		"onload"	: "",
		"onclose"	: "",
		"width"		: "500",
		"height"	: "400"
	}
	this.data = jQuery.extend(this.data, option);

	//-- 스크린 크기에 맞추어 width, height 변경
	var screenHeight = ((window.innerHeight > 0) ? window.innerHeight : screen.height) - 1;;
	var screenWidth = (window.innerWidth > 0) ? window.innerWidth : screen.width;
	var width = (this.data.width > screenWidth) ? screenWidth : this.data.width;
	var height = (this.data.height > screenHeight) ? screenHeight : this.data.height;

	//var width = this.data.width;
	//var height = this.data.height;
	var modalid = this.data.modalid;
	var title = this.data.title;

	divcontents =
		"<div class='modal fade' id='"+modalid+"' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true'>"+
		"	<div class='modal-dialog' style='width:"+width+"px;' id='"+modalid+"_box'>"+
		"		<div class='modal-content'>"+
		"			<div class='modal-header'>"+
		"				<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>"+
		"				<h4 class='modal-title' id='"+modalid+"_title'>"+title+"</h4>"+
		"			</div>"+
		"			<div class='modal-body' style='height:"+height+"px;' id='"+modalid+"_body'>"+
		"			</div>"+
		"		</div>"+
		"	</div>"+
		"</div>";

	$("body").append(divcontents);
}

$M.ModalDialog.prototype = {
	setTitle :
		function(title) {
			$("#"+this.data.modalid+"_title").html(title);
		},
	addContents :
		function(data) {
			$("#"+this.data.modalid+"_body").html(data);
		},
	clear :
		function() {
			$("#"+this.data.modalid+"_body").html("");
		},
	resize :
		function(inwidth, inheight) {
			//-- 스크린 크기에 맞추어 width, height 변경
			var screenHeight = ((window.innerHeight > 0) ? window.innerHeight : screen.height) - 80;;
			var screenWidth = ((window.innerWidth > 0) ? window.innerWidth : screen.width) - 20;
			var width = (inwidth > screenWidth) ? screenWidth : inwidth;
			var height = (inheight > screenHeight) ? screenHeight : inheight;
			$("#"+this.data.modalid+"_box").css("width",width+"px");
			$("#"+this.data.modalid+"_body").css("height",height+"px");
		},
	show :
		function() {
			$("#"+this.data.modalid).modal();
		},
	close :
		function() {
			$("#"+this.data.modalid).modal('hide');
		},
	remobe :
		function () {
			$("#"+this.data.modalid).remove();
		}
}
