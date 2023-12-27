/*******************************************************************************
	DHTML 관련 공통 유틸리티

	Author: Kim Kyoung shil
*******************************************************************************/
var browserType			= checkBrowserType();	// 사용자 브라우져 타입

/**
 * 사용자 브라우져 체크
 */
function checkBrowserType() {
	var agt = navigator.userAgent.toLowerCase();
	if (agt.indexOf("chrome") != -1) return 'Chrome';
	if (agt.indexOf("opera") != -1) return 'Opera';
	if (agt.indexOf("staroffice") != -1) return 'Star Office';
	if (agt.indexOf("webtv") != -1) return 'WebTV';
	if (agt.indexOf("beonex") != -1) return 'Beonex';
	if (agt.indexOf("chimera") != -1) return 'Chimera';
	if (agt.indexOf("netpositive") != -1) return 'NetPositive';
	if (agt.indexOf("phoenix") != -1) return 'Phoenix';
	if (agt.indexOf("firefox") != -1) return 'Firefox';
	if (agt.indexOf("safari") != -1) return 'Safari';
	if (agt.indexOf("skipstone") != -1) return 'SkipStone';
	if (agt.indexOf("msie") != -1) return 'IExplorer';
	if (agt.indexOf("trident") != -1) return 'IExplorer';
	if (agt.indexOf("netscape") != -1) return 'Netscape';
	if (agt.indexOf("mozilla/5.0") != -1) return 'Mozilla';
}


/**
 * 마우스 X 위치
 */
function checkMouseX(evt) {
	var xCoord = 0;
	if (browserType == "IE"){
		xCoord = event.clientX;
	}
	else if (browserType == "NE"){
		xCoord = evt.pageX;
	}
	return xCoord;
}


/**
 * 마우스 Y 위치
 */
function checkMouseY(evt) {
	var yCoord = 0;
	if (browserType == "IE"){
		yCoord = event.clientY;
	}
	else if (browserType == "NE"){
		yCoord = evt.pageY;
	}
	return yCoord;
}


/**
 * 마우스 버튼값 구하기
 * @return left,center,right
 */
function getMouseButton(evt) {
	var button 		= "left";
	var mouseKey	= 1;
	if (browserType == "IE") {
		mouseKey = event.button
		if (mouseKey == 1) {
			button = "left";
		}
		else if (mouseKey == 2) {
			button = "right";
		}
		else if (mouseKey == 4) {
			button = "center";
		}
	}
	else {
		mouseKey = evt.which;
		if (mouseKey == 1) {
			button = "left";
		}
		else if (mouseKey == 2) {
			button = "center";
		}
		else if (mouseKey == 3) {
			button = "right";
		}
	}
	return button;
}


/**
 * 객체의 넓이 구하기
 */
function getWidth(obj) {
	if (obj) {
		var objWidth = obj.offsetWidth;
		return objWidth;
	}
}


/**
 * 객체의 높이 구하기
 */
function getHeight(obj) {
	if (obj) {
		var objHeight = obj.offsetHeight;
		return objHeight;
	}
}


/**
 * 윈도우 넓이 구하기
 */
function getWindowWidth() {
	var winWidth = 0;
	if (browserType == "IE") {
		winWidth = document.body.offsetWidth;
	}
	else if (browserType == "NE") {
		winWidth = window.innerWidth;
	}
	return winWidth;
}


/**
 * 윈도우 높이 구하기
 */
function getWindowHeight() {
	var winHeight = 0;
	if (browserType == "IE") {
		winHeight = document.body.offsetHeight;
	}
	else if (browserType == "NE") {
		winHeight = window.innerHeight;
	}
	return winHeight;
}


/**
 * 스크롤값(X) 구하기
 */
function getScrollX() {
	var scrollX		= 0;

    if (browserType == "NE") {
        scrollX		= window.pageXOffset;
    }
    else {
        scrollX		= document.body.scrollLeft;
    }

	return scrollX;
}


/**
 * 스크롤값(Y) 구하기
 */
function getScrollY() {
	var scrollY		= 0;

	if (browserType == "NE") {
        scrollY		= window.pageYOffset;
    }
    else {
        scrollY		= document.body.scrollTop;
    }

	return scrollY;
}


/**
 * 객체 가져오기
 * @param objName(객체명)
 */
function getObject(objName) {
	/*
	var returnObj = null;
	if (browserType == "IE") {
		//returnObj = eval("document.all."+objName);
		returnObj = document.getElementById(objName);
	}
	else if (browserType == "NE") {
		returnObj = document.getElementById(objName);
	}

	return returnObj;
	*/
	return document.getElementById(objName);
}


/**
 * 객체 DISPLAY 변경
 */
function changeDisplay(obj) {
	if (obj) {
		var displayStyle = obj.style.display;
		if (displayStyle == "block") {
			offDisplay(obj);
		}
		else if (displayStyle == "none") {
			onDisplay(obj);
		}
	}
}


/**
 * DISPLAY on
 */
function onDisplay(obj) {
	if (obj) {
		obj.style.display = "block";
	}
}


/**
 * DISPLAY off
 */
function offDisplay(obj) {
	if (obj) {
		obj.style.display = "none";
	}
}


/**
 * 객체 보이기
 */
function visibleObject(obj) {
	if (obj) {
		obj.style.visibility = "visible";
	}
}


/**
 * 객체 숨기기
 */
function hiddenObject(obj) {
	if (obj) {
		obj.style.visibility = "hidden";
	}
}


/**
 * 객체 선택시 색상 반전
 */
function onSelectColor(obj) {
	if (obj) {
		obj.style.backgroundColor	= "highlight";
		obj.style.color				= "highlighttext";
	}
}


/**
 * 객체 선택 비활성 색상 복원
 */
function offSelectColor(obj, color, bgColor) {
	if (obj) {
		if (color == null) {
			color = "";
		}
		if (bgColor == null) {
			bgColor = "";
		}
		obj.style.color				= color;
		obj.style.backgroundColor	= bgColor;
	}
}


/*
 * 객체 사이즈 설정
 */
function resizeObject(obj, newWidth, newHeight) {
	if (obj) {
		if (nullToEmpty(newWidth) != "") {
			obj.style.width = newWidth+"px";
		}
		if (nullToEmpty(newHeight) != "") {
			obj.style.height = newHeight+"px";
		}
	}
}


/**
 * 객체 넓이 설정
 */
 function medio_resizeWidth(obj, newWidth) {
 	if (obj) {
		obj.style.width		= newWidth+"px";
	}
 }


/**
 * 객체 높이 설정
 */
function medio_resizeHeight(obj, newHeight) {
	if (obj) {
		obj.style.height	= newHeight+"px";
	}
}


/**
 * 객체 이동 설정 (위치)
 */
function moveObject(obj, topPosition, leftPosition) {
	if (obj) {
		if (nullToEmpty(topPosition) != "") {
			obj.style.top       = topPosition+"px";
		}
		if (nullToEmpty(leftPosition) != "") {
			obj.style.left      = leftPosition+"px";
		}
	}
}


/**
 * 객체의 위치 (document 내에서 절대적인 위치)
 */
function getPosition(obj) {
	var pos = { x:0, y:0 };

	if ( document.layers ) {
		pos.x = obj.x;
		pos.y = obj.y;
	}
	else {
		do {
			pos.x += parseInt( obj.offsetLeft );
			pos.y += parseInt( obj.offsetTop );
			obj = obj.offsetParent;
		} while (obj);
	}
	return pos;
}


/**
 * 객체의 왼쪽 위치 (상대위치)
 */
function getLeftPosition(obj) {
	var value = 0;
	if (obj) {
		if (browserType == "IE") {
			if (obj.currentStyle.left == "auto") {
				value = 0;
			}
			else {
				value = parseInt(obj.currentStyle.left);
			}
		}
		else {
			value = parseInt(obj.style.left);
		}
	}
	return value;
}


/**
 * 객체 위쪽 위치 (상대위치)
 */
function getTopPosition(obj) {
	var value = 0;
	if (obj) {
		if (browserType == "IE") {
			if (obj.currentStyle.top == "auto") {
				value = 0;
			}
			else {
				value = parseInt(obj.currentStyle.top);
			}
		}
		else {
			value = parseInt(obj.style.top);
		}
	}
	return value;
}


/**
 * 객체의 속성값 구하기
 */
function getObjectAttribute(obj, attrName) {
	var attrValue	= null;

	if (obj) {
		attrValue = obj.getAttribute(attrName);
	}
	return attrValue;
}


/**
 * 객체ID 구하기
 */
function getObjectId(obj) {
	var objectId = getObjectAttribute(obj, "id");
	return objectId;
}


/**
 * 객체를 맨 위로 위치하기
 */
function makeOnTop(obj) {
	var max = 0;
	if (obj) {
		var topel = $("body");
		topel.each(function() {
			//Find the current z-index value
			var z = parseInt($(this).css("z-index"), 10);
			// Keep either the current max, or the current z-index, whichever is higher
			max = Math.max(max, z);
        });
		// Set the box that was clicked to the highest z-index plus one
		obj.style.zIndex = max + 1;
	}
}


/**
 * Attribute의 Value 추출 (name=value 에서 value 추출
 */
function getAttributeValue(attr, name) {
	attr = nullToEmpty(attr).toLowerCase();
	var value = "";
	if (attr.length > 1) {
		var idx1 = attr.indexOf(name);
		if (idx1 != -1) {
			var idx2 = attr.indexOf("=",idx1);
			if (idx2 != -1) {
				var idx3 = attr.indexOf(",",idx2);
				var oValue = "";
				if (idx3 == -1) {
					oValue = attr.substring(idx2+1);
				}
				else {
					oValue = attr.substring(idx2+1,idx3);
				}
				for (var i = 0; i < oValue.length; i++) {
					if (oValue.charCodeAt(i) != 32) {
						value += oValue.charAt(i);
					}
				}

			}
		}

	}
	return value;
}


/**
 * 이미지 변경
 */
function changeImage(imgObj, imgSrc) {
	imgObj.src = imgSrc;
}


/**
 * 문자열에서 구분자로 분자열 분리하기
 * @param str		문자열
 * @param idx		구분자를 기준으로 n번째 문자열 추출
 * @param divideStr 구분문자
 */
function getDivideString(str, idx, divideChar) {
	var result = "";
	if (nullToEmpty(str) != "") {
		var strArr = str.split(divideChar);
		result = strArr[idx-1];
	}

	if (!result) {
		result = "";
	}

	return result;
}


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
		//result = result.replace(/\%/g,'*');
		//result = escape(url).replace(/+/g, '%2C').replace(/"/g,'%22').replace(/|'/g, '%27');
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
 * 이미지 width 변경
 * 호출방법
	onload = goComplete;
	var imgObjId = "attach_picture";
	var maxImgWidth = 675;
	function goComplete() {
		imgReSize();
	}
 */
function imgReSize() {
	while(true) {
		var imgObj = getObject(imgObjId);
		if(imgObj.length) {
			for(var i=0; i<imgObj.length; i++) {
				var imgPerObj = imgObj[i];
				if(imgPerObj == null) {
					setTimeout("imgReSize", 500);
				} else {
					if(imgPerObj.width >= maxImgWidth) {
						imgPerObj.width = maxImgWidth;
					}
				}
			}
			break;
		} else {
			if(imgObj == null) {
				setTimeout("imgReSize", 500);
			} else {
				if(imgObj.width >= maxImgWidth) {
					imgObj.width = maxImgWidth;
				}
				break;
			}
		}
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

function GetCookie (name) {
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
/*
function SetCookie(name, value) {
	var argv = SetCookie.arguments;
	var argc = SetCookie.arguments.length;
	var expires = (2 < argc) ? argv[2] : null;
	var path = (3 < argc) ? argv[3] : null;
	var domain = (4 < argc) ? argv[4] : null;
	var secure = (5 < argc) ? argv[5] : false;
	document.cookie = name + "=" + escape (value) +
		((expires == null) ? "" : ("; expires=" + expires.toGMTString())) +
		((path == null) ? "" : ("; path=" + path)) +
		((domain == null) ? "" : ("; domain=" + domain)) +
		((secure == true) ? "; secure" : "");
}*/
function setCookie(name, value, expiredays) {
	var todayDate = new Date();
	todayDate.setDate(todayDate.getDate() + expiredays);
	document.cookie = name + "=" + escape(value) + "; path=/; expires=" + todayDate.toGMTString() + ";"
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

/**
 * jquery를 이용한 하위 element 사이즈 조절.
 * @param elementId
 * @param maxwidth
 */
function resizeSubContentsByMaxWidth(elementId, maxwidth) {
	$("#"+elementId).find("img").css("max-width", maxwidth+"px");
	$("#"+elementId).find("div").css("max-width", maxwidth+"px");
	$("#"+elementId).find("p").css("max-width", maxwidth+"px");
	$("#"+elementId).find("iframe").css("width", maxwidth+"px");
}

/**
 * jquery를 이용한 하위 element 사이즈 조절.
 * @param elementId
 * @param padding
 */
function resizeSubContents(elementId, padding) {
	var defaultMaxWidth = "763";
	var element = $("#"+elementId);
	//var width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
	var width = element.width();
	var maxwidth = width - padding;
	$("#"+elementId).find("img").css("max-width", maxwidth+"px");
	$("#"+elementId).find("div").css("max-width", maxwidth+"px");
	$("#"+elementId).find("p").css("max-width", maxwidth+"px");
	$("#"+elementId).find("iframe").css("width", maxwidth+"px");
}

/**
 * jquery를 이용한 하위 element 사이즈 조절.
 * @param elementId
 * @param padding
 */
function resizeSubContentsByClass(elementClass, padding) {
	var width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
	var maxwidth = width - padding;
	if(width < 768) {
		$("."+elementClass).find("img").css("max-width", maxwidth+"px");
		$("."+elementClass).find("div").css("max-width", maxwidth+"px");
		$("."+elementClass).find("p").css("max-width", maxwidth+"px");
		$("."+elementClass).find("iframe").css("width", maxwidth+"px");
	}
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

function toHalfChar(x_char) {
	try {
		var x_2byteChar = new String;
		var len = x_char.length;
		for (var i = 0; i < len; i++) {
			var c = x_char.charCodeAt(i);
			if (c >= 65281 && c <= 65374 && c != 65340) {
				x_2byteChar += String.fromCharCode(c - 65248);
			} else if (c == 8217) {
				x_2byteChar += String.fromCharCode(39);
			} else if (c == 8221) {
				x_2byteChar += String.fromCharCode(34);
			} else if (c == 12288) {
				x_2byteChar += String.fromCharCode(32);
			} else if (c == 65507) {
				x_2byteChar += String.fromCharCode(126);
			} else if (c == 65509) {
				x_2byteChar += String.fromCharCode(92);
			} else {
				x_2byteChar += x_char.charAt(i);
			}
		}
		return x_2byteChar;
	} catch (err) {
		return '';
	}
}


