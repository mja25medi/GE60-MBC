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
                            <li class="active"><a href="#">장바구니</a></li>
                            <li><a href="#">결제내역</a></li>  
                            <li><a href="#">문의내역</a></li> 
                            <li><a href="#">받은쪽지</a></li>    
                            <li><a href="#">개인정보 수정</a></li>  
                            <li><a href="#">아바타 편집</a></li>      
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">장바구니</h3>
                       
                    <div class="res_tbl_wrap">
                        <table>
                            <caption>장바구니 목록</caption>
                            <thead>
                                <tr>
                                    <th scope="col">과정명</th>
                                    <th scope="col" width="10%">수량</th>
                                    <th scope="col" width="10%">가격</th>
                                    <th scope="col" width="10%">삭제</th>
                                    <th scope="col" width="10%">주문</th>
                                    <th scope="col" width="10%">
                                        <span class="custom-input onlychk"><input type="checkbox" id="chkall"><label for="chkall"></label></span>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td scope="row" class="title" data-label="과정명">데이터 분석을 위한 파이썬 시작하기</td>
                                    <td class="m_hidden" data-label="수량">1개</td>
                                    <td data-label="가격">300,000원</td>
                                    <td data-label="삭제">
                                        <button class="btn type3">삭제</button>
                                    </td>
                                    <td data-label="주문">
                                        <button class="btn type6">바로결제</button>
                                    </td>
                                    <td data-label="선택"><span class="custom-input onlychk"><input type="checkbox" id="chk1"><label for="chk1"></label></span></td>
                                </tr>
                                <tr>
                                    <td scope="row" class="title" data-label="과정명">데이터 분석을 위한 파이썬 시작하기</td>
                                    <td class="m_hidden" data-label="수량">1개</td>
                                    <td data-label="가격">300,000원</td>
                                    <td data-label="삭제">
                                        <button class="btn type3">삭제</button>
                                    </td>
                                    <td data-label="주문">
                                        <button class="btn type6">바로결제</button>
                                    </td>
                                    <td data-label="선택"><span class="custom-input onlychk"><input type="checkbox" id="chk2"><label for="chk2"></label></span></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <div class="basket-total-price">
                        <div class="order_price_area">
                            <div class="tit_area">주문금액</div>
                            <div class="price_area">
                                <span>90,000원</span>
                            </div>
                        </div>
                        <div class="estimated_price_area">
                            <div class="tit_area">할인금액</div>
                            <div class="price_area">
                                <span class="fcBlue">50,000원</span>
                            </div>
                        </div>
                        <div class="total_price_area">
                            <div class="tit_area">총 결제금액</div>
                            <div class="price_area">
                                <span class="fcBlack">400,000원</span>
                            </div>
                        </div>
                    </div>

                    <div class="btns justify mt20">
                        <div class="left"><button class="btn gray2">강좌 더보기</button></div>
                        <div class="right">
                            <button class="btn">선택결제</button>
                            <button class="btn type4">전체결제</button>
                        </div>
                    </div>

                </div>
                <!-- //content -->
            </div>
            <!-- //contentWrap -->
              

        </main>

        <%@ include file="../inc/hrd_footer.jsp" %>
       
    </div>
</body>

</html>