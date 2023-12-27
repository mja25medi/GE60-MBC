/**
 * Do Start
 */
var pageLoadCnt = 0;
function doPageInit(page) {
 	if(edutrackAPI != null) {
 		pageLoadCnt++;
 		var locate = "";
 		try {
 			locate = edutrackAPI.GetValue("finalConnPage");
 		} catch (err) {

 		}
 		edutrackAPI.SetValue("finalConnPage",page);
 		return locate;
	} else {
		alert('Error! Do Not loaded Edutrack API.');
	}
}

function doPageQuit(ratio, page) {
	 if(edutrackAPI != null) {
		 edutrackAPI.SetPage(page, ratio);
	 } else {
		 alert('Error! Do Not loaded Edutrack API.');
	 }
}

function getCurrentPage() {
	var loc = location.href;
 	//---- 파라미터 날리기
 	loc = loc.split("?")[0];
 	var locs = loc.split("/");
 	loc = locs[locs.length-1];
 	return loc;
}

function getPageLoadCnt() {
	return this.pageLoadCnt;
}

function getIsReadedPage(page) {
	if(edutrackAPI.GetCredit() == 'credit') {
		var readPage = edutrackAPI.GetValue("accmConnPage");
		if(readPage.indexOf(page) > 0) return true;
		return false;
	} else {
		return true;
	}
}