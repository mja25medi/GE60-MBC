<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<%pageContext.setAttribute("crlf", "\n");%>
<c:set var="examStareVO" value="${examStareVO}"/>
<c:set var="vo" value="${vo}"/>
       <c:if test="${examVO.examTypeCd eq 'ON' }">
<div class="modal_cont">
        <div class="table_list">
            <ul class="list">
                <li class="head"><label>이름</label></li>
                <li>${studentVO.userNm}</li>
                <li class="head"><label>아이디</label></li>
                <li>${studentVO.userId}</li>
            </ul>
            <ul class="list">
                <li class="head"><label>최종응시일시</label></li>
                <li>	<meditag:dateformat property="${vo.startDttm}" type="0" delimeter="."/> ~ <meditag:dateformat property="${vo.endDttm}" type="0" delimeter="."/></li>
                <li class="head"><label>응시횟수</label></li>
                <li> ${vo.stareCnt} <spring:message code="common.title.times.postfix"/></li>
            </ul>
            <ul class="list">
                <li class="head"><label>점수</label></li>
                <li>${vo.totGetScore}/<fmt:formatNumber value="100" pattern="#.#" minFractionDigits="1"/></li>
            </ul>
            <ul class="list">
                <li class="head"><label>첨언</label></li>
                <li><p class="text_none">${fn:replace(vo.atchCts,crlf,"<br/>")}</p></li>
            </ul>
        </div>
        <c:forEach items="${questionList}" var="item">
        <c:url var="mark" value="mark_right"/>
		<c:set var="correctText" value="correct"/>
		<c:if test="${stareInfo[item.examQstnSn]['score'] eq '0'}">
			<c:url var="mark" value="mark_wrong"/>
			<c:set var="correctText" value="incorrect"/>
		</c:if>
        <div class="list_view_box">
            <div class="list_title ${mark }">
                <div class="type"><span class="num">${item.qstnNo}.</span>선택문항</div>
                <div class="score"><meditag:codename code="${item.qstnType}" category="EXAM_QSTN_TYPE" /> : <fmt:formatNumber value="${stareInfo[item.examQstnSn]['score']}" pattern="#.#" minFractionDigits="1"/> / <fmt:formatNumber value="${item.qstnScore}" pattern="#.#" minFractionDigits="1"/>점</div>
            </div>
            <div class="list_item">
                <div class="quest">${item.title}</div>
                <c:if test="${item.qstnType eq 'K'}">
					  <ol class="field">
						<c:if test="${not empty item.empl1}"><li class="ui checkbox"><input type="radio" id="check0201" class="hidden" disabled><label for="check0201">${item.empl1}</label></li></c:if>
						<c:if test="${not empty item.empl2}"><li class="ui checkbox"><input type="radio" id="check0202" class="hidden" disabled><label for="check0202">${item.empl2}</label></li></c:if>
						<c:if test="${not empty item.empl3}"><li class="ui checkbox"><input type="radio" id="check0203" class="hidden" disabled><label for="check0203">${item.empl3}</label></li></c:if>
						<c:if test="${not empty item.empl4}"><li class="ui checkbox"><input type="radio" id="check0204" class="hidden" disabled><label for="check0204">${item.empl4}</label></li></c:if>
						<c:if test="${not empty item.empl5}"><li class="ui checkbox"><input type="radio" id="check0205" class="hidden" disabled><label for="check0205">${item.empl5}</label></li></c:if>
					</ol>
				</c:if>
                <c:if test="${item.qstnType eq 'S'}">
					 <div class="checkImg">
                  	  	 <c:if test="${stareInfo[item.examQstnSn]['answer'] eq 'O'}">
                 	  		 <input id="imgChk_true2" type="radio" name="imgChk2" checked disabled><label class="imgChk true" for="imgChk_true"></label>
                   			 <input id="imgChk_false2" type="radio" name="imgChk2"  disabled><label class="imgChk false" for="imgChk_false"></label>
                    	</c:if>
                  	  	 <c:if test="${stareInfo[item.examQstnSn]['answer'] eq 'X'}">
          	                 <input id="imgChk_true2" type="radio" name="imgChk2"  disabled><label class="imgChk true" for="imgChk_true"></label>
                   			 <input id="imgChk_false2" type="radio" name="imgChk2" checked disabled> <label class="imgChk false" for="imgChk_false"></label>
                    	</c:if>
                	</div>
				</c:if>
                <c:if test="${item.qstnType eq 'J'}"><!-- 서술 -->
					<div class="form-row">
                    <textarea rows="5" class="form-control">${stareInfo[item.examQstnSn]['answer']}</textarea>
                </div>
				</c:if>
                <c:if test="${item.qstnType eq 'D'}"><!-- 단답 -->
					  <div class="form-inline">
                    <input class="form-control" type="text"value="${stareInfo[item.examQstnSn]['answer']}">
                </div>
				</c:if>
                
                <ul class="feedback">
                    <li><strong>정답</strong>
                 		<c:if test="${item.qstnType eq 'D'}">${fn:replace(item.rgtAnsr, '|', ',')}</c:if>
						<c:if test="${item.qstnType ne 'D'}">${fn:replace(item.rgtAnsr,crlf,"<br/>")}</c:if>
					</li>
                    <li><strong>학습자답</strong>
                  	  ${fn:replace(stareInfo[item.examQstnSn]['answer'],crlf,"<br/>")}
                    </li>
                    <li><strong>해설</strong>${item.qstnExpl}</li>
                </ul>
            </div>
        </div>
        </c:forEach>
    </div>
    </c:if>
			<c:if test="${examVO.examTypeCd eq 'OFF' }">
				<table class="table table-bordered">
					<colgroup>
						<col style="width:20%" />
						<col style="width:30%" />
						<col style="width:20%" />
						<col style="width:30%" />
					</colgroup>
					<tbody>
						<tr>
							<th scope="row"><spring:message code="lecture.title.exam.name"/></th>
							<td colspan="3">
								${examVO.examTitle}
							</td>
						</tr>
						<tr>
							<th scope="row"><spring:message code="lecture.title.exam.duration"/></th>
							<td colspan="3">
								<meditag:dateformat type="8" delimeter="." property="${examVO.examStartDttm}"/>~<meditag:dateformat type="8" delimeter="." property="${examVO.examEndDttm}"/>
							</td>
						</tr>
						<tr>
							<th scope="row"><spring:message code="lecture.title.exam.desc"/></th>
							<td colspan="3">
								${fn:replace(examVO.examCts,crlf,"<br/>")}
							</td>
						</tr>
						<tr>
							<th scope="row"><spring:message code="user.title.userinfo.name"/></th>
							<td>
								${studentVO.userNm}
							</td>
							<th scope="row"><spring:message code="user.title.userinfo.userid"/></th>
							<td>
								${studentVO.userId}
							</td>
						</tr>
						<tr>
							<th scope="row"><spring:message code="lecture.title.exam.rate.score"/></th>
							<td colspan="3">
								${vo.totGetScore}/<fmt:formatNumber value="100" pattern="#.#" minFractionDigits="1"/>
							</td>
						</tr>
					</tbody>
				</table>
			</c:if>
			    <div class="modal_btns">
     				   <button type="button" class="btn" onclick="javascript:parent.modalBoxClose()"><spring:message code="button.close"/></button>
   					 </div>
