$(document).ready(function () { 
    
    // NAV 메뉴
    $(document).on('click','.js-menu_toggle.closed',function(e){
        $(this).removeClass('closed').addClass('opened');
        $('.lnb').css({ 'left':'0px' });
    });

    $(document).on('click','.js-menu_toggle.opened',function(e){
        $(this).removeClass('opened').addClass('closed');
        $('.lnb').css({ 'left':'-31rem' });
    });
    
    $('ul.navList > li').each(function(){
        if ($(this).hasClass("on") == true) {
            $(this).find("ul.sub").css("display", "block");
            $(this).find("img").attr("src", $(this).find("img").attr("src").replace(".svg", "_on.svg"));
        }else{
            $(this).find("img").attr("src", $(this).find("img").attr("src").replace("_on.svg", ".svg"));
        }
    });
    
    var menu = $('ul.navList > li > a');
    var submenu = $('ul.navList li ul.sub li');
    menu.unbind('click').bind('click', function(e) {
        if ($(this).parent().hasClass("on") != true) {
            $(menu).parent().removeClass("on");
            $(this).parent().addClass("on");
            $(menu).parent().find("ul.sub").slideUp('fast');
            $(this).parent().find("ul.sub").slideDown('fast');
            
            $(menu).find("img").attr("src", function (index, attr) {
                return attr.replace("_on.svg", ".svg");
            });
            $(this).find("img").attr("src", $(this).find("img").attr("src").replace(".svg", "_on.svg"));
        } else {
            $(menu).parent().removeClass("on");
            $(this).find("img").attr("src", $(this).find("img").attr("src").replace("_on.svg", ".svg"));
            $(this).parent().find("ul.sub").slideUp('fast');
        }
    });
    submenu.click(function() {
        if ($(this).hasClass("current") != true) {
            $(submenu).removeClass("current");
            $(this).addClass("current");
        } else {
            $(submenu).removeClass("current");
        }
    });
    
    // 모바일 테이블 가로스크롤 스크롤 시 이미지 fadeOut
    $(".scroll_table_w").each(function () {
        $(this).find(".scroll_table").on("scroll", function () {
            if (!$(this).siblings(".scroll_img_w").hasClass("on")) {
                $(this).siblings(".scroll_img_w").addClass("on");
                $(this).siblings(".scroll_img_w").fadeOut(400);
            }
        });
    })
    
     // 모바일 과제결과 스크롤 시 이미지 fadeOut
     $(".scroll_img_w").on("click", function () {
        $(this).fadeOut(400);
    }),
    $(".task_area").on("scroll", function () {
        $(this).find(".scroll_img_w").fadeOut(400);
    });

       
    // 코딩학습창 탭
    $("ul.left_number li").unbind('click').bind('click', function(e) {
		$("ul.left_number li").removeClass('on');
		$(this).addClass("on");
	});
    // 코딩학습창 왼쪽 영역최소화
    $(".indent_btn").unbind('click').bind('click', function(e) {
        if ($(this).hasClass("active") != true) {
            $(this).toggleClass('active');
			$('.panel-left').css({width:'140px'});
			$('.right_content').hide();
		} else {
            $(this).toggleClass('active');
			$('.panel-left').css({width:'50%'});
			$('.right_content').css({display:'flex'});
		}
	});

    // 코딩학습창 강사 왼쪽 영역최소화
    $(".indent_btn.prof_c").unbind('click').bind('click', function(e) {
        if ($(this).hasClass("active") != true) {
            $(this).toggleClass('active');
			$('.panel-left').css({width:'50px'});
			$('.right_content').hide();
		} else {
            $(this).toggleClass('active');
			$('.panel-left').css({width:'50%'});
			$('.right_content').css({display:'flex'});
		}
	});


     // 문제내용
     $(".btn_more_que").unbind('click').bind('click', function(e) {
		$(".task_question").toggleClass('on');
		$(this).addClass("on");
	});

     /*  제한조건  */  
     $hr = $(".condition_area");
     $hi = $(".condition_input");
     //제한조건 추가 
     $(".condition_add").on("click", function(){
         $iT = $hi.val();
         
         if($iT){		      
             $hr.append("<button type='button' class='condition_tag'><span class='tagSpan'>"+$iT+"</span><i class='xi-close-circle'></i><span class='sr-only'>파일삭제</span></button>");
             $hi.val("");
         }
     });
     $hi.on("keyup", function(key){        
         if(key.keyCode == 13){           
             $(".condition_add").trigger("click");
         }        
     });
     
     //제한조건 삭제
     $hr.on("click", "button", function(){
         $(this).remove();
     });

     /********** Summernote Editor **********/
     /*$('#editor').summernote({
        height: 400,
        lang: 'ko-KR',
        placeholder: '내용을 입력하세요',
        toolbar: [
            ['font', ['style', 'fontsize', 'color', 'bold', 'italic', 'underline', 'clear']],
            ['para', ['ul', 'ol', 'paragraph', 'height', 'table']],
            ['insert', ['link', 'picture', 'video', 'hr', 'emoji', 'math']],
            ['view', ['fullscreen', 'codeview']]
        ]
    });*/
    
});
