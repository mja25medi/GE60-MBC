// 기본 경로로 변경해서 반환하는 함수
function cUrl(url) {
	if (url.charAt(0) == "/") {
		return CONTEXT_ROOT + url;
	} else {
		return CONTEXT_ROOT + "/" + url;
	}
}

function showAttribute(obj) {
	try {
		var data = '';
		for (var attr in obj) {
			if (typeof(obj[attr]) == 'string' || typeof(obj[attr]) == 'number') {
				data = data + 'Attr Name : ' + attr + ', Value : ' + obj[attr] + ', Type : ' + typeof(obj[attr]) + '\n';
			} else if(typeof(obj[attr]) != 'function') {
				data = data + 'Attr Name : ' + attr + ', Type : ' + typeof(obj[attr]) + '\n';
			} else {
				data = data + 'Attr Name : ' + attr + ', Type : ' + typeof(obj[attr]) + '\n';
			}
		}
		return data;
	} catch (e) {
		alert(e.message);
	}
}

//-- 모든 페이지에서 사용할 전역 변수
var ItemVO = new Object();
var MessageVO = new Object();

