/**
 * 파일의 확장자를 판별해서 적절한 아이콘 이미지 URL을 반환한다.
 */
$M.findFileIcon = function(filename) {
	var ext     = filename.slice(filename.lastIndexOf(".")+1).toLowerCase();
	var iconUrl = lmsIconPath + "filetype/";
	var iconFile= "";

	var iconMap = {
		"alz"	: 	"alz.gif",
		"zip"	: 	"zip.gif",
		"rar"	: 	"rar.gif",
		"7z"	: 	"zip.gif",

		// 오피스
		"doc"	: 	"doc.gif",
		"docx"	: 	"doc.gif",
		"ppt"	: 	"ppt.gif",
		"pptx"	: 	"ppt.gif",
		"xls"	: 	"xls.gif",
		"xlsx"	: 	"xls.gif",

		// HTML
		"html"	: 	"html.gif",
		"htm"	: 	"html.gif",

		// 문서
		"hwp"	: 	"hwp.gif",
		"pdf"	: 	"pdf.gif",
		"xml"	: 	"xml.gif",
		"txt"	: 	"text.gif",

		// 오디오
		"mp3"	: 	"mp3.gif",
		"wma"	: 	"mp3.gif",
		"wav"	: 	"mp3.gif",

		// 비디오
		"avi"	: 	"movie.gif",
		"wmv"	: 	"movie.gif",
		"ra"	: 	"ra.gif",

		// 사진
		"gif"	: 	"gif.gif",
		"jpg"	: 	"jpg.gif",
		"bmp"	: 	"bmp.gif",
		"png"	: 	"image.gif",

		"_default":	"default.gif"
	};

	for(var i in iconMap) {
		if (i == ext) {
			iconFile = iconMap[i];
			break;
		}
	}

	if(iconFile == "")
		iconFile = iconMap['_default'];

	return iconUrl + iconFile;
};


/**
 * Jquery fileupload plug-in
 * 첨부파일을 표현하는 기본 클래스
 * this.data 의 기본 설정값을 잘 이용할 것.
 */
$M.JqueryFileUpload = function(option) {

	this.data = {
		"varName"			: "",
		"size"				: 0,
		"files" 			: new Array(),
		"fileSns"			: "",
		"uploaderId"		: "fileupload", // input type='file' 의 id
		"fileListId"		: "fileList",  // 업로드후 보여줄 파일 목록의 id
		"progressId"		: "progress", // 업로드시 보여지 progress-bar의 id
		"maxcount"			: 10, // 파일 업로드 제한 겟수
		"maxsize"			: 256, // MB 단위로 파일 1개 제한 용량.
		"previewImage"		: false, // 이미지 첨부인 경우 true , 썸네일이 보이는 목록반환
		"infoUse"	: false,		//사이즈 안내문구 사용여부
		"previewImageMaxWidth" : "",
		"onAppend"			: jQuery.proxy(this, "onAppend"),
		"uploadSetting"	: {	// uploadify 기본 설정 default 값.
			'url'			: '/app/file/upload', //Context.JQUERY_FILE_UPLOAD,	// upload url
			'dataType'		: 'json',
			'dropZone'		: '',
			'formData'		: { 'repository': 'GENERAL',		// 파일 저장소 코드
                                'organization' : '',
								'type'		: 'file' },			// 추가 파라매터
			'add'			: jQuery.proxy(this, "add"),
			'done'			: jQuery.proxy(this, "done"),
			'fail'			: jQuery.proxy(this, "fail"),
			"progressall"   : jQuery.proxy(this, "progressall"),
		}
	};

	// 설정값 병합
	this.data.uploadSetting = jQuery.extend(this.data.uploadSetting, option.uploadSetting);
	option.uploadSetting = this.data.uploadSetting;
	this.data = jQuery.extend(this.data, option);

	// 설정 점검용 코드
	if(this.data.varName == "") {
		alert('Failed Initialize file uploader !! ');
		return false;
	}

	// 첨부파일 번호목록으로 초기화
	if(this.data.fileSns != "") {
		this.appendBySn(this.data.fileSns);
	}

	if(this.count() > 0 ) {
		if(this.data.files[0].fileSn == undefined)
			this.data.files = new Array();
		this.onAppend();
	}

	this.initFileuploder();
};

// $M.AttachFiles.prototype
$M.JqueryFileUpload.prototype = {
		initFileuploder	:	// file uploader 초기화
			function() {
				$('#'+this.data.progressId).hide();
				$('#'+this.data.uploaderId)
					.fileupload(this.data.uploadSetting).prop('disabled', !$.support.fileInput)
			        .parent().addClass($.support.fileInput ? undefined : 'disabled');
				$('#'+this.data.uploaderId).fileupload({ 'dropZone' : $('#'+this.data.fileListId)});
			},
		add 	:
			function (e, data) {
				var file = data.files[0];
				if(this.count() >= this.data.maxcount) {
					alert(getCommonMessage('msg035', this.data.maxcount));
					return;
				}
				if(file.size >= (this.data.maxsize*1024*1024)) {
					alert(getCommonMessage('msg035', byteConvertor(this.data.maxsize*1024*1024), byteConvertor(file.size)));
					return;
				}
				$('#'+this.data.progressId).show();
				data.submit();
			},
		done	:	// 기본 onComplete 이벤트
			function(e, data) {
				//this.append(jQuery.parseJSON(data.result));	// 첨부파일 추가
				this.append(data.result);	// 첨부파일 추가
				$('#'+this.data.progressId).hide();
			},
		fail	:
			function(e, data) {
				alert("Error! Failed file upload.");
				/*
				alert(showAttribute(data._response.jqXHR));
				alert(data._response.jqXHR.status + " : " + data._response.jqXHR.statusText);
				alert(showAttribute(data._response));
				*/
			},
		progressall :
			function (e, data) {
				var progress = parseInt(data.loaded / data.total * 100, 10);
				$('#'+this.data.progressId+' .progress-bar').css(
					'width',
					progress + '%'
				);
			},
		reset	:	// 파일 목록 초기화 (파일 삭제 이벤트는 일어나지 않음)
			function() {
				this.data.size = 0;
				this.data.files = [];
			},
		append	:	// 첨부파일 추가.
			function(filedata) {
				// 파일정보가 유효하지 않으면 추가하지 않는다.
				if(filedata.fileSn == undefined || filedata.fileSn == 0)
					return false;
				// 파일이 제한 갯수를 넘으면 업로드된 파일을 다시 삭제한다.
				if(this.count() >= this.data.maxcount) {
					alert(getCommonMessage('msg035', this.data.maxcount));
					$.post(Context.FILE_DELETE + filedata.fileSn);
				} else {
					//try {
						this.data.size += filedata.filesize;
						this.data.files.push( filedata );
					//} catch (e) {}
				}
				this.data.onAppend();
			},
		appendBySn	:	// 첨부파일 정보로 추가
			function(fileSn) {
				if(isNull(fileSn) || fileSn == 0) return;
				$.getJSON(Context.FILE_JSONVIEW, { "fileSns" : fileSn }, $.proxy(this, "appendList"));
				this.data.onAppend();
			},
		appendList :	// 파일 목록 병합
			function(fileList) {
				for(var i = 0; i < fileList.length; i++) {
					this.data.files = this.data.files.concat(fileList[i]);
				}
				this.data.onAppend();
			},
		appendPhoto :	// 사진파일 목록 병합
				function(fileList) {
					for(var i = 0; i < fileList.length; i++) {
						this.data.files = this.data.files.concat(fileList[i]);
					}
					this.listPoto();
				},
		getFileSnAll	:	// 첨부파일의 모든 고유번호 문자열 "!@!" 구분자로 구하기
			function() {
				var params = "";
				for (i = 0; i < this.count(); i++) {
					params += (params == "") ? this.data.files[i].fileSn : "!@!" + this.data.files[i].fileSn;
				}
				return params;
			},
		getFileName :
			function() {
				var params = "";
				for (i = 0; i < this.count(); i++) {
					params += (params == "") ? this.data.files[i].filename : "!@!" + this.data.files[i].filename;
				}
				return params;
			},
		getFileSize :
			function() {
				var params = "";
				for (i = 0; i < this.count(); i++) {
					params += (params == "") ? byteConvertor(this.data.files[i].filesize) : "!@!" + byteConvertor(this.data.files[i].filesize);
				}
				return params;
			},
		count	:	// 파일목록의 갯수를 반환
			function() {
				if(this.data.files.length == undefined) return 0;
				return this.data.files.length;
			},
		remove :
			function(fileSn) {
				if(!confirm(getCommonMessage('msg036'))) return false;
				$.getJSON(
					Context.FILE_DELETE + fileSn,	// url
					{},
					jQuery.proxy(this, "removeCallBack")
				);
				return false;
			},
		removeNotConfirm :
				function(fileSn) {
					$.getJSON(
						Context.FILE_DELETE + fileSn,	// url
						{},
						jQuery.proxy(this, "removeCallBack")
					);
					return false;
				},
		removeAll:	// 첨부파일 전체 삭제.
			function() {
				$.getJSON(
					Context.FILE_DELETES,
					{files : this.getFileSnAll()},
					jQuery.proxy(this, "removeCallBack")
				);
			},
		removeCallBack	:	// 파일 삭제 콜백 (실패하면 메시지만 표시)
			function(response) {
				if(response.result != 'success') {
					alert(response.message);
				} else if(response.message == 'ALL') {
					this.reset();
				} else {
					for (i = 0; i < this.count(); i++) {
						if(this.data.files[i].fileSn == response.result_message) {
							this.data.files.splice(i, 1);	// 배열 삭제
							break;
						}
					}
				}
				this.data.onAppend();
			},
		onAppend	:	// 첨부파일 목록 표시용 html태그 반환
			function() {
				$('#'+this.data.fileListId).empty();
				for (i = 0; i < this.count(); i++) {
					if(this.data.infoUse)	$("#sizeInfo").hide();
					var file = this.data.files[i];
					var btnDelete = '<a class="btnRemoveFile" id="btnRemoveFile_'+file.fileSn+'" onclick="'+this.data.varName+'.remove(\''+file.fileSn+'\');" onkeydown="if($M.Check.Event.isEnter(event)){'+this.data.varName+'.remove(\''+file.fileSn+'\');}" href="#_none"><img src="'+lmsIconPath+'icon_delete.gif" alt="Delete File"/></a>';
					var imgSrc = (this.data.previewImage) ? file.thumburl : $M.findFileIcon(file.filename);
					//var imgWidth = (this.data.previewImageMaxWidth != "") ? "style='max-width:"+this.data.previewImageMaxWidth+"px;'" : "" ;
					var isflot = (this.data.previewImage) ? '' : 'float:left;';
					var imgWidth ="style='max-width:200px;'";
					$('<div id="" style="margin-top:5px;"><p><a href="javascript:fileDown(\'' + file.fileSn + '\');" style="cursor:pointer;'+isflot +' "' +
							'title="Download: ' + file.filename + '" >' +
							'<img src="' + imgSrc + '" '+imgWidth+'/>' + file.filename + ' (' + byteConvertor(file.filesize) + ') ' + btnDelete + '</a></p></div>').appendTo('#'+this.data.fileListId);
				}
			}
};

$(document).bind('drop dragover', function (e) {
    e.preventDefault();
});