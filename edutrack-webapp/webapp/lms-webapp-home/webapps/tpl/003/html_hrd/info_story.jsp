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
                                    <li>개발원소개</li>
                                    <li><span class="current">개발원 이야기</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">개발원소개</h4>
                    </div>
                    <!-- //h1_area --> 

                    <div class="subMenu">
                        <ul class="menu">
                            <li class="active"><a href="#">개발원 이야기</a></li>
                            <li><a href="#">조직현황/강사진</a></li>  
                            <li><a href="#">세미콜론 매거진</a></li> 
                            <li><a href="#">인재채용</a></li> 
                            <li><a href="#">뉴스룸</a></li> 
                            <li><a href="#">오시는길</a></li>        
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">개발원 이야기</h3>

                    <div class="info_story">
                        <div class="img_yellow">
                            <img class="vc_single_image-img " src="../img/contents/glory_banner_pc2.png" title="glory_banner_pc2">
                           
                        </div>

                        <div class="story01">
                            <dl>
                                <dt><img src="../img/contents/page_tit_ai.png"></dt>
                                <dd><h4>스마트인재개발원은?</h4>
                                <p>2016년에 설립되어 지역선도형 SW인력양성의 HUB로서의 비전을 가지고 
                                창의적이고 융합적 사고를 가진 SW 전문인력 양성을 위하여 차별화된 
                                교과과정 설계와 교육운영의 노하우로 높은 수료율과 취업률, 교육만족도를
                                유지하고 있습니다. 4차산업혁명을 선도하는 명실상부한 SW전문교육기관
                                으로서 자리매김은 물론 청년취업률 제고와 지역경제 활성화에도 기여할 수 
                                있도록 최선을 다하겠습니다.</p></dd>
                            </dl>
                        </div>

                        <div class="row story02">
                            <div class="col-sm-4">
                                <div class="profile">
                                    <em class="point_tit">공유공감형 융합인재양성!</em>
                                    <div class="page_tit">
                                        <h4>스마트인재개발원<br>
                                        <strong>차준섭 이사장</strong>을<br>
                                        소개합니다!</h4>
                                    </div>
                                    <ul class="default_list">
                                        <li>(사)스마트인재개발원 이사장</li>
                                        <li>인공지능사관학교 원장</li>
                                        <li>호남대학교 명예교수</li>
                                        <li>전) (사)한국스마트미디어학회장</li>
                                        <li>전) 호남대학교 소프트웨어학과 교수</li>
                                        <li>전) 호남대학교 정보통신원/공과대학장</li>
                                        <li>전) 호남대학교 정보산업대학원/평생교육원장</li>
                                    </ul>
                                </div>

                            </div>
                            <div class="col-sm-8">
                                <div class="flex-container">
                                    <div class="cont-none">
                                        <div class="embed-responsive embed-responsive-16by9">
                                            <!-- <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/o0Jp2FV3lFU"></iframe> -->
                                            <iframe class="embed-responsive-item" title="현장인터뷰 이사람 - 차준섭 스마트인재개발원장" src="https://www.youtube.com/embed/o0Jp2FV3lFU?feature=oembed" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen=""></iframe>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div class="story03">
                            <div class="page_tit">
                                <h4>스마트인재개발원<br>
                                <strong>연혁 및 교육실적</strong></h4>
                            </div>
                            <div class="history_list">
                                <ul>
                                    <li><strong>2022</strong>
                                        <ul>
                                            <li>2022 광주인공지능사관학교 3기 주 운영기관 선정</li>
                                            <li>과기부 기업멤버십 SW캠프 사업 선정 (구, 혁신성장)</li>
                                            <li>고용부 K-Digital Training 사업 3년연속 선정</li>
                                            <li>2022 청년친화형기업 ESG 지원사업 선정</li>
                                            <li>재직자과정 운영 (파이썬, DT) – LS그룹, LG전자 등</li>
                                            <li>대학 및 유관기관 합작 교육사업 운영 (전남대, Kibwa 등)</li>
                                            <li>인력양성 업무협약 (대학, 지자체, 유관기관 등)</li>
                                            <li>광주서구, 서울, 순천 분원 개원</li>
                                        </ul>
                                    </li>
                                    <li><strong>2021</strong>
                                        <ul>
                                            <li>과기부 혁신성장 청년인재 집중양성사업 5년연속 선정</li>
                                            <li>고용부 K-Digital Training 사업 2년연속 선정</li>
                                            <li>혁신성장 5개, K-디지털 28개 교육과정 선정 및 운영</li>
                                            <li>재직자과정 운영 (파이썬, DT) – LS그룹, LG전자 등</li>
                                            <li>인력양성 업무협약 (한전KDN, 에너지밸리기업개발원 등)</li>
                                            <li>서울 합자법인설립 (Lab4DX)</li>
                                        </ul>
                                    </li>
                                    <li><strong>2020</strong>
                                        <ul>
                                            <li>고용부 4차산업혁명 선도인력 훈련기관 4년연속 선정</li>
                                            <li>과기부 혁신성장 청년인재 집중양성기관 3년연속 선정</li>
                                            <li>고용부 청년취업아카데미 4개대학, 10개과정 운영</li>
                                            <li>고용부 K-디지털 핵심실무인재 양성기관 선정</li>
                                            <li>재직자과정 운영 (파이썬) – 광주소방본부, LS그룹 등</li>
                                            <li>제 2교육장 개소 / 평생교육시설 인가</li>
                                            <li>인력양성 업무협약 (KT, 광주 북구/남구, 순천대 등)</li>
                                        </ul>
                                    </li>
                                    <li><strong>2019</strong>
                                        <ul>
                                            <li>고용부 4차산업혁명 선도인력 훈련기관 3년연속 선정</li>
                                            <li>과기부 혁신성장 청년인재 집중양성기관 2년연속 선정</li>
                                            <li>고용부 청년취업아카데미 4개대학, 13개과정 운영</li>
                                            <li>과기부 이공계전문기술연수사업 운영</li>
                                        </ul>
                                    </li>
                                    <li><strong>2018</strong>
                                        <ul>
                                            <li>고용부 4차산업혁명 선도인력 훈련기관 2년연속 선정</li>
                                            <li>과기부 혁신성장 청년인재 집중양성기관 선정</li>
                                            <li>고용부 청년취업아카데미 4개대학, 10개과정 운영</li>
                                            <li>과기부 이공계전문기술연수사업 운영</li>
                                            <li>재직자과정 운영 (데이터사이언스 기초) – 광주소방본부</li>
                                        </ul>
                                    </li>
                                    <li><strong>2017</strong>
                                        <ul>
                                            <li>고용부 4차산업혁명 선도인력 훈련기관 선정</li>
                                            <li>과기부 이공계전문기술연수사업 운영</li>
                                            <li>광주광역시 청년예비창업가 발굴육성사업 운영</li>
                                            <li>GITCT 에너지SW/ICT융합 창의인재양성사업 운영</li>
                                        </ul>
                                    </li>
                                    <li><strong>2016</strong>
                                        <ul>
                                            <li>사단법인 스마트인재개발원 설립</li>
                                            <li>고용부 지역맞춤형일자리창출사업 운영</li>
                                            <li>미래창조과학부 이공계전문기술연수사업 운영</li>
                                            <li>미래창조과학부 ICT융합인력양성사업 운영</li>
                                            <li>미래창조과학부 SW/ICT융합창의인재양성사업 운영</li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="story04 process_wrap">
                            <div class="page_tit">
                                <h4>스마트인재개발원만의<br>
                                <strong>차별화된 교육운영시스템</strong></h4>
                            </div>
                            <div class="process">
                                <div class="left">수강생 모집</div>
                                <ul>
                                    <li><strong>01. 모집 및 홍보</strong>
                                        <ul>
                                            <li>다양한 온/오프라인 매체와 지속적인 브랜딩 및 홍보</li>
                                            <li>협약기업/대학 네트워크로 교육생 추천 및 모집군 강화</li>
                                        </ul>
                                    </li>
                                    <li><strong>02. 교육과정 설계</strong>
                                        <ul>
                                            <li>내부 연구개발부서와 산학연 합동 교육과정 설계</li>
                                            <li>기업 및 NCS 수요을 반영한 맞춤형 커리큘럼 설계</li>
                                        </ul>
                                    </li>
                                    <li><strong>03. 교육운영</strong>
                                        <ul>
                                            <li>전문 내부강사진 양성/운영</li>
                                            <li>공인강사 인증제도로 내부 강사진 역량강화 및 양성</li>
                                            <li>기업/전문교수 외부강사진</li>
                                        </ul>
                                    </li>
                                    <li><strong>04. 취업역량강화</strong>
                                        <ul>
                                            <li>강의 및 프로젝트 평가</li>
                                            <li>이력서, 자기소개서, 모의 면접, 포트폴리오 등 다양한 체험형 특강을 교육 중 실시</li>
                                        </ul>
                                    </li>
                                    <li><strong>05. 취업연계</strong>
                                            <ul>
                                            <li>300여개 협약기업을 중심으로 한 수료생 취업연계</li>
                                            <li>한전 및 공공기관 협력업체, 혁신도시 기업체 수요대응</li>
                                        </ul>
                                    </li>
                                </ul>
                                <div class="right">수료후 취업</div>
                            </div>
                        </div>

                        <div class="story05">
                            <ul class="slider_system">
                                <li>
                                    <div class="system">
                                        <div class="tit">주강사 + TA 운영 및 양성시스템</div>
                                        <div class="img"><img src="../img/contents/system1.png"></div>
                                        <div class="txt">
                                            <ul class="default_list">
                                                <li>매 시간 주강사와 TA 동시 투입 / 주강사의 핵심이론 중심 수업 후 TA의 실습 중심 수업으로 학습 내용 습득에 효과적</li>
                                                <li>주강사 / 특강강사 / TA 강의평가 시행 후 결과반영으로 수업의 질을 높이며 교육생과 지속적으로 소통</li>
                                                <li>주 2회 리허설을 통해 강사 점검 및 전문강사양성 기반 구축</li>
                                            </ul>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="system">
                                        <div class="tit">Mate(FSP) 수업운영 시스템</div>
                                        <div class="img"><img src="../img/contents/system2.png"></div>
                                        <div class="txt">스스로 학습하고 파트너를 도와줌으로써 교육효과를 극대화하고 교육생 간의 연대감을 증진시킴</div>
                                    </div>
                                </li>
                                <li>
                                    <div class="system">
                                        <div class="tit">과목 별 및 프로젝트 평가 시스템</div>
                                        <div class="img"><img src="../img/contents/system3.png"></div>
                                        <div class="txt">
                                            <ul class="default_list">
                                                <li>주 단위 평가를 통해 교육생 수준관리</li>
                                                <li>과목별 짝꿍 및 자리배치 조정을 통해 학습효과 향상</li>
                                                <li>성적부진자 개별상담 후 보충수업 및 별도관리</li>
                                                <li>성적우수자 및 성적향상자 시상으로 학습의욕 고취 및 동기부여</li>
                                                <li>문제해결능력과 개인성향/팀워크 분석을 위한 파트너 평가 실시</li>
                                            </ul>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="system">
                                        <div class="tit">기업 실무 프로젝트 멘토링 및 스터디 LAB 운영</div>
                                        <div class="img"><img src="../img/contents/system4.png"></div>
                                        <div class="txt">관련분야 기업 실무진이 프로젝트 기획단계부터 참여하여 실무에 가장 적합한 프로젝트와 포트폴리오 멘토링을 지원하며, 취업확정형 스터디 LAB을 별도로 운영하여 기업이 요구하는 기술과 수강생 수준을 고도화하여 바로 취업하는 시스템도 운영하고 있습니다.</div>
                                    </div>
                                </li>
                            </ul>  
                        </div>

                        <div class="story06">
                            <div class="page_tit">
                                <h4>숫자와 결과가 증명하는<br>
                                <strong>스마트인재개발원의 우수성</strong></h4>
                            </div>
                            <div class="page_desc">매년 가파르게 성장하고 있는 스마트인재개발원<br>
                                한 과정 최대 경쟁률 부터 교육예산 증가 추이 까지<br>
                                매년 상승하고 있는 수치는 그 우수성을 증명합니다</div>
                            <div class="row counter_list">
                                <div class="col-sm-3">
                                    <div class="counter_box high">
                                        <div class="inner">
                                            <div class="counter focus in-view" data-count="86.7">86.7</div>
                                            <div class="mark">%</div>
                                        </div>
                                        <div class="tit">2022<span class="br"></span>취업률</div>
                                    </div>
                                </div>
                                <div class="col-sm-3">
                                    <div class="counter_box">
                                        <div class="inner">
                                            <div class="counter focus in-view" data-count="3173">3173</div>
                                        </div>
                                        <div class="tit">취업한<span class="br"></span>수료생</div>
                                    </div>
                                </div>
                                <div class="col-sm-3">
                                    <div class="counter_box">
                                        <div class="inner">
                                            <div class="counter focus in-view" data-count="311">311</div>
                                            <div class="mark"></div>
                                        </div>
                                        <div class="tit">협약<span class="br"></span>기업수</div>
                                    </div>
                                </div>
                                <div class="col-sm-3">
                                    <div class="counter_box">
                                        <div class="inner">
                                            <div class="counter focus in-view" data-count="90.2">90.2</div>
                                            <div class="mark">%</div>
                                        </div>
                                        <div class="tit">2022<span class="br"></span>수료율</div>
                                    </div>
                                </div>
                            </div>
                            <div class="row counter_list">
                                <div class="col-sm-6">
                                    <div class="system color">
                                        <div class="tit">연도 별 운영과정 및 교육예산 증가추이</div>
                                        <div class="img"><img decoding="async" src="../img/contents/result1.png"></div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="system color">
                                        <div class="tit">협약기업 증가추이 및 누적 취업인원</div>
                                        <div class="img"><img decoding="async" src="../img/contents/result2.png"></div>
                                    </div>
                                </div>
                            </div>
                            
                        </div>

                        <div class="story07">
                            <div class="page_tit">
                                <h4>매년 산학연 협력을 위해<br>
                                <strong>업무 협약을 맺고 있습니다.</strong></h4>
                            </div>
                            <div class="page_desc">4차산업혁명시대를 선도하는 교육기관에 걸맞는<br>
                                교육의 품질 향상과 다양한 취업처 확보 및 지역발전을<br>
                                위해 다양한 기업, 기관, 대학과 업무협약을 맺고 있습니다.</div>
                            <div class="partner_list">
                                <div class="animatedBackground cooperate1-container"></div>
                                <div class="animatedBackground cooperate2-container"></div>
                                <div class="animatedBackground cooperate3-container"></div>
                            </div>
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