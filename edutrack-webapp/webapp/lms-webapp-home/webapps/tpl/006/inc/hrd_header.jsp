<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<header class="header">
	<div class="logo_search">
		<div class="container">
			<h1 class="logo">
				<a href="#0" class="link">
					<span class="sr-only">투썬캠퍼스 로고</span>
				</a>
			</h1>
			<button id="btn_sch" class="btn_sch_open">
				<i class="xi-search" aria-hidden="true"></i><span class="sr-only">검색버튼</span
				><!-- 모바일 검색버튼-->
			</button>



			<!-- css 확인을 위해 임시 추가함_검색_240412 -->
			<div class="sch_layer">                            
                <form class="sch_form" name="sch_form" id="schForm">                               
                    <label for="allKeyWord" class="title">통합검색</label>
                    <input type="text" name="allKeyWord" id="allKeyWord" placeholder="과정명을 검색하세요">
                    <button id="btnSch" class="btn_sch">
                        <i class="xi-search" aria-hidden="true"></i><span class="sr-only">검색버튼</span>
                    </button>
                </form>
                <button type="button" class="btn_sch_close">
                    <i class="xi-close-thin" aria-hidden="true"></i><span class="sr-only">검색 닫기</span>
                </button>                            
            </div>
			<!-- //css 확인을 위해 임시 추가함_검색_240412 -->




			<div class="gnb_util">
				<div class="member_area">
					<div class="login">
						<ul class="nav">
							<li>
								<a href="" class=""><span>로그인</span></a>
							</li>
							<li>
								<a href="" class=""><span>회원가입</span></a>
							</li>
							<li>
								<a href="" class=""><span>나의 학습실</span></a>
							</li>
						</ul>
					</div>
					<!-- 반영시 style="display: none;" 삭제 -->
					<div class="logout" style="display: none">
						<span class="welcome"
							><span class="text"><strong>홍길동</strong>님</span> <em class="badge">3</em></span
						>
						<ul class="nav">
							<li>
								<a href="" class=""><span>로그아웃</span></a>
							</li>
							<li>
								<a href="" class=""><span>개인정보수정</span></a>
							</li>
						</ul>
					</div>
				</div>
				<!-- //member_area -->
			</div>
			<!-- //gnb_util -->
		</div>
		<!-- //container -->
	</div>
	<!--//logo_search-->

	<div class="gnb_area">
		<div class="counsel_box">
			<div class="box_bg"></div>
			<div class="txt_box">
				<p class="head">교육 상담시간</p>
				<p class="tel">031-706-8400</p>
				<ul>
					<li>평일 9:00 ~ 18:00 / 점심시간 12:00 ~ 13:00</li>
					<li>주말, 공휴일 휴무</li>
				</ul>
			</div>
		</div>
		<nav class="nav container" id="gnb">
			<button type="button" class="btn_allmenu"><span class="sr-only">전체메뉴보기</span><i class="xi-bars"></i></button>
			<h2 class="pop_title">전체메뉴</h2>

			<ul id="head_menu" class="topmenu">
				<li class="depth1">
					<a href="#0"><span>교육원 소개</span></a>
					<div class="submenu">
						<ul class="depth2">
							<li><a href="#0">투썬 Overview</a></li>
							<li><a href="#0">비전/미션</a></li>
							<li><a href="#0">전략과제</a></li>
							<li><a href="#0">연혁</a></li>
							<li><a href="#0">조직도</a></li>
							<li><a href="#0">찾아오시는 길</a></li>
							<li><a href="#0">강사지원</a></li>
						</ul>
					</div>
				</li>
				<li class="depth1">
					<a href="#0"><span>국민내일배움카드</span></a>
					<div class="submenu">
						<ul class="depth2">
							<li><a href="#0">내일배움카드란?</a></li>
							<li><a href="#0">발급조건</a></li>
							<li><a href="#0">발급절차​</a></li>
							<li><a href="#0">수강생 유의사항</a></li>
							<li><a href="#0">모사답안 처리기준</a></li>
						</ul>
					</div>
				</li>
				<li class="depth1">
					<a href="#0"><span>과정소개</span></a>
					<div class="submenu">
						<ul class="depth2">
							<li><a href="#0">전체과정</a></li>
							<li><a href="#0">내일배움카드 훈련과정</a></li>
						</ul>
					</div>
				</li>
				<li class="depth1">
					<a href="#0"><span>학습지원센터</span></a>
					<div class="submenu">
						<ul class="depth2">
							<li><a href="#0">공지사항</a></li>
							<li><a href="#0">자주하는 질문</a></li>
							<li><a href="#0">과정상담신청</a></li>
							<li><a href="#0">수강신청절차</a></li>
							<li><a href="#0">1:1문의</a></li>
							<li><a href="#0">학습자료실</a></li>
							<li><a href="#0">수강후기</a></li>
							<li><a href="#0">원격지원요청</a></li>
						</ul>
					</div>
				</li>
				<li class="depth1">
					<a href="#0"><span>나의 학습실</span></a>
					<div class="submenu">
						<ul class="depth2">
							<li><a href="#0">수강현황</a></li>
							<li><a href="#0">회원정보</a></li>
							<li><a href="#0">증명서발급​</a></li>
							<li><a href="#0">상담내역​</a></li>
							<li><a href="#0">결제내역</a></li>
						</ul>
					</div>
				</li>
			</ul>
			<button type="button" class="pop_close"><i class="xi-close"></i><span class="sr-only">전체메뉴 레이어 닫기</span></button>
		</nav>
	</div>
	<!-- //gnb_area -->
</header>
