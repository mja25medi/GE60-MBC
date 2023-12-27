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

$M.CreateEditor = function (params) {

	var option = {
			"editorId"			:	"",					// 필수 : 에디터가 표시될 컨트롤 ID
			"formId"			:	"",					// 필수 : Submit을 처리할 Form ID
			"textareaId"		:	"",					// 필수 : 에디터 본문을 처리할 textarea ID
			"repositoryCode"	:	"",					// 필수 : 파일 전송에 필요한 저장소 코드
			"language"			:	"ko",				// 선택 : 언어 코드 (예, ko, en, jp...)
			"imageBtnYn"		:	true,				// 선택 : 사진 첨부 버튼 표시 여부
			"attachBtnYn"		:	true,				// 선택 : 사진, 외부컨텐츠 첨부 버튼 영역 표시 여부
			"attachments"		:	{ "image" : [] },	// 선택 : 저장된 첨부파일의 JSON DATA
			"attachmentSns"		:	"",					// 선택 : 저장된 첨부파일의 JSON DATA
			"editorHeight"		:	"350px",			// 선택 : 로딩후 설정할 에디터의 세로 크기
			"editorResize"		:	true,				// 선택 : 로딩후 설정할 에디터의 세로 크기
			"initeditor"		:	function() {}		// 선택 : 에디터 로딩후 초기화
	};

	// 설정값 병합
	option = $.extend({}, option, params);

	if( option.editorId == "" || option.formId == "" || option.textareaId == "" || option.repositoryCode == "" ) {
		var message = option.editorId + "\n" + option.formId + "\n" + option.textareaId + "\n" + option.repositoryCode;
		alert('The required value is not enough to set up the loading of the editor.\n' + message);
		return false;
	}

	var editorParam = "?";
	editorParam += (option.imageBtnYn)  ? "" : '&imageBtnFlag=false';
	editorParam += (option.attachBtnYn) ? "" : '&attachBtnFlag=false';
	editorParam += (option.editorResize)? "" : '&editorResizeFlag=false';

	if(option.attachmentSns != "" && option.attachmentSns != 0) {
		$.getJSON(Context.FILE_JSONVIEW, { "daumeditor"	: "true", "fileSns" : option.attachmentSns },
				function(response) {
					option.attachments = response;
					editorLoad();
				}
		);
	} else {
		if(option.attachments['image'] == undefined)
			option.attachments = { "image" : [] };
		editorLoad();
	}

	function editorLoad() {
		// 타겟에 에디터를 Ajax로 로딩후 콜백으로 초기화
		//alert("Context.EDITOR_URL:"+Context.EDITOR_URL+":"+editorParam);
		$("#"+option.editorId).load(Context.EDITOR_URL + editorParam, editorLoadCallback);
	}

	// 에디터 페이지 호출이 끝난 뒤 에디터에 대한 초기화를 수행하는 콜백 함수이다.
	function editorLoadCallback() {
		/*------ 파일 첨부 삭제 이벤트 오버라이딩 : 서버에서도 파일 삭제 -----*/
		Trex.module("notify removed attachments",
			function(editor, toolbar, sidebar, canvas, config){
				editor.getAttachBox().observeJob(Trex.Ev.__ENTRYBOX_ENTRY_REMOVED, function(entry) {
					//console.log('첨부 삭제 이벤트 발생.... 파일번호 : ' + cUrl("/file/delete/") + entry.data.fileSn + ':' + entry.data);
					var fileDeleteUrl = cUrl("/app/file/delete/") + entry.data.fileSn;
					console.log("파일 삭제 시도: " + fileDeleteUrl);
					$.post(fileDeleteUrl);
				});
			}
		);

		/*-------- 에디터 로드 시작 ----------*/
		new Editor({
			txHost: '', /* 런타임 시 리소스들을 로딩할 때 필요한 부분으로, 경로가 변경되면 이 부분 수정이 필요. ex) http://xxx.xxx.com */
			txPath: cUrl('/libs/daumeditor/'), /* 런타임 시 리소스들을 로딩할 때 필요한 부분으로, 경로가 변경되면 이 부분 수정이 필요. ex) /xxx/xxx/ */
			txVersion: '7.5.11', /* 수정필요없음. */
			txService: 'sample', /* 수정필요없음. */
			txProject: 'sample', /* 수정필요없음. 프로젝트가 여러개일 경우만 수정한다. */
			initializedId: "", /* 대부분의 경우에 빈문자열 */
			wrapper: "tx_trex_container"+"", /* 에디터를 둘러싸고 있는 레이어 이름(에디터 컨테이너) */
			form: option.formId+"", /* 등록하기 위한 Form 이름 */
			txIconPath: cUrl("/libs/daumeditor/images/icon/"), /*에디터에 사용되는 이미지 디렉터리, 필요에 따라 수정한다. */
			txDecoPath: cUrl("/libs/daumeditor/images/deco/"), /*본문에 사용되는 이미지 디렉터리, 서비스에서 사용할 때는 완성된 컨텐츠로 배포되기 위해 절대경로로 수정한다. */
			canvas: {
				styles: {
					color: "#444444", /* 기본 글자색 */
					fontFamily: "굴림", /* 기본 글자체 */
					fontSize: "12pt", /* 기본 글자크기 */
					backgroundColor: "#fff", /*기본 배경색 */
					lineHeight: "1.5", /*기본 줄간격 */
					padding: "0px" /* 위지윅 영역의 여백 */
				}
			},
			sidebar: {
				attachbox: {
					show: true,
					confirmForDeleteAll: true
				},
				attacher: {
					image:	{
						multiple: true,
						multipleuse: false,
						disabledonmobile: false,
						checksize: false,
						boxonly: false,
						wysiwygonly: true,
						features: {left: 0, top: 0, width: 600, height: 400},
						objattr: { width: 800 },	// 크기 조절 width:690, height:300 -> 이미지 싸이즈가 큰 경우 크리 조정용.
						popPageUrl: cUrl("/libs/daumeditor/popupFile.jsp?type=image&repository=" + option.repositoryCode)
					}
					,file: {
						multiple: true,
						multipleuse: false,
						disabledonmobile: false,
						boxonly: true,
						wysiwygonly: true,
						features: {left: 0, top: 0, width: 500, height: 400},
						popPageUrl: cUrl("/lib/daumeditor/popupFile.jsp?type=file&repository=" + option.repositoryCode)
					}
				},
				embeder: {
					media: {
						wysiwygonly: true,
						useCC: false,
						disabledonmobile: false,
						features: { left:250, top:65, width:440, height:258 },
						popPageUrl: cUrl("/libs/daumeditor/popupMultimedia.jsp")
					}
				}
			},
			size: {
				//contentWidth: 800 /* 지정된 본문영역의 넓이가 있을 경우에 설정 */
			},
			events: {
				preventUnload : false	// 페이지 벗어나기 경고 비활성화.
			}
		});

		/* 저장된 컨텐츠를 불러오기 위한 함수 호출 */
		Editor.modify({
			"attachments": function() { /* 저장된 첨부가 있을 경우 배열로 넘김, 위의 부분을 수정하고 아래 부분은 수정없이 사용 */
				var allattachments = [];
				// 이미지만 추출.
				allattachments = allattachments.concat(option.attachments['image']);

				//alert(showAttribute(option.attachments['image'][0]['data']));
				//for(var i in option.attachments) { allattachments = allattachments.concat(option.attachments[i]); }
				return allattachments;
			}(),
			//"content": option.textareaId /* 내용 문자열, 주어진 필드(textarea) 엘리먼트 */
			"content": $tx(option.textareaId) /* 내용 문자열, 주어진 필드(textarea) 엘리먼트 */
		});

		if(option.initeditor != undefined) {
			option.initeditor();	// 에디터 초기화 추가 함수가 있으면 수행한다.
		}

		// 에디터 초기화 추가 함수가 있으면 수행한다.
		Editor.getCanvas().setCanvasSize({ height: option.editorHeight });
		option.initeditor();

	}; // function editorLoadCallback end.
};

// 첨부파일의 ID들을 가져오는 매서드를 Editor에 추가..
Editor.getAttachmentIds = function(editor, type, mode) {

	var addParam = function(params, value) {
		return (params == '') ? value : params + '!@!' + value;
	};

	var fileList = editor.getAttachments(type, mode);
	var _rtn = "";
	for(var i=0; i < fileList.length; i++) {
		//if(attachments[i].existStage) : 현재 본문에 존재하는 경우 전송.
		_rtn = addParam(_rtn, fileList[i].data.fileSn);
	}
	return _rtn;
};

