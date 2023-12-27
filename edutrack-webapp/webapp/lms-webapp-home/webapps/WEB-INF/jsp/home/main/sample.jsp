<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>

<%
out.println("context:"+request.getContextPath());
%>

</br>
</br>
[시험]
</br>
<input type="button" value="시험 메인[기본레이아웃]" onclick="location.href='/exam/examHome/main';">  &nbsp;
<input type="button" value="시험 메인[강의실 레이아웃]" onclick="location.href='/exam/examHome/main?tplType=LECT';"></br>
<input type="button" value="시험 상세 조회[기본레이아웃]" onclick="location.href='/exam/examHome/view';"> &nbsp;
<input type="button" value="시험 상세 조회[강의실 레이아웃]" onclick="location.href='/exam/examHome/view?tplType=LECT';"></br>
<input type="button" value="시험 응시[기본레이아웃]" onclick="location.href='/exam/examHome/addPaperForm';"> &nbsp;
<input type="button" value="시험 응시[강의실 레이아웃]" onclick="location.href='/exam/examHome/addPaperForm?tplType=LECT';"> </br>
 
</br>
</br>
 [과제]
</br>
<input type="button" value="과제 상세 조회[기본레이아웃]" onclick="location.href='/asmnt/asmntHome/view';"> &nbsp;
 <input type="button" value="과제 상세 조회[강의실 레이아웃]" onclick="location.href='/asmnt/asmntHome/view?tplType=LECT';"> </br>
 <input type="button" value="팀 과제 상세 조회[기본레이아웃]" onclick="location.href='/asmnt/asmntHome/viewTeam';">&nbsp;
 <input type="button" value="팀 과제 상세 조회[강의실 레이아웃]" onclick="location.href='/asmnt/asmntHome/viewTeam?tplType=LECT';"> </br>
 <input type="button" value="과제 제출 폼(파일)[기본레이아웃]" onclick="location.href='/asmnt/asmntHome/sendForm?type=file';">&nbsp;
 <input type="button" value="과제 제출 폼(파일)[강의실 레이아웃]" onclick="location.href='/asmnt/asmntHome/sendForm?tplType=LECT&type=file';"> </br>
 <input type="button" value="과제 제출 폼(텍스트)[기본레이아웃]" onclick="location.href='/asmnt/asmntHome/sendForm?type=text';">&nbsp;
 <input type="button" value="과제 제출 폼(텍스트)[강의실 레이아웃]" onclick="location.href='/asmnt/asmntHome/sendForm?tplType=LECT&type=text';"> </br>
 
 
</br>
</br>
 ===================================================================================================== </br>
 사이트 관리자 </br>
 ===================================================================================================== </br>

</br>
</br>
<input type="button" value="사이트관리자메인" onclick="location.href='/exam/examHome/main';">  &nbsp;  <input type="button" value="관리자메인" onclick="location.href='/home/mainMgr/main';"> </br>

 
</br>
</br>
<input type="button" value="사이트관리자로그인" onclick="location.href='/user/goMenuPage?mcd=MM00000001';">  &nbsp;   <input type="button" value="로그인" onclick="location.href='/user/userMgr/loginForm';"> 
  
  </br>

  