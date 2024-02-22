<%@	page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:url var="imageBase" value="/img/"/>

				<c:forEach items="${qnaQstnList }" var="item" varStatus="status">
	                <div class="table_list" id="qnaList">
		                    <ul class="list">
		                        <li class="head"><label>번호</label></li>
		                        <li><label>${status.count }</label></li>
		                    </ul>
		                    <ul class="list">
		                        <li class="head"><label>상태</label></li>
		                        <li>
		                        	<c:if test="${item.stsCd eq 'WAIT' }"><label class="btn3 sm solid fcViolet">답변대기</label></c:if>
									<c:if test="${item.stsCd eq 'COMP' }"><label class="btn3 sm solid fcBlack">답변완료</label></c:if>
		                        </li>
		                    </ul>
		                    <ul class="list">
		                        <li class="head"><label>제목</label></li>
		                        <li>${item.qnaTitle }</li>
		                    </ul>
		                    <ul class="list">
		                        <li class="head"><label>내용</label></li>
		                        <li>${item.qnaCts}</li>
		                    </ul>
		                    <ul class="list">
		                        <li class="head"><label>등록일</label></li>
		                        <li><meditag:dateformat type="1" delimeter="." property="${item.regDttm}" /></li>
		                    </ul>
	                </div>	                    
                </c:forEach>        
                <c:if test="${empty qnaQstnList }">
                	<div class="table_list" id="qnaList" style="border-bottom: 2px solid #222;">
						<p style="text-align: center; margin: 50px;">등록된 문의가 없습니다.</p>
					</div>
	            </c:if>          
                <br /><br />
                <!------------ 문의등록 ------------>
                <form id="lecQnaForm" name="lecQnaForm" onsubmit="return false" method="post">
				<input type="hidden" name="crsCreCd" id="crsCreCd" value="${CRSCRECD }" />
			    <input type="hidden" name="unitCd" id="unitCd" value="${param.unitCd }" />
				<input type="hidden" name="qnaCtgrCd" value="LEC" />
				<input type="hidden" name="editorYn" value="N" />
				<input type="hidden" name="lecYn" value="Y" />
				
                <div class="tstyle">
                    <ul class="dbody">
                        <li>
                            <div class="row">
                                <label for="titleInput" class="form-label col-sm-2">제목</label>
                                <div class="col-sm-10">
                                    <div class="form-row">
                                        <input class="form-control" type="text" name="qnaTitle" id="qnaTitle" value="" maxlength="100" placeholder="제목을 입력하세요">
                                    </div>             
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="row">
                                <label for="contTextarea" class="form-label col-sm-2">내용</label>
                                <div class="col-sm-10">
                                    <div class="form-row">
                                        <textarea name="qnaCts" class="form-control" id="qnaCts" rows="10" title="<spring:message code="board.title.bbs.atcl.cnts"/>"></textarea>
                                    </div>
                                </div>
                            </div>
                        </li>                           
                    </ul>
                </div>
                </form>	
			