<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<meditag:css href="css/jquery.datetimepicker.min.css"/>
<meditag:js src="/js/jquery.datetimepicker.full.min.js"/>
	
<meditag:css href="libs/admin-lte/plugins/fullcalendar/fullcalendar.css"/>
<meditag:js src="/libs/admin-lte/plugins/fullcalendar/moment.min.js"/>
<meditag:js src="/libs/admin-lte/plugins/fullcalendar/fullcalendar.min.js"/>
<meditag:js src="/libs/admin-lte/plugins/fullcalendar/lang-all.js"/>

			<ul class="tabs mb0 two_list">
				<li><a href="/home/org/fac/resCurrMain" title="시설">시설</a></li>
				<li class="active"><a href="/home/org/equ/rentCurrMain" title="장비">장비</a></li>
			</ul>

            <!-- 본문 content 부분 -->
            <div class="content">
                <div id='calendar'></div>
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
                        	var chk = confirm('대관/대여 신청 페이지로 이동하시겠습니까?');
                        	if(chk) {
                        		goLink();
                        	}
                        },
                        editable: true,
                        eventLimit: true, // allow "more" link when too many events '{item.XXXX}',
                        events: [
                        	<c:forEach var="entry" items="${rentInfoListMap}" varStatus="status">
								<c:forEach var="item" items="${entry.value}">
		                            {
		                                title: "${item.equNm} [${item.rentCnt}대]",
		                                start: "${item.parseStartDttm}",
		                                end: "${item.parseEndDttm}",
		                                allDay : true,
		                                url: "javascript:viewData('${item.rentCd}')",
		                                borderColor : colors[${status.index} % colors.length],
		                                backgroundColor: colors[${status.index} % colors.length]
		                            },
								</c:forEach>
                            </c:forEach>
                        ]
                    });
                });
                
                function viewData(rentCd) {
                	var url = '<c:url value="/home/org/equ/viewRentInfoPop"/>' + "?rentCd=" + rentCd;
                	viewPopup(url);
                }
                
                function viewPopup(url) {
                	var addContent = "<iframe id='equRentInfoForm' name='equRentInfoForm' width='100%' height='100%' "
            			+ "frameborder='0' scrolling='auto' src='"+url+"'/>";
                	modalBox.clear();
                	modalBox.addContents(addContent);
                	modalBox.resize(850, 600);
                	modalBox.setTitle("예약 상세 조회");
                	modalBox.show();
                }
                
                function modalBoxClose() {
                	modalBox.clear();
                	modalBox.close();
                }
                </script>
                <small class="fcDarkGray mt10">
					<i class="xi-error mr5" aria-hidden="true"></i>
					" 날짜를 선택하면 대여 가능한 시간대를 선택할 수 있는 레이어 팝업이 출력됩니다. 대여기간을 선택 하신 후 온라인 신청서를 작성하시면 됩니다."
                </small>
            </div>
            <!-- //본문 content 부분 -->
	
<script type="text/javascript">
	function goLink() {
		if(${empty USERNO}) {
			alert("로그인이 필요한 서비스입니다.");
			document.location.href = "/home/main/goMenuPage?mcd=HM04001000";
			return;
		}
		document.location.href = "/home/main/goMenuPage?mcd=MC00000037";
	}
</script>

