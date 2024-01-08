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
                                    <li><span class="current">모사답안 처리기준</span></li>
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
                            <li><a href="#">훈련 진행절차</a></li>   
                            <li><a href="#">훈련진행 유의사항</a></li> 
                            <li class="active"><a href="#">모사답안 처리기준</a></li>      
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">모사답안 처리기준</h3>

                    <div class="training_area box">
                        <dl class="guide_info">
                            <div>
                                <dt>
                                    모사답안 판단기준
                                </dt>
                                <dd>
                                    <ul class="info_list">
                                        <li>제출된 답안 중 동료 학습자와의 모사율이 80% 이상인 경우</li>
                                        <li>인터넷 자료, 출판물, 기타 자료 등을 80% 이상 복사하여 제출한 경우</li>
                                        <li>해당 교육과정의학스밴용 및 교안, 참고도서를 80%이상 복사하여 제출한 경우</li>
                                        <li>서체, 디자인 형식 등을 달리하여 새로이 작성한 과제처럼 보이나 아래 기준에 부합되는 경우</li>
                                    </ul>
                                    <div class="inner_box">
                                       * 동료 학습자 과제와 비교 시 내용 뿐 아니라 작성 순서까지 모두 동일한 경우<br>
                                       * 동료 학습자 과제, 요약본 등의 내용을 그대로 옮긴 후 순서 등을 일부 편집한 경우</p>
                                    </div>
                                    ※ 단, 답안이 동일할 수 밖에 없는 수식, 정답형 과제 등의 과제유형은 판단기준에서 제외
                                </dd>
                            </div>
                            <div>
                                <dt>
                                    모사답안 처리방침
                                </dt>
                                <dd>
                                    <ul class="info_list">
                                        <li>모사답안을 제출한 학습자 : 과제점수 0점 처리 → 해당 교육과정 미수료</li>
                                        <li>동료학습자에게 답안을 제공한 학습자 : 과제점수 0점 처리 → 해당 교육과정 미수료</li>
                                    </ul>
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