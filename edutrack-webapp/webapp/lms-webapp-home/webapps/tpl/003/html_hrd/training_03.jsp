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
                        <li><a href="#" title="비활성 탭메뉴">K-디지털 트레이닝 과정</a></li>
                        <li class="active"><a href="#" title="선택된 탭메뉴">K-디지털 기초역량 훈련과정</a></li>
                        <li><a href="#" title="비활성 탭메뉴">사업주 직업능력개발훈련</a></li>
                    </ul>
                    
                    <div class="training_area box">
                        <dl class="guide_info">
                            <div>
                                <dt>
                                    K-Digital Credit(디지털 기초역량훈련)이란?
                                </dt>
                                <dd>
                                    디지털 기초훈련을 언제·어디서든 원격으로 수강할 수 있도록 정부가 지원하는 과정입니다. 데이터 분석과 코딩 등을 주요 내용으로 포함하며, 
                                    초급에서 중급까지 다양한 수준으로 구성되어 관심이 있는 분이라면 누구든 기초 지식부터 실무 역량까지 쌓을 수 있도록 진행됩니다.
                                </dd>
                            </div>
                            <div>
                                <dt>
                                    지원 대상
                                </dt>
                                <dd>
                                    <p class="point_box"><i class="xi-check mr5"></i>국민내일배움카드 발급이 가능한 자(구직자, 재직자 무관) 중 최근 2년 이내에 디지털·신기술 분야의 직업능력개발훈련을 받지 않은 사람</p>
                                    디지털 기초훈련을 언제·어디서든 원격으로 수강할 수 있도록 정부가 지원하는 과정입니다. 데이터 분석과 코딩 등을 주요 내용으로 포함하며, 
                                    초급에서 중급까지 다양한 수준으로 구성되어 관심이 있는 분이라면 누구든 기초 지식부터 실무 역량까지 쌓을 수 있도록 진행됩니다.
                                </dd>
                            </div>
                            <div>
                                <dt>
                                    지원 내용 및 유의사항
                                </dt>
                                <dd>
                                    <p class="point_box"><i class="xi-check mr5"></i>기존 국민내일배움카드 수강비 지원 한도인 300~500만원과 별도로 50만원 추가 지급</p>
                                    <ul class="list-dot mb10">
                                        <li>추가 지급된 크레딧은 K-크레딧 과정 수강에만 사용할 수 있으며, 유효기간은 1년</li>
                                        <li>K-Digital Credit 과정에 한하여 1년에 총 5회까지 신청 가능</li>
                                        <li>크레딧 잔액이 남은 경우 1회에 한하여 잔액을 초과하는 훈련과정도 추가부담 없이 수강할 수 있음</li>
                                    </ul>
                                    <p class="point_box"><i class="xi-check mr5"></i>훈련비의 90%를 지원하며, 수료 시 자비부담분 10% 전액 환급</p>
                                    <ul class="list-dot">
                                        <li>학습진도율 80% 미만일 경우 미수료 처리</li>
                                    </ul>
                                </dd>
                            </div>
                            <div>
                                <dt>
                                    신청 절차
                                </dt>
                                <dd>
                                    <ol class="curriculum">
                                        <li class="step">
                                            <div class="circle type1">
                                                <b>01</b>
                                                <span>국민내일<br>배움카드 발급</span>
                                            </div>
                                            <ul class="default_list">
                                                <li>1) 직업능력지식포털 HRD-Net(www.hrd.go.kr) 회원가입</li>
                                                <li>2) 로그인 후 국민내일배움카드 발급신청</li>
                                                <li>※ 공인인증서 로그인 및 본인인증 필요</li>
                                                <li>※ 카드 발급에는 1~2주 정도 소요</li>
                                            </ul>
                                        </li>
                                        <li class="step">
                                            <div class="circle type2">
                                                <b>02</b>
                                                <span>훈련과정<br>수강신청</span>
                                            </div>
                                            <ul class="default_list">
                                                <li>카드 발급 후, 직업훈련포털 로그인하여</li>  
                                                <li> K-디지털 크레딧 > 훈련과정 수강신청</li>                                                   
                                            </ul>
                                        </li>
                                        <li class="step">
                                            <div class="circle type3">
                                                <b>03</b>
                                                <span>훈련비 결제</span>
                                            </div>
                                            <ul class="default_list">
                                                <li>선발 과정이 완료되면</li>  
                                                <li>안내에 따라 훈련비 결제</li>
                                            </ul>
                                        </li>
                                    </ol>
                                </dd>
                            </div>
                        </dl>
                        

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