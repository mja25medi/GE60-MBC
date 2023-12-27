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

/**
 * null 값을 ""으로 변환
 */
function nullToEmpty(value) {
	if (value == null) {
		value = "";
	}
	return value;
}


/**
 * URL 엔코딩
 */
function getUrlEncode(url) {
	var result = "";
	if (nullToEmpty(url) != "") {
		result = encodeURIComponent(url);
	}
	return result;
}


/**
 * URL 디코딩
 */
function getUrlDecode(url) {
	var result = "";
	if (nullToEmpty(url) != "") {
		result = url.replace(/\*/g,'%');
		result = decodeURIComponent(result);
	}
	return result;
}


/**
 * 파일의 확장명 반환
 */
function getFileExtention(fileName) {
	var ext = "";

	if (nullToEmpty(fileName) != "") {
		var idx = fileName.lastIndexOf(".");
		if (idx > -1) {
			ext = (fileName.substring(idx+1)).toLowerCase();
		}
	}

	return ext;
}

/**
 * 서버경로에서 파일명만 추출하는 함수
 * @param fname
 * @return
 */
function getFileName(filePath) {
	if (filePath !="") {
		var arr = filePath.split("\\");
		var name = arr[arr.length-1];
		return	name;
	}
	else {
		return	"";
	}
}

/**
* 쿠키값 처리
*/
function getCookieVal(offset) {
	var endstr = document.cookie.indexOf (";", offset);
	if(endstr == -1) {
		endstr = document.cookie.length;
	}
	return unescape(document.cookie.substring(offset, endstr));
}

function getCookie (name) {
	var arg = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	while(i < clen) {
		var j = i + alen;
		if(document.cookie.substring(i, j) == arg) {
			return getCookieVal(j);
		}
		i = document.cookie.indexOf(" ", i) + 1;
		if(i == 0) {
			break;
		}
	}
	return null;
}

function setCookie(name, value, expiredays) {
	var todayDate = new Date();
	todayDate.setDate(todayDate.getDate() + expiredays);
	document.cookie = name + "=" + escape(value) + "; path=/; expires=" + todayDate.toGMTString() + ";"
}

/*
 * Url과 파라미터를 받아 URL을 완성해서 리턴한다.
 * 웹표준의 & 를 피하기 위한 방안.
 */
function generateUrl(url, param) {
	var returnUrl = "";
	returnUrl = cUrl(url);
	var i = 0;
	if(param != undefined) {
		$.each(param, function(key, value) {
			if(value != undefined && value != "" ) {
				if(i == 0) returnUrl += "?"+key+"="+value;
				else returnUrl += "&"+key+"="+value;
				i++;
			}
		});
	}
	return returnUrl;
}

function isNull(str) {

	if(str == "" || str == null || str == " " || str == undefined)
		return true;
	else
		return false;
}

function isNotNull(str) {
	return !isNull(str);
}

function isEmpty(str) {
	for (var i = 0; i < str.length; i++) {
		if (str.substring(i, i+1) != " ") {
			return false;
		}
	}
	return true;
}

function convertToBase64(str) {
	var output = "";
	if(str != "") {
		var keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
		var text = str;
		var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
		var i = 0;
		input = utf8_encode(text);
		while (i < input.length) {
			chr1 = input.charCodeAt(i++);
			chr2 = input.charCodeAt(i++);
			chr3 = input.charCodeAt(i++);
			enc1 = chr1 >> 2;
			enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
			enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
			enc4 = chr3 & 63;
			if (isNaN(chr2)) {
				enc3 = enc4 = 64;
			} else if (isNaN(chr3)) {
				enc4 = 64;
			}
			output = output +
			keyStr.charAt(enc1) + keyStr.charAt(enc2) +
			keyStr.charAt(enc3) + keyStr.charAt(enc4);
		}
		return output;
	} else {
		return "";
	}
}

function utf8_encode(string) {
	string = string.replace(/\r\n/g,"\n");
	var utftext = "";
	for (var n = 0; n < string.length; n++) {
		var c = string.charCodeAt(n);
		if (c < 128) {
			utftext += String.fromCharCode(c);
		} else if((c > 127) && (c < 2048)) {
			utftext += String.fromCharCode((c >> 6) | 192);
			utftext += String.fromCharCode((c & 63) | 128);
		} else {
			utftext += String.fromCharCode((c >> 12) | 224);
			utftext += String.fromCharCode(((c >> 6) & 63) | 128);
			utftext += String.fromCharCode((c & 63) | 128);
		}
	}
	return utftext;
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