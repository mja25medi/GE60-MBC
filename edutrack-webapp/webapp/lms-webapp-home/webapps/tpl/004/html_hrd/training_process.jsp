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
                                    <li><span class="current">훈련 진행절차</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">교육안내</h4>
                    </div>
                    <!-- //h1_area --> 
                    
                    <div class="subMenu">
                        <ul class="menu">
                            <li><a href="#">훈련과정</a></li>
                            <li><a href="#">고용보험 환급제도</a></li>  
                            <li><a href="#">훈련비 환급방법</a></li> 
                            <li class="active"><a href="#">훈련 진행절차</a></li>   
                            <li><a href="#">훈련진행 유의사항</a></li> 
                            <li><a href="#">모사답안 처리기준</a></li>      
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">훈련 진행절차</h3>

                    <div class="apply_process">
                        <div class="page_tit">
                            <h4>수강신청부터 강의실까지<br>
                                <strong>지원절차 안내</strong></h4>
                        </div>
                        
                        <ul class="top">
                            <li>
                                <div class="step">STEP. 01</div>
                                <div class="ctt"><strong>온라인 수강신청</strong>
                                    수강하고 싶은 과정과 날짜를 정하셨다면?
                                    홈페이지에서 빠르게 수강신청을 해주세요!
                                    망설이면 원하는 과정이 마감될 수 있어요! </div>
                            </li>
                            <li>
                                <div class="step">STEP. 02</div>
                                <div class="ctt"><strong>개별면담 및 사전학습 수행</strong>
                                    과정 참여 의지와 교육대상을 확인하는 개별
                                    사전면담과 일주일간의 사전학습을 성실히
                                    수행해주시면 선발 가능성이 높아집니다!</div>
                            </li>
                            <li>
                                <div class="step">STEP. 03</div>
                                <div class="ctt"><strong>국민내일배움카드 발급</strong>
                                    교육 수강을 위해 내일배움카드는 반드시
                                    필요합니다, 발급절차 안내는 STEP. 02
                                    수행 시에 별도로 안내해 드립니다!</div>
                            </li>
                        </ul>
                        <ul class="bottom">
                            <li>
                                <div class="step">STEP. 04</div>
                                <div class="ctt"><strong>합격자 발표</strong>
                                    축하드립니다! 합격자 발표는 개별 문자를
                                    통해 안내되며 합격안내 문자를 받으셨다면
                                    이제는 스마트인재개발원 수강생!</div>
                            </li>
                            <li>
                                <div class="step">STEP. 05</div>
                                <div class="ctt"><strong>HRD-Net 최종 수강등록</strong>
                                    최종수강등록 단계로 발급받으신 내일배움
                                    카드를 통해 HRD-Net 수강등록을 완료
                                    하면 이제 정말 끝나요! </div>
                            </li>
                            <li>
                                <div class="step">STEP. 06</div>
                                <div class="ctt"><strong>개강, START!</strong>
                                    이제 5개월간 취업을 향해 달려가세요!
                                    2022년 한 해 90.2%가 수료하고 83.6%가 취업을
                                    했답니다! 여러분들도 할 수 있습니다!</div>
                            </li>
                        </ul>
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