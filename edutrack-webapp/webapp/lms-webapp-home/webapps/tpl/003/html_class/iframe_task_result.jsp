<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../inc/class_common.jsp" %>
<script src="../js/iframeResizer.contentWindow.min.js"></script>

<body class="scroll_custom">
    <div class="modal_cont task_area">
        
        <div class="row">
            <div class="col-sm-5">
                <div class="table_list">
                    <ul class="list">
                        <li class="head"><label>제출일자</label></li>
                        <li>2023.11.11 00:00</li>
                        <li class="head"><label>제출횟수</label></li>
                        <li>10회</li>
                    </ul>
                    <ul class="list">
                        <li class="head"><label>첨언</label></li>
                        <li>
                            문제 유형을 먼저 파악하시고 과제를 푸시면 도움 됩니다.
                        </li>
                    </ul>
                    <ul class="list">
                        <li class="head"><label>점수</label></li>
                        <li>100점</li>
                    </ul>                        
                </div>
                <div class="txt-right mt20">
                    <button class="btn type4">평가완료</button>
                    <button class="btn type2">닫기</button>
                </div>

                <div class="board_top">
                    <h4>과제정보</h4>
                </div>
                <div class="table_list">                      
                    <ul class="list">
                        <li class="head"><label>과제제목</label></li>
                        <li>코딩 과제입니다.</li>
                    </ul>
                    <ul class="list">
                        <li class="head"><label>첨부파일</label></li>
                        <li>
                            <ul class="add_file">
                                <li>                    
                                    <a href="#" class="file_down">
                                        <img src="../../_img/board/file_doc.png" alt="">
                                        <span class="text">첨부파일명마우스오버 시.doc</span>
                                        <span class="fileSize">(6KB)</span>
                                    </a>                    
                                    <span class="link">
                                        <a class="btn-line btn-down" href="#" title="154873973477000.png 다운로드">다운로드<i></i></a>
                                    </span>
                                </li>
                                <li>
                                    <a href="#" class="file_down">
                                        <img src="../../_img/board/file_img.png" alt="">
                                        <span class="text">154873973477000.jpg</span>
                                        <span class="fileSize">(6KB)</span>
                                    </a>
                                    <span class="link">
                                        <a class="btn-line btn-down" href="#" title="154873973477000.png 다운로드">다운로드<i></i></a>
                                    </span>
                                </li>
                            </ul>
                        </li>
                    </ul>                    
                    <ul class="list">
                        <li class="head"><label>과제 내용</label></li>
                        <li>주어진 조건을 이용하여 함수를 만들어보세요.</li>
                    </ul>
                </div>

            </div>
            <div class="col-sm-7">
                <div class="segment task_pop">
                    <div class="test_field">
                        <ul class="left_number">
                            <li><a href="#0">1번 문제</a></li>
                            <li><a href="#0">2번 문제</a></li>
                            <li class="on"><a href="#0">3번 문제</a></li>
                            <li><a href="#0">4번 문제</a></li>
                            <li><a href="#0">5번 문제</a></li>
                        </ul>
                        <div class="right_content">
                            <div class="table_list">                      
                                <ul class="list">
                                    <li class="head"><label>문제제목</label></li>
                                    <li>java 코딩 문제 입니다. </li>
                                </ul>  
                                <ul class="list">
                                    <li class="head"><label>언어</label></li>
                                    <li>java</li>
                                    <li class="head"><label>난이도</label></li>
                                    <li>하</li>
                                </ul>                                           
                                <ul class="list">
                                    <li class="head"><label>문제 내용</label></li>
                                    <li>
                                        <div class="task_question">
                                        주어진 정수 n이 홀수인지 짝수인지 확인하는 프로그램을 작성하시오. 
                                        주어진 정수 n이 홀수인지 짝수인지 확인하는 프로그램을 작성하시오. 
                                        주어진 정수 n이 홀수인지 짝수인지 확인하는 프로그램을 작성하시오.
                                        </div>    
                                        <a href="#0" class="btn btn_more_que"> <i class="xi-angle-left-min"></i></a> 
                                    </li>
                                </ul>                               
                               
                            </div>
                            <div class="board_top">
                                <h4>채점결과</h4>
                            </div>
                            <div class="cont">
                                <label class="h_title fcBlue">문제 설명</label>
                                <div class="task_txt">주어진 정수 n이 홀수인지 짝수인지 확인하는 프로그램을 작성하시오</div>                                
                            </div>
                            <div class="answer">
                                <label for="contTextarea" class="h_title fcBlue">제출한 코드</label>
                                <div class="form-row task_txt">
                                    <pre>
class Solution {
    public int[] solution(int[] num_list) {
        int[] result = new int[2];
        for(int num : num_list){
            int res = (num%2==0) ? ++result[0] : ++result[1]; 
        }
        return result;
    }
}
                                    </pre>
                                </div>                               
                            </div>
                            <div class="cont">
                                <label class="h_title fcBlue">채점 결과</label>
                                <div class="task_txt">
                                    <span class="fcDarkblue">테스트케이스1 : 성공</span><br>
                                    이유 :  n변수에 13 정수값이 성공적으로 대입되었습니다.<br>
                                    <span class="fcDarkblue">테스트케이스2: 성공</span><br>
                                    이유 :  if~else 문을 사용하여 코드를 작성하기<br>
                                    <span class="fcDarkblue">테스트케이스3: 성공</span><br>
                                    이유 :  조건문으로 n을 2로 나눈 나머지가 0인지 아닌지로 판단하기
                                </div>                                
                            </div>
                        </div>
                    </div>
                </div>

                <div class="scroll_img_w">
                    <div class="scroll_img">
                        <p class="txt">가로로 스와이프하여 <br>내용을 확인해주세요</p>
                    </div>
                </div>
                
            </div>
        </div>
    </div>
    
</body>
</html>