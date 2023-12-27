<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<meditag:css href="css/jquery.datetimepicker.min.css"/>
<meditag:js src="/js/jquery.datetimepicker.full.min.js"/>

<meditag:css href="libs/admin-lte/plugins/fullcalendar/fullcalendar.css"/>
<meditag:js src="/libs/admin-lte/plugins/fullcalendar/moment.min.js"/>
<meditag:js src="/libs/admin-lte/plugins/fullcalendar/fullcalendar.min.js"/>
<meditag:js src="/libs/admin-lte/plugins/fullcalendar/lang-all.js"/>

	
            <!-- 본문 content 부분 -->
            <div class="content">
            	<div class="factit_box">
                    <h5><i class="xi-check"></i> ${facInfo.facCtgrNm} - ${facInfo.facNm }</h5>
                </div>
                <div id='calendar' class="mt10"></div>
                <script>
                var colors = ["LightGreen", "LightSalmon", "LightSeaGreen", "LightSkyBlue", "LightSlateGray",
                	"LightYellow", "PaleVioletRed", "PapayaWhip" , "Peru"];
                $(document).ready(function() {
                	var today = new Date();
                	modalBox = new $M.ModalDialog({"modalid" : "test1"});
                	
                    $('#calendar').fullCalendar({
                        lang : 'ko',
                        header: {
                            left: 'today',
                            center: 'prev, title ,next',
                            right: ''
                        },
                        defaultDate: today.getFullYear() + "-" + (today.getMonth()+1) + "-"+ today.getDate() ,
                        navLinks: false, // can click day/week names to navigate views
                        selectable: true,
                        selectHelper: false,
                        select: function(start, end) {
                        	var targetDate = new Date(start);
                       		$('#calendar').fullCalendar('unselect');
							goLink(targetDate.toDateString());
                        },
                        editable: true,
                        eventLimit: true, // allow "more" link when too many events '{item.XXXX}',
                        events: [
							<c:forEach var="item" items="${resInfoList}" varStatus="status">
	                            {
	                                title: "${item.resStm} ~ ${item.resEtm} (${item.userNm})",
	                                start: "${item.resDt}",
	                                end: "${item.resDt}",
	                                allDay: true,
	                                url: "javascript:viewData('${item.resCd}')",
	                                borderColor : colors[${status.index} % colors.length],
	                                backgroundColor: colors[${status.index} % colors.length]
	                            },
							</c:forEach>
                        ]
                    });
                });
                
                function viewData(resCd) {
                	var url = '<c:url value="/home/org/fac/viewResInfoPop2"/>' + "?resCd=" + resCd;
                	viewPopup(url);
                }
                
                function viewPopup(url) {
                	modalBox.clear();
                	modalBox.addContents(url);
                	modalBox.resize(700, 600);
                	modalBox.setTitle("시설 예약 상세");
                	modalBox.show();
                }
                
                function modalBoxClose() {
                	modalBox.clear();
                	modalBox.close();
                }
                </script>
                <small class="fcDarkGray mt10">
					<i class="xi-error mr5" aria-hidden="true"></i>
					" 날짜를 선택하시면 대관 가능한 시간대를 선택할 수 있는 레이어 팝업이 출력됩니다. 대관시간을 선택 하신 후 온라인 신청서를 작성하시면 됩니다."
                </small>
            </div>
            <!-- //본문 content 부분 -->
	
<script type="text/javascript">

	var targetDt;

	function goLink(date) {
		
		if(${empty USERNO}) {
			alert("로그인이 필요한 서비스입니다.");
			document.location.href = "/home/main/goMenuPage?mcd=HM04001000";
			return;
		}
		var today = new Date();
		targetDt = new Date(today.toDateString());
		var dt = new Date(date);
 		if(dt < targetDt){
			alert("시설 예약은 오늘 이후의 날짜만 가능합니다.");
			return;
		}
		targetDt = dt;
		
		// 모달 
		var facCd = '${facInfo.facCd}';
		var url = '<c:url value="/home/org/fac/addResFormPop"/>' + "?facCd=" + facCd;
		var addContent = "<iframe id='addFacResForm' name='addFacResForm' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(850, 600);
		modalBox.setTitle("시설 예약 시간 선택");
		modalBox.show();
}
</script>
