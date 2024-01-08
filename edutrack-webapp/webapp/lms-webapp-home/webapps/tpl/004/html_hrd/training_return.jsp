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
                                    <li><span class="current">훈련비 환급방법</span></li>
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
                            <li class="active"><a href="#">훈련비 환급방법</a></li> 
                            <li><a href="#">훈련 진행절차</a></li>   
                            <li><a href="#">훈련진행 유의사항</a></li> 
                            <li><a href="#">모사답안 처리기준</a></li>      
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">훈련비 환급방법</h3>

                    <div class="training_area box">
                        <dl class="guide_info">
                            <div>
                                <dt>
                                    환급 자격
                                </dt>
                                <dd>
                                    <ul class="info_list mb10">
                                        <li>23.05.01 이전 개강 : 진도율 80% 이상 달성(수료) + 종강 후 6개월 내 HRD-Net 수강평 등록</li>
                                        <li>23.05.01 이후 개강 : 진도율 80% 이상 달성(수료) 및 실습 참여 + 종강 후 6개월 내 HRD-Net 수강평 등록</li>
                                    </ul>
                                    ※ 환급은 종강 이후 6개월 이내에 신청해야 합니다. <br>
                                    ※ 수료 여부는 종강 이후 <a href="https://www.hrd.go.kr/" target="_blank" class="fcBlue">HRD-Net</a>에 훈련결과가 등록된 이후 확인 가능합니다.<br>
                                    ※ 자세한 실습 내용은 훈련 시작 후 수강 가이드를 통해 별도로 안내됩니다.
                                </dd>
                            </div>
                            <div>
                                <dt>
                                    신청방법
                                </dt>
                                <dd>
                                    <a href="https://www.hrd.go.kr/" target="_blank" class="fcBlue">HRD-Net</a>을 통해 훈련생이 직접 신청하며, HRD-Net을 통해 환급내역 확인<br>
                                    [마이서비스→ 나의카드→ 국민내일배움카드→ 자비부담금 환급신청]<br>
                                    ※ 카카오뱅크, 토스 등과 같은 인터넷뱅킹으로는 불가합니다.
                                </dd>
                            </div>
                            <div>
                                <dt>
                                    적용 시점
                                </dt>
                                <dd>
                                    21.10.01 부터 <a href="https://www.hrd.go.kr/" target="_blank" class="fcBlue">HRD-Net</a>을 통해 신청 가능, 개정 전 수료자도 소급 적용<br><br>
                                    ※ HRD-Net 홈페이지에 등록된 <a href="https://www.hrd.go.kr/hrdp/hg/phgao/PHGAO0603T.do?currentTab=6&subTab=603" target="_blank" class="fcBlue">환급 방법 가이드 페이지</a> 링크와 이미지를 공유해드리니 참고하시기 바랍니다.
                                </dd>
                                <dd class="mt50">
                                    <img src="../img/contents/return_img.gif" alt="국민내일배움카드 자비부담금 환급신청" />
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