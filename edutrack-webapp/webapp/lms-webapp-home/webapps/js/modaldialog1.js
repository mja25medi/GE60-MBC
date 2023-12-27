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
		"height"	: "400",
		"modal"		: true,
		"nomargin"	: false
	};
	this.data = jQuery.extend(this.data, option);

	this.initModal();
};

$M.ModalDialog.prototype = {
	initModal :
		function() {
			//-- 스크린 크기에 맞추어 width, height 변경
			var screenHeight = ((window.innerHeight > 0) ? window.innerHeight : screen.height) - 1;;
			var screenWidth = (window.innerWidth > 0) ? window.innerWidth : screen.width;
			var width = (this.data.width > screenWidth) ? screenWidth : this.data.width;
			var height = (this.data.height > screenHeight) ? screenHeight : this.data.height;

			var modalid = this.data.modalid;
			var title = this.data.title;
			var nomargin = this.data.nomargin;

			var modalYn = 'true';
			if(this.data.modal) modalYn = 'static';

			if(!this.data.nomargin) {
				divcontents =
					"        <div class=\"modal_popup_wrap\"  id='"+modalid+"'> "+
	            	"        	<div class=\"modal_popup\"> "+
	            	"        		<h4 class=\"modal_title\" id='"+modalid+"_title'>"+title+"</h4>"+
	            	"        		<div class=\"modal_cont\" id='"+modalid+"_body'> </div> "+
	            	"			<button type=\"button\" class=\"modal_close\" onclick=\"modalClose('"+modalid+"')\" ><i class=\"xi-close-thin\"></i><span class=\"sr-only\">팝업닫기</span></button>" +
	            	"        	</div> "+
	        		"        </div>";
			} else {
				divcontents =
					"        <div class=\"modal_popup_wrap\"  id='"+modalid+"'> "+
	            	"        <div class=\"modal_popup m_large\"> "+
	            	"        <h4 class=\"modal_title\" id='"+modalid+"_title'>"+title+"</h4>"+
	            	"		<button type=\"button\" class=\"modal_close\" onclick=\"modalClose('"+modalid+"')\" ><i class=\"xi-close-thin\"></i><span class=\"sr-only\">팝업닫기</span></button>" +
	            	"        <div class=\"modal_cont\" id='"+modalid+"_body'> </div> "+
	        		"        </div> "+
	        		"        </div>";
			}
			$("body").append(divcontents);
		},
		setTitle :
			function(title) {
				/*if(!this.data.nomargin) {
					$("#"+this.data.modalid+"_title").html(title);
				}*/
				$("#"+this.data.modalid+"_title").html(title);
			},
		addContents :
			function(data) {
				$("#"+this.data.modalid+"_body").html(data);
				//$("#"+this.data.modalid+"_body").load(data, {});
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
				
				//modal1
				//$("#"+this.data.modalid).css("width",width+"px");
				//$("#"+this.data.modalid).css("height",height+"px");
				
				
				$("#"+this.data.modalid +" .modal_popup").css("width",width+"px");
				$("#"+this.data.modalid+"_body iframe").height(height);
				//$("#"+this.data.modalid+"_body").css("height",(height-50)+"px");
			},
		move :
			function(posx, posy) {
				//modal1
				//$("#"+this.data.modalid).css("position","absolute");
				//$("#"+this.data.modalid).css("top",posx+"px");
				//$("#"+this.data.modalid).css("left",posy+"px");
			
				//PC
				$("#"+this.data.modalid+" .modal_popup").css("top",posx+"px");
				$("#"+this.data.modalid+" .modal_popup").css("left",posy+"px");
				$("#"+this.data.modalid+" .modal_popup").css("transform","none")
				
				//반응형에서는 move 미적용
			},
		moveCenter :
			function() {
				$("#"+this.data.modalid).css("position","relative");
			},
		show :
			function() {
				//$("#"+this.data.modalid).fadeIn();
				$("#"+this.data.modalid).css('display','flex').hide().fadeIn();
	            // 형제인 다른 팝업 닫기
	            $("#"+this.data.modalid).siblings('.popup').fadeOut();

	            // 딤드 처리 필요 시 오픈
	            if( $('.dimmed:visible').length == 0 ){
	                $('.dimmed').fadeIn();
	            }
			},
		close :
			function() {
				$("#"+this.data.modalid).fadeOut();

	            // 딤드 사용한 경우 숨김
	            if( $('.dimmed:visible').length > 0 ){
	                $('.dimmed').fadeOut();
	            }
			},
		remove :
			function () {
				$("#"+this.data.modalid).remove();
			}
};

function modalClose(modalId) {
	$("#"+modalId+"_body").html("");
	$("#"+modalId).fadeOut();
}
