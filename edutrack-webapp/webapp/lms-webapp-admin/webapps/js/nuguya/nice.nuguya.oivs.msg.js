//	#############################################################################
//	#####
//	#####	한국신용정보주식회사				메시지 운영 Javascript 소스
//	#####
//	#####	=====================================================================
//	#####
//	#####	Descriptions
//	#####		- 서비스 운영에 필요한 메시지를 관리한다.
//	#####
//	#####	---------------------------------------------------------------------
//	#####
//	#####	작성자 		: (주)유비아이에스컨설팅 (www.ubisc.com)
//	#####	원본참조	:
//	#####	원본파일	:
//	#####	작성일자	: 2006.02.01
//	#####
//	#############################################################################


function checkMsgSelf()
{
	return "Loaded";
}

//
//	화면에서 처리하는 기본 검증 메시지를 반환한다.
//
function getCheckMessage( msgCode )
{
	var strMessage = "";

	switch( msgCode )
	{
		case "S99"	:
			strMessage = "서비스 제공에 필요한 필수 환경에 문제가 발생하였습니다. [관련정보 : nice.nuguya.oivs.crypto.js] \r\n고객상담센터(☎ 1588-2486) 로 연락하십시오. ";
			break;
		case "S98"	:
			strMessage = "서비스 제공에 필요한 필수 환경에 문제가 발생하였습니다. [관련정보 : nice.nuguya.oivs.msg.js] \r\n고객상담센터(☎ 1588-2486) 로 연락하십시오. ";
			break;
		case "S97"	:
			strMessage = "서비스 제공에 필요한 필수 환경에 문제가 발생하였습니다. [관련정보 : nice.nuguya.oivs.util.js] \r\n고객상담센터(☎ 1588-2486) 로 연락하십시오. ";
			break;
		case "S96"	:
			strMessage = "데이터를 보안처리할 수 없습니다.\n전달된 정보가 없습니다.[관련정보 : nice.nuguya.oivs.util.js] ";
			break;

		case "S00"	:
			strMessage = "서비스 제공에 필요한 필수 환경에 문제가 발생하였습니다. [관련정보 : ajax.Start() - null action] \r\n고객상담센터(☎ 1588-2486) 로 연락하십시오";
			break;
		case "S01"	:
			strMessage = "서비스 제공을 위한 통신 중에 오류가 발생하였습니다. [관련정보 : WebService Proxy Call] \r\n고객상담센터(☎ 1588-2486) 로 연락하십시오";
			break;
		case "S02"	:
			strMessage = "서비스 제공에 필요한 보안 데이터를 처리할 수 없습니다. [관련정보 : nice.nuguya.oivs.service.js (setClientData)] \r\n고객상담센터(☎ 1588-2486) 로 연락하십시오";
			break;
		case "S11"	:
			strMessage = "거래일련번호 생성에 필요한 최소 길이는 20자리입니다. \r\n[관련정보 : getOrderNo()]";
			break;
		case "S21"	:
			strMessage = "주민등록번호를 입력하십시오.";
			break;
		case "S22"	:
			strMessage = "주민등록번호가 형식에 맞지 않습니다.";
			break;
		case "S23"	:
			strMessage = "성명을 입력하십시오.";
			break;
		case "S24"	:
			strMessage = "내국인 성명은 한글만 가능합니다.";
			break;
		case "S25"	:
			strMessage = "주민등록번호는 숫자만 입력 가능합니다.";
			break;
		case "S26"	:
			strMessage = "외국인등록번호에 오류가 있습니다. 다시 확인하십시오.";
			break;
		case "S27"	:
			strMessage = "외국인등록번호를 입력하십시오.";
			break;
		case "S28"	:
			strMessage = "성명에 공백이나 소문자가 존재합니다. 다시 확인하십시오.";
			break;
		case "S31"	:
			strMessage = "실명안심차단 일시해제(5분) 처리를 수행하시겠습니까?";
			break;
		case "S32"	:
			strMessage = "마이크레딧에서 명의도용차단을 해제 또는 일시해제 처리하시기 바랍니다.";
			break;
		case "S33"	:
			strMessage = "인증키 유효기간을 재설정 하시겠습니까?";
			break;
		case "S41"	:
			strMessage = "사업자등록번호/법인번호를 입력하십시오.";
			break;
		case "S42"	:
			strMessage = "기업명/법인명을 입력하십시오.";
			break;
		case "S43"	:
			strMessage = "기업명/법인명은 한글만 가능합니다.";
			break;
		case "S44"	:
			strMessage = "사업자등록번호/법인번호는 숫자만 입력 가능합니다.";
			break;
		case "S45"	:
			strMessage = "사업자등록번호에 오류가 있습니다";
			break;
		case "S60"	:
			strMessage	= "회원사 아이디를 입력하십시오";
			break;
		case "S61"	:
			strMessage	= "회원사의 IP Address를 확인할 수 없습니다.\n회원사 관리자에게 문의하십시오";
			break;
		case "S62"	:
			strMessage	= "실명인증/대체인증에 대한 조회 사유를 선택하십시오";
			break;
		case "S63"	:
			strMessage	= "서비스 호출을 위한 거래일련번호가 존재하지 않습니다.\n생성 버튼을 사용하여 생성하십시오";
			break;
		case "S64"	:
			strMessage	= "서비스 결과를 수신할 결과 URL을 설정하십시오.";
			break;
		case "S65"	:
			strMessage	= "아이핀ID를 입력하십시오";
			break;
		case "S66"	:
			strMessage	= "나이스아이핀 호출 사용자 비밀번호를 입력하십시오";
			break;
		case "S67"	:
			strMessage	= "거래일련번호는 최소 14자리에서 최대 20자리까지 가능합니다.\n생성 버튼을 사용하여 자동 생성하십시오.";
			break;
		case "S68"	:
			strMessage	= "주민등록번호를 확인하십시오.\n주민등록번호는 13자리 숫자이어야 합니다.";
			break;
		case "S69"	:
			strMessage	= "실명인증키 서비스 요청이 취소되었습니다.";
			break;
		case "S70"	:
			strMessage	= "아이핀 전송을 취소하었습니다.";
			break;
	}

	return strMessage;
}

//
//	서비스의 결과에 대한 메시지를 반환한다.
//
function getMessage( retCd, retDtlCd )
{
	var strMessage = "결과의 검증을 수행할 수 없습니다. [메시지코드 : " + retCd + "," + retDtlCd + " ] \r\n고객상담센터(☎ 1588-2486) 로 연락하십시오";

	switch( retCd + retDtlCd )
	{
		case "1A"	:
			strMessage = "실명확인 성공\n(주민번호/사업자번호/법인번호 와 성명/기업명이 일치합니다)";
			break;
		case "2B"	:
			strMessage = "실명확인 실패\n(주민번호/사업자번호/법인번호 와 성명/기업명이 일치하지 않습니다)";
			break;
		case "2C"	:
			strMessage = "실명확인 실패\n(본인 요청에 의하여 일치여부를 확인할 수 없습니다 - 명의도용차단설정)";
			break;
		case "2D"	:
			strMessage = "실명확인 실패\n(주민번호/사업자번호/법인번호 구성 및 성명/기업명 구성에 오류가 있습니다)";
			break;
		case "2E"	:
			strMessage = "실명확인 실패\n(통신장애입니다. 잠시 후에 다시 시도해 주십시오)\n\n상태가 지속되면 고객상담센터(☎ 1588-2486) 로 연락하십시오)";
			break;
		case "2F"	:
			strMessage = "실명확인 불가\n(성명/기업명에 두음법칙 오류입니다)";
			break;
		case "3G"	:
			strMessage = "실명확인 불가\n(데이터가 등록되어 있지 않습니다)";
			break;
		case "3H"	:
			strMessage = "실명확인 불가\n(등록된 데이터가 불완전합니다)";
			break;
		case "2Y"	:
			strMessage = "실명확인 불가\n(본인 요청에 의하여 일치여부를 확인할 수 없습니다 - 실명안심차단설정)";
			break;
		case "3Z"	:
			strMessage = "실명확인 불가\n(시스템 검증 작업을 진행할 수 없습니다)\n\n상태가 지속되면 고객상담센터(☎ 1588-2486) 로 연락하십시오)";
			break;
		case "00"	:
			strMessage = "";
			break;
	}

	return strMessage;
}

//
//
//
function getCertKeyMessage( svcType, retCd, retDtlCd )
{
	var strMessage = "";

	switch( retCd + retDtlCd )
	{
		case "1A"	:
			if ( svcType == strPersonalCertKey )
				strMessage = "나이스아이핀 성공\n(아이핀ID와 비밀번호가 일치합니다)";
			else
				strMessage = "실명인증 성공\n(주민번호와 성명이 일치합니다)";
			break;
		case "2B"	:
			if ( svcType == strPersonalCertKey )
				strMessage = "비밀번호가 일치하지 않습니다.";
			else
				strMessage = "실명인증 실패\n(주민번호와 성명이 일치하지 않습니다)";
			break;
		case "2X"	:
			if ( svcType == strPersonalCertKey )
				strMessage = "비밀번호 틀렸습니다. 9회 이상 초과되어 로그인이 불가능합니다.	\n초기화요청은 콜센터 1588-2486으로 문의하세요.";
			break;			
		case "2C"	:
			strMessage = "본인 요청에 의하여 일치여부를 확인할 수 없습니다.(명의도용차단 상태)\n마이크레딧을 통해서 차단해제 또는 임시해제를 수행하십시오.";
			break;
		case "2D"	:
			if ( svcType == strPersonalCertKey )
				strMessage = "개인인증 실패\n(등록된 주민번호를 확인하십시오.)";
			else
				strMessage = "실명인증 실패\n(주민번호 구성 및 성명 구성에 오류가 있습니다)";
			break;
		case "2E"	:
			if ( svcType == strPersonalCertKey )
				strMessage = "개인인증 실패\n(통신장애입니다. 잠시 후에 다시 시도해 주십시오)\n\n상태가 지속되면 고객상담센터(☎ 1588-2486) 로 연락하십시오)";
			else
				strMessage = "실명인증 실패\n(통신장애입니다. 잠시 후에 다시 시도해 주십시오)\n\n상태가 지속되면 고객상담센터(☎ 1588-2486) 로 연락하십시오)";
			break;
		case "2F"	:
			if ( svcType == strPersonalCertKey )
				strMessage = "개인인증 불가\n(성명에 두음법칙 오류입니다)";
			else
				strMessage = "실명인증 불가\n(성명에 두음법칙 오류입니다)";
			break;
		case "3G"	:
			if ( svcType == strPersonalCertKey )
				strMessage = "등록된 아이핀ID가 아닙니다.";
			else
				strMessage = "실명인증 불가\n(데이터가 등록되어 있지 않습니다)";
			break;
		case "3H"	:
			if ( svcType == strPersonalCertKey )
				strMessage = "개인인증 불가\n(유효기간만료 또는 폐기된 인증서 혹은 비밀번호 9회 오류입니다.)";
			else
				strMessage = "실명인증 불가\n(등록된 실명 데이터가 불완전합니다)";
			break;
		case "2Y"	:
			strMessage = "본인 요청에 의하여 일치여부를 확인할 수 없습니다. (실명안심차단 상태)";
			break;
		case "3Z"	:
			if ( svcType == strPersonalCertKey )
				strMessage = "개인인증 불가\n(시스템 검증 작업을 진행할 수 없습니다)\n\n상태가 지속되면 고객상담센터(☎ 1588-2486) 로 연락하십시오)";
			else
				strMessage = "실명인증 불가\n(시스템 검증 작업을 진행할 수 없습니다)\n\n상태가 지속되면 고객상담센터(☎ 1588-2486) 로 연락하십시오)";
			break;
		case "00"	:
			strMessage = "";
			break;
	}

	return strMessage;
}
