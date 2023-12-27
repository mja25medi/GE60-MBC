/**
 * Edutrack Client API Adapter
 *
 * Mediopia Tech Inc.
 */

 /**
 * Edutrack Client API Adapter
 */
function EdutrackAPIAdapter() {
	this.isInitialized					= false;
	this.isTerminated					= false;
	this.errorCode						= "0";
	this.startTime						= "0";
	this.endTime						= "0";
	this.credit							= "";
	this.bookmarkType					= "";
	this.showTime						= "";
	this.totalPageCnt					= "";

	//Bookmark DTO
	this.BookmarkDTO = new Object();
	this.BookmarkDTO.stdNo 			= "";
	this.BookmarkDTO.sbjCd 			= "";
	this.BookmarkDTO.unitCd			= "";
	this.BookmarkDTO.studyYn		= "";
	this.BookmarkDTO.studyDttm		= "";
	this.BookmarkDTO.regNo			= "";
	this.BookmarkDTO.quizPassYn		= "";
	this.BookmarkDTO.passScore		= "0";
	this.BookmarkDTO.maxScore		= "0";
	this.BookmarkDTO.minScore		= "0";
	this.BookmarkDTO.connTotTm		= "0";
	this.BookmarkDTO.connTm			= "0";
	this.BookmarkDTO.finalConnPage	= "";
	this.BookmarkDTO.accmConnPage	= "";
	this.BookmarkDTO.connPageCnt	= "0";
	this.BookmarkDTO.totPageCnt		= "0";
	this.BookmarkDTO.prgrRatio		= "0";
	this.BookmarkDTO.connCnt		= "0";
	this.BookmarkDTO.seekTime		= "0";
	this.BookmarkDTO.mobileTm		= "0";

	this.BookmarkDTO.errorCode		= "0";
	this.BookmarkDTO.result			= "";

	this.Initialize 					= ApiInitialize;
	this.InitializeCallback				= ApiInitializeCallback;
	this.Terminate						= ApiTerminate;
	this.Commit							= ApiCommit;
	this.CommitCallback					= ApiCommitCallback;
	this.GetValue						= ApiGetValue;
	this.GetErrorString					= ApiGetErrorString;
	this.GetLastError					= ApiGetLastError;
	this.GetSeekTime					= ApiGetSeekTime;
	this.GetMobileCurTime				= ApiGetMobileCurTime;
	this.GetCredit						= ApiGetCredit;

	this.SetValue						= ApiSetValue;
	this.SetStdNo						= ApiSetStdNo;
	this.SetSbjCd						= ApiSetSbjCd;
	this.SetUnitCd						= ApiSetUnitCd;
	this.SetRegNo						= ApiSetRegNo;
	this.SetCredit						= ApiSetCredit;
	this.SetBookmarkType				= ApiSetBookmarkType;
	this.SetPage						= ApiSetPage;
	this.SetTotalPageCnt				= ApiSetTotalPageCnt;
	this.SetShowTime					= ApiSetShowTime;
	this.SetSeekTime					= ApiSetSeekTime;
	this.SetMobileCurTime				= ApiSetMobileCurTime;
}

/**
 * Initialize
 */
function ApiInitialize(parameter) {
	jQuery.ajaxSetup({
        async: false
	});
	var result = "false";
	if (parameter != null && parameter != "") {
		this.errorCode = "201";
	}
	else {
		if (this.isInitialized == true) {
			this.errorCode = "103";
		}
		else {
			var BookmarkDTO = this.BookmarkDTO;
			//---- 시작시간 세팅
			this.startTime = getTimeStamp('start');


			jQuery.getJSON( cUrl("/edu/BookmarkLecture.do"),		// url
					{ 	"cmd" : "indexViewBookmark",
						"bookmarkJson" : jQuery.toJSON(BookmarkDTO)
					},			// params
					jQuery.proxy(this, "InitializeCallback")
				);
		}
	}
	return result;
}

function ApiInitializeCallback(resultDTO) {
	var outBookmarkDTO = resultDTO.returnDto;
	result = outBookmarkDTO.result;
	if (result == "true") {
		//this.BookmarkDTO	= outBookmarkDTO;
		//-- 2016.05.02 Ajax로 재 전송되는 사이즈가 커져 필요한 값만 가져 오도록 수정.
		this.BookmarkDTO.stdNo 			= outBookmarkDTO.stdNo;
		this.BookmarkDTO.sbjCd 			= outBookmarkDTO.sbjCd;
		this.BookmarkDTO.unitCd			= outBookmarkDTO.unitCd;
		this.BookmarkDTO.studyYn		= outBookmarkDTO.studyYn;
		this.BookmarkDTO.studyDttm		= outBookmarkDTO.studyDttm;
		this.BookmarkDTO.regNo			= outBookmarkDTO.regNo;
		this.BookmarkDTO.quizPassYn		= outBookmarkDTO.quizPassYn;
		this.BookmarkDTO.passScore		= outBookmarkDTO.passScore;
		this.BookmarkDTO.maxScore		= outBookmarkDTO.maxScore;
		this.BookmarkDTO.minScore		= outBookmarkDTO.minScore;
		this.BookmarkDTO.connTotTm		= outBookmarkDTO.connTotTm;
		this.BookmarkDTO.connTm			= outBookmarkDTO.connTm;
		this.BookmarkDTO.finalConnPage	= outBookmarkDTO.finalConnPage;
		this.BookmarkDTO.finalStudyPage	= outBookmarkDTO.finalStudyPage;
		this.BookmarkDTO.accmConnPage	= outBookmarkDTO.accmConnPage;
		this.BookmarkDTO.connPageCnt	= outBookmarkDTO.connPageCnt;
		this.BookmarkDTO.totPageCnt		= outBookmarkDTO.totPageCnt;
		this.BookmarkDTO.prgrRatio		= outBookmarkDTO.prgrRatio;
		this.BookmarkDTO.connCnt		= outBookmarkDTO.connCnt;
		this.BookmarkDTO.seekTime		= outBookmarkDTO.seekTime;
		this.BookmarkDTO.errorCode		= outBookmarkDTO.errorCode;
		this.BookmarkDTO.result			= outBookmarkDTO.result;
		this.BookmarkDTO.prgrChkType    = outBookmarkDTO.prgrChkType;

		this.isInitialized 	= true;
		this.isTerminated	= false;
		this.errorCode 		= "0";
	} else {
		this.BookmarkDTO.quizPassYn		= "";
		this.BookmarkDTO.passScore		= "0";
		this.BookmarkDTO.maxScore		= "0";
		this.BookmarkDTO.minScore		= "0";
		this.BookmarkDTO.connTotTm		= "0";
		this.BookmarkDTO.connTm			= "0";
		this.BookmarkDTO.finalConnPage	= "";
		this.BookmarkDTO.accmConnPage	= "";
		this.BookmarkDTO.connPageCnt	= "0";
		this.BookmarkDTO.totPageCnt		= "0";
		this.BookmarkDTO.prgrRatio		= "0";
		this.BookmarkDTO.connCnt		= "0";
		this.BookmarkDTO.seekTime		= "0";
		this.BookmarkDTO.mobileTm		= "0";
	}
}


/**
 * Terminate
 */
function ApiTerminate(parameter) {
	var result = "false";
	if (parameter != null && parameter != "") {
		this.errorCode = "201";
	}
	else if (this.isTerminated == true) {
		this.errorCode = "113";
	}
	else {
		if (this.isInitialized == true) {
			// Terminate 처리
			result = this.Commit("");
		}
		else {
			this.errorCode = "112";
		}
	}
	return result;
}


/**
 * Commit
 */
function ApiCommit(parameter) {
	var result = "false";
	if (parameter != null && parameter != "") {
		this.errorCode = "201";
	}
	else if (this.isTerminated == true) {
		this.errorCode = "143";
	}
	else {
		if (this.isInitialized == true) {
			// credit 인 경우만 저장
			if (this.credit == "credit") {
				this.endTime = getTimeStamp('end');
				this.BookmarkDTO.connTm = this.endTime - this.startTime;
				if(this.BookmarkDTO.connTm > 999999999) this.BookmarkDTO.connTm = 999999999;
				if(parseInt(this.BookmarkDTO.connTm,10) < 0) this.BookmarkDTO.connTm = -this.BookmarkDTO.connTm; //-- 자바스크립트 오류로 음수가 되는 경우 양수로 저환한다.
				this.BookmarkDTO.connTotTm = parseInt(this.BookmarkDTO.connTotTm,10) + parseInt(this.BookmarkDTO.connTm,10);
				if(this.BookmarkDTO.connTotTm > 999999999) this.BookmarkDTO.connTotTm = 999999999;
				this.BookmarkDTO.connCnt = parseInt(this.BookmarkDTO.connCnt,10) + 1;
				if(this.BookmarkDTO.connCnt > 999) this.BookmarkDTO.connCnt = 999;

				//---- bookmark Type 에 따른 진도율 계산.
				if(this.bookmarkType == "PAGE") {
					if(this.BookmarkDTO.prgrRatio > 100) this.BookmarkDTO.prgrRatio = 100;
				} else if(this.bookmarkType == "TIME") {
					if(this.showTime == "" || this.showTime == "0") {
						this.BookmarkDTO.prgrRatio = 100;
					} else {
						var ratio = parseInt(this.BookmarkDTO.connTotTm,10) / (parseInt(this.showTime,10)*60);
						if(ratio > 1) this.BookmarkDTO.prgrRatio = 100;
						else this.BookmarkDTO.prgrRatio = ratio * 100;
					}
				}

				jQuery.getJSON( cUrl("/edu/BookmarkLecture.do"),		// url
						{ 	"cmd" : "indexAddBookmark",
							"bookmarkJson" : jQuery.toJSON(this.BookmarkDTO)
						},
						jQuery.proxy(this, "CommitCallback") // params
					);

			}
			else {
				result = "true";
			}
		}
		else {
			this.errorCode = "142";
		}
	}
	return result;
}

function ApiCommitCallback(resultDTO) {
	if(resultDTO.returnResult >= 0) {
		this.isTerminated = true;
		this.isInitialized = false;
		//alert(parent.document.frames.name)
		top.opener.listContents();
		//alert(document.frames.name)
		return "true";
	}
}

/**
 * Get Value
 */
function ApiGetValue(parameter) {
	var result 		= "";
	if (this.isInitialized == false) {
		this.errorCode = "122";
		return result;
	}

	if (parameter == null || parameter == "") {
		this.errorCode = "301";
		return result;
	}

	result = eval("this.BookmarkDTO."+parameter);
	return result;
}


/**
 * Set Value
 */
function ApiSetValue(parameter, value) {
	if(parameter == "finalConnPage") {
		this.BookmarkDTO.finalConnPage = value;
	}
}


/**
 * Get ErrorString
 */
function ApiGetErrorString(errorCode) {

}


/**
 * Get Diagnostic
 */
function ApiGetDiagnostic(diagnosticCode) {

}

/**
 * Get Diagnostic
 */
function ApiGetCredit() {
	return this.credit;
}

/**
 * Get Last Error
 */
function ApiGetLastError() {
	return this.errorCode;
}

/**
 * Set StdNo
 */
function ApiSetStdNo(stdNo) {
	this.BookmarkDTO.stdNo = stdNo;
}
/**
 * Set SbjCd
 */
function ApiSetSbjCd(sbjCd) {
	this.BookmarkDTO.sbjCd = sbjCd;
}

/**
 * Set UnitCd
 */
function ApiSetUnitCd(unitCd) {
	this.BookmarkDTO.unitCd = unitCd;
}
/**
 * Set RegNo
 */
function ApiSetRegNo(regNo) {
	this.BookmarkDTO.regNo = regNo;
}
/**
 * Set credit
 */
function ApiSetCredit(credit) {
	this.credit = credit;
}
/**
 * Set bookmarkType
 */
function ApiSetBookmarkType(bookmarkType) {
	this.bookmarkType = bookmarkType;
}
/**
 * Set bookmarkType
 */
function ApiSetTotalPageCnt(totalPageCnt) {
	this.totalPageCnt = totalPageCnt;
}
/**
 * Set bookmarkType
 */
function ApiSetShowTime(showTime) {
	this.showTime = showTime;
}

function ApiSetSeekTime(seekTime) {
	this.BookmarkDTO.seekTime = seekTime;
}

function ApiSetMobileCurTime(mobileTime) {
	this.BookmarkDTO.mobileTm = mobileTime;
}

function ApiGetSeekTime() {
	return this.BookmarkDTO.seekTime;
}

function ApiGetMobileCurTime() {
	return this.BookmarkDTO.mobileTm;
}

/**
 * Set page
 */
function ApiSetPage(page, ratio) {
	if(page != "") {
		var ratios = this.BookmarkDTO.prgrRatio;
		var pages =	this.BookmarkDTO.accmConnPage;
		if(pages == null) pages = "";
		var cnt = 0;
		if(pages != "" && pages != null) {
			var pageArray = pages.split("#");
			for(var i=0; i<pageArray.length;i++) {
				if(pageArray[i] == page) cnt++;
			}
		}
		if(cnt == 0) {
			this.BookmarkDTO.accmConnPage = pages+"#"+page;
			this.BookmarkDTO.prgrRatio = ratios + parseInt(ratio,10);
			this.BookmarkDTO.connPageCnt = parseInt(this.BookmarkDTO.connPageCnt,10) + 1;
		}
		//this.BookmarkDTO.finalConnPage = page;
	}
}

/**
 * Set StartTime
 */
function getTimeStamp(str) {
	var ts = 0;
	jQuery.getJSON( cUrl("/edu/BookmarkLecture.do"),		// url
			{ 	"cmd" : "indexGetTimeStamp",
				"param" : str
			},			// params

			function(result) {
				ts = result.returnMessage;
			}
		);
	return ts;


//	var ts = new String();
//	var d = new Date();
//	ts = d.getTime().toString();
//	ts = ts.substr(0, ts.length - 3);

}