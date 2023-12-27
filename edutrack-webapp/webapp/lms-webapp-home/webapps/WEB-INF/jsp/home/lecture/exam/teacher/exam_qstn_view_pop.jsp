<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set var="gubun" value="${gubun}"/>
<c:set var="examQuestionVO" value="${vo}" />

	<form id="examForm" name="examForm" onsubmit="return false" action="/mng/lecture/exam">
	<input type="hidden" name="crsCreCd" value="${vo.crsCreCd}" />
	<input type="hidden" name="examSn" value="${vo.examSn}" />
	<input type="hidden" name="examQstnSn" value="${vo.examQstnSn}" />
	<input type="hidden" name="rgtAnsr" value="${vo.rgtAnsr}" />
	<input type="hidden" name="qstnScore" value="${vo.qstnScore}" />
	<input type="hidden" name="attachImageSns" value="${vo.attachImageSns}" />
	<input type="hidden" name="editorYn" value="Y"/>
	<input type="hidden" name="qstnType" id="qstnType" value="${vo.qstnType}"/>
	<div class="modal_cont">
		<div class="list_view_box mt0">
			<div class="list_title mt0">
				<div class="type">
					<span class="num">${examQuestionVO.qstnNo}.</span><meditag:codename code="${examQuestionVO.qstnType}" category="EXAM_QSTN_TYPE" />문항</div>
				<div class="score">
					<meditag:codename code="${examQuestionVO.qstnType}" category="EXAM_QSTN_TYPE" /> : ${examQuestionVO.qstnScore}<spring:message code="common.title.score" />
				</div>
			</div>
			<div class="list_item">
				<div class="quest">${examQuestionVO.title}</div>
				<c:if test="${examQuestionVO.qstnType eq 'K'}">
				<c:if test="${not empty examQuestionVO.qstnCts}"><div class="exam_area">${examQuestionVO.qstnCts}</div>	</c:if>
					<ol class="field">
						<c:if test="${not empty examQuestionVO.empl1}">
							<li class="ui checkbox"><input type="radio" name="ans_${examQuestionVO.qstnNo}"	id="ans_${examQuestionVO.qstnNo}" value="1" <c:if test="${examQuestionVO.rgtAnsr eq '1'}">checked</c:if> /><label for="ans_${status.count}_01">${examQuestionVO.empl1}</label></li>
						</c:if>
						<c:if test="${not empty examQuestionVO.empl2}">
							<li class="ui checkbox"><input type="radio"	name="ans_${examQuestionVO.qstnNo}"	id="ans_${examQuestionVO.qstnNo}" value="2" <c:if test="${examQuestionVO.rgtAnsr eq '2'}">checked</c:if> /><label for="ans_${status.count}_02">${examQuestionVO.empl2}</label></li>
						</c:if>
						<c:if test="${not empty examQuestionVO.empl3}">
							<li class="ui checkbox"><input type="radio"	name="ans_${examQuestionVO.qstnNo}"	id="ans_${examQuestionVO.qstnNo}" value="3" <c:if test="${examQuestionVO.rgtAnsr eq '3'}">checked</c:if> /><label for="ans_${status.count}_03">${examQuestionVO.empl3}</label></li>
						</c:if>
						<c:if test="${not empty examQuestionVO.empl4}">
							<li class="ui checkbox"><input type="radio"	name="ans_${examQuestionVO.qstnNo}"	id="ans_${examQuestionVO.qstnNo}" value="4" <c:if test="${examQuestionVO.rgtAnsr eq '4'}">checked</c:if> /><label for="ans_${status.count}_04">${examQuestionVO.empl4}</label></li>
						</c:if>
						<c:if test="${not empty examQuestionVO.empl5}">
							<li class="ui checkbox"><input type="radio"	name="ans_${examQuestionVO.qstnNo}"	id="ans_${examQuestionVO.qstnNo}" value="5" <c:if test="${examQuestionVO.rgtAnsr eq '5'}">checked</c:if>/><label for="ans_${status.count}_05">${examQuestionVO.empl5}</label></li>
						</c:if>
					</ol>
					<ul class="quest_write">
						<li><strong for="explanArea">해설</strong>
	                        <div class="form-row">
	                            ${examQuestionVO.qstnExpl }
	                        </div>
	                    </li>
                    </ul>
				</c:if>
				<c:if test="${examQuestionVO.qstnType eq 'S'}">
				<c:if test="${not empty examQuestionVO.qstnCts}"><div class="exam_area">${examQuestionVO.qstnCts}</div>	</c:if>
					<ul class="quest_write">
						 <li><strong for="rightArea">정답</strong>
							<div class="checkImg">
								<input id="ans_${examQuestionVO.qstnNo}_01" type="radio" name="ans_${examQuestionVO.qstnNo}" value="O" <c:if test="${examQuestionVO.rgtAnsr eq 'O'}">checked</c:if>> <label class="imgChk true" for="ans_${examQuestionVO.qstnNo}_01"></label>
								<input id="ans_${examQuestionVO.qstnNo}_02" type="radio" name="ans_${examQuestionVO.qstnNo}" value="X" <c:if test="${examQuestionVO.rgtAnsr eq 'X'}">checked</c:if>> <label class="imgChk false" for="ans_${examQuestionVO.qstnNo}_02"></label>
							</div>
						</li>
					</ul>
					<ul class="quest_write">
						<li><strong for="explanArea">해설</strong>
	                        <div class="form-row">
	                             ${examQuestionVO.qstnExpl }
	                        </div>
	                    </li>
                    </ul>
				</c:if>
				<c:if test="${examQuestionVO.qstnType eq 'D'}">
				<c:if test="${not empty examQuestionVO.qstnCts}">
					 <div class="form-row">
                  ${examQuestionVO.qstnCts}
                </div>	
				</c:if>
					<ul class="quest_write">
                    <li><strong for="rightArea">정답</strong>
                        <div class="form-row">
                            ${fn:replace(examQuestionVO.rgtAnsr, '|', ',')}
                        </div>
                    </li>
                    <li><strong for="explanArea">해설</strong>
                        <div class="form-row">
                            ${examQuestionVO.qstnExpl }
                        </div>
                    </li>
                </ul>
				</c:if>
				<c:if test="${examQuestionVO.qstnType eq 'J'}">
				<c:if test="${not empty examQuestionVO.qstnCts}">
					 <div class="form-row">
                   ${examQuestionVO.qstnCts}
                </div>	
				</c:if>
				<ul class="quest_write">
                    <li><strong for="rightArea">정답</strong>
                        <div class="form-row">
                           ${examQuestionVO.rgtAnsr}
                        </div>
                    </li>
                    <li><strong for="explanArea">해설</strong>
                        <div class="form-row">
                             ${examQuestionVO.qstnExpl }
                        </div>
                    </li>
                </ul>
				</c:if>
			</div>
		</div>
	</div>
</form>
<div class="modal_btns">
	<button type="button" class="btn" onclick="parent.modalBoxClose()"><spring:message code="button.close" /></button>
</div>
<script type="text/javascript">
	var summernote;
	var enableQstnNo;
	// 페이지 초기화
	$(document).ready(function() {
		enableQstnNo = Number($("#lastQstnNo", parent.subWorkFrame.document ).val()) +1;
		$('.inputNumber').inputNumber();	// 숫자만 입력되도록 설정.
		$('.inputSpecial').inputSpecial();  // 특수키가 입력이 들어오묜 false return;
	});

</script>
