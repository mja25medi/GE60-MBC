/**
 * 다음에디터 파일첨부 팝업에서 공통으로 사용하는 스크립트
 * @author SungKook
 */
// 창닫기 이벤트에 파일 삭제 바인딩.
window.onbeforeunload = function() {
	AttachFiles.removeAll();
};

/**
 * 첨부파일 클래스
 */
var AttachFiles = {
	size: 0,
	files: [],
	str: "",
	mode: "file",	// default.. file or image
	listId: "#tbodyFileList",
	initialize: function(paramMode) {
		this.mode = paramMode;
		this.checkBtn();
	},
	// 첨부파일 추가 매서드
	append: function(filedata) {
		this.size += filedata.filesize;
		this.files.push( filedata );
		this.list();
	},
	remove: function(idx) {
		var deletefileSn = this.files[idx].fileSn;
		$.post(
				cUrl("/app/file/delete/" + deletefileSn),		// url
				{},												// params
				function() {
					// 배열 삭제
					AttachFiles.files.splice(idx, 1);
					AttachFiles.list();
				}
		);
	},
	removeAll: function() {
		if(this.files.length == 0) return;
		var deleteFiles = "";
		for (i = 0; i < this.files.length; i++) {
			if(deleteFiles == "")
				deleteFiles += this.files[i].fileSn;
			else
				deleteFiles += "!@!" + this.files[i].fileSn;
		}
		$.post( cUrl("/app/file/deletes"), {files : deleteFiles},
				function() {
					AttachFiles.files = [];
					AttachFiles.list();	// 리스트 삭제용.
				}
		);
	},
	list: function() {
		this.checkBtn();	// 버튼 표시 및 숨기기.
		if(this.mode == 'image')
			this.listImage();
		else if(this.mode == 'file')
			this.listFile();
	},
	listFile: function() {
		var html = "";
		for(i=0; i < this.files.length; i++ ) {
			html += "<li class='list-group-item'>"
				 +  "	File Name : " + this.files[i].filename + " "
				 +  "	(File Size:" + this.files[i].filesize + " )"
				 +  "	<span class='pull-right'><a href='#' onclick=\"AttachFiles.remove(" + i + ", 'file')\" class='btn btn-warning btn-xs'>삭제</a></span>"
				 +  "</li>";
		}
		$(this.listId).html(html);
	},
	listImage: function() {
		var html = "";
		for(i=0; i < this.files.length; i++ ) {
			html += "<li class='list-group-item'>"
				+  "	<img src='" + this.files[i].thumburl + "' alt = '" + this.files[i].filename + "'>"
				+  "	<span class='pull-right'><a href='#' onclick=\"AttachFiles.remove(" + i + ", 'file')\" class='btn btn-warning btn-xs'>삭제</a></span>"
				+  "	<p>" + this.files[i].filename + "</p>"
				+  "</li>";
		}
		$(this.listId).html(html);
	},
	checkBtn: function() {
		if(this.files.length > 0) {
			//$("#buttonArea").show();	// 등록, 취소 버튼 표시.
			$("a.btnlink").show();	// 등록, 취소 버튼 표시.
		} else {
			//$("#buttonArea").hide();	// 등록, 취소 버튼 표시.
			$("a.btnlink").hide();	// 등록, 취소 버튼 숨김
		}
	}
};

/**
 * 확인 버튼 클릭시 본문 첨부.
 * @return
 */
function done() {
	if (typeof(execAttach) == 'undefined') { //Virtual Function
		alert('본문에 첨부할 수 없습니다.\n창을 닫고 다시 시도해 주시기 바랍니다.');
		return;
	}

	for(i=0; i < AttachFiles.files.length; i++) {
		execAttach(AttachFiles.files[i]);
	}

	// 창닫기 이벤트 해제후 닫아야 파일이 삭제되지 않음.
	window.onbeforeunload = function() {};
	closeWindow();
}

/**
 * 작업 취소 및 창닫기
 * @return
 */
function cancle() {
	AttachFiles.removeAll();
	window.close();
}

/**
 * 파일 업로더 초기화
 * @param attacherName 'image' or 'file'
 * @return
 */
function initUploader(attacherName) {
    var _opener = PopupUtil.getOpener();
    if (!_opener) {
        alert('잘못된 경로로 접근하셨습니다.');
        return;
    }

    var _attacher = getAttacher(attacherName, _opener);
    registerAction(_attacher);
    AttachFiles.initialize(attacherName);
}
