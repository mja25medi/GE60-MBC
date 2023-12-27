/**
 * 선택된 컨트롤에 제한된 값을 입력하고 양식을 강제적으로 설정하는 플러그인
 */
jQuery(function($) {

	// private method
	function isNumberInput(key) {
		if(!(key==8||key==9||key==13||key==46||key==144||(key>=48&&key<=57)||(key>=96&&key<=105)||key==110||key==190||(key>=37&&key<=40))) {
			return false;
		}
		return true;
	}

	function char_remove(str) {
    	var retStr = "";
    	var retCode;
    	var ret;
    	for(var i=0; i < str.length; i++) {
    		retCode = str.charCodeAt(i);
    		ret = str.charAt(i);
    		if (((retCode > 47) && (retCode < 58)))  {
    			retStr += ret;
    		}
    	}
    	return retStr;
	}

	function check_Num01(obj) {
		for ( var i = 0; i < obj.length; i++) {
			if (obj.substring(i, i + 1) < "0" || obj.substring(i, i + 1) > "9") {
				alert("이 필드는 숫자만 입력 가능합니다.");
				return false;
			}
		}
		return true;
	}

	//특수문자는 입력 할수가 없음
	function nonSpecialKey(key){
		if ((key > 32 && key < 45)||(key > 45 && key < 48) || (key > 57 && key < 65) || (key > 90 && key < 97)){ // -는 허용 함...주소땜에
			return  false;
		}
		return true;
	}

	function getLastDays(year, month) {
		var totalDays;
        switch (eval(month)){
            case 1 :
                totalDays = 31;
                break;
            case 2 :
                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
                    totalDays = 29;
                else
                    totalDays = 28;
                break;
            case 3 :
                totalDays = 31;
                break;
            case 4 :
                totalDays = 30;
                break;
            case 5 :
                totalDays = 31;
                break;
            case 6 :
                totalDays = 30;
                break;
            case 7 :
                totalDays = 31;
                break;
            case 8 :
                totalDays = 31;
                break;
            case 9 :
                totalDays = 30;
                break;
            case 10 :
                totalDays = 31;
                break;
            case 11 :
                totalDays = 30;
                break;
            case 12 :
                totalDays = 31;
                break;
        }
        return totalDays;
	}


	// -------------------------------------------------------
	// Public function
	// -------------------------------------------------------

	// jQuery extension function. A paramater object could be used to overwrite the default settings
	$.fn.inputNumber = function() {
		if (!this) return false;
		return this.each(function() {
			$(this).keydown(function(event) {
				if(!isNumberInput(event.keyCode)) {
					return false;
				}
			});
		});
	};

	$.fn.inputSpecial = function() {
		if (!this) return false;
		return this.each(function() {
			$(this).keypress(function(event) {          //keydown 함수 쓰면 특수문자 검증 안됨
				if(!nonSpecialKey(event.keyCode)) {
					return false;
				}
			});
		});
	};

	$.fn.inputDate = function(delimChar) {
		if (!this)
			return false;
		if(delimChar == undefined)
			delimChar = '.';
		return this.each(function() {
			var $me = $(this);
			//$me.inputNumber();	// 숫자만 입력
			$me.keyup(function(event) {
				//-- 방향키 및 백스페이스키 딜리트키는 해당 기능 작동하도록함.
				//if(event.keyCode == 8 || (event.keyCode >= 35 && event.keyCode <= 40) || event.keyCode == 46) return;
				if(event.keyCode == 8) return;

				var _date = char_remove($me.val());
				var year = "";
				var month = "";
				var day = "";
				var lastDay = 31;
				var outputDate = "";
				if(_date.length > 7) {
					year = _date.substring(0, 4);
					month = _date.substring(4, 6);
					day = _date.substring(6, 8);
					if(parseInt(month) < 0 || parseInt(month) > 12) {
						month = "";
						day = "";
						outputDate = year + delimChar;
					} else {
						lastDay = getLastDays(year, month);
						if(parseInt(day) < 0 || parseInt(day) > lastDay) {
							day = "";
							outputDate = year + delimChar + month + delimChar;
						} else {
							outputDate = year + delimChar + month + delimChar + day;
						}
					}
					$me.val(outputDate);
				} else if(_date.length > 6) {
				} else if(_date.length > 5) {
					year = _date.substring(0, 4);
					month = _date.substring(4, 6);
					if(parseInt(month) < 0 || parseInt(month) > 12) {
						month = "";
						day = "";
						outputDate = year + delimChar;
					} else {
						outputDate = year + delimChar + month + delimChar;
					}
					$me.val(outputDate);
				} else if(_date.length > 4) {
				} else if(_date.length > 3) {
					year = _date.substring(0, 4);
					outputDate = year + delimChar;
					$me.val(outputDate);
				}

			});
		});
	};

	/**
	 * targetId 컨트롤의 selectbox값을 ajax로 갱신한다.
	 * @param target 값 변경 대상 컨트롤
	 * @param url 값을 조회할 ajax url
	 * @param param ajax용 파라매터
	 * @param selectedCode 기본값 밸류
	 * @param 대상 컨트롤에 추가 이벤트 발생이 필요할 경우 trigger의 인자.
	 * <pre>
	 * 사용 예)
	 * $(".radioJobDiv").bind(($.browser.msie ? "click" : "change"), function(event) {
	 *		$(this).codeSelect(
	 *			'#selectboxJobCtgr',
	 *			'/SystemCodeAdmin.do',
	 *			{	'cmd'	:	'listSelectCd',
	 *				'codeCtgrCd'	: 'JOB_CTGR',
	 *				'cdCd'			: $(this).val()	}
	 *			, ""
	 *			, "change"
	 *		);
	 *
	 *	});
	 * </pre>
	 */
	$.fn.codeSelect = function(targetId, url, param, selectedCode, event) {
		if (!this) return false;
		if (selectedCode == undefined) selectedCode = '';
		return this.each(function() {
			var $me = $(this);
			$.getJSON(Context.ROOT + url, param,
				function(response) {
					//alert(targetId + " : " + Context.ROOT + url + " : " + param.optnCd + ">" + selectedCode);
					$(targetId).empty();
					if(response.returnList.length > 0) {
						for (var i=0; i < response.returnList.length; i++) {
							var code = response.returnList[i];
							$('<option/>')
								.attr("selected", (code.codeCd == selectedCode) ? "selected" : "")
								.text(code.codeNm)
								.attr("value", code.codeCd)
								.appendTo(targetId);
						}
					} else {
						$('<option/>').text('해당없음').attr("value", '').appendTo(targetId);
					}
					if(event != undefined) {
						$(targetId).trigger(event);
					}
				}
			);
		});
	};

	$.fn.stringValues = function(seperator) {
		if(!this) return false;
		if(seperator == undefined)
			seperator = ",";

		var checkedString = "";

		if($(this).length == 0) return checkedString;

		$(this).each(function() {
			checkedString += seperator + $(this).val();
		});
		return checkedString.substring(1);
	};
});

