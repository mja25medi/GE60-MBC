<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="researchDTO" value="${researchForm.researchDTO}" />
<c:set var="itemList" value="${researchForm.researchResultList}"/>
<c:url var="img_base" value="/img/home" />
<mhtml:class_html>
<mhtml:class_head>

</mhtml:class_head>
<mhtml:class_body>
	<div id="content">
		<mhtml:class_location />

		<table class="board_b">
			<caption>설문정보</caption>
			<colgroup>
				<col style-"width:20%"/>
				<col style-"width:80%"/>
			</colgroup>
			<tbody>
				<tr>
					<th class="hwname" style="width:20%">설문 제목</th>
					<td>
						<strong>${researchDTO.reshTitle}</strong>
					</td>
				</tr>
				<tr>
					<th class="hwname lm70">설문 기간</th>
					<td colspan="3"><strong>${researchDTO.reshStartDttm} ~ ${researchDTO.reshEndDttm}</strong></td>
				</tr>
			</tbody>
		</table>

		<nested:form action="/ResearchLecture.do" onsubmit="return false" id="researchForm">
		<input type="hidden" name="cmd" />
		<input type="hidden" property="researchItemDTO.crsCreCd" />
		<input type="hidden" property="researchItemDTO.reshSn" />
		<input type="hidden" property="researchItemDTO.reshSn" />
		<input type="hidden" property="researchAnswerDTO.splitData" />
		<input type="hidden" property="researchAnswerDTO.reshSn" value="${researchDTO.reshSn}"/>
		<table class="board_lecture" summary="번호,분류명,제목">
			<caption>과제은행정보</caption>
			<colgroup>
				<col width="6%" />
				<col width="auto" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">설문내용</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${itemList}" varStatus="status">
				<input type="hidden" name="unitSn" value="${item.reshItemSn}"/>
				<input type="hidden" name="reshItemTypeCd" value="${item.reshItemTypeCd}"/>
				<c:if test="${item.reshItemTypeCd eq 'D'}">
				<tr>
					<td>${status.count}</td>
					<td class="survay">
						${item.reshItemCts} (${item.reshItemTypeNm})
						<textarea name="etcOption" style="width:90%;height:50px;"></textarea>
					</td>
				</tr>
				</c:if>
				<c:if test="${item.reshItemTypeCd eq 'K'}">
				<input type="hidden" name="etcOption" value=""/>
				<tr>
					<td>${status.count}</td>
					<td class="survay">
						${item.reshItemCts} (${item.reshItemTypeNm})
						<ul class="qngraph">
							<c:if test="${not empty item.empl1}">
							<li>
								<input type="radio" name="expl${item.reshItemSn}" value='1' style="float:left"/> <p>1. ${item.empl1}</p>
							</li>
							</c:if>
							<c:if test="${not empty item.empl2}">
							<li>
								<input type="radio" name="expl${item.reshItemSn}" value='2' style="float:left"/> <p>2. ${item.empl2}</p>
							</li>
							</c:if>
							<c:if test="${not empty item.empl3}">
							<li>
								<input type="radio" name="expl${item.reshItemSn}" value='3' style="float:left"/> <p>3. ${item.empl3}</p>
							</li>
							</c:if>
							<c:if test="${not empty item.empl4}">
							<li>
								<input type="radio" name="expl${item.reshItemSn}" value='4' style="float:left"/> <p>4. ${item.empl4}</p>
							</li>
							</c:if>
							<c:if test="${not empty item.empl5}">
							<li>
								<input type="radio" name="expl${item.reshItemSn}" value='5' style="float:left"/> <p>5. ${item.empl5}</p>
							</li>
							</c:if>
							<c:if test="${not empty item.empl6}">
							<li>
								<input type="radio" name="expl${item.reshItemSn}" value='6' style="float:left"/> <p>6. ${item.empl6}</p>
							</li>
							</c:if>
							<c:if test="${not empty item.empl7}">
							<li>
								<input type="radio" name="expl${item.reshItemSn}" value='7' style="float:left"/> <p>7. ${item.empl7}</p>
							</li>
							</c:if>
							<c:if test="${not empty item.empl8}">
							<li>
								<input type="radio" name="expl${item.reshItemSn}" value='8' style="float:left"/> <p>8. ${item.empl8}</p>
							</li>
							</c:if>
							<c:if test="${not empty item.empl9}">
							<li>
								<input type="radio" name="expl${item.reshItemSn}" value='9' style="float:left"/> <p>9. ${item.empl9}</p>
							</li>
							</c:if>
							<c:if test="${not empty item.empl10}">
							<li>
								<input type="radio" name="expl${item.reshItemSn}" value='10' style="float:left"/> <p>10. ${item.empl10}</p>
							</li>
							</c:if>
						</ul>
					</td>
				</tr>
				</c:if>
				</c:forEach>
				<c:if test="${empty itemList}">
				<tr>
					<td colspan="2">* 등록된 설문 문제가 없습니다.</td>
				</tr>
				</c:if>

			</tbody>
		</table>
		</nested:form>
		<div class="btn_right">
			<a href="#" class="btn01" onclick="submitVV()">저장</a>
			<a href="#" class="btn02" onclick="goList()">목록</a>
		</div>
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
		$('#researchForm').find('input[name=cmd]').val(cmd);
		$('#researchForm').submit();
	}


	function submitVV(){
		var f = document.researchForm;
		if(validateResearch() == -1){
			alert("모든 설문에 답변을 부탁드립니다.");
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
		f["researchAnswerDTO.splitData"].value = ansStr;
		process("saveResearch");	// cmd
	}


	function validateResearch(){
		var f = document.researchForm;
		var selected = 0;
		var itemSnObject = document.getElementsByName("unitSn");
		var  reshItemTypeCd  = document.getElementsByName("reshItemTypeCd"); //문제 유형
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
</script>
</mhtml:class_body>
</mhtml:class_html>
