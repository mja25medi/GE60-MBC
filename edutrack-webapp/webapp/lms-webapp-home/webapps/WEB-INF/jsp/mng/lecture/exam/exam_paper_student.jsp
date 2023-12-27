<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/mng/common/page_init.jsp" %>
<c:url var="img_base" value="/img/home" />
<div class="wrap" style="width:840px;">
	<div class="contents">
		<form id="examForm" name="examForm" onsubmit="return false" action="/LectureExamAdmin.do">
		<input type="hidden" name="cmd"/>
		<ul class="question">
			<br><br>
			<c:forEach items="${questionList}" var="item">
        		<c:if test="${item.qstnType eq 'K'}">
            	<li>
            		<span style="font-weight:bold; font-size:16px;">
            		<p class="first">${item.qstnNo}. ${item.title} (${item.qstnTypeNm} : ${item.qstnScore}점)</p>
            		</span>
            		<font size=2>
            		${item.qstnCts}
            		</font>
                	<ul>
                    <font size=2>	<c:if test="${not empty item.empl1}"><li><input type="radio" name="ans_${item.qstnNo}" id="ans_${item.qstnNo}"  value="1"/> 1. ${item.empl1}</li></c:if></font>
                    <font size=2>   <c:if test="${not empty item.empl2}"><li><input type="radio" name="ans_${item.qstnNo}" id="ans_${item.qstnNo}"  value="2"/> 2. ${item.empl2}</li></c:if></font>
                    <font size=2>   <c:if test="${not empty item.empl3}"><li><input type="radio" name="ans_${item.qstnNo}" id="ans_${item.qstnNo}"  value="3"/> 3. ${item.empl3}</li></c:if></font>
                    <font size=2>   <c:if test="${not empty item.empl4}"><li><input type="radio" name="ans_${item.qstnNo}" id="ans_${item.qstnNo}"  value="4"/> 4. ${item.empl4}</li></c:if></font>
                    <font size=2>   <c:if test="${not empty item.empl5}"><li><input type="radio" name="ans_${item.qstnNo}" id="ans_${item.qstnNo}"  value="5"/> 5. ${item.empl5}</li></c:if></font>
					<br><br>
                    </ul>
                </li>
                <br/>
                </c:if>
                <c:if test="${item.qstnType eq 'S'}">
                <li>
                	<span style="font-weight:bold; font-size:16px;">
                	<p class="first">${item.qstnNo}. ${item.title} (${item.qstnTypeNm} : ${item.qstnScore}점)</p>
            		</span>
            		<font size=2>
                	${item.qstnCts}
            		</font>
                	<div class="check_box">
					<font size=2>	<input type="radio" name="ans_${item.qstnNo}" id="ans_${item.qstnNo}" value="O"/><label for="o">O</label></h3></font>
					<font size=2>	<input type="radio" name="ans_${item.qstnNo}" id="ans_${item.qstnNo}" value="X"/><label for="x">X</label></h3></font>
					<br><br>
					</div>
                </li>
                <br/>
                </c:if>
                <c:if test="${item.qstnType eq 'D'}">
                <li>
            		<span style="font-weight:bold; font-size:16px;">
                	<p class="first">${item.qstnNo}. ${item.title} (${item.qstnTypeNm} : ${item.qstnScore}점)</p>
            		</span>
            		<font size=2>
                	${item.qstnCts}
            		</font>
                    <font size=2> <input type="text" name="ans_${item.qstnNo}" id="ans_${item.qstnNo}" style="width:780px;" class="text"/></h3></font>
                    <br><br>
                </li>
                <br/>
                </c:if>
                <c:if test="${item.qstnType eq 'J'}">
                <li>
            		<span style="font-weight:bold; font-size:16px;">
                	<p class="first">${item.qstnNo}. ${item.title} (${item.qstnTypeNm} : ${item.qstnScore}점)</p>
            		</span>
            		<font size=2>
                	${item.qstnCts}
            		</font>
                	<font size=2> <textarea name="ans_${item.qstnNo}" id="ans_${item.qstnNo}" cols="1" rows="5" style="width:780px;" ></textarea></h3></font>
                	<br><br>
                </li>
                <br/>
                </c:if>
             </c:forEach>
		</ul>
		
		</form>
	</div>
</div>
