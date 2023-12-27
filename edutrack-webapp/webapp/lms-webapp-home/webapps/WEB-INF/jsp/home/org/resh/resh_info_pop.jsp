<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
    <div class="modal_cont">
        <div class="table_list">
            <ul class="list">
                <li class="head"><label>설문명</label></li>
                <li>${vo.reshTitle }</li>
            </ul>
            <ul class="list">
                <li class="head"><label>설문기간</label></li>
                <li><meditag:dateformat type="8" delimeter="." property="${vo.startDttm}"/>~<meditag:dateformat type="8" delimeter="." property="${vo.endDttm}"/></li>
            </ul>
           
            <ul class="list">
                <li class="head"><label>설문내용</label></li>
                <li>
                   ${vo.reshCts }

                </li>
            </ul>
        </div>
    </div>
    <div class="modal_btns">        
        <button type="button" class="btn type2" onclick="parent.modalBoxClose()">닫기</button>
    </div>
        