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
                                    <li>교육안내</li>
                                    <li><span class="current">훈련과정</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">교육안내</h4>
                    </div>
                    <!-- //h1_area --> 
                    
                    <div class="subMenu">
                        <ul class="menu">
                            <li class="active"><a href="#">훈련과정</a></li>
                            <li><a href="#">고용보험 환급제도</a></li>  
                            <li><a href="#">훈련비 환급방법</a></li> 
                            <li><a href="#">훈련 진행절차</a></li>   
                            <li><a href="#">훈련진행 유의사항</a></li> 
                            <li><a href="#">모사답안 처리기준</a></li>      
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">훈련과정</h3>

                    <ul class="tabs mb20 four_list">
                        <li><a href="#" title="비활성 탭메뉴">지원제도안내</a></li>
                        <li class="active"><a href="#" title="선택된 탭메뉴">K-디지털 트레이닝 과정</a></li>
                        <li><a href="#" title="비활성 탭메뉴">K-디지털 기초역량 훈련과정</a></li>
                        <li><a href="#" title="비활성 탭메뉴">사업주 직업능력개발훈련</a></li>
                    </ul>
                    
                    <div class="training_area box">
                        <dl class="guide_info">
                            <div>
                                <dt>
                                    K-Digital Training이란?
                                </dt>
                                <dd>
                                    <ul class="info_list mb10">
                                        <li>K-Digital Training이란, 디지털 분야의 인재를 양성하기 위한 교육으로 (재)대구디지털혁신진흥원에서 2021년부터 시행하고 있습니다.</li>
                                        <li>국민내일배움카드 발급 시 전액 국비 지원 교육이 가능합니다.</li>
                                    </ul>
                                </dd>
                            </div>
                            <div>
                                <dt>
                                    수강대상
                                </dt>
                                <dd>
                                    <ul class="list-dot">
                                        <li>빅데이터, 인공지능 분야에 대한 취업 의지가 있는분(비전공자 포함)</li>
                                        <li>졸업예정자, 구직자</li>
                                    </ul>
                                </dd>
                            </div>
                            <div>
                                <dt>
                                    교육비용
                                </dt>
                                <dd>
                                    <ul class="list-dot">
                                        <li>국민내일배움카드 발급 시 전액 국비지원</li>
                                    </ul>
                                </dd>
                            </div>
                            <div>
                                <dt>
                                    교육분야
                                </dt>
                                <dd>
                                    <ul class="list-type">
                                        <li><span class="icon_type"><strong class="fcBlue">인공지능</strong></span></li>
                                        <li><span class="icon_type"><strong class="fcBlue">빅데이터</strong></span></li>
                                    </ul>
                                </dd>
                            </div>
                            <div>
                                <dt>
                                    직업훈련 참여절차
                                </dt>
                                <dd>
                                    <ol class="curriculum">
                                        <li class="step">
                                            <div class="circle type1">
                                                <b>01</b>
                                                <span>국민내일<br>배움카드 발급</span>
                                            </div>
                                            <ul class="default_list">
                                                <li>국민내일배움카드 발급(택 1)</li>
                                                <li>- 온라인 신청: HRD-net 홈페이지 회원가입 후 발급신청</li>
                                                <li>- 오프라인 신청: 관할 고용센터로 문의 후 방문신청</li>
                                                <li>※ 체크카드로 신청 시, 일주일 내에 수령 가능</li>
                                            </ul>
                                        </li>
                                        <li class="step">
                                            <div class="circle type2">
                                                <b>02</b>
                                                <span>훈련과정<br>수강신청</span>
                                            </div>
                                            <ul class="default_list">
                                                <li>카드수령 후 해당 훈련 수강가능</li>                                              
                                            </ul>
                                        </li>
                                        <li class="step">
                                            <div class="circle type3">
                                                <b>03</b>
                                                <span>HRD-Net<br>수강신청</span>
                                            </div>
                                            <ul class="default_list">
                                                <li>HRD-net에서 대구디지털산업진흥원 검색</li>
                                                <li>인공지능 또는 빅데이터 교육신청</li>
                                            </ul>
                                        </li>
                                    </ol>

                                    <div class="gray_box">
                                        <span class="fcRed">※ 국민내일배움카드 신청 주의사항</span>
                                        <ul class="info_list">
                                            <li>체크카드 등 발급 방문 때, 은행 안내사항 준수 또는 발급확인서 지참 필수.</li>
                                            <li>발급확인서: HRD-net에서 출력 또는 고용센터 방문 후 수령가능</li>
                                            <li>이외 카드 관련 문의사항: 고용노동부 1350</li>
                                        </ul>
                                    </div>

                                </dd>
                            </div>
                        </dl>
                        <div class="btns">
                            <a href="https://www.hrd.go.kr/" target="_blank" class="btn type4">HRD-Net 바로가기 <i class="xi-external-link"></i></a>
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