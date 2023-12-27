<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
    <%@ include file="../inc/home_common.jsp" %>
    <link rel="stylesheet" href="../css/sub.css"><!-- sub 페이지에서 사용 -->
<body>
    
    <div id="wrap">
        <%@ include file="../inc/hrd_header.jsp" %>

        <main class="main">
            <div id="contentWrap" class="container">

                <!-- content -->
                <div id="content" class="content">
                    <div class="h1_area">
                        <div class="location_bar">
                            <nav class="location">
                                <ul>
                                    <li><i class="xi-home-o" aria-hidden="true"></i><span class="sr-only">Home</span></li>
                                    <li>지원센터</li>
                                    <li><span class="current">공지사항</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">지원센터</h4>
                    </div>
                    <!-- //h1_area --> 

                    <div class="subMenu">
                        <ul class="menu">
                            <li class="active"><a href="#">공지사항</a></li>
                            <li><a href="#">학습자료실</a></li>  
                            <li><a href="#">1:1 문의하기</a></li> 
                            <li><a href="#">FAQ</a></li>        
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">공지사항</h3>
                    
                    <div class="tstyle_view">
                        <div class="title">개인정보처리방침 변경 안내</div>
                        <ul class="head">
                            <li class="write"><strong>작성자</strong><span>관리자</span></li>
                            <li class="date"><strong>작성일시</strong><span>2023.10.28 15:47</span></li>
                            <li class="hit"><strong>조회수</strong><span>5</span></li>
                        </ul>
                        <div class="tb_contents">
                        안녕하세요!<br>
                        찾아주신 수강생분들께 진심으로 감사드립니다.<br><br>

                        개인정보처리방침이 변경되어 안내드립니다.<br><br>


                        변경된 개인정보처리방침은 2023년 08월 21일부터 사전 고지 후 2023년 08월 28일부터 효력이 발생합니다.<br><br>

                        - 시행예정일 : 2023년 08월 28일<br>
                        - 변경내용 : 하단 각각 링크를 통해 현행 방침과 개정 방침 내용 확인 가능<br><br>


                        ▷Link : 개인정보처리방침 '현행안' 전문 보기<br><br>

                        2023년 07월 04일 부터 시행 될 주요 개정내용은 하단 상세링크를 통해 확인 가능합니다.<br><br>

                        ▷ Link : 개인정보처리방침 '개정안' 전문 보기<br><br>

                        ※ 회원 여러분께서 본 공지일로부터 30일 내에 별도 의사표시를 하지 않는 경우, 변경 후 개인정보처리방침에 동의하신 것으로 간주합니다.<br>
                        변경 후 약관의 적용에 동의하지 않는 회원은 이용계약을 해지하고 회원 탈퇴를 요청할 수 있습니다.<br><br>

                        회원 여러분께 더 좋은 서비스로 보답하겠습니다.<br>
                        감사합니다.<br><br>
                           
                        </div>
                        <div class="add_file_list">
                            <strong class="title">첨부파일</strong>
                            <ul class="add_file">
                                <li>                    
                                    <a href="#" class="file_down">
                                        <img src="../../_img/board/file_doc.png" alt="">
                                        <span class="text">첨부파일명마우스오버 시.doc</span>
                                        <span class="fileSize">(6KB)</span>
                                    </a>                    
                                    <span class="link">
                                        <a class="btn-line btn-down" href="#"
                                        title="154873973477000.png 다운로드">다운로드<i></i></a>
                                    </span>
                                </li>
                                <li>
                                    <a href="#" class="file_down">
                                        <img src="../../_img/board/file_img.png" alt="">
                                        <span class="text">154873973477000.jpg</span>
                                        <span class="fileSize">(6KB)</span>
                                    </a>
                                    <span class="link">
                                        <a class="btn-line btn-down" href="#"
                                        title="154873973477000.png 다운로드">다운로드<i></i></a>
                                    </span>
                                </li>
                            </ul>
                        </div>

                        <div class="answer">
                            <div class="title_area">
                                <strong class="title">답변입니다.</strong>
                                <span class="date"><b>관리자</b><em>2023.10.28</em></span>
                            </div>
                            <div class="cont">이 문제는 다른 문제에 비해서는 많이 어려운 편에 속하는 문제이니, 풀지 못했다고 해도 크게 상심하실 필요가 없습니다. <br>
                                답변드린 내용 중 더 궁금하신 점 있으시다면 언제든 편하게 질문주세요! 감사합니다</div>
                        </div>
                        

                    </div>

                    <div class="btn_area">
                        <button type="button" class="btn gray2">목록</button>
                    </div>

                    <ul class="list_board">
                        <li><span>이전글</span><a
                        href="#">이전글이 없습니다.</a></li>
                        <li><span>다음글</span><a
                            href="#">다음글의 제목이 출력됩니다.</a></li>
                    </ul>   

                    
                </div>
                <!-- //content -->
            </div>
            <!-- //contentWrap -->
              

        </main>

        <%@ include file="../inc/hrd_footer.jsp" %>
       
    </div>
</body>

</html>