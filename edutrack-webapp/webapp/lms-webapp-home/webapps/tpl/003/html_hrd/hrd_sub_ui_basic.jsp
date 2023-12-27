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
                <!-- snb -->
                <section id="snb" class="snb">
                    <h2 class="title">나의 강의실</h2>
                    <nav class="nav">
                        <ul id="left_menu_top" class="depth2">
                            <li class="active">
                                <a href="#0">나의 학습</a>
                            </li>
                            <li>
                                <a href="#0">종료과정 성적조회</a>                                                         
                            </li>
                            <li>
                                <a href="#0">수료증</a>                                
                            </li>  
                            <li>
                                <a href="#0">설문</a>                                
                            </li> 
                            <li>
                                <a href="#0">장바구니</a>                                
                            </li>                                                        
                        </ul>
                    </nav>
                </section>
                <!-- //snb -->

                <!-- content -->
                <div id="content" class="content">
                    <div class="h1_area">
                        <div class="location_bar">
                            <nav class="location">
                                <ul>
                                    <li><i class="xi-home-o" aria-hidden="true"></i><span class="sr-only">Home</span></li>
                                    <li>나의 강의실</li>
                                    <li><span class="current">나의 학습</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">나의 학습</h4>
                    </div>
                    <!-- //h1_area --> 
                    

                    <div class="mt60 mb20 fcWhite">
                        <span style="background-color: #000; padding:5px 15px;">레이아웃</span>
                    </div>

                    <div class="row">
                        <div class="col-sm-12">

                            <div style="width: 100%; height: 200px; background-color: #f5f5f5;">
                                레이아웃은 row <br>
                                col-sm-1 ~ 12 사용
                            </div>
                            
                        </div>
                    </div>

                    <div class="row mt40">
                        <div class="col-sm-4">
                            <div style="width: 100%; height: 200px; background-color: #f5f5f5;">
                                col-sm-4
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <div style="width: 100%; height: 200px; background-color: #f5f5f5;">
                                
                                col-sm-8
                            </div>
                        </div>
                    </div>

                    <div class="mt50 mb20 fcWhite">
                        <span style="background-color: #000; padding:5px 15px;">색상</span>
                    </div>

                    <b class="">글자_기본색상_또는 fcDarkgray 클래스 삽입</b><br><br>

                    <b class="fcRed">fcRed 글자_</b><br><br>

                    <b class="fcBlue">fcBlue 글자_</b><br><br>

                    <b class="fcDarkgray">fcDarkgray 글자</b><br><br>


                    <div class="mt50 mb20 fcWhite">
                        <span style="background-color: #000; padding:5px 15px;">배경색상</span>
                    </div>

                    <span class="bcGray" style="padding-left: 2em;"></span> class="bcGray"_배경색 <br><br>
                    <!-- <span class="solid gray" style="padding-left: 2em;"></span> class="solid fcGreen"_배경색 <br><br> -->





                    <div class="mt50 mb20 fcWhite">
                        <span style="background-color: #000; padding:5px 15px;">라벨_상태</span>
                    </div>

                    <span class="label fcGreen">class="label fcGreen"_대여 신청 중</span>  <br><br>
                    <span class="label fcRed">class="label fcRed"_승인불가</span>  <br><br>
                    <span class="label fcBlue">class="label fcBlue"_승인완료</span>  <br><br>

                    

                    <div class="mt50 mb20 fcWhite">
                        <span style="background-color: #000; padding:5px 15px;">라벨_카테고리</span>
                    </div>
                    <span class="label basic bcBlue">class="label basic bcBlue"_인공지능</span>  <br><br>
                    <span class="label basic bcPurple">class="label basic bcPurple"_오프라인</span>  <br><br>
                    <span class="label basic bcTeal">class="label basic bcTeal"_온라인</span>  <br><br>
                    <span class="label basic bcRed">class="label basic bcRed"_임시</span>  <br><br>
                    <span class="label basic bcGray">class="label basic bcGray"_키워드</span>  <br><br>






                    <div class="mt50 mb20 fcWhite">
                        <span style="background-color: #000; padding:5px 15px;">제목, 본문</span>
                    </div>


                    <h5 class="mb30">h5 tag, 27px 과정 목표</h5>
                    <h6 class="mb30">h6 tag, 24px 과정 목표</h6>

                    <b class="mt30 mb30">b tag, 두꺼운 글꼴</b>

                    <p class="mt30 mb30">
                        <b>문단_ 18px, line-height 1.8</b><br>
                        역사를 보이는 이성은 그들의 바이며, 이것은 맺어, 일월과 할지라도 것이다. 그들은 이것을 뭇 고동을 튼튼하며, 있는 것은 보는 것이다. 길을 자신과 힘차게 풀밭에 부패뿐이다. 천지는 끓는 두기 피어나기 인생에 살았으며, 위하여 없는 위하여서.
                    </p>

                    <div class="mt50 mb20 fcWhite">
                        <span style="background-color: #000; padding:5px 15px;">단락형태</span>
                    </div>

                    <article class="mt40">
                        
                        <h5 class="mb20">h5 tag 사용</h5>
                        <p><b>article tag로 콘텐츠를 감쌈</b><br>
                            역사를 보이는 이성은 그들의 바이며, 이것은 맺어, 일월과 할지라도 것이다. 그들은 이것을 뭇 고동을 튼튼하며, 있는 것은 보는 것이다. 길을 자신과 힘차게 풀밭에 부패뿐이다.
                        </p>
                    </article>

                    <br><br><br>

                    <small class="fcRed">
                        <i class="xi-error mr5" aria-hidden="true"></i> 
                        추가 안내글_ 경고 등등
                    </small>

                    <br><br>

                    <small class="fcDarkgray">
                        <i class="xi-error mr5" aria-hidden="true"></i> 
                        추가 안내글_ 경고 등등
                    </small>

                    <br><br><br>







                    <div class="mt50 mb20 fcWhite">
                        <span style="background-color: #000; padding:5px 15px;">폼</span>
                    </div>
                    
                        <form class="">  <!-- form-inline -->    
                            
                            <fieldset>
                                <div class="form-row">
                                    <select name="" id="" class="form-select">
                                        <option value="" selected>셀렉트</option>
                                        <option value="">옵션1</option>
                                        <option value="">옵션2</option>
                                    </select>
                                </div>

                                <div class="form-row">
                                    <label class="form-label mr5" for="a1">안내글</label>
                                    <select name="" id="" class="form-select">
                                        <option value="" selected>셀렉트가 한줄에 여러개</option>
                                        <option value="">옵션1</option>
                                        <option value="">옵션2</option>
                                    </select>
                                    <select name="" id="" class="form-select">
                                        <option value="" selected>셀렉트 클래스명을 다르게 할 것</option>
                                        <option value="">옵션1</option>
                                        <option value="">옵션2</option>
                                    </select>
                                    <select name="" id="" class="form-select">
                                        <option value="" selected>셀렉트 클래스명을 다르게 할 것</option>
                                        <option value="">옵션1</option>
                                        <option value="">옵션2</option>
                                    </select>
                                </div>
                                <div class="form-row">
                                    <label class="form-label mr5" for="a1">안내글</label>
                                    <select name="" id="" class="form-select">
                                        <option value="" selected>셀렉트가 한줄에 여러개</option>
                                        <option value="">옵션1</option>
                                        <option value="">옵션2</option>
                                    </select>
                                    <select name="" id="" class="form-select">
                                        <option value="" selected>셀렉트 클래스명을 다르게 할 것</option>
                                        <option value="">옵션1</option>
                                        <option value="">옵션2</option>
                                    </select>
                                    <input type="text" class="form-control" id="s1">
                                </div>

                                <div class="form-row">
                                    <div class="calendar w100">
                                        <label class="mb0"><input type="text" class="datepicker" name="start1" value="" id="s1"></label> 
                                        <label class="mb0"><input type="text" class="datepicker" name="start2" value="" id="s2"></label>
                                    </div>
                                </div>

                                <div class="form-row">
                                    <div class="calendar w100">
                                        title 
                                        <label class="mb0"><input type="text" class="datepicker" name="start1" value="" id="s1"></label>
                                    </div>
                                </div>

                                <div class="form-inline">
                                    <label class="form-label mr5">
                                        title
                                    </label>
                                    <input type="text" class="form-control" id="s1">
                                </div>

                                <div class="form-inline">
                                    <label class="form-label mr5">
                                        title
                                    </label>
                                    <input type="text" class="form-control" id="s1">
                                </div>

                                <div class="form-inline">
                                        
                                    <label class="form-label mr5">
                                        title
                                    </label>
                                    <input type="text" class="form-control">
                                </div>

                                <br>

                                <div class="form-inline">
                                    <label class="form-label">
                                        
                                    </label>
                                    <input type="text" class="form-control" value="input tag">
                                </div>
                                <div class="form-row">
                                    <label class="form-label">
                                        
                                    </label>
                                    <textarea type="text" class="form-control" placeholder="textarea tag"></textarea>
                                </div>
                                
                            </fieldset>
                        </form>


                        <br><br><br>



                    <div class="mt50 mb20 fcWhite">
                        <span style="background-color: #000; padding:5px 15px;">페이지 상단 영역</span>
                    </div>

                    <div class="board_info">
                        <div class="page_info">
                            페이지 상단_필요한 경우 페이지 제목, 정보 입력
                        </div>
                        <div class="btns">
                            <form class="form-inline">                                
                                <fieldset class="form-row">                                   
                                    <select class="form-select" name="searchKey" id="select" title="검색어 분류를 선택하세요.">
                                        <option value="all">시설 분류 전체</option>
                                        <option value="title">시설</option>
                                    </select>  

                                    <input type="text" class="form-control" >
                                    
                                    <button type="button" class="btn3 gray2">버튼</button>
                                    <button type="button" class="btn icon" title="버튼이름입력">
                                        <i class="xi-paper-o xi-x"></i>
                                    </button>
                                </fieldset>
                            </form>
                        </div>
                    </div>

                    <div class="btns left mt30">
                        <button class="btn icon" title="버튼이름 입력">
                            <i class="xi-paper-o xi-x"></i>
                        </button>
                        <button class="btn icon" title="샘플강의보기">
                            <i class="xi-play-circle-o xi-x"></i>
                        </button>
                    </div>








                    <div class="mt50 mb20 fcWhite">
                        <span style="background-color: #000; padding:5px 15px;">페이지 하단_버튼 묶음</span>
                    </div>

                    <div class="btns left mt30">
                        <button class="btn icon" title="버튼이름 입력">
                            <i class="xi-paper-o xi-x"></i>
                        </button>
                        <button class="btn icon" title="샘플강의보기">
                            <i class="xi-play-circle-o xi-x"></i>
                        </button>
                    </div>

                    <div class="btns left mt30">
                        <button class="btn icon" title="버튼이름 입력">
                            <i class="xi-paper-o xi-x"></i>
                        </button>
                        <button class="btn">버튼1</button>
                        <button class="btn gray2">버튼2</button>
                    </div>

                    <div class="btns center mt30">
                        <button class="btn">버튼1</button>
                        <button class="btn gray2">버튼2</button>
                        <button class="btn type4">버튼3</button>
                    </div>

                    <div class="btns right mt30">
                        <button class="btn">버튼1</button>
                        <button class="btn type4">버튼2</button>
                    </div>








                    
                    
                    <div class="mt50 mb20 fcWhite">
                        <span style="background-color: #000; padding:5px 15px;">표 형태1</span>
                    </div>

                    <div class="table2">
                        <div class="row">
                            <div class="col">
                                <span class="title">thead-내용</span>
                                <div>내용</div>
                            </div>
                            <div class="col">
                                <span class="title">thead-내용</span>
                                <div>
                                    내용
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <span class="title">
                                    thead-내용
                                </span>
                                <div>
                                    내용
                                </div>
                            </div>
                            <div class="col">
                                <span class="title">
                                    thead-내용
                                </span>
                                <div>
                                    내용
                                </div>
                            </div>
                        </div>
                                                
                        <div class="row">
                            <div class="col">
                                <span class="title">
                                    thead-내용
                                </span>
                                <div>
                                    내용
                                </div>
                            </div>
                            <div class="col">
                                <span class="title">
                                    thead-내용
                                </span>
                                <div>
                                    내용
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col">
                                <span class="title">thead-내용</span>
                                <div>
                                    - 내용 <br>
                                    - 내용 
                                </div>
                            </div>
                        </div>
                    
                    </div>


                    <div class="mt50 mb20 fcWhite">
                        <span style="background-color: #000; padding:5px 15px;">
                            표 형태2_ <br>
                            반응형이며, thead와 tbody를 나눠서 사용해야 하는 경우<br>
                            like-table 클래스 사용
                        </span>
                    </div>

                    <div class="like-table" data-toggle="like-table">
                        <div class="thead">                                
                            <span class="th w40">과정명</span>
                            <span class="th w10">아이콘 버튼</span>
                            <span class="th">샘플강의</span>
                            <span class="th ">한글</span>
                            <span class="th ">수강신청</span>
                        </div>
                        <ul class="tbody">
                            <li class="tr">
                                <dl class="td txt-left">
                                    <dt>과정명</dt>
                                    <dd>
                                        인간은 용감하고 관현악이며, 청춘에서만 군영과 일월과 풍부하게 두손을 구하기
                                    </dd>
                                </dl>
                                <dl class="td">
                                    <dt>아이콘 버튼</dt>
                                    <dd>
                                        <button class="btn icon" title="버튼이름입력">
                                            <i class="xi-paper-o xi-x"></i>
                                        </button>
                                    </dd>
                                </dl> 
                                <dl class="td">
                                    <dt>샘플강의</dt>
                                    <dd>
                                        <button class="btn solid fcGreen" title="샘플강의보기">
                                            <i class="xi-play-circle-o xi-x"></i> 두번째색
                                        </button>
                                    </dd>
                                </dl>
                                <dl class="td">
                                    <dt>한글</dt>
                                    <dd>
                                        한글입숨
                                    </dd>
                                </dl>
                                <dl class="td">
                                    <dt>표에서 사용하는 버튼1</dt>
                                    <dd>
                                        <button class="btn solid fcBlue">메인컬러</button>
                                    </dd>
                                </dl>
                            </li>     
                            <li class="tr">
                                <dl class="td txt-left ">
                                    <dt>과정명</dt>
                                    <dd>
                                        인간은 용감하고 관현악이며
                                    </dd>
                                </dl>
                                <dl class="td">
                                    <dt>아이콘 버튼</dt>
                                    <dd>
                                        <button class="btn icon" title="버튼이름입력">
                                            <i class="xi-paper-o xi-x"></i>
                                        </button>
                                    </dd>
                                </dl> 
                                <dl class="td">
                                    <dt>샘플강의</dt>
                                    <dd>
                                        <button class="btn solid fcGreen" title="샘플강의보기">
                                            <i class="xi-play-circle-o xi-x"></i> 두번째색
                                        </button>
                                    </dd>
                                </dl>
                                <dl class="td">
                                    <dt>한글</dt>
                                    <dd>
                                        한글입숨
                                    </dd>
                                </dl>
                                <dl class="td">
                                    <dt>표에서 사용하는 버튼1</dt>
                                    <dd>
                                        <button class="btn solid fcGray">수강취소</button>
                                    </dd>
                                </dl>
                            </li>                        
                        </ul>
                    </div>       
                    
                    


                    <div class="mt50 mb20 fcWhite">
                        <span style="background-color: #000; padding:5px 15px;">표 형태3_<br>
                            table tag 사용, 모바일에서 가로스크롤이 생기는 경우</span>
                    </div>

                    <div class="table-scroll">
                        <div class="tbl-main-wrap">
                            <div class="tbl-main">
                                <table class="tbl">
                                    <colgroup>
                                        <col width="10%">
                                        <col width="*">
                                        <col width="10%">
                                        <col width="10%">
                                        <col width="10%">
                                        <col width="10%">
                                        <col width="10%">
                                    </colgroup>
                                    <thead>
                                        <th>기수명</th>
                                        <th>과정명</th>
                                        <th>결제상태</th>
                                        <th>결제방법</th>
                                        <th>결제금액</th>
                                        <th>신청일</th>
                                        <th>기타</th>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>2022년도 33기수</td>
                                            <td class="txt-left">이상은 가장 낙원을 밥을 이상은 가장 낙원을 밥을 이상은 가장 낙원을 밥을 이상은 가장 낙원을 밥을 이상은 가장 낙원을 밥을 이상은 가장 낙원을 밥을 이상은 가장 낙원을 밥을</td>
                                            <td>
                                                결제완료
                                                <div class="mt8">
                                                    <button class="btn">결제취소</button>
                                                    <button class="btn solid dark">결제취소</button>
                                                </div>
                                            </td>
                                            <td>무통장입금</td>
                                            <td>0원</td>
                                            <td>10/30 14:52</td>
                                            <td>-</td>
                                        </tr>
                                        <tr>
                                            <td>2022년도 33기수</td>
                                            <td class="txt-left">이상은 가장 낙원을 밥을</td>
                                            <td>결제완료</td>
                                            <td>무통장입금</td>
                                            <td>0원</td>
                                            <td>10/30 14:52</td>
                                            <td>
                                                <div class="txt-left sm">
                                                    입금은행 : 신한(통합)은행<br>
                                                    입금자명 : 학습자1<br>
                                                    입금계좌 : 56211111602750<br>
                                                    입금만료 : 09/22까지
                                                </div>
                                            </td>
                                        </tr>
                                        <!-- <tr>
                                            <td colspan="7" class="alert">
                                                <div class="cnt"> 
                                                    <b>2022년도 33기수</b>의 수강일은 2020-11-10(화) 부터 2020-12-25(금) 까지 입니다.
                                                    <p>
                                                        <span class="ml5 mr5"><b>최초 수강일 : </b> 2020-11-23 11:41:34</span>
                                                        <span class="ml5 mr5"><b>최종 수강일 : </b> 2020-11-23 11:47:09</span>
                                                    </p>
                                                </div>
                                            </td>
                                        </tr> -->
                                        
                                    </tbody>
                                </table>
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