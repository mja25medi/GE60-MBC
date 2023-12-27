<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@ include file="../inc/class_common.jsp" %>
<link rel="stylesheet" href="../css/sidebar.min.css">
<script src="../js/sidebar.min.js"></script>
<body>
    <div class="vod_wrap">
        <div class="vod_header">
            <div class="top_inner">
                <div class="title"><button type="button"><i class="xi-angle-left" aria-hidden="true"></i></button>파이썬 설치 및 강의 환경 설정<button type="button"><i class="xi-angle-right" aria-hidden="true"></i></button></div>
                <div class="group_btn">
                    <button type="button" class="btn-line inquiry-button">문의하기</button>
                    <button type="button" class="btn-line lecture-button">목차열기</button>
                </div>
            </div>
        </div>
        <div class="vod_content">
            <div data-player-id="cdcc4202-ef0b-4e03-a43a-d1fcf6d83157">
            <script src="//cdn.flowplayer.com/players/ffdf2c44-aa29-4df8-a270-3a199a1b119e/native/flowplayer.async.js">
                {
                    "src": "//cdn.flowplayer.com/a30bd6bc-f98b-47bc-abf5-97633d4faea0/hls/de3f6ca7-2db3-4689-8160-0f574a5996ad/playlist.m3u8",
                    "my_option": "my_value"
                }
            </script>
            </div>
<!--            <video controls><source src="https://www.w3schools.com/html/mov_bbb.mp4" type="video/mp4" /></video>-->
            <div class="ui right wide sidebar inbox-inquiry">
                <!------------ 문의목록 ------------>
                <div class="board_top">
                    <div class="page_btn">
                        <button type="button" class="btn type4">등록</button>
                    </div>
                </div>
                <div class="table_list">
                    <ul class="list">
                        <li class="head"><label>번호</label></li>
                        <li>5</li>
                    </ul>
                    <ul class="list">
                        <li class="head"><label>상태</label></li>
                        <li><label class="btn3 sm solid fcViolet">처리중</label></li>
                    </ul>
                    <ul class="list">
                        <li class="head"><label>등록일</label></li>
                        <li>2023.10.24</li>
                    </ul>
                    <ul class="list">
                        <li class="head"><label>번호</label></li>
                        <li>4</li>
                    </ul>
                    <ul class="list">
                        <li class="head"><label>상태</label></li>
                        <li><label class="btn3 sm solid fcBlack">처리완료</label></li>
                    </ul>
                    <ul class="list">
                        <li class="head"><label>등록일</label></li>
                        <li>2023.10.24</li>
                    </ul>
                </div>
                <br /><br />
                <!------------ 문의등록 ------------>
                <div class="tstyle">
                    <ul class="dbody">
                        <li>
                            <div class="row">
                                <label for="titleInput" class="form-label col-sm-2">제목</label>
                                <div class="col-sm-10">
                                    <div class="form-row">
                                        <input class="form-control" type="text" name="titleInput" id="titleInput" value="" maxlength="100" placeholder="제목을 입력하세요"> 
                                    </div>             
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="row">
                                <label for="contTextarea" class="form-label col-sm-2">내용</label>
                                <div class="col-sm-10">
                                    <div class="form-row">
                                        <textarea class="form-control" rows="10" id="contTextarea"></textarea>
                                    </div>
                                </div>
                            </div>
                        </li>                           
                    </ul>
                </div>
                <div class="btns mt30">
                    <button type="button" class="btn gray2">저장</button>
                    <button type="button" class="btn type5">취소</button>
                </div>
            </div>
            <div class="ui right wide sidebar inbox-lecture">
                <!------------ 강의목록 ------------>
                <div class="learn_top">
                    <ul class="right_state">
                        <li class="step01">학습 미진행</li>
                        <li class="step02">학습 진행중</li>
                        <li class="step03">학습완료</li>
                    </ul>
                </div>
                <div class="course_list">
                    <div class="item step03">
                        <div class="title">
                            <h5><span>파이썬 설치 및 강의 환경 설정</span></h5>
                            <div class="prog_rate">
                                <ul>
                                    <li>
                                        <span class="header">진도율</span><span class="meta">20%</span>
                                        <div class="progress">
                                            <div class="bar red_type" style="width: 20%;"></div>
                                        </div>
                                    </li>
                                    <li>10분/45분</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="item step02">
                        <div class="title">
                            <h5><span>변수 네이밍 규칙</span></h5>
                            <div class="prog_rate">
                                <ul>
                                    <li>
                                        <span class="header">진도율</span><span class="meta">60%</span>
                                        <div class="progress">
                                            <div class="bar red_type" style="width: 60%;"></div>
                                        </div>
                                    </li>
                                    <li>30분/45분</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="item step01">
                        <div class="title">
                            <h5><span>시퀀스 타입 인덱싱</span></h5>
                            <div class="prog_rate">
                                <ul>
                                    <li>
                                        <span class="header">진도율</span><span class="meta">0%</span>
                                        <div class="progress">
                                            <div class="bar red_type" style="width: 0%;"></div>
                                        </div>
                                    </li>
                                    <li>0분/45분</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        /********** wide-inbox sidebar **********/
        $('.inbox-inquiry').sidebar({dimPage: false, closable: false, exclusive: true, context: '.vod_content'})
        .sidebar('attach events', '.inquiry-button', 'toggle')
        
        $('.inbox-lecture').sidebar({dimPage: false, closable: false, exclusive: true, context: '.vod_content'})
        .sidebar('attach events', '.lecture-button', 'toggle')
    </script>
</body>
</html>