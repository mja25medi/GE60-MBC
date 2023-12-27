///	#############################################################################
///	#####
///	#####	한국신용정보주식회사	서비스 운영에 필요한 Utility Javascript 소스
///	#####
///	#####	=====================================================================
///	#####
///	#####	Descriptions
///	#####		- 서비스 운영에 필요한 Utility 성격의 함수를 관리한다.
///	#####
///	#####	---------------------------------------------------------------------
///	#####
///	#####	작성자 		: (주)유비아이에스컨설팅 (www.ubisc.com)
///	#####	원본참조	:
///	#####	원본파일	:
///	#####	작성일자	: 2006.02.01
///	#####
///	#############################################################################

var CRNDSIZE					= "24";
var strPersonalCertKey			= "3";			//	개인인증키 서비스 프로세스
var strRealNameCertKey			= "4";			//	실명인증키 서비스 프로세스

var strDelimeter				= "!#!";		//	반환 정보 Delimeter
var strDataDelimeter 			= "!@!";		//	반환된 데이터 Delimeter
var strHttpURL_G				= "http://secure.nuguya.com";
var strHttpsURL_G				= "https://secure.nuguya.com";
var strSafeBlockExptUrl			= strHttpsURL_G + "/nuguya/realname/rlnmSafeBlockExpt.do";						//	SafeBlock 임시해제 URL
//var strValidExpireUrl			= strHttpURL_G + "/nuguya/certkey/personalcertkey/RlnmValidExpire.do";		//	개인인증키 유효기간 재 설정
var strCertKeyServiceUrl		= strHttpsURL_G + "/nuguya/certkey/service.do";								//	개인인증키 / 실명인증키 서비스 URL
var strSendConfirmUrl			= strHttpsURL_G + "/nuguya/certkey/personalcertkey/IpinSendConfirm.do";		//	개인인증키 전송 검증 URL
var strNoticeUrl				= strHttpsURL_G + "/nuguya/certkey/notice.do";

function checkUtilSelf()
{
	return "Loaded";
}

function encode( data )
{
	return encodeURIComponent( data );
}

function decode( data )
{
	return decodeURIComponent( data );
}

function trimPKCS5Padding( data )
{
	return data.replace( /[\1\2\3\4\5\6\7]/g, '\0' );
}

//
//	전달된 정보를 암호화된 문자열로 반환한다.
//
function makeEncryptInfo( dataValues )
{
	var CRndValue = cryptoObject.getRandomKey( CRNDSIZE );
	var CDESValue = "";

	if ( dataValues.length == 0 )
	{
		var err = new Error();
		err.message = "makeEncryptInfo";
		err.description = getCheckMessage( "S96" );
		throw err;
	}

	var nIndex = 0;
	for( nIndex = 0; nIndex < dataValues.length - 1; nIndex++ )
	{
		CDESValue += dataValues[nIndex] + strDelimeter;
	}
	CDESValue += dataValues[nIndex];
	//alert("다음정보를 전달하겠습니다.:"+CDESValue);
	CDESValue = cryptoObject.encode64( CRndValue + strDelimeter +
		cryptoObject.des( CRndValue, CDESValue, 1, 1, CRndValue ) );

	//alert("암호화된 파라매터입니다.:"+CDESValue);
	return CDESValue;
}

//
//	전달된 정보를 암호화된 문자열로 반환한다.
//
function makeEncryptInfo2( dataValues, realkey )
{
	var CRndValue = cryptoObject.getRandomKey( CRNDSIZE );
	var CDESValue = "";

	if ( dataValues.length == 0 )
	{
		var err = new Error();
		err.message = "makeEncryptInfo";
		err.description = getCheckMessage( "S96" );
		throw err;
	}

	var nIndex = 0;
	for( nIndex = 0; nIndex < dataValues.length - 1; nIndex++ )
	{
		CDESValue += dataValues[nIndex] + strDelimeter;
	}
	CDESValue += dataValues[nIndex];

	CDESValue = cryptoObject.encode64( CRndValue + strDelimeter +
		cryptoObject.des( realkey, CDESValue, 1, 1, realkey ) );

	return CDESValue;
}
//
//	개인인증키 인증키 검증에 필요한 정보를 암호화하여 반환한다.
//
function makeProcessInfo( dataObject )
{
	//	Format : NiceId + PingInfo + OrderNo + TrCd + InqRsn + ReturnURL + ValidTerm + userNm + resIdNo + userId
	return makeEncryptInfo( new Array( dataObject.niceId, dataObject.pingInfo, dataObject.orderNo, dataObject.trCd,
		dataObject.inqRsn, dataObject.returnUrl, dataObject.validTerm, encode( dataObject.niceNm ), dataObject.resIdNo, encode(dataObject.userId), dataObject.SIKey ) );
}

//
//	회원사 서버에 전송할 대체인증 정보를 암호화하여 반환한다.
//
function makeCertKeyInfo( strNiceId, strPingInfo, strOrderNo, strInqRsn, strReturnUrl )
{
	//	Format : NiceId + PingInfo + OrderNo + InqRsn + ReturnURL
	return makeEncryptInfo( new Array( strNiceId, strPingInfo, strOrderNo, strInqRsn, strReturnUrl, "##" ) );
}

//
//	회원사 서버에 전송할 대체인증 정보를 암호화하여 반환한다.
//
function makeCertKeyInfoPA( strNiceId, strPingInfo, strOrderNo, strInqRsn, strReturnUrl, strSIKey )
{
	//	Format : NiceId + PingInfo + OrderNo + InqRsn + ReturnURL
	return makeEncryptInfo( new Array( strNiceId, strPingInfo, strOrderNo, strInqRsn, strReturnUrl, strSIKey ) );
}


//
//	회원사에 전달할 대체정보를 암호화하여 반환한다.
//
function makeCertKeyReturnInfo( dataObject )
{
	//alert("회원사에 전달할 대체정보를 암호화합니다");
	//	Format : NiceId + OrderNo + TrCd + RetCd + RetDtlCd + Message + PAKey + Name + BirtyYear + Sex
	return makeEncryptInfo( new Array( dataObject.niceId, dataObject.orderNo, dataObject.trCd, dataObject.retCd,
		dataObject.retDtlCd, encode( dataObject.message ), dataObject.PAKey, encode( dataObject.niceNm ), dataObject.birthYear, dataObject.sex,dataObject.dupeInfo, dataObject.LIKey) );
}

//
//	회원사에 전달할 대체정보를 암호화하여 반환한다.
//
function makeIpinReturnInfo( dataObject )
{
	//	Format : NiceId + OrderNo + TrCd + RetCd + RetDtlCd + Message + PAKey + Name + BirtyYear + Sex
	return makeEncryptInfo2( new Array( dataObject.niceId, dataObject.orderNo, dataObject.trCd, dataObject.retCd,
		dataObject.retDtlCd, encode( dataObject.message ), dataObject.PAKey, encode( dataObject.niceNm ), dataObject.birthYear, dataObject.sex,dataObject.LIKey, dataObject.ageGroup,dataObject.foreigner, dataObject.sendTime), dataObject.authKeyStr );
}
//
//	회원사 서버에 전송할 정보를 암호화하여 반환한다.
//
function makeSendInfo( strNm, strNo, strRsn, strForeigner )
{
	//	Format : Name + resIdNo + InqRsn + Foreigner
	return makeEncryptInfo( new Array( encode( strNm ), strNo, strRsn, strForeigner ) );
}

//
//	회원사 서버에 전송할 기업 정보를 암호화하여 반환한다.
//
function makeBizSendInfo( strNo, strNm, strRsn, strInfoFlag )
{
	//	Format : BizNo + BizNm + InqRsn + InfoFlag
	return makeEncryptInfo( new Array( strNo, encode( strNm ), strRsn, strInfoFlag ) );
}

//
//	기업 실명확인 사유코드 설정함수
//
function setCoInqRsn( inqRsn )
{
	var optionText = "회원가입";
	var optionValue = "01";
	var textArray = optionText.split( ";" );
	var valueArray = optionValue.split( ";" );
	var nIndex = 0;

	for( nIndex = 0; nIndex < valueArray.length; nIndex++ )
	{
		var oOption = document.createElement( "OPTION" );
		inqRsn.options.add( oOption );
		oOption.innerText = textArray[nIndex];
		oOption.value = valueArray[nIndex];
	}
}

//
//	실명확인 사유코드 설정함수
//
function setInqRsn( inqRsn )
{
	var optionText = "회원가입;기존회원 확인;성인인증;비회원 확인;기타 사유";
	var optionValue = "10;20;30;40;90";
	var textArray = optionText.split( ";" );
	var valueArray = optionValue.split( ";" );
	var nIndex = 0;

	for( nIndex = 0; nIndex < valueArray.length; nIndex++ )
	{
		var oOption = document.createElement( "OPTION" );
		inqRsn.options.add( oOption );
		oOption.innerText = textArray[nIndex];
		oOption.value = valueArray[nIndex];
	}
}

//
//	내/외국인 구분 코드 설정 함수
//
function setForeigner( foreigner )
{
	var optionText = "내국인;외국인";
	var optionValue = "1;2";
	var textArray = optionText.split( ";" );
	var valueArray = optionValue.split( ";" );
	var nIndex = 0;

	for( nIndex = 0; nIndex < valueArray.length; nIndex++ )
	{
		var oOption = document.createElement( "OPTION" );
		foreigner.options.add( oOption );
		oOption.innerText = textArray[nIndex];
		oOption.value = valueArray[nIndex];
	}
}

//
//	클라이언트 거래일련번호 포맷기준 생성 함수 (YYYYMMDDHHMMSS+Random)
//
function getOrderNo( digits )
{
	if ( digits < 20 )
	{
		alert( getMessage( "S11" ) );
		return;
	}

	//	일자 기준 정보를 생성한다.
	var strOrderNo = new String( getYYYYMMDDHHMMSS() );

	//	랜덤키를 생성한다.
	strOrderNo = strOrderNo + cryptoObject.getRandomKey( digits - strOrderNo.length );

	return strOrderNo;
}

//
//	현재 일자와 시간을 'YYYYMMDDHHMMSSsss' 형식으로 반환한다.
//
function getYYYYMMDDHHMMSSsss()
{
	var today = new Date();
	var strDateTime = (today.getFullYear() * 100 + today.getMonth() + 1) * 100;
	strDateTime = ( strDateTime + today.getDate() ) * 100;
	strDateTime = ( strDateTime + today.getHours() ) * 100;
	strDateTime = ( strDateTime + today.getMinutes() ) * 100;
	strDateTime = ( strDateTime + today.getSeconds() ) * 1000;
	strDateTime = strDateTime + today.getMilliseconds();

	return strDateTime;
}

//
//	현재 일자와 시간을 'YYYYMMDDHHMMSS' 형식으로 반환한다.
//
function getYYYYMMDDHHMMSS()
{
	var today = new Date();
	var strDateTime = (today.getFullYear() * 100 + today.getMonth() + 1) * 100;
	strDateTime = ( strDateTime + today.getDate() ) * 100;
	strDateTime = ( strDateTime + today.getHours() ) * 100;
	strDateTime = ( strDateTime + today.getMinutes() ) * 100;
	strDateTime = strDateTime + today.getSeconds();

	return strDateTime;
}

//
//	현재 일자를 'YYYYMMDD' 형식으로 반환한다.
//
function getYYYYMMDD()
{
	var today = new Date();
	var strDate = (today.getFullYear() * 100 + today.getMonth() + 1) * 100;
	strDate = strDate + today.getDate();

	return strDate;
}

//
//	현재 시각을 'HHMMSSsss' 형식으로 반환한다.
//
function getHHMMSSsss()
{
	var today = new Date();
	var strTime = today.getHours() * 100;
	strTime = ( strTime + today.getMinutes() ) * 100;
	strTime = ( strTime + today.getSeconds() ) * 1000;
	strTime = strTime + today.getMilliseconds();

	return strTime;
}

//
//	현재 시각을 'HHMMSS' 형식으로 반환한다.
//
function getHHMMSS()
{
	var today = new Date();
	var strTime = today.getHours() * 100;
	strTime = ( strTime + today.getMinutes() ) * 100;
	strTime = strTime + today.getSeconds();

	return strTime;
}

//
//	문자 입력을 검증한다.
//
function checkString( srcString )
{
	var tmpChar;

	for( nIndex = 0; nIndex < srcString.length; nIndex++ )
	{
		tmpChar = srcString.charAt( nIndex );

		if ( ! ( (tmpChar < '0' || tmpChar > '9') && (tmpChar < 'a' || tmpChar > 'z') && (tmpChar < 'A' || tmpChar > 'Z') ) ) { return false; }
	}

	return true;
}

//
//	숫자 입력을 검증한다.
//
function checkNumeric( srcNumeric )
{
	if ( srcNumeric.Number == NaN ) return false;
//	var tmpChar;
//
//	for( nIndex = 0; nIndex < srcNumeric.length; nIndex++ )
//	{
//		tmpChar = srcNumeric.charAt( nIndex );
//
//		if ( tmpChar < '0' || tmpChar > '9' ) { return false; }
//	}

	return true;
}

//
//	주민등록번호 형식을 검증한다.
//
function checkResIdNo( srcNumeric )
{
	var sum = 0;

	if ( srcNumeric.length < 13 ) return false;

	for( nIndex = 0; nIndex < 8; nIndex++ ) { sum += srcNumeric.substring( nIndex, nIndex + 1 ) * ( nIndex + 2 ); }
	for( nIndex = 8; nIndex < 12; nIndex++ ) { sum += srcNumeric.substring( nIndex, nIndex + 1 ) * ( nIndex - 6 ); }

	sum = 11 - ( sum % 11 );

	if ( sum >= 10 ) { sum -= 10; }
	if ( srcNumeric.substring( 12, 13 ) != sum || ( srcNumeric.substring( 6, 7 ) != 1 && srcNumeric.substring( 6, 7 ) != 2 ) ) { return false; }

	return true;
}

//
//	사업자등록번호 형식을 검증한다.
//
function checkBizNo( srcNumeric )
{
	var checkID = new Array(1, 3, 7, 1, 3, 7, 1, 3, 5, 1);
	var i, chkSum=0, c2, remander;

	for( i = 0; i <= 7; i++ ) chkSum += checkID[i] * srcNumeric.charAt(i);

	c2 = "0" + ( checkID[8] * srcNumeric.charAt(8) );
	c2 = c2.substring( c2.length - 2, c2.length );

	chkSum += Math.floor( c2.charAt(0) ) + Math.floor( c2.charAt(1) );

	remander = (10 - (chkSum % 10)) % 10 ;

	if (Math.floor(srcNumeric.charAt(9)) == remander)
		return true;
	else
		return false;
}

//
//	외국인등록번호를 형식을 검증한다.
//
function checkForeignNo( srcNumeric )
{
	var ch = srcNumeric.substring( 11, 12 );

	if ( ch != 7 && ch != 8 && ch != 9 )
		return false;
	else
		return true;
}

//
//	외국인 성명을 검증한다.
//
function checkForeignNm( srcNm )
{
	var chr = srcNm.split( "\\" );
	var strChar = chr[chr.length - 1];

	//	공백 검증
	if ( (/[\s]/).test( strChar ) )
	{
		return false;
	}

	for( nIndex = 0; nIndex < strChar.length; nIndex++ )
	{
		chr = strChar.substr( nIndex, 1 );

		if ( chr >= 'a' && chr <= 'z' )
			return false;
	}

	return true;
}

function getRandomKey( digits )
{
	var rndKey, nIndex;

	rndKey = "";

	do
	{
		nIndex = Math.floor( Math.random() * cryptoObject.KeyStr.length ) + 1;
		rndKey = rndKey + cryptoObject.KeyStr.substr( nIndex, 1 );
	}
	while ( rndKey.length < digits )

	return rndKey;
}

function endProcess()
{
	window.close();
	opener.focus();
}

function goReturnPage()
{
	var form = document.getElementById( "resForm" );
	if(oivsObject.identifydata != ""){
		//alert("1");
		form.IDENTIFYDATA.value = oivsObject.identifydata ;
		form.RETURNURL.value = oivsObject.pareturnUrl;
	}else{
		//		alert("2");
		//alert(form.SendInfo.value);
		form.SendInfo.value = oivsObject.clientData;
	}
	form.action = oivsObject.returnUrl;
	form.submit();
}

function goSafeBlockExpt()
{
	var popupWindow = window.open("", "popupSafeBlockExpt", "top=100, left=200, status=1, width=417, height=352" );
	popupWindow.document.location=strSafeBlockExptUrl;
	popupWindow.focus();
	return;
}

function goSafeBlockExptSelf()
{
	window.resizeTo( 425, 380 );
	document.location = strSafeBlockExptUrl;
	return;
}

function goSubmitUrl( encData, formObject, targetUrl )
{
	formObject.SendInfo.value = encData;
	formObject.action = targetUrl;
	formObject.submit();
}
