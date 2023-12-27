/*******************************************************************************
	자바스크립le트 jQuery기반 공통 함수 (통일교육원 프로젝트중 생성)
*******************************************************************************/

/**
 * 파일다운로드를 새창 없이 iframe으로 수행시키는 공통함수(jQuery)
 * @param id filerepository의 id값
 */
function fileDown(id) {
	downloadUrl = lmsFileDownload + id;
	// download용 iframe이 없으면 만든다.
	if ( $("#_m_download_iframe").length == 0 ) {
		iframeHtml =
			'<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>' +
			'<form name="_m_download_form" id="_m_download_form" target="_m_download_iframe"></form>';
		$("body").append(iframeHtml);
	}
	// 폼에 action을 설정하고 submit시킨다.
	$("#_m_download_form").attr('action', downloadUrl).submit();
}

function ecatalog(docu, kd, dir) {
	if (screen.width < 800) { alert("The screen resolution should be over 800*600"); return; }

	if 		(kd == "fixed") { x = 1024; y = 768; wname = "fixed_ecatalog"; }
	else if (screen.width > 1280 || screen.height > 1024) { x = 1280; y = 1024; wname = "ecatalog"; }
	else 	{ x = screen.width; y = screen.height; wname = "ecatalog"; }
	x = x - 10; y = y - 58;
	property = "toolbar=no,location=no,directories=no,status=no,menubar=no,resizable=no,scrollbars=no,copyhistory=no,";
	property += "width=" + x + ",height=" + y + ",left=" + 0 + ",top=" + 0;
	docu = docu + "/ecatalog" + dir + ".html";
	ecawin = window.open(docu, wname, property);
}

/**
 * byte를 용량에 따라 b, kb, mb, gb, tb로 계산하여 리턴함 (JavaScript)
 * @param int bytes
 * @return String
 */
function byteConvertor(bytes) {
	bytes = parseInt(bytes);
	var s = ['bytes', 'KB', 'MB', 'GB', 'TB', 'PB'];
	var e = Math.floor(Math.log(bytes)/Math.log(1024));
	if(e == "-Infinity") return "0 "+s[0];
	else return (bytes/Math.pow(1024, Math.floor(e))).toFixed(2)+" "+s[e];
}

/**
 * 플래쉬 소스 Embed 스크립트.
 */
$M.Flash = function (trans, width, height) {
	mainbody = "<object classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' codebase='https://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0' width='"+ width +"' height='"+ height +"'>";
	mainbody += "<param name='movie' value='"+ trans +"'>";
	mainbody += "<param name='quality' value='high'>";
	mainbody += "<param name='wmode' value='transparent'>";
	mainbody += "<param name='menu' value='false'>";
	mainbody += "<embed src='"+ trans +"' quality='high' pluginspage='https://www.macromedia.com/go/getflashplayer' type='application/x-shockwave-flash' width='"+ width +"' height='"+ height +"'></embed>"
	mainbody += "</object>";
	document.write(mainbody);
	return;
};

/**
 * 팝업 오픈 스크립트
 */
$M.Popup = function (name, url, width, height, top, left, toolbar, menubar, statusbar, scrollbar, resizable) {
	toolbar_str = toolbar ? 'yes' : 'no';
	menubar_str = menubar ? 'yes' : 'no';
	statusbar_str = statusbar ? 'yes' : 'no';
	scrollbar_str = scrollbar ? 'yes' : 'no';
	resizable_str = resizable ? 'yes' : 'no';
	window.open(url, name, 'left='+left+',top='+top+',width='+width+',height='+height+',toolbar='+toolbar_str+',menubar='+menubar_str+',status='+statusbar_str+',scrollbars='+',resizable='+resizable_str);
	return false;
};

/**
 * $M.Check 네임스페이스 설정.
 */
$M.Check = {};

/**
 * 이벤트 체크
 */
$M.Check.Event = {
	isClick :
		function(event) {
			return (event.type == 'click');
		},
	isEnter :
		function(event) {
			return (event.type == 'keydown' && event.keyCode == 13);
		},
	isClickEnter :
		function(event) {
			return ( $M.Check.Event.isClick(event) || $M.Check.Event.isEnter(event) );
		}
};

/**
 * alert 상태 클래스.
 * setMsg로 오류메시지를 표시하고 return 에 check()를 사용한다.
 */
$M.Check.Alert = function() {
	this.message = "";
	this.hasError = false;
};

$M.Check.Alert.prototype = {
	setMsg	:	// 메시지를 설정하고 에러보유로 변경
		function(msg) {
			this.message += (this.hasError) ? "\n\n" + msg : msg;
			this.hasError = true;
		},
	alert	:	// 오류가 있으면 메시지를 표시.
		function() {
			if(this.hasError)
				alert(this.message);
			return !this.hasError;
		}
};

function changeLang(lang) {
	document.location.href = cUrl("/MainHome.do")+"?cmd=indexChgLang&langCd="+lang;
}

function showLoadingImage(parentId) {
	$("<div id='loadingImage'><img src='/img/ajaxLoader.gif' alt='loading...' style='position:absolute;top:47%;left:47%;'/></div>").css({
	    position: "absolute",
	    width: "100%",
	    height: "100%",
	    top: "0",
	    left: "0",
	    background: "#fff",
	    filter: "alpha(opacity=60)"
	}).appendTo($("#"+parentId).css("position", "relative"));
}

function hideLoadingImage(parentId) {
	$("#"+parentId).find("#loadingImage").remove();
}
