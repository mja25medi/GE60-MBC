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
                                    <li>수강신청</li>
                                    <li><span class="current">원격훈련과정</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">수강신청</h4>
                    </div>
                    <!-- //h1_area --> 

                    <div class="subMenu">
                        <ul class="menu">
                            <li class="active"><a href="#">원격훈련과정</a></li>
                            <li><a href="#">집체훈련과정</a></li>  
                            <li><a href="#">혼합훈련과정</a></li>        
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">수강신청결제</h3>   
  
                    
                    <div class="course_pay">
                        <div class="agree_course">
                            <div class="terms_area">
                                <h4 class="seg_title">강의계획서 확인/동의</h4>   
                                <ul class="tabs mb0" id="courseInfoTab">                                    
                                    <li class="active"><a href="#0">빅데이터 분석</a></li>
                                    <li><a href="#0">융합서비스개발자과정</a></li>                                    
                                </ul>
                                <div class="box terms rect" id="courseInfoArea">
                                    <div class="table_list">
                                        <ul class="list">
                                            <li class="head"><label>과정명</label></li>
                                            <li>빅데이터 분석</li>
                                            <li class="head"><label>교육구분</label></li>
                                            <li>국비지원</li>
                                        </ul>
                                        <ul class="list">
                                            <li class="head"><label>과목수</label></li>
                                            <li>5과목</li>
                                            <li class="head"><label>결제금액</label></li>
                                            <li>무료</li>
                                        </ul>
                                        <ul class="list">
                                            <li class="head"><label>과정소개</label></li>
                                            <li>"파이썬을 이용한 머신러닝, 딥러닝 실전 개발 입문(위키북스)" 서적을 기반으로 하는 강좌입니다. </li>
                                        </ul>
                                        <ul class="list">
                                            <li class="head"><label>학습목표</label></li>
                                            <li>본 과정은 머신러닝을 위한 데이터 수집 및 분석 과정을 파이썬을 이용하여 처리하는 방법을 쉽게 익힐 수 있도록 다양한 예제를 활용한 강좌입니다.<br>
                                                본 과정을 통해 인공지능 프로그래밍과 머신러닝을 위한 데이터 처리에 대한 이해를 돕는 계기가 될 것으로 기대합니다.<br>
                                                웹 프로그래밍과 인공 지능 프로그래밍에 대한 중급 수준의 과정으로 이론, 실습, 동영상 강의로 구성되어 코딩 초보자도 무리 없이 완주하실 수 있습니다.
                                             </li>
                                        </ul>                                        
                                    </div>

                                </div>
                                <div class="terms_agree txt-left">
                                    <span class="custom-input">
                                        <input type="checkbox" name="courseAgreeYn" id="courseAgreeY" value="Y">
                                        <label for="courseAgreeY">위 강의계획서를 과정별로 확인하였으며 동의합니다.</label>
                                    </span>
                                </div>
                            </div>
                        </div>

                        <div class="agree_pay">
                            <div class="terms_area">
                                <h4 class="seg_title">환불규정 확인/동의</h4>  
                                <div class="box terms rect" id="refundRuleInfoArea">
                                    교육비 반환 및 재화의 환급 및 반품, 교환 관련 규정 <br>
                1. “원격교육시설”은 이용자가 구매 신청한 재화 또는 용역이 품절 등의 사유로 재화의 인도 또는 용역을 제공할 수 없을 때에는 지체없이 그 사유를 이용자에게 통지합니다. <br>
                2. “원격교육시설”은 이용자의 재화 결제금 및 입금액의 과오금이 발생한 경우 그 사실을 인지한 날로부터 5일(영업일 기준)이내에 이용자에게 통지하고 환급하거나 환급에 필요한 조치를 취합니다. <br>
                3. 다음 각호의 경우에는 “원격교육시설”은 배송된 재화일 지라도 재화를 반품받은 다음 5일(영업일 기준)이내에 이용자의 요구에 따라 즉시 환불 또는 교환 조치를 합니다. 단, 이용자의 그 반품 및 교환조치의 요구기간은 배송된 날로부터 14일 이내로 합니다. <br>
                (1).배송된 재화가 주문내용과 상이하거나 “원격교육시설”이 제공하는 “서비스”에서 게시된 정보와 상이할 경우 <br>
                (2).디지털 콘텐츠(동영상 등)의 구매에 관한 계약을 체결한 이용자는 디지털 콘텐츠를 공급받은 후 7일 이내에는 구매계약의 철회, 변경을 할 수 있다. <br>
                4. 디지털 콘텐츠(동영상 등)의 이용자가 이용을 계속할 수 없는 경우, 또는 “원격교육시설”의 폐쇄 등으로 교습을 계속할 수 없는 경우 학습비 반환에 대해서는 평생교육법 제28조 및 동 시행령 제28조 제2항에 따라 다음의 각호와 같이 정합니다. <br>
                (1).“원격교육시설”의 설치·운영자가 교습을 할 수 없게 된 경우, 반환사유 발생일은 교습을 할 수 없게 된 날이며 반환금액은 이미 낸 학습비를 일할 계산한 금액을 반환합니다. <br>
                (2).이용자가 할인에 의해 학습비를 결제하였을 경우, 학습비 반환금액의 기준은 할인이 적용되어 실제 결제한 금액에 의하여 정합니다. <br>
                (3).“원격교육시설”은 학습비 반환사유가 발생한 날부터 5일(영업일 기준) 이내에 반환하여야 한다. 5.“원격교육시설”과 이용자가 체결한 구매계약에 의해서 제공되는 내역이 디지털 콘텐츠(동영상 등)의 학습비와 재화(도서 교재 등)가 포함되었을 경우, 각각 구분하여 학습비 반환 및 환급 기준을 적용합니다.
                                </div> 
                                <div class="terms_agree txt-left">
                                    <span class="custom-input">
                                        <input type="checkbox" name="refundAgreeYn" id="refundAgreeY" value="Y">
                                        <label for="refundAgreeY">위 환불규정을 확인하였으며 동의합니다.</label>
                                    </span>
                                </div>
                            </div>
                        </div>

                        <div class="res_tbl_wrap">
                            <table>
                                <caption>장바구니 목록</caption>
                                <thead>
                                    <tr>
                                        <th scope="col" width="25%">교육구분</th>
                                        <th scope="col">과정명</th>
                                        <th scope="col" width="25%">가격</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td class="m_hidden" data-label="교육구분">국비지원</td>
                                        <td scope="row" class="title" data-label="과정명">데이터 분석을 위한 파이썬 시작하기</td>
                                        <td data-label="가격" c>300,000원</td>                                       
                                    </tr>
                                    <tr>
                                        <td class="m_hidden" data-label="교육구분">국비지원</td>
                                        <td scope="row" class="title" data-label="과정명">데이터 분석을 위한 파이썬 시작하기</td>
                                        <td data-label="가격">300,000원</td>                                       
                                    </tr>                                    
                                </tbody>
                            </table>
                            <div class="total_price">
                                <div class="price">
                                    <strong>총 결제금액 : <span class="fcBlue">600,000원</span></strong>
                                </div>
                            </div>
                        </div>

                        <div class="btns mt30">
                            <button type="button" class="btn type4">결제하기</button>
                            <button type="button" class="btn type2">취소</button>
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