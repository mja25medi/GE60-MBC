<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="returnAssignmentVO" value="${returnAssignmentVO}"/>
<c:set var="assignmentSubVO" value="${assignmentSubVO}"/>
<c:set var="studentVO" value="${studentVO}"/>
    <div class="coding_wrap">
        <div class="coding_header">
            <h1>${returnAssignmentVO.asmtTitle }</h1>
            <div class="right_util">
                <div class="request pair_ing"><i class="xi-catched" style="font-size: 2.2rem;"></i>${studentVO.userNm } 학생과 코딩 학습 공유중 </div>
                <button type="button" class="btn" onclick="self.close()">종료</button>
            </div>
        </div>
        <div class="panel-container coding-test prof_mo">
            <div class="panel-left">
                <div class="test_field">
                    <button type="button" class="indent_btn prof_c"></button>
                    <ul class="left_number">
                       <li></li>
                    </ul>
                    <div class="right_content">
                        <div class="board_top">
                            <h4>${assignmentSubVO.asmtTitle }</h4>
                        </div>
                        <div class="cont">
                            <label class="h_title">문제 설명</label>
                            <div class="mb20">${assignmentSubVO.asmtCts}</div>
                            <label class="h_title">제한사항</label>
                            <div class="mb20">
                            	<c:forEach items="${listSubConst}" var="listSubConst" varStatus="status">
                            	 테스트케이스${status.count }. ${listSubConst.constCts} <br/>
                            	</c:forEach>
                            </div>
                        </div>
                        
                    </div>
                </div>
            </div>
            <div class="splitter"></div>
            <div class="panel-right2">
                <div class="panel-top">
                    <iframe src="${studentVO.ideUrl }" style="height: 100%;"></iframe>
                </div>
                
            </div>
        </div>
    </div>
    <script>
         $(".panel-left").resizable({
             handleSelector: ".splitter",
             resizeHeight: false
         });
         
    </script>

    <!-- modal popup 보여주기 버튼(개발시 팝업id 삭제) -->
<!--     <button class="modal__btn" id="popup1" style="width: 100%;">채점결과 팝업</button>
    <script type="text/javascript">
        $("#popup1").click(function() {
            $('body').css("overflow", "hidden");
            $("#coding_test_result").css('display','flex').hide().fadeIn();
            iFrameResize({ scrolling: true }, '#iframe1')
        })
    </script>
    modal popup
    <div id="coding_test_result" class="modal_popup_wrap">                             
        <div class="modal_popup modal_medium">
            <h4 class="modal_title">채점결과</h4>
            <iframe id="iframe1" src="iframe_coding_test_result.jsp"></iframe>
            <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
        </div>
    </div> -->