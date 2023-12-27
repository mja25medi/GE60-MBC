/*******************************************************************************
	DaumEditor Load Wrapper v1.0

	사용법:
		- 에디터 초기화 :
			createDaumEditor(option)
			option = {
				"editorId"			:	"",					// 필수 : 에디터가 표시될 컨트롤 ID
				"formId"			:	"",					// 필수 : Submit을 처리할 Form ID
				"textareaId"		:	"",					// 필수 : 에디터 본문을 처리할 textarea ID
				"repositoryCode"	:	"",					// 필수 : 파일 전송에 필요한 저장소 코드
				"language"			:	"ko",				// 선택 : 언어 코드 (예, ko, en, jp...)
				"imageBtnYn"		:	true,				// 선택 : 사진 첨부 버튼 표시 여부
				"attachments"		:	{ "image" : [] },	// 선택 : 저장된 첨부파일의 JSON DATA
				"editorHeight"		:	"350px",			// 선택 : 로딩후 설정할 에디터의 세로 크기
				"initeditor"		:	function() {}		// 선택 : 에디터 로딩후 초기화
			}
	Author: SungKook
	MEDIOPIA TECH CORP.
*******************************************************************************/

/**
 * Summernote를 표현하는 기본 클래스
 * this.data 의 기본 설정값을 잘 이용할 것.
 */
$M.SummerNote = function(option) {

	this.data = {
			"editorId"			:	"",					// 필수 : 에디터가 표시될 컨트롤 ID
			"textareaId"		:	"",					// 필수 : 에디터 본문을 처리할 textarea ID
			"repositoryCode"	:	"",					// 필수 : 파일 전송에 필요한 저장소 코드
			"organization"		:	"",					// 필수 : 파일 전송에 필요한 저장소 코드
			"editorHeight"		:	"350px",			// 선택 : 로딩후 설정할 에디터의 세로 크기
			"attachments"		:	{ "image" : [] },	// 선택 : 저장된 첨부파일의 JSON DATA
			"fileListId"		: 	"fileList",  		// 업로드후 보여줄 파일 목록의 id
			"files"				: 	new Array(),
			"fileSns"			:	"",
			"language"			:	"en-US",			// 선택 : 언어를 선택한다.(에:ko-KR, en-US....)
			"fontNames"			:   ['Arial','Arial Black','Comic Sans MS','Courier New','Helvetica','Impact','Tahoma','Times New Roman','Verdana'],
			"defaultFontName"	: 	'Comic Sans MS',
			"sendFile"     		:   $.proxy(this, "sendFile"),
			"appendFile"   		:   $.proxy(this, "appendFile"),
			"appendFileBySn"   	:   $.proxy(this, "appendFileBySn"),
			"appendFileList"   	:   $.proxy(this, "appendFileList"),
			"getFileSnAll"   	:   $.proxy(this, "getFileSnAll"),
			"removeFile"   		:   $.proxy(this, "removeFile"),
			"removeFileAll"		:   $.proxy(this, "removeFileAll")
	};

	// 설정값 병합
	this.data = jQuery.extend({}, this.data, option);
	// 설정 점검용 코드
	if( this.data.editorId == "" || this.data.formId == "" || this.data.textareaId == "" || this.data.repositoryCode == "" ) {
		var message = this.data.editorId + "\n" + this.data.formId + "\n" + this.data.textareaId + "\n" + this.data.repositoryCode;
		alert('The required value is not enough to set up the loading of the editor.\n' + message);
		return false;
	}

	//-- 언어 설정에 따른 메시지 키 변경
	if(localeKey == 'ko') {
		this.data.language = 'ko-KR';
		this.data.fontNames = ['맑은 고딕','굴림체','돋움체','바탕체','궁서체','Arial','Arial Black','Comic Sans MS','Courier New','Helvetica','Impact','Tahoma','Times New Roman','Verdana'];
		this.data.defaultFontName = '맑은 고딕';
	} else if (localeKey =='jp') {
		this.data.language = 'ja-JP';
		this.data.fontNames = ['Meiryo','Arial','Arial Black','Comic Sans MS','Courier New','Helvetica','Impact','Tahoma','Times New Roman','Verdana'];
		this.data.defaultFontName = 'Meiryo';
	} else {
		this.data.fontNames = ['Arial','Arial Black','Comic Sans MS','Courier New','Helvetica','Impact','Tahoma','Times New Roman','Verdana'];
		this.data.defaultFontName = 'Helvetica';
	}

	//-- 첨부된 이미지가 있는 경우 files에 추가함.
	if(this.data.attachments.image.length > 0) {
		for(var i=0; i < this.data.attachments.image.length; i++) {
			this.data.files.push(this.data.attachments.image[i].data);
		}
	}

	// 이미지 번호목록으로 초기화
	if(this.data.fileSns != "") {
		this.appendFileBySn(this.data.fileSns);
	}

	this.initSummernote();
};

// $M.AttachFiles.prototype
$M.SummerNote.prototype = {
		initSummernote	:	// file uploader 초기화
			function() {
				var data = this.data;
				$('#'+this.data.editorId).summernote({
					height: this.data.editorHeight,
					lang: this.data.language,
					fontNames: this.data.fontNames,
					defaultFontName: this.data.defaultFontName,
					focus:true,
					toolbar: [
					    ['style',['bold','italic','underline','clear','fontname']],
					    ['fontsize', ['fontsize']],
					    ['color',['color']],
					    ['font', ['strikethrough', 'superscript', 'subscript','clear']],
					    ['para', ['ul', 'ol', 'paragraph']],
					    ['height', ['height']],
					    ['group',['table']],
					    ['insert',['hr','picture','video','link']],
					    ['codeview', ['codeview']],
					],
					callbacks : {
						onImageUpload: function(files, editor, $editable) {
							data.sendFile(files, $editable);
						},
						onMediaDelete : function($target, editor, $editable) {
							if($target[0].id.indexOf(data.editorId+"_img_") == 0) {
							//-- 서버에서 이미지 삭제
								data.removeFile($target[0].id.replace(data.editorId+"_img_",""));
							}
							$target.remove();
						}
					}
				});
			},
		sendFile 	:
			function (files, welEditable) {
				var data = this.data;
				for(var i=0; i < files.length; i++) {
					indata = new FormData();
					indata.append("file", files[i]);
					indata.append("repository", this.data.repositoryCode);
					indata.append("organization", this.data.organization);
					indata.append("type", "image");
					$.ajax({
						url: cUrl('/app/file/upload'),
						data: indata,
						cache: false,
						contentType: false,
						processData: false,
						type: 'POST',
						success: function(fileData){
							//-- view image
							$('#'+data.editorId).summernote("insertImage", fileData.imageurl, data.editorId+"_img_"+fileData.fileSn);
							//-- add file list
							data.appendFile(fileData);
						},
						error: function(jqXHR, textStatus, errorThrown) {
							console.log(textStatus+" "+errorThrown);
						}
					});
				}
			},
		resetFile	:	// 파일 목록 초기화 (파일 삭제 이벤트는 일어나지 않음)
			function() {
				this.data.files = [];
			},
		appendFile	:	// 첨부파일 추가.
			function(fileData) {
				this.data.files.push(fileData);
			},
		appendFileBySn	:	// 첨부파일 정보로 추가
			function(fileSn) {
				if(isNull(fileSn) || fileSn == 0) return;
				$.getJSON(Context.FILE_JSONVIEW, { "fileSns" : fileSn }, $.proxy(this, "appendFileList"));
			},
		appendFileList :	// 파일 목록 병합
			function(fileList) {
				for(var i = 0; i < fileList.length; i++) {
					this.data.files = this.data.files.concat(fileList[i]);
				}
			},
		getFileSnAll	:	// 첨부파일의 모든 고유번호 문자열 "!@!" 구분자로 구하기
			function() {
				var params = "";
				for (i = 0; i < this.getFileCount(); i++) {
					params += (params == "") ? this.data.files[i].fileSn : "!@!" + this.data.files[i].fileSn;
				}
				return params;
			},
		getFileCount	:	// 파일목록의 갯수를 반환
			function() {
				if(this.data.files.length == undefined) return 0;
				return this.data.files.length;
			},
		insertText :
			function (text) {
				$('#'+this.data.editorId).summernote("insertText", text);
			},
		insertObject :
			function (obj) {
				$('#'+this.data.editorId).summernote("insertNode", obj);
			},
		removeFile :
			function(fileSn) {
				//if(!confirm(getCommonMessage('msg036'))) return false;
				$.getJSON(
					Context.FILE_DELETE + fileSn,	// url
					{},
					jQuery.proxy(this, "removeCallBack")
				);
				return false;
			},
		removeFileAll:	// 첨부파일 전체 삭제.
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
				} else if(response.result_message == 'ALL') {
					this.resetFile();
				} else {
					for (i = 0; i < this.getFileCount(); i++) {
						if(this.data.files[i].fileSn == response.result_message) {
							this.data.files.splice(i, 1);	// 배열 삭제
							break;
						}
					}
				}
			}
};

