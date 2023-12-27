// Global Navigation
function navSwitch(menunum)	{
	//var totalmenu = document.getElementById('gnb').getElementsByTagName('ul').length;
	var totalmenu = $(".topmenu").length;
	for (i=0;i<totalmenu;i++) {
		if(i==menunum)	{
			$("#topmenu"+i).addClass("on");
			$("#submenu"+i).attr("style","display:block");
		} else {
			$("#topmenu"+i).removeClass("on");
			$("#submenu"+i).attr("style","display:none");
		}
	}
}

//-- 홈페이지의 메뉴 연결용 펑션
function goMenuPage(menuCode) {
	document.location.href = cUrl("/MainHome.do?cmd=goMenuPage&mcd="+menuCode);
}