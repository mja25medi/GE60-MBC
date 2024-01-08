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
                                    <li>마이 페이지</li>
                                    <li><span class="current">받은쪽지</span></li>
                                </ul>
                            </nav>                                                   
                        </div>
                        <h4 class="title_h1">마이 페이지</h4>
                    </div>
                    <!-- //h1_area --> 
                    
                    <div class="subMenu">
                        <ul class="menu">
                            <li><a href="#">장바구니</a></li>
                            <li><a href="#">결제내역</a></li>  
                            <li><a href="#">문의내역</a></li> 
                            <li class="active"><a href="#">받은쪽지</a></li>    
                            <li><a href="#">개인정보 수정</a></li>  
                            <li><a href="#">아바타 편집</a></li>      
                        </ul>
                    </div>
                    <!-- //2depth--> 
                    
                    <h3 class="subMenu_title">받은 쪽지</h3>
                    
                    <div class="board_info">
                        <span class="caution fcRed">* 쪽지는 최대 180일 동안 보관됩니다.</span>
                    </div>

                    <table class="tstyle list">
                        <caption>쪽지 목록</caption>
                        <colgroup>
                            <col class="w8 m_hidden">
                            <col class="w15 m_hidden">
                            <col>
                            <col class="w15 m_hidden">
                            <col class="w10 m_hidden">
                        </colgroup>
                        <thead class="dhead">
                            <tr>
                                <th scope="col" class="m_hidden">번호</th>
                                <th scope="col" class="m_hidden">보낸사람</th>
                                <th scope="col" class="title">제목</th>
                                <th scope="col" class="m_hidden">보낸시간</th>
                                <th scope="col" class="m_hidden">삭제</th>
                            </tr>
                        </thead>
                        <tbody class="dbody" id="listView">                            
                            <tr>
                                <td class="m_hidden" data-title="번호">17</td>
                                <td class="m_hidden" data-title="보낸사람">관리자</td>
                                <td class="title" data-title="제목">
                                    <a href="#">학습 확인 안내 입니다.</a>
                                </td>
                                <td class="m_hidden" data-title="보낸시간">2023.10.28 10:00:00</td>
                                <td class="m_hidden" data-title="삭제"><button class="btn type3">삭제</button></td>
                            </tr>
                            <tr>
                                <td class="m_hidden" data-title="번호">17</td>
                                <td class="m_hidden" data-title="보낸사람">관리자</td>
                                <td class="title" data-title="제목">
                                    <a href="#">학습 확인 안내 입니다.</a>
                                </td>
                                <td class="m_hidden" data-title="보낸시간">2023.10.28 10:00:00</td>
                                <td class="m_hidden" data-title="삭제"><button class="btn type3">삭제</button></td>
                            </tr>                          
                        </tbody>
                    </table>

                    <div class="board_pager">
                        <span class="inner">
                            <a href="" class="page_first" title="첫페이지"><i class="xi-angle-left-min"></i><span class="sr_only">첫페이지</span></a>
                            <a href="" class="page_prev" title="이전페이지"><i class="xi-angle-left-min"></i><span class="sr_only">이전페이지</span></a>
                            <a href="" class="page_now" title="1페이지"><strong>1</strong></a>
                            <a href="" class="page_none" title="2페이지">2</a>
                            <a href="" class="page_none" title="3페이지">3</a>
                            <a href="" class="page_next" title="다음페이지"><i class="xi-angle-right-min"></i><span class="sr_only">다음페이지</span></a>
                            <a href="" class="page_last" title="마지막페이지"><i class="xi-angle-right-min"></i><span class="sr_only">마지막페이지</span></a>
                        </span>
                    </div>
                    <!-- //board_pager -->
                   

                </div>
                <!-- //content -->
            </div>
            <!-- //contentWrap -->
              

        </main>

        <%@ include file="../inc/hrd_footer.jsp" %>
       
    </div>
</body>

</html>