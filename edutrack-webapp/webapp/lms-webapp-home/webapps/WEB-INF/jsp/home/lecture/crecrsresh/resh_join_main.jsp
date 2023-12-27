<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="creCrsReshVO" value="${vo}" />
<c:set var="itemList" value="${reshBankItemList}"/>
<c:url var="img_base" value="/img/home" />
   				<div class="learn_top">
                    <div class="left_title">
                        <h3>강의평가</h3>
                    </div>
                </div>
                <div class="segment">
                	<div class="table_list">
                        <ul class="list">
                            <li class="head"><label>설문제목</label></li>
                            <li>${creCrsReshVO.reshTitle}</li>
                        </ul>
                        <ul class="list">
                            <li class="head"><label>설문기간</label></li>
                            <li><meditag:dateformat type="8" delimeter="." property="${creCrsReshVO.startDttm}"/> ~<meditag:dateformat type="8" delimeter="." property="${creCrsReshVO.endDttm}"/></li>
                        </ul>
                    </div>
                </div>
				<form id="creCrsReshForm" name="creCrsReshForm" onsubmit="return false">
					<input type="hidden" name="crsCreCd" value="${crsReshAnsrVO.crsCreCd}" />
					<input type="hidden" name="reshSn" value="${crsReshAnsrVO.reshSn}" />
					<input type="hidden" name="reshAnsrStr" value="${crsReshAnsrVO.reshAnsrStr}"/>
					<div class="segment">
						<c:forEach var="item" items="${itemList}" varStatus="status">
							<c:set var="styleStr" value=""/>
							<c:if test="${item.emplViewType eq 'HEIGHT' }"><c:set var="styleStr" value=""/></c:if>
								<input type="hidden" name="unitSn" value="${item.reshItemSn}"/>
								<input type="hidden" name="reshItemTypeCd" value="${item.reshItemTypeCd}"/>
								<div class="survey_list">
									<div class="title">
										${status.count}. ${item.reshItemCts} 
										<c:if test="${item.reshItemTypeCd eq 'D' }">
											<small>(<meditag:codename code="${item.reshItemTypeCd}" category="RESH_ITEM_TYPE_CD"/>/<spring:message code="course.title.reshbank.itemd.maxlength"/>)</small>
										</c:if>
										<c:if test="${item.reshItemTypeCd eq 'K' }">
											<small>(<meditag:codename code="${item.reshItemTypeCd}" category="RESH_ITEM_TYPE_CD"/>)</small>
										</c:if>
									</div>
									<c:if test="${item.reshItemTypeCd eq 'D'}">
										<textarea class="form-control etcOption" rows="5" id="contTextarea"  name="etcOption" maxlength="1000" ></textarea>
									</c:if>
									<c:if test="${item.reshItemTypeCd eq 'K'}">
										<input type="hidden" name="etcOption" value=""/>
										<ol>
											<c:if test="${not empty item.empl1}">
											  <li style="${styleStr}">
												  <div class="custom-input"><input type="radio" name="expl${item.reshItemSn}" id="chk0101${item.reshItemSn}"  value='1' ><label for="chk0101${item.reshItemSn}">${item.empl1}</label></div>
				                              </li>
											</c:if>
											<c:if test="${not empty item.empl2}">
											  <li style="${styleStr}">
												  <div class="custom-input"><input type="radio" name="expl${item.reshItemSn}" id="chk0102${item.reshItemSn}"  value='2' ><label for="chk0102${item.reshItemSn}">${item.empl2}</label></div>
				                              </li>
											</c:if>
											<c:if test="${not empty item.empl3}">
											  <li style="${styleStr}">
												  <div class="custom-input"><input type="radio" name="expl${item.reshItemSn}" id="chk0103${item.reshItemSn}"  value='3' ><label for="chk0103${item.reshItemSn}">${item.empl3}</label></div>
				                              </li>
											</c:if>
											<c:if test="${not empty item.empl4}">
											  <li style="${styleStr}">
												  <div class="custom-input"><input type="radio" name="expl${item.reshItemSn}" id="chk0104${item.reshItemSn}"  value='4' ><label for="chk0104${item.reshItemSn}">${item.empl4}</label></div>
				                              </li>
											</c:if>
											<c:if test="${not empty item.empl5}">
											  <li style="${styleStr}">
												  <div class="custom-input"><input type="radio" name="expl${item.reshItemSn}" id="chk0105${item.reshItemSn}"  value='5' ><label for="chk0105${item.reshItemSn}">${item.empl5}</label></div>
				                              </li>
											</c:if>
											<c:if test="${not empty item.empl6}">
											  <li style="${styleStr}">
												  <div class="custom-input"><input type="radio" name="expl${item.reshItemSn}" id="chk0106${item.reshItemSn}"  value='6' ><label for="chk0106${item.reshItemSn}">${item.empl6}</label></div>
				                              </li>
											</c:if>
											<c:if test="${not empty item.empl7}">
											  <li style="${styleStr}">
												  <div class="custom-input"><input type="radio" name="expl${item.reshItemSn}" id="chk0107${item.reshItemSn}"  value='7' ><label for="chk0107${item.reshItemSn}">${item.empl7}</label></div>
				                              </li>
											</c:if>
											<c:if test="${not empty item.empl8}">
											  <li style="${styleStr}">
												  <div class="custom-input"><input type="radio" name="expl${item.reshItemSn}" id="chk0108${item.reshItemSn}"  value='8' ><label for="chk0108${item.reshItemSn}">${item.empl8}</label></div>
				                              </li>
											</c:if>
											<c:if test="${not empty item.empl9}">
											  <li style="${styleStr}">
												  <div class="custom-input"><input type="radio" name="expl${item.reshItemSn}" id="chk0109${item.reshItemSn}"  value='9' ><label for="chk0109${item.reshItemSn}">${item.empl9}</label></div>
				                              </li>
											</c:if>
											<c:if test="${not empty item.empl10}">
											  <li style="${styleStr}">
												  <div class="custom-input"><input type="radio" name="expl${item.reshItemSn}" id="chk0110${item.reshItemSn}"  value='10' ><label for="chk0110${item.reshItemSn}">${item.empl10}</label></div>
				                              </li>
											</c:if>
											<c:if test="${not empty item.empl11}">
											  <li style="${styleStr}">
												  <div class="custom-input"><input type="radio" name="expl${item.reshItemSn}" id="chk0111${item.reshItemSn}"  value='11' ><label for="chk0111${item.reshItemSn}">${item.empl11}</label></div>
				                              </li>
											</c:if>
											<c:if test="${not empty item.empl12}">
											  <li style="${styleStr}">
												  <div class="custom-input"><input type="radio" name="expl${item.reshItemSn}" id="chk0112${item.reshItemSn}"  value='12' ><label for="chk0112${item.reshItemSn}">${item.empl12}</label></div>
				                              </li>
											</c:if>
											<c:if test="${not empty item.empl13}">
											  <li style="${styleStr}">
												  <div class="custom-input"><input type="radio" name="expl${item.reshItemSn}" id="chk0113${item.reshItemSn}"  value='13' ><label for="chk0113${item.reshItemSn}">${item.empl13}</label></div>
				                              </li>
											</c:if>
											<c:if test="${not empty item.empl14}">
											  <li style="${styleStr}">
												  <div class="custom-input"><input type="radio" name="expl${item.reshItemSn}" id="chk0114${item.reshItemSn}"  value='14' ><label for="chk0114${item.reshItemSn}">${item.empl14}</label></div>
				                              </li>
											</c:if>
											<c:if test="${not empty item.empl15}">
											  <li style="${styleStr}">
												  <div class="custom-input"><input type="radio" name="expl${item.reshItemSn}" id="chk0115${item.reshItemSn}"  value='15' ><label for="chk0115${item.reshItemSn}">${item.empl15}</label></div>
				                              </li>
											</c:if>
											<c:if test="${not empty item.empl16}">
											  <li style="${styleStr}">
												  <div class="custom-input"><input type="radio" name="expl${item.reshItemSn}" id="chk0116${item.reshItemSn}"  value='16' ><label for="chk0116${item.reshItemSn}">${item.empl16}</label></div>
				                              </li>
											</c:if>
											<c:if test="${not empty item.empl17}">
											  <li style="${styleStr}">
												  <div class="custom-input"><input type="radio" name="expl${item.reshItemSn}" id="chk0117${item.reshItemSn}"  value='17' ><label for="chk0117${item.reshItemSn}">${item.empl17}</label></div>
				                              </li>
											</c:if>
											<c:if test="${not empty item.empl18}">
											  <li style="${styleStr}">
												  <div class="custom-input"><input type="radio" name="expl${item.reshItemSn}" id="chk0118${item.reshItemSn}"  value='18' ><label for="chk0118${item.reshItemSn}">${item.empl18}</label></div>
				                              </li>
											</c:if>
											<c:if test="${not empty item.empl19}">
											  <li style="${styleStr}">
												  <div class="custom-input"><input type="radio" name="expl${item.reshItemSn}" id="chk0119${item.reshItemSn}"  value='19' ><label for="chk0119${item.reshItemSn}">${item.empl19}</label></div>
				                              </li>
											</c:if>
											<c:if test="${not empty item.empl20}">
											  <li style="${styleStr}">
												  <div class="custom-input"><input type="radio" name="expl${item.reshItemSn}" id="chk0120${item.reshItemSn}"  value='20' ><label for="chk0120${item.reshItemSn}">${item.empl20}</label></div>
				                              </li>
											</c:if>
										</ol>
									</c:if>
								</div>
						</c:forEach>
						<c:if test="${empty itemList}">
							<li class="list-group-item"><spring:message code="lecture.message.research.question.nodata"/></li>
						</c:if>
					</div>
				</form>
				 <div class="btns mt30">
					 <button type="button" class="btn gray2" onclick="submitVV()"><spring:message code="button.add"/></button>
					 <button type="button" class="btn type5" onclick="goList()"><spring:message code="button.list"/></button>
				</div>

<script type="text/javascript">
	var ItemDTO = new Object();
	var reshItemSn ="";
	var values ="";

	$(document).ready(function() {
	});

	/**
	 * 서브밋 처리
	 */
	function process(cmd) {
		$('#creCrsReshForm').attr("action","/lec/creCrsResh/"+cmd);
		document.creCrsReshForm.submit();
	}

	function goList() {
		document.location.href = cUrl("/lec/creCrsResh/stdMain");
	}

	function submitVV(){
		var f = document.creCrsReshForm;
		if(validateResearch() == -1){
			alert("<spring:message code="lecture.message.research.answer.allquestion"/>");
			return false;
		}

		var ansStr = "";
		var val = "";

		var itemSnObject = document.getElementsByName("unitSn");
		var reshItemTypeCd  = document.getElementsByName("reshItemTypeCd"); //문제 유형
		var etcOption  = document.getElementsByName("etcOption");

		for(var i =0; i < itemSnObject.length; i++) {
			var explObj = document.getElementsByName("expl"+itemSnObject[i].value);
			var typeObj = reshItemTypeCd[i].value;
			var option = etcOption[i].value;

			for(var j=0; j < explObj.length; j++) {
				if(explObj[j].checked == true) val = j+1;
			}
			if(typeObj =='K'){
				ansStr =  ansStr +"|" +itemSnObject[i].value + "/" + val +"/"+ option +"/" + typeObj ;
			}else if(typeObj =='D'){
				ansStr =  ansStr +"|" + itemSnObject[i].value  + "/"+ val +"/"+ option +"/" + typeObj ;
			}
		} //end of for
		f["reshAnsrStr"].value = ansStr;
		$("#creCrsReshForm").attr("action","/lec/creCrsResh/join");
		document.creCrsReshForm.submit();
	}


	function validateResearch(){
		var f = document.creCrsReshForm;
		var selected = 0;
		var itemSnObject = document.getElementsByName("unitSn");
		var reshItemTypeCd  = document.getElementsByName("reshItemTypeCd"); //문제 유형
		var etcOption  = document.getElementsByName("etcOption");

		for(var i =0; i < itemSnObject.length; i++) {
			selected ="";
			var explObj = document.getElementsByName("expl"+itemSnObject[i].value);
			var typeObj = reshItemTypeCd[i].value;
			var option = etcOption[i].value;

			for(var j=0; j < explObj.length; j++) {
				if(explObj[j].checked == true) selected = 1;
			}
			if(typeObj =='K'){                           //선택형
				if(selected != 1){
					selected = -1 ;
				}
			}else if (typeObj =='D'){                   //서술형
				if(option ==''){
					selected = -1;
				}

			}
		}
		return selected;
	}


	function fnChkByte(obj, maxByte) {
		var str = obj.value;
		var str_len = str.length;

		var rbyte = 0;
		var rlen = 0;
		var one_char = "";
		var str2 = "";

		for (var i = 0; i < str_len; i++) {
			one_char = str.charAt(i);
			if (escape(one_char).length > 4) {
				rbyte += 2; //한글2Byte
			} else {
				rbyte++; //영문 등 나머지 1Byte
			}

			if (rbyte <= maxByte) {
				rlen = i + 1; //return할 문자열 갯수
			}
		}

		if (rbyte > maxByte) {
			alert("한글 " + (maxByte / 2) + "자 / 영문 " + maxByte
					+ "자를 초과 입력할 수 없습니다.");
			str2 = str.substr(0, rlen); //문자열 자르기
			obj.value = str2;
			fnChkByte(obj, maxByte);
		} else {
			//document.getElementById('byteInfo').innerText = rbyte;
		}
	}
</script>

