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
                                    <li>마이 페이지</li>
                                    <li><span class="current">장바구니</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">마이 페이지</h4>
                    </div>
                    <!-- //h1_area --> 
                    
                    <div class="subMenu">
                        <ul class="menu">
                            <li><a href="#">장바구니</a></li>
                            <li class="active"><a href="#">결제내역</a></li>  
                            <li><a href="#">문의내역</a></li> 
                            <li><a href="#">받은쪽지</a></li>    
                            <li><a href="#">개인정보 수정</a></li>  
                            <li><a href="#">아바타 편집</a></li>      
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <div class="board_info m_column_row">
                        <h3 class="subMenu_title">결제내역</h3>
                        <div class="page_btn flex-none">
                            <button type="button" class="btn">환불안내</button>
                        </div>
                    </div>

                    <div class="res_tbl_wrap">
                        <table>
                            <caption>결제내역 목록</caption>
                            <thead>
                                <tr>
                                    <th scope="col" width="14%">기수명</th>
                                    <th scope="col">과정명</th>
                                    <th scope="col" width="10%">과정가격</th>
                                    <th scope="col" width="10%">결제상태</th>
                                    <th scope="col" width="8%">결제방법</th>
                                    <th scope="col" width="10%">총금액</th>
                                    <th scope="col" width="10%">신청일</th>
                                    <th scope="col" width="12%">기타</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td scope="row" data-label="기수명">2023년 1기</td>
                                    <td scope="row" class="title" data-label="과정명">데이터 분석을 위한 파이썬 시작하기</td>
                                    <td data-label="과정가격">100,000원</td>
                                    <td data-label="결제상태">
                                        결제완료
                                    </td>
                                    <td data-label="결제방법">계좌 이체</td>
                                    <td data-label="총금액">100,000원</td>
                                    <td data-label="신청일">2023.10.28</td>
                                    <td data-label="기타"></td>
                                </tr>
                                <tr>
                                    <td scope="row" data-label="기수명">2023년 1기</td>
                                    <td scope="row" class="title" data-label="과정명">데이터 분석을 위한 파이썬 시작하기</td>
                                    <td data-label="과정가격">100,000원</td>
                                    <td data-label="결제상태">
                                        결제완료
                                        <div class="btn_pay">
                                            <button class="btn solid dark">결제취소</button>
                                            <button class="btn solid blue">환불신청</button>
                                        </div>
                                    </td>
                                    <td data-label="결제방법">무통장입금</td>
                                    <td data-label="총금액">100,000원</td>
                                    <td data-label="신청일">2023.10.28</td>
                                    <td data-label="기타">

                                        <div class="txt-left sm">
                                            입금은행 : 신한(통합)은행<br>
                                            입금자명 : 학습자1<br>
                                            입금계좌 : 56211111602750<br>
                                            입금만료 : 09/22까지
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td scope="row" data-label="기수명">2023년 1기</td>
                                    <td scope="row" class="title" data-label="과정명">데이터 분석을 위한 파이썬 시작하기</td>
                                    <td data-label="과정가격">100,000원</td>
                                    <td data-label="결제상태">
                                        결제취소
                                    </td>
                                    <td data-label="결제방법">카드 결제</td>
                                    <td data-label="총금액">100,000원</td>
                                    <td data-label="신청일">2023.10.28</td>
                                    <td data-label="기타"></td>
                                </tr>
                                <tr>
                                    <td scope="row" data-label="기수명">2023년 1기</td>
                                    <td scope="row" class="title" data-label="과정명">데이터 분석을 위한 파이썬 시작하기</td>
                                    <td data-label="과정가격">100,000원</td>
                                    <td data-label="결제상태">
                                        결제취소
                                    </td>
                                    <td data-label="결제방법">카드 결제</td>
                                    <td data-label="총금액">100,000원</td>
                                    <td data-label="신청일">2023.10.28</td>
                                    <td data-label="기타"></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>


                </div>
                <!-- //content -->
            </div>
            <!-- //contentWrap -->
              

        </main>

        <!-- modal popup 보여주기 버튼(개발시 팝업id 삭제) -->
        <button class="modal__btn" id="popup1">환불안내 팝업</button>
        <script type="text/javascript">
            $("#popup1").click(function() {
                $('body').css("overflow", "hidden");
                $("#refund_info").css('display','flex').hide().fadeIn();
                iFrameResize({ scrolling: true }, '#iframe1')
            })
        </script>
        <!-- modal popup -->
        <div id="refund_info" class="modal_popup_wrap">                             
            <div class="modal_popup modal_medium">
                <h4 class="modal_title">환불안내</h4>
                <iframe id="iframe1" src="iframe_refund_info.jsp"></iframe>
                <button type="button" class="modal_close"><i class="xi-close-thin"></i><span class="sr-only">팝업닫기</span></button>
            </div>
        </div>
        <!-- //modal popup -->

        

        <%@ include file="../inc/hrd_footer.jsp" %>
       
    </div>
</body>

</html>