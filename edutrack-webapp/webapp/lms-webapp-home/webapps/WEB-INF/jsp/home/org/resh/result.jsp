<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<c:set var="crsReshResultVO" value="${vo}" />
<c:set var="orgReshVO" value="${orgReshVO}" />
<c:set var="orgReshResultList" value="${orgReshResultList}" />
				<div class="card_wrap">
                    <div class="card">
                        <ul class="category_list">
                            <li>
                                <span class="category">설문명</span>
                                <p>${orgReshVO.reshTitle}</p>
                            </li>
                            <li>
                                <span class="category">설문기한</span>
                                <p><meditag:dateformat type="8" delimeter="." property="${orgReshVO.startDttm}"/> ~<meditag:dateformat type="8" delimeter="." property="${orgReshVO.endDttm}"/></p>
                            </li>
                            
                            <li>
                                <span class="category">설문내용</span>
                                <p>${orgReshVO.reshCts}</p>
                            </li>
                        </ul>
                    </div>
                    <div class="card board">
                        <div class="con">
                            <div class="bd_tb_info caption_info">
                                <p class="tb_caption">설문결과</p>
                            </div>
                            
							<form id="creCrsReshForm" name="creCrsReshForm" onsubmit="return false">
							<input type="hidden" name="crsCreCd" value="${crsReshAnsrVO.crsCreCd}" />
							<input type="hidden" name="reshSn" value="${crsReshAnsrVO.reshSn}" />
							<input type="hidden" name="reshAnsrStr" value="${crsReshAnsrVO.reshAnsrStr}"/>
							</form>

                            <!-- 게시판 -->
                            <div class="bd_survey">
                                <ul>
                            	<c:forEach var="item" items="${orgReshResultList}" varStatus="status">
                            		<c:set var="styleStr" value="st2"/>
									<c:if test="${item.emplViewType eq 'HEIGHT'}"><c:set var="styleStr" value=""/></c:if>
									
	                           		<input type="hidden" name="unitSn" value="${item.reshItemSn}"/>
	                           		<input type="hidden" name="reshItemTypeCd" value="${item.reshItemTypeCd}"/>                            
                                    <li class="tr">
                                        <div class="td tit">${status.count}. 
                                        	<c:if test="${item.reshItemTypeCd eq 'D'}">서술형 설문 영역</c:if>
                                        	<c:if test="${item.reshItemTypeCd eq 'K'}">선택형 설문 영역</c:if>
                                        </div>
                                        <div class="td question">
                                        <c:if test="${item.reshItemTypeCd eq 'D'}">
                                        	${fn:replace(fn:replace(item.reshItemCts,"<p>",""),"</p>","")}
                                        	<textarea name="etcOption" id="etcOption" class="custom_textarea"  maxlength="1000" readonly="readonly">${item.reshAnsr}</textarea>
                                        </c:if>
                                        <c:if test="${item.reshItemTypeCd eq 'K'}">
                                        	<input type="hidden" name="etcOption" value=""/>
                                        	${fn:replace(fn:replace(item.reshItemCts,"<p>",""),"</p>","")}
                                            <div class="multi_choice_group ${styleStr }">
                                            	<c:if test="${not empty item.empl1}">
	                                                <label  class="custom_radio">
	                                                    <input type="radio" name="expl${item.reshItemSn}" id="expl${item.reshItemSn}" value="1" <c:if test="${item.reshAnsr eq '1' }"> checked</c:if> disabled="disabled" >
	                                                    <span class="checkmark"></span>
	                                                    ${item.empl1}
	                                                </label>
                                            	</c:if>
                                            	<c:if test="${not empty item.empl2}">
	                                                <label  class="custom_radio">
	                                                    <input type="radio" name="expl${item.reshItemSn}" id="expl${item.reshItemSn}" value="2" <c:if test="${item.reshAnsr eq '2' }"> checked</c:if> disabled="disabled" >
	                                                    <span class="checkmark"></span>
	                                                    ${item.empl2}
	                                                </label>
                                            	</c:if>                                            	
                                            	<c:if test="${not empty item.empl3}">
	                                                <label  class="custom_radio">
	                                                    <input type="radio" name="expl${item.reshItemSn}" id="expl${item.reshItemSn}" value="3" <c:if test="${item.reshAnsr eq '3' }"> checked</c:if> disabled="disabled" >
	                                                    <span class="checkmark"></span>
	                                                    ${item.empl3}
	                                                </label>
                                            	</c:if>                                            	
                                            	<c:if test="${not empty item.empl4}">
	                                                <label  class="custom_radio">
	                                                    <input type="radio" name="expl${item.reshItemSn}" id="expl${item.reshItemSn}" value="4" <c:if test="${item.reshAnsr eq '4' }"> checked</c:if> disabled="disabled" >
	                                                    <span class="checkmark"></span>
	                                                    ${item.empl4}
	                                                </label>
                                            	</c:if>                                              	
                                            	<c:if test="${not empty item.empl5}">
	                                                <label  class="custom_radio">
	                                                    <input type="radio" name="expl${item.reshItemSn}" id="expl${item.reshItemSn}" value="5" <c:if test="${item.reshAnsr eq '5' }"> checked</c:if> disabled="disabled" >
	                                                    <span class="checkmark"></span>
	                                                    ${item.empl5}
	                                                </label>
                                            	</c:if>                                             	
                                            	<c:if test="${not empty item.empl6}">
	                                                <label  class="custom_radio">
	                                                    <input type="radio" name="expl${item.reshItemSn}" id="expl${item.reshItemSn}" value="6" <c:if test="${item.reshAnsr eq '6' }"> checked</c:if> disabled="disabled" >
	                                                    <span class="checkmark"></span>
	                                                    ${item.empl6}
	                                                </label>
                                            	</c:if>                                             	
                                            	<c:if test="${not empty item.empl7}">
	                                                <label  class="custom_radio">
	                                                    <input type="radio" name="expl${item.reshItemSn}" id="expl${item.reshItemSn}" value="7" <c:if test="${item.reshAnsr eq '7' }"> checked</c:if> disabled="disabled" >
	                                                    <span class="checkmark"></span>
	                                                    ${item.empl7}
	                                                </label>
                                            	</c:if>                                             	
                                            	<c:if test="${not empty item.empl8}">
	                                                <label  class="custom_radio">
	                                                    <input type="radio" name="expl${item.reshItemSn}" id="expl${item.reshItemSn}" value="8" <c:if test="${item.reshAnsr eq '8' }"> checked</c:if> disabled="disabled" >
	                                                    <span class="checkmark"></span>
	                                                    ${item.empl8}
	                                                </label>
                                            	</c:if>
                                            	<c:if test="${not empty item.empl9}">
	                                                <label  class="custom_radio">
	                                                    <input type="radio" name="expl${item.reshItemSn}" id="expl${item.reshItemSn}" value="9" <c:if test="${item.reshAnsr eq '9' }"> checked</c:if> disabled="disabled" >
	                                                    <span class="checkmark"></span>
	                                                    ${item.empl9}
	                                                </label>
                                            	</c:if>                                              	
                                            	<c:if test="${not empty item.empl10}">
	                                                <label  class="custom_radio">
	                                                    <input type="radio" name="expl${item.reshItemSn}" id="expl${item.reshItemSn}" value="10" <c:if test="${item.reshAnsr eq '10' }"> checked</c:if> disabled="disabled" >
	                                                    <span class="checkmark"></span>
	                                                    ${item.empl10}
	                                                </label>
                                            	</c:if>                                             	
                                            	<c:if test="${not empty item.empl11}">
	                                                <label  class="custom_radio">
	                                                    <input type="radio" name="expl${item.reshItemSn}" id="expl${item.reshItemSn}" value="11" <c:if test="${item.reshAnsr eq '11' }"> checked</c:if> disabled="disabled" >
	                                                    <span class="checkmark"></span>
	                                                    ${item.empl11}
	                                                </label>
                                            	</c:if>
                                            	<c:if test="${not empty item.empl12}">
	                                                <label  class="custom_radio">
	                                                    <input type="radio" name="expl${item.reshItemSn}" id="expl${item.reshItemSn}" value="12" <c:if test="${item.reshAnsr eq '12' }"> checked</c:if> disabled="disabled" >
	                                                    <span class="checkmark"></span>
	                                                    ${item.empl12}
	                                                </label>
                                            	</c:if>                                            	
                                            	<c:if test="${not empty item.empl13}">
	                                                <label  class="custom_radio">
	                                                    <input type="radio" name="expl${item.reshItemSn}" id="expl${item.reshItemSn}" value="13" <c:if test="${item.reshAnsr eq '13' }"> checked</c:if>disabled="disabled"  >
	                                                    <span class="checkmark"></span>
	                                                    ${item.empl13}
	                                                </label>
                                            	</c:if>                                            	
                                            	<c:if test="${not empty item.empl14}">
	                                                <label  class="custom_radio">
	                                                    <input type="radio" name="expl${item.reshItemSn}" id="expl${item.reshItemSn}" value="14" <c:if test="${item.reshAnsr eq '14' }"> checked</c:if> disabled="disabled" >
	                                                    <span class="checkmark"></span>
	                                                    ${item.empl14}
	                                                </label>
                                            	</c:if>                                              	
                                            	<c:if test="${not empty item.empl15}">
	                                                <label  class="custom_radio">
	                                                    <input type="radio" name="expl${item.reshItemSn}" id="expl${item.reshItemSn}" value="15" <c:if test="${item.reshAnsr eq '15' }"> checked</c:if> disabled="disabled" >
	                                                    <span class="checkmark"></span>
	                                                    ${item.empl15}
	                                                </label>
                                            	</c:if>                                             	
                                            	<c:if test="${not empty item.empl16}">
	                                                <label  class="custom_radio">
	                                                    <input type="radio" name="expl${item.reshItemSn}" id="expl${item.reshItemSn}" value="16" <c:if test="${item.reshAnsr eq '16' }"> checked</c:if> disabled="disabled" >
	                                                    <span class="checkmark"></span>
	                                                    ${item.empl16}
	                                                </label>
                                            	</c:if>                                             	
                                            	<c:if test="${not empty item.empl17}">
	                                                <label  class="custom_radio">
	                                                    <input type="radio" name="expl${item.reshItemSn}" id="expl${item.reshItemSn}" value="17" <c:if test="${item.reshAnsr eq '17' }"> checked</c:if> disabled="disabled" >
	                                                    <span class="checkmark"></span>
	                                                    ${item.empl17}
	                                                </label>
                                            	</c:if>                                             	
                                            	<c:if test="${not empty item.empl18}">
	                                                <label  class="custom_radio">
	                                                    <input type="radio" name="expl${item.reshItemSn}" id="expl${item.reshItemSn}" value="18" <c:if test="${item.reshAnsr eq '18' }"> checked</c:if> disabled="disabled" >
	                                                    <span class="checkmark"></span>
	                                                    ${item.empl18}
	                                                </label>
                                            	</c:if>
                                            	<c:if test="${not empty item.empl19}">
	                                                <label  class="custom_radio">
	                                                    <input type="radio" name="expl${item.reshItemSn}" id="expl${item.reshItemSn}" value="19" <c:if test="${item.reshAnsr eq '19' }"> checked</c:if> disabled="disabled" >
	                                                    <span class="checkmark"></span>
	                                                    ${item.empl19}
	                                                </label>
                                            	</c:if>                                              	
                                            	<c:if test="${not empty item.empl20}">
	                                                <label  class="custom_radio">
	                                                    <input type="radio" name="expl${item.reshItemSn}" id="expl${item.reshItemSn}" value="20" <c:if test="${item.reshAnsr eq '20' }"> checked</c:if> disabled="disabled" >
	                                                    <span class="checkmark"></span>
	                                                    ${item.empl20}
	                                                </label>
                                            	</c:if>                                            	
                                            </div>
                                        </c:if>
                                        </div>
                                	</li>                                                            
                          		</c:forEach>                                
                                </ul>
                            </div>                            
                            <!-- // 게시판 -->
                            <div class="bottom_btn_wrap">
<!--                                 <a href="#;" class="btn md btn_turquoise" onclick="editResh()">수정</a> -->
                                <a href="javascript:history.back();" class="btn md btn_turquoise">목록</a>
                            </div>
                        </div>
                    </div>
                </div>
                
                
<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
