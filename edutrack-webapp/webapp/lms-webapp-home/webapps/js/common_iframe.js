
//document.write("<iframe name='hiddenFrame' width='0' height='0' frameborder='0'></iframe>");



/**
 * IFRAME Resize
 *@param	iFrame 			객체(this)
 *@param	defaultHeight	기본 높이
 */
function resizeIframe(curFrame, defaultHeight) {
	curFrame.style.display = "block";
	if (defaultHeight == null) {
		defaultHeight = 0;
	}
	var height = 0;

	if (curFrame.contentDocument && curFrame.contentDocument.body.offsetHeight) { //ns6 syntax
		height = curFrame.contentDocument.body.offsetHeight;
	} else if (curFrame.document && curFrame.document.body.scrollHeight) { //ie5+ syntax
		height = curFrame.document.body.scrollHeight;
	}
	if (height < defaultHeight) {
		height = defaultHeight;
	}
	curFrame.style.height = height+"px";
}

function resizeIframe3(curFrame, height) {
	curFrame.style.display = "block";
	curFrame.style.height = height+"px";
}

function resetIframe3(curFrame) {
	curFrame.style.height = '500px';
}

function resizeIframe2(curFrame) {
	curFrame.style.display = "block";
	if (curFrame.contentDocument && curFrame.contentDocument.body.offsetHeight) { //ns6 syntax
		curFrame.style.height = curFrame.contentDocument.body.offsetHeight + 16 + "px";
	}
	else if (curFrame.Document && curFrame.Document.body.scrollHeight) { //ie5+ syntax
		curFrame.style.height = curFrame.Document.body.scrollHeight + "px";
	}
	if (curFrame.contentDocument && curFrame.contentDocument.body.offsetWidth) { //ns6 syntax
		curFrame.style.width = curFrame.contentDocument.body.offsetWidth + 16 + "px";
	}
	else if (curFrame.Document && curFrame.Document.body.scrollWidth) { //ie5+ syntax
		curFrame.style.width = curFrame.Document.body.scrollWidth + "px";
	}

}


function fullIframe(curFrame) {
	curFrame.style.display = "block";
	if (curFrame.contentDocument && curFrame.contentDocument.body.offsetHeight) { //ns6 syntax
		curFrame.height = curFrame.contentDocument.body.offsetHeight + 16;
	}
	else if (curFrame.Document && curFrame.Document.body.scrollHeight) { //ie5+ syntax
		curFrame.height = curFrame.Document.body.scrollHeight;
	}
}

function resizeBodyIframe(frameName){
	try
	{
		var curFrame = document.getElementById(frameName);
		var frmWidth;
		var frmHeight;
		if (curFrame.contentDocument && curFrame.contentDocument.body.offsetHeight) { //ns6 syntax
		    frmWidth = curFrame.contentDocument.body.offsetWidth + 16;
			frmHeight = curFrame.contentDocument.body.offsetHeight + 16;
		}
		else if (curFrame.Document && curFrame.Document.body.scrollHeight) { //ie5+ syntax
		    frmWidth =  curFrame.Document.body.scrollWidth + 16;
			frmHeight = curFrame.Document.body.scrollHeight + 16;
		}

		curFrame.style.height = frmHeight;
		curFrame.style.width = frmWidth;
	}
	catch (e)
	{
	}
}

/**
 * 팝업용으로 사용되는 iframe의 컨텐츠를 생성.
 * @param iframeId
 * @param src
 * @param cmd
 * @param params
 * @return
 */
function generateIframeSrc(iframeId, src, cmd, params) {
	var content = "<iframe id='"+iframeId+"' name='"+iframeId+"' width='100%' height='100%' frameborder='0' scrolling='no' "
				+ "src='" + src
				+ "?cmd=" + cmd;
	if(params != undefined && params != '') {
		content += params;
	}

	content += "'/>";

	return content;
}