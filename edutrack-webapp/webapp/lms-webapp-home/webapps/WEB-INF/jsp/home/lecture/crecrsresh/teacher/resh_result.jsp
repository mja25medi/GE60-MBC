<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="crsReshResultVO" value="${vo}" />
<c:set var="creCrsReshVO" value="${creCrsReshVO}" />
<c:set var="crsReshResultList" value="${crsReshResultList}" />

<div class="learn_top">
	<div class="left_title">
		<h3>강의평가관리</h3>
		<button type="button" class="btn3 fl"" onclick="location.href='<c:url value="/lec/creCrsResh/tchMain"/>?creCrsReshVO.crsCreCd=${crsReshResultVO.crsCreCd}'"><spring:message code="button.list"/></button>
	</div>
	<div class="right_btn">
		<button type="button" class="btn3" onclick="excelDownload()"><img src="/tpl/002/img/excel_icon.png"></i><spring:message code="button.download.excel"/></button>
	</div>
</div>
<div class="segment">
	<div class="table_list">
		<ul class="list">
			<li class="head"><label><spring:message code="lecture.title.research.name"/></label></li>
			<li>${creCrsReshVO.reshTitle}</li>
		</ul>
		<ul class="list">
			<li class="head"><label><spring:message code="lecture.title.research.duration"/></label></li>
			<li><meditag:dateformat type="8" delimeter="." property="${creCrsReshVO.startDttm}"/> ~<meditag:dateformat type="8" delimeter="." property="${creCrsReshVO.endDttm}"/></li>
			<li class="head"><label><spring:message code="lecture.title.research.regyn"/></label></li>
			<li>
				<c:set var="regYn" value="${creCrsReshVO.regYn}"/>
				<c:if test="${empty creCrsReshVO.regYn}"><c:set var="regYn" value="N"/></c:if>
				<meditag:codename code="${regYn}" category="OPEN_YN" />
			</li>
		</ul>
	</div>
</div>

<c:forEach var="item" items="${crsReshResultList}" varStatus="status">
	<div class="segment">
		<div class="list_view_box mt0">
			<div class="list_item">
				<div class="survey">${status.count}. ${item.reshItemCts}</div>
           		<ol	class="cont_list">
					<c:if test="${item.reshItemTypeCd eq 'D'}">
						<a href="#" onclick="listReshPopD('${item.reshItemSn}')" class="btn type4"><spring:message code="button.view.result"/></a>
					</c:if>
					<c:if test="${item.reshItemTypeCd eq 'K'}">
						<c:if test="${not empty item.empl1}">
							<li>
								<div class="text"><span class="circle_num">1</span>${item.empl1}</div>
								<div class="progress">
									<div class="inner">
                                  		<c:if test="${item.empl1Ratio > 0}">
											<a href="javascript:listReshPopK('${item.reshItemSn}','1')">
												<div class="bar" style="width: ${item.empl1Ratio}%;"></div>
											</a>
										</c:if>
										<c:if test="${item.empl1Ratio == 0}">
											<div class="bar" style="width: ${item.empl1Ratio}%;"></div>
										</c:if>
                                    </div><small>${item.empl1Ratio}%</small>
								</div>
							</li>
						</c:if>
						<c:if test="${not empty item.empl2}">
							<li>
								<div class="text"><span class="circle_num">2</span>${item.empl2}</div>
                                <div class="progress">
                                	<div class="inner">
                                  		<c:if test="${item.empl2Ratio > 0}">
											<a href="javascript:listReshPopK('${item.reshItemSn}','2')">
												<div class="bar" style="width: ${item.empl2Ratio}%;"></div>
											</a>
										</c:if>
										<c:if test="${item.empl2Ratio == 0}">
											<div class="bar" style="width: ${item.empl2Ratio}%;"></div>
										</c:if>
                                    </div><small>${item.empl2Ratio}%</small>
                                </div>
							</li>
						</c:if>
						<c:if test="${not empty item.empl3}">
							<li>
								<div class="text"><span class="circle_num">3</span>${item.empl3}</div>
                                <div class="progress">
                                	<div class="inner">
                                  		<c:if test="${item.empl3Ratio > 0}">
											<a href="javascript:listReshPopK('${item.reshItemSn}','3')">
												<div class="bar" style="width: ${item.empl3Ratio}%;"></div>
											</a>
										</c:if>
										<c:if test="${item.empl3Ratio == 0}">
											<div class="bar" style="width: ${item.empl3Ratio}%;"></div>
										</c:if>
                                    </div><small>${item.empl3Ratio}%</small>
                                </div>
							</li>
						</c:if>
						<c:if test="${not empty item.empl4}">
							<li>
								<div class="text"><span class="circle_num">4</span>${item.empl4}</div>
                                <div class="progress">
                                	<div class="inner">
                                  		<c:if test="${item.empl4Ratio > 0}">
											<a href="javascript:listReshPopK('${item.reshItemSn}','4')">
												<div class="bar" style="width: ${item.empl4Ratio}%;"></div>
											</a>
										</c:if>
										<c:if test="${item.empl4Ratio == 0}">
											<div class="bar" style="width: ${item.empl4Ratio}%;"></div>
										</c:if>
                                    </div><small>${item.empl4Ratio}%</small>
                                </div>
                        	</li>
						</c:if>
						<c:if test="${not empty item.empl5}">
							<li>
								<div class="text"><span class="circle_num">5</span>${item.empl5}</div>
                                <div class="progress">
                                	<div class="inner">
                                  		<c:if test="${item.empl5Ratio > 0}">
											<a href="javascript:listReshPopK('${item.reshItemSn}','5')">
												<div class="bar" style="width: ${item.empl5Ratio}%;"></div>
											</a>
										</c:if>
										<c:if test="${item.empl5Ratio == 0}">
											<div class="bar" style="width: ${item.empl5Ratio}%;"></div>
										</c:if>
                                    </div><small>${item.empl5Ratio}%</small>
                                </div>
                        	</li>
						</c:if>
						<c:if test="${not empty item.empl6}">
							<li>
								<div class="text"><span class="circle_num">6</span>${item.empl6}</div>
                                <div class="progress">
                                	<div class="inner">
                                  		<c:if test="${item.empl6Ratio > 0}">
											<a href="javascript:listReshPopK('${item.reshItemSn}','6')">
												<div class="bar" style="width: ${item.empl6Ratio}%;"></div>
											</a>
										</c:if>
										<c:if test="${item.empl6Ratio == 0}">
											<div class="bar" style="width: ${item.empl6Ratio}%;"></div>
										</c:if>
                                    </div><small>${item.empl6Ratio}%</small>
                                </div>
							</li>
						</c:if>
						<c:if test="${not empty item.empl7}">
							<li>
								<div class="text"><span class="circle_num">7</span>${item.empl7}</div>
                                <div class="progress">
                                	<div class="inner">
                                  		<c:if test="${item.empl7Ratio > 0}">
											<a href="javascript:listReshPopK('${item.reshItemSn}','7')">
												<div class="bar" style="width: ${item.empl7Ratio}%;"></div>
											</a>
										</c:if>
										<c:if test="${item.empl7Ratio == 0}">
											<div class="bar" style="width: ${item.empl7Ratio}%;"></div>
										</c:if>
                                    </div><small>${item.empl7Ratio}%</small>
								</div>
							</li>
						</c:if>
						<c:if test="${not empty item.empl8}">
							<li>
								<div class="text"><span class="circle_num">8</span>${item.empl8}</div>
                                <div class="progress">
                                	<div class="inner">
                                  		<c:if test="${item.empl8Ratio > 0}">
											<a href="javascript:listReshPopK('${item.reshItemSn}','8')">
												<div class="bar" style="width: ${item.empl8Ratio}%;"></div>
											</a>
										</c:if>
										<c:if test="${item.empl8Ratio == 0}">
											<div class="bar" style="width: ${item.empl8Ratio}%;"></div>
										</c:if>
                                    </div><small>${item.empl8Ratio}%</small>
								</div>
							</li>
						</c:if>
						<c:if test="${not empty item.empl9}">
							<li>
								<div class="text"><span class="circle_num">9</span>${item.empl9}</div>
                                <div class="progress">
                                	<div class="inner">
                                  		<c:if test="${item.empl9Ratio > 0}">
											<a href="javascript:listReshPopK('${item.reshItemSn}','9')">
												<div class="bar" style="width: ${item.empl9Ratio}%;"></div>
											</a>
										</c:if>
										<c:if test="${item.empl9Ratio == 0}">
											<div class="bar" style="width: ${item.empl9Ratio}%;"></div>
										</c:if>
                                    </div><small>${item.empl9Ratio}%</small>
								</div>
							</li>
						</c:if>
						<c:if test="${not empty item.empl10}">
							<li>
								<div class="text"><span class="circle_num">10</span>${item.empl10}</div>
                                <div class="progress">
                                	<div class="inner">
                                  		<c:if test="${item.empl10Ratio > 0}">
											<a href="javascript:listReshPopK('${item.reshItemSn}','10')">
												<div class="bar" style="width: ${item.empl10Ratio}%;"></div>
											</a>
										</c:if>
										<c:if test="${item.empl10Ratio == 0}">
											<div class="bar" style="width: ${item.empl10Ratio}%;"></div>
										</c:if>
                                    </div><small>${item.empl10Ratio}%</small>
								</div>
							</li>
						</c:if>
						<c:if test="${not empty item.empl11}">
							<li>
								<div class="text"><span class="circle_num">11</span>${item.empl11}</div>
                                <div class="progress">
                                	<div class="inner">
                                  		<c:if test="${item.empl11Ratio > 0}">
											<a href="javascript:listReshPopK('${item.reshItemSn}','11')">
												<div class="bar" style="width: ${item.empl11Ratio}%;"></div>
											</a>
										</c:if>
										<c:if test="${item.empl11Ratio == 0}">
											<div class="bar" style="width: ${item.empl11Ratio}%;"></div>
										</c:if>
                                    </div><small>${item.empl11Ratio}%</small>
								</div>
							</li>
						</c:if>
						<c:if test="${not empty item.empl12}">
							<li>
								<div class="text"><span class="circle_num">12</span>${item.empl12}</div>
                                <div class="progress">
                                	<div class="inner">
                                  		<c:if test="${item.empl12Ratio > 0}">
											<a href="javascript:listReshPopK('${item.reshItemSn}','12')">
												<div class="bar" style="width: ${item.empl12Ratio}%;"></div>
											</a>
										</c:if>
										<c:if test="${item.empl12Ratio == 0}">
											<div class="bar" style="width: ${item.empl12Ratio}%;"></div>
										</c:if>
                                    </div><small>${item.empl12Ratio}%</small>
								</div>
							</li>
						</c:if>
						<c:if test="${not empty item.empl13}">
							<li>
								<div class="text"><span class="circle_num">13</span>${item.empl13}</div>
                                <div class="progress">
                                	<div class="inner">
                                  		<c:if test="${item.empl13Ratio > 0}">
											<a href="javascript:listReshPopK('${item.reshItemSn}','13')">
												<div class="bar" style="width: ${item.empl13Ratio}%;"></div>
											</a>
										</c:if>
										<c:if test="${item.empl13Ratio == 0}">
											<div class="bar" style="width: ${item.empl13Ratio}%;"></div>
										</c:if>
                                    </div><small>${item.empl13Ratio}%</small>
								</div>
							</li>
						</c:if>
						<c:if test="${not empty item.empl14}">
							<li>
								<div class="text"><span class="circle_num">14</span>${item.empl14}</div>
                                <div class="progress">
                                	<div class="inner">
                                  		<c:if test="${item.empl14Ratio > 0}">
											<a href="javascript:listReshPopK('${item.reshItemSn}','14')">
												<div class="bar" style="width: ${item.empl14Ratio}%;"></div>
											</a>
										</c:if>
										<c:if test="${item.empl14Ratio == 0}">
											<div class="bar" style="width: ${item.empl14Ratio}%;"></div>
										</c:if>
                                    </div><small>${item.empl14Ratio}%</small>
								</div>
							</li>
						</c:if>
						<c:if test="${not empty item.empl15}">
							<li>
								<div class="text"><span class="circle_num">15</span>${item.empl15}</div>
                                <div class="progress">
                                	<div class="inner">
                                  		<c:if test="${item.empl15Ratio > 0}">
											<a href="javascript:listReshPopK('${item.reshItemSn}','15')">
												<div class="bar" style="width: ${item.empl15Ratio}%;"></div>
											</a>
										</c:if>
										<c:if test="${item.empl15Ratio == 0}">
											<div class="bar" style="width: ${item.empl15Ratio}%;"></div>
										</c:if>
                                    </div><small>${item.empl15Ratio}%</small>
								</div>
							</li>
						</c:if>
						<c:if test="${not empty item.empl16}">
							<li>
								<div class="text"><span class="circle_num">16</span>${item.empl16}</div>
                                <div class="progress">
                                	<div class="inner">
                                  		<c:if test="${item.empl16Ratio > 0}">
											<a href="javascript:listReshPopK('${item.reshItemSn}','16')">
												<div class="bar" style="width: ${item.empl16Ratio}%;"></div>
											</a>
										</c:if>
										<c:if test="${item.empl16Ratio == 0}">
											<div class="bar" style="width: ${item.empl16Ratio}%;"></div>
										</c:if>
                                    </div><small>${item.empl16Ratio}%</small>
								</div>
							</li>
						</c:if>
						<c:if test="${not empty item.empl17}">
							<li>
								<div class="text"><span class="circle_num">17</span>${item.empl17}</div>
                                <div class="progress">
                                	<div class="inner">
                                  		<c:if test="${item.empl17Ratio > 0}">
											<a href="javascript:listReshPopK('${item.reshItemSn}','17')">
												<div class="bar" style="width: ${item.empl17Ratio}%;"></div>
											</a>
										</c:if>
										<c:if test="${item.empl17Ratio == 0}">
											<div class="bar" style="width: ${item.empl17Ratio}%;"></div>
										</c:if>
                                    </div><small>${item.empl17Ratio}%</small>
								</div>
							</li>
						</c:if>
						<c:if test="${not empty item.empl18}">
							<li>
								<div class="text"><span class="circle_num">18</span>${item.empl18}</div>
                                <div class="progress">
                                	<div class="inner">
                                  		<c:if test="${item.empl18Ratio > 0}">
											<a href="javascript:listReshPopK('${item.reshItemSn}','18')">
												<div class="bar" style="width: ${item.empl18Ratio}%;"></div>
											</a>
										</c:if>
										<c:if test="${item.empl18Ratio == 0}">
											<div class="bar" style="width: ${item.empl18Ratio}%;"></div>
										</c:if>
                                    </div><small>${item.empl18Ratio}%</small>
								</div>
							</li>
						</c:if>
						<c:if test="${not empty item.empl19}">
							<li>
								<div class="text"><span class="circle_num">19</span>${item.empl19}</div>
                                <div class="progress">
                                	<div class="inner">
                                  		<c:if test="${item.empl19Ratio > 0}">
											<a href="javascript:listReshPopK('${item.reshItemSn}','19')">
												<div class="bar" style="width: ${item.empl19Ratio}%;"></div>
											</a>
										</c:if>
										<c:if test="${item.empl19Ratio == 0}">
											<div class="bar" style="width: ${item.empl19Ratio}%;"></div>
										</c:if>
                                    </div><small>${item.empl19Ratio}%</small>
								</div>
							</li>
						</c:if>
						<c:if test="${not empty item.empl20}">
							<li>
								<div class="text"><span class="circle_num">20</span>${item.empl20}</div>
                                <div class="progress">
                                	<div class="inner">
                                  		<c:if test="${item.empl20Ratio > 0}">
											<a href="javascript:listReshPopK('${item.reshItemSn}','20')">
												<div class="bar" style="width: ${item.empl20Ratio}%;"></div>
											</a>
										</c:if>
										<c:if test="${item.empl20Ratio == 0}">
											<div class="bar" style="width: ${item.empl20Ratio}%;"></div>
										</c:if>
                                    </div><small>${item.empl20Ratio}%</small>
								</div>
							</li>
						</c:if>
					</c:if>
				</ol>
			</div>
		</div>
	</div>
</c:forEach>
<c:if test="${empty crsReshResultList}">
	<li class="list-group-item">
		<spring:message code="lecture.message.research.question.nodata"/>
	</li>
</c:if>

<script type="text/javascript">
	var modalBox = null;
	$(document).ready(function() {
		modalBox = new $M.ModalDialog({
			"modalid" : "modal1"
		});
	});

	function modalBoxClose() {
		modalBox.clear();
		modalBox.close();
	}

	function listReshPopD(itemSn){
		var addContent  = "<iframe id='opinionReadFrame' name='opinionReadFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/creCrsResh/mainOpinionPop"/>"
			+ "?crsCreCd=${crsReshResultVO.crsCreCd}&amp;reshItemSn="+itemSn
			+"&amp;reshSn=${crsReshResultVO.reshSn}&amp;reshAnsr=&amp;reshAnsrTypeCd=D'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(1000, 600);
		modalBox.setTitle("<spring:message code="lecture.title.research.result"/>");
		modalBox.show();
	}

	function listReshPopK(itemSn, reshAnsr){
		var addContent  = "<iframe id='opinionReadFrame' name='opinionReadFrame' width='100%' height='100%' "
			+ "frameborder='0' scrolling='auto' src='<c:url value="/lec/creCrsResh/mainOpinionPop"/>"
			+ "?crsCreCd=${crsReshResultVO.crsCreCd}&amp;reshItemSn="+itemSn
			+"&amp;reshSn=${crsReshResultVO.reshSn}&amp;reshAnsr="+reshAnsr+"&amp;reshAnsrTypeCd=K'/>";
		modalBox.clear();
		modalBox.addContents(addContent);
		modalBox.resize(750, 720);
		modalBox.setTitle("<spring:message code="lecture.title.research.result"/>");
		modalBox.show();

	}


	function setHeader() {
		var retVal = "";
		for(var i=0; i < $("#target option").size(); i++) {
			if(i > 0 && i  < $("#target option").size()) retVal += "@$";
			retVal += $("#target option:eq("+i+")").val();
		}
		return retVal;
	}

	function excelDownload() {
		var url = cUrl("/lec/creCrsResh/reshExcelDownload")+"?excelHeader="+setHeader()+"${AMPERSAND}crsCreCd=${crsReshResultVO.crsCreCd}${AMPERSAND}reshSn=${crsReshResultVO.reshSn}";

		<c:if test="${BROWSER eq 'IE'}">
		window.open(url,'pdf','top=0;left=0,scrollbars=auto');
		</c:if>
		<c:if test="${BROWSER ne 'IE'}">
		if ( $("#_m_download_iframe").length == 0 ) {
			iframeHtml =
				'<iframe id="_m_download_iframe" name="_m_download_iframe" style="visibility: none; display: none;"></iframe>';
			$("body").append(iframeHtml);
		}
		$('#_m_download_iframe').attr('src', url);
		</c:if>

	}

	/**
	 * 수정 화면 토글
	 */
	function viewEditForm() {
		$("#viewResh").hide();
		$("#editResh").show();
	}

	/**
	 * 수정 화면 토글
	 */
	function hideEditForm() {
		$("#viewResh").show();
		$("#editResh").hide();
	}

	/**
	 * 설문 수정
	 */
	function editResh() {
		if(!validate(document.creCrsReshForm)) return;

		var startDttm = $("#startDttm").val();
		var endDttm = $("#endDttm").val();
		var startHour = $("#startHour").val();
		var startMin = $("#startMin").val();
		var endHour = $("#endHour").val();
		var endMin = $("#endMin").val();
		var startDttmArray = startDttm.split(".");
		var endDttmArray = endDttm.split(".");
		var StartDttmObj = new Date(startDttmArray[0], Number(startDttmArray[1])-1, startDttmArray[2], startHour, startMin, 01);
		var EndDttmObj = new Date(endDttmArray[0], Number(endDttmArray[1])-1, endDttmArray[2], endHour, endMin, 01);

		if(StartDttmObj >= EndDttmObj){
			alert('<spring:message code="lecture.message.resh.alert.result.date"/>');
			return;
		}

		$('#creCrsReshForm').attr("action","/lec/creCrsResh/edit");
		document.creCrsReshForm.submit();
	}

	/**
	 * 설문 삭제
	 */
	function delResh() {
		if(parseInt("${creCrsReshVO.ansrCnt}",10) > 0){
			if(confirm("<spring:message code="lecture.message.research.delete.confirm"/>")){
				$('#creCrsReshForm').attr("action","/lec/creCrsResh/remove");
				document.creCrsReshForm.submit();
			}
		}else{
			if(confirm("<spring:message code="course.message.resh.confirm.delete"/>")) {
				$('#creCrsReshForm').attr("action","/lec/creCrsResh/remove");
				document.creCrsReshForm.submit();
			}
		}
	}

	//Date Type YYYYMMDD
	function chgDate(date){
		var chgDate = date.replace(/\./g,"");
		return chgDate;
	}

	//날짜 체크하기.
	function dateCheck(startDate , endDate){
		if(startDate > endDate) {
			return false;
		}else{
			return true;
		}
	}

	//날짜 사이의 간격 구하기
	function getGapDate() {
		var f = document.f;
		var from = f.serviceStart.value;
		var to = f.serviceEnd.value;
		if(from != "" && to != "") {
			var days = jsGetDays(chgDate(from),chgDate(to));

			f.serviceDay.value = days;

		}
	}

</script>
