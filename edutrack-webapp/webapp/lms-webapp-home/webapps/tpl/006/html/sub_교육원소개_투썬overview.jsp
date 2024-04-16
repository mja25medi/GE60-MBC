<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
	<%@ include file="../inc/home_common.jsp" %>
	<link rel="stylesheet" href="../css/sub.css" /><!-- sub 페이지에서 사용 -->
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
										<li>교육원 소개</li>
										<li><span class="current">투썬 Overview</span></li>
									</ul>
								</nav>
							</div>
							<h4 class="title_h1">교육원 소개</h4>
						</div>
						<!-- //h1_area -->

						<div class="subMenu">
							<ul class="menu">
								<li class="active"><a href="#">투썬 Overview</a></li>
								<li><a href="#">비전/미션</a></li>
								<li><a href="#">전략과제</a></li>
								<li><a href="#">연혁</a></li>
								<li><a href="#">조직도</a></li>
								<li><a href="#">오시는 길</a></li>
								<li><a href="#">강사지원</a></li>
							</ul>
						</div>
						<!-- //2depth-->

						<h3 class="subMenu_title">투썬 Overview</h3>
                      
                        <div class="overview">
                            <div class="sect1">
								<div class="sect_tit">
									<p class="tit">
										투썬캠퍼스 원격평생교육원에 오신 <br>
										모든 학습자 여러분을 진심으로 환영합니다.
									</p>
								</div>
                            </div>
                            <div class="img_bg">
                                <div class="grad">TWOSUN Overview</div> 
                            </div>
                            <div class="sect2">
								<ul class="txt_box">
									<li>
										<p class="txt">
											우리 사회는 지난 팬데믹으로 인한 교육 결손, 심화되는 교육 불평등, 가속화되는 디지털 격차 등의 여러 복합적인 위기와 도전에 직면해 있는 상황입니다. <br>
											이러한 가운데 투썬캠퍼스 원격평생교육원은 지식의 장벽을 허물어 교육의 문턱을 낮추고 더 많은 분들이 학습의 기회를 가질 수 있도록 노력하고 있습니다.
										</p>                        
									</li>
									<li>
										<p class="txt">
											언제 어디에서나 편리하게 이용할 수 있는 투썬캠퍼스 교육 플랫폼을 통해 앞으로도 학습자분들께 더 효과적이고 풍부한 교육 경험을 제공함으로써 <br>평생교육학습사회를 만들어 나가는 데 기여하겠습니다. 
										</p>
									</li>
									<li>
										<p class="txt">
											이를 통해 학습자분들의 삶의 질을 높이고 사회에서 지속적으로 가치를 만들어 내며 도약할 수 있도록 최선을 다하겠습니다. 
										</p>
									</li>
								</ul>
                            </div>
                            <div class="row sect3">
                                <div class="col-sm-6">
									<div class="tit">투썬캠퍼스 Overview</div>
									<div class="desc">주식회사 투썬캠퍼스는 대한민국 미래 성장 자원인 청년들의 창업-취업-진로 역량 향상을 추구하는 평생교육원이자 전문 액셀러레이터입니다.</div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="txt-center">
                                        <img src="../img/contents/logo_01.png">
                                    </div>
                                </div>
                            </div>
							<div class="row sect3">
                                <div class="col-sm-6">
									<div class="tit">투썬에듀 Overview</div>
									<div class="desc">투썬에듀는 디지털 분야 실무중심 훈련과정을 제공함으로써 학습자분들이 디지털 분야의 핵심 인재로 성장할 수 있도록 지원합니다.</div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="txt-center">
                                        <img src="../img/contents/logo_02.png">
                                    </div>
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
