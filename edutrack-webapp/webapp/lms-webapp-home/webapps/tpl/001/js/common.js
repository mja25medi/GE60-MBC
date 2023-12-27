// JavaScript Document

/********************************************************************************************************
 * 전역 함수모음
*********************************************************************************************************/

$(function(){

	/* =======================================================
	  Default Setting
	=========================================================== */

	/********** 접근성 바로가기 메뉴 **********/
	key_AccessMenu();
	function key_AccessMenu() {

		$("#key_access").find(">ul>li>a").bind({
			focusin:function(e) {
				$("#key_access").css({"z-index":"10000"});
				$(this).parent("li").addClass("select");
			},
			focusout:function(e) {
				$("#key_access").css({"z-index":"-1"});
				$(this).parent("li").removeClass("select");
			},
			click:function(e) {
				$elm = $($(this).attr("href"));
				$elm.focus();
			}
		});
	}

	/********** 페이지 로딩 **********/
	document.getElementById('loading_page').style.display="none";
	document.getElementById('wrap').style.display="";


	/********** selectbox CSS 적용 **********/
    $('.ui.dropdown')
    .dropdown();

	/********** SCROLL TOP 함수 스크립트 **********/
	$(window).scroll(function() {
		if ($(this).scrollTop()) {
			$('#toTop').fadeIn();
		} else {
			$('#toTop').fadeOut();
		}
	});

    /********** semantic-ui Using **********/
    //FooTable
    $('.table').footable({
        "on": {
            "resize.ft.table": function(e, ft){
                $('.ui.checkbox').checkbox();
            },
            "after.ft.paging": function(e, ft){
                $('.ui.checkbox').checkbox();
            },
            "after.ft.sorting": function(e, ft, sorter){
                $('.ui.checkbox').checkbox();
            },
            "after.ft.filtering": function(e, ft, filter){
                $('.ui.checkbox').checkbox();
            }
        }
    });
    $('.ui.dropdown').dropdown({
        direction: 'downward'
    });
    $('.ui.checkbox').checkbox();
    //$('.ui.rating').rating();
    $('.tooltip').popup({
        inline: true,
        //on: 'click',
        variation : 'tiny',
        position: 'right center'
    });
    $('.ui.accordion').accordion();
    $('.ui.sticky').sticky({
        offset       : 20,
        context: '#context'
    });

    /**** FooTable list-num ******/
    $('[name="listNum"]').on('change', function(e){
    	e.preventDefault();
    	var newSize = $(this).val();
    	if($(this).attr('data-table-id')){
        	FooTable.get("#"+$(this).attr('data-table-id')).pageSize(newSize);
    	}else{
    		FooTable.get("#list-table").pageSize(newSize);
    	}
    	$('.ui.checkbox').checkbox();
    });

    /**** FooTable CHECKBOX ******/
	$("#checkAll").click(function(){
		if($("#checkAll").prop("checked")) {
			if(!$("input[name=ckBoxId]").attr("disabled")){
				$("input[name=ckBoxId]").prop("checked",true);
			}
		} else {
			$("input[name=ckBoxId]").prop("checked",false);
		}
	})

    /********** inquiry-inbox sidebar **********/
    $('.inquiry-inbox').sidebar({dimPage: false, closable: false, exclusive: true})
    .sidebar('attach events', '.inquiry-button', 'toggle')
    .sidebar('setting', 'transition', 'overlay')
    .sidebar('setting', 'mobileTransition', 'overlay');

    $('.close').click(function() {
        $('.inquiry-inbox').sidebar('hide');
    });
    
    /********** wide-inbox sidebar **********/
    $('.wide-inbox').sidebar({dimPage: false, closable: false, exclusive: true})
    .sidebar('attach events', '.sidebar-button', 'toggle')
    .sidebar('setting', 'transition', 'overlay')
    .sidebar('setting', 'mobileTransition', 'overlay');

    $('.close').click(function() {
        $('.wide-inbox').sidebar('hide');
    });

    /********** TAB show / hide **********/
    $(".listTab li.select a").removeAttr("href");
	$(".listTab.menuBox li").click(function() {
		$(".listTab.menuBox li").removeClass('select');
		$(this).addClass("select");
		$("div.tab_content").hide().eq($(this).index()).show();
		var selected_tab = $(this).find("a").attr("href");
		$(selected_tab).fadeIn();

		return false;
	});

    $(".tab-view a").click(function() {
		$(".tab-view a").removeClass('on');
		$(this).addClass("on");
		$(".tab_content_view").hide().eq($(this).index()).show();
		var selected_tab = $(this).find("a").attr("href");
		$(selected_tab).fadeIn();

		return false;
	});

    /********** select button **********/
	$(".active-btn").click(function() {
		$(".active-btn").removeClass('select');
		$(this).addClass("select");
	});
    
    $('.flex-item-box input[type=checkbox]').change(function () {
        if ($(this).is(":checked")) {
            $(this).parent().parent().find('.chk-box').addClass("on");
        } else {
            $(this).parent().parent().find('.chk-box').removeClass("on");
        }
    });

    /********** toggle_btn **********/
    $('.toggle_btn').click(function() {
		$(this).parent().parent().find('.toggle_box').eq(0).toggleClass('on');
	});

    $(".slide-btn").click(function() {
        $(this).parents().find('.variable-box').toggleClass('full');
        $(this).parents().find('.tip-box').toggleClass('show');
    });
    /********** grid Table **********/
	$('.grid-table tbody td').click(function(event) {
		if (event.target.type !== 'radio') {
			$(':radio', this).trigger('click');
		}
	});
	$.fn.autowidth = function(width) {
		var n = $( ".grid-table th.col" ).length;
		$(this).css({'width' : (70 / $('.grid-table th.col').length) + '%'});
	};
	$('.grid-table th.col').autowidth($(window).innerWidth());

    $.fn.autowidth = function(width) {
		var n = $( ".grid-table.star th.col" ).length;
		$(this).css({'width' : (20 / $('.grid-table.star th.col').length) + '%'});
	};
	$('.grid-table.star th.col').autowidth($(window).innerWidth());


    /********** reponsive length size **********/
    $.fn.autowidth = function(width) {
        var n = $( ".global_tab a" ).length;
        if (width <= 750){
            $('.global_tab a').css({'width' : 50+'%'})
        }
        else {
            $('.global_tab a').css({'width' : (100 / $('.global_tab a').length) + '%'});
            if(n >= 6){
            $('.global_tab a').css({'width' : '25%'})
            }
        }

        var w = $( ".subMenu a" ).length;
        if (width <= 750){
            $('.subMenu li').css({'width' : 50+'%'})
        }
        else {
            $('.subMenu li').css({'width' : (100 / $('.subMenu li').length) + '%'});
            if(w >= 6){
            $('.subMenu li').css({'width' : '20%'})
            }
        }
    };
    $('.global_tab', '.subMenu').autowidth($(window).innerWidth());

    $(window).resize(function() {
        $('.global_tab', '.subMenu').autowidth($(window).innerWidth());
    });

    /********** semantic.button event **********/
    var $buttons = $('.ui.buttons .button');
    handler = {
        activate: function() {
            $(this)
            .addClass('active')
            .siblings()
            .removeClass('active');
        }
    };
    $buttons.on('click', handler.activate);

    var $toggle  = $('.클래스');
    $toggle.state({
        text: {
            inactive : '적용',
            active   : '미적용'
        }
    });

    $('.toggle-use')
    .checkbox({
        onChecked: function() {
            $("label[for='"+$(this).attr("id")+"']").text('사용');
        },
        onUnchecked: function() {
            $("label[for='"+$(this).attr("id")+"']").text('미사용');
        }
    });

    $('.toggle-allow')
    .checkbox({
        onChecked: function() {
            $("label[for='"+$(this).attr("id")+"']").text('허용');
        },
        onUnchecked: function() {
            $("label[for='"+$(this).attr("id")+"']").text('미허용');
        }
    });

    $('.toggle-board')
    .checkbox({
        onChecked: function() {
            $("label[for='"+$(this).attr("id")+"']").text('블로그형');
        },
        onUnchecked: function() {
            $("label[for='"+$(this).attr("id")+"']").text('리스트형');
        }
    });

    $('.toggle-gallery')
    .checkbox({
        onChecked: function() {
            $("label[for='"+$(this).attr("id")+"']").text('뉴스형');
        },
        onUnchecked: function() {
            $("label[for='"+$(this).attr("id")+"']").text('그리드형');
        }
    });


    /********** semantic simple-uploader **********/
    $('input:text, .ui.button', '.simple-uploader').on('click', function(e) {
        $('.simple-uploader input:file', $(e.target).parents()).click();
    });

    $('input:file', '.simple-uploader').on('change', function(e) {
        var name = e.target.files[0].name;
        $('input:text', $(e.target).parent()).val(name);
    });

    /********** mobile login button **********/
    $('#showleftUser').click(function(){
        $(this).parent().find('#loginForm').slideToggle(0);
    });

    /********** image radio-box **********/
    $(".designImg label").each(function(){
        if($(this).find('input[type="radio"]').first().attr("checked")){
            $(this).addClass('on');
        }else{
            $(this).removeClass('on');
        }
    });
    $(".designImg label").on("click", function(e){
        $(".designImg label").removeClass('on');
        $(this).addClass('on');
        var $radio = $(this).find('input[type="radio"]');
        $radio.prop("checked",!$radio.prop("checked"));

        e.preventDefault();
    });

    /********** mCustomScrollbar **********/
//    $("body").mCustomScrollbar({
//        theme: "dark-3",
//        scrollInertia: 100
//    });
    $(".scrollbox").mCustomScrollbar({
        theme: "dark-thin",
        scrollbarPosition: "outside",
        scrollInertia: 100
    });
    
    /********** wrap_btn sticky **********/
    var bottom_button = $('.wrap_btn');
    var box_height = $('#container').height() +100;
    var window_height = $(window).height();
    if(box_height >= window_height) {
        bottom_button.addClass('btn_fixed');
    }if($(bottom_button).length > 0){
        $('#container').css('padding-bottom','6em');
    }
    $(window).scroll(function(){
        con_top = $(this).scrollTop();
        con_width = $(this).width();
        con_height = $(this).height();
        body_height = $(document).height();
        footer_wrap_height = $('footer').height();
        if(con_top + con_height >= body_height - footer_wrap_height) {
            bottom_button.removeClass('btn_fixed');
        }else{
            bottom_button.addClass('btn_fixed');
        }
    });
    
    /********** header info-toggle **********/
    $('.info-toggle').click(function(){
        $(this).parents().find('.classInfo').toggleClass('fold');
    });
    
    /********** grid-table mobile colspan add **********/
    $("tr.mo td").attr("colspan", $(".grid-table thead th").length - 1);
    
    /********** class-LNB **********/
//    $('#class-lnb a, #class-lnb button').on('mouseenter focusin',function(){
//		$('#class-lnb').addClass('active');
//	});
//	
//	var floatingLeave =$('#container a:last-child,#footer a:first-child');
//	
//	floatingLeave.on('focusin',function(){
//		$('#class-lnb').removeClass('active');	
//	})
//	$('#class-lnb').on('mouseleave',function(){
//		$('#class-lnb').removeClass('active');
//	});
    
//    $('.class-menu-btn').click(function(){
//        $(this).parent().toggleClass('active');
//    });
    
    
    $.fn.autowidth = function(width) {
        if (width >= 768){
            $('#class-lnb a, #class-lnb button').on('mouseenter focusin',function(){
                $('#class-lnb').addClass('active');
            });

            var floatingLeave =$('#container a:last-child,#footer a:first-child');

            floatingLeave.on('focusin',function(){
                $('#class-lnb').removeClass('active');	
            })
            $('#class-lnb').on('mouseleave',function(){
                $('#class-lnb').removeClass('active');
            });
        }
        else {
            $('.class-menu-btn').click(function(){
                $(this).parent().toggleClass('active');
            });
        }
    };
    $('#class-lnb').autowidth($(window).innerWidth());

    $(window).resize(function() {
        $('#class-lnb').autowidth($(window).innerWidth());
    });
    
	$(window).resize(function(event){
		controller();
	});
	controller();
 });


function controller(){
	var winWidth = $(window).width();
	if ( winWidth >= 1280 ) {

		/****************************
		 * PC 화면 이벤트
		 ****************************/
//        $('.classSection').attr('style', 'display:block;');
        var flag = false;
        $(window).scroll(function(){
            if  ($(window).scrollTop() >= 60 && flag == false){
                flag = true;
                 $('.gn-menu-wrapper').stop().animate({
                    marginTop: '0'
                    }, 100, 'swing');
            }
            else if  ($(window).scrollTop() <= 60 && flag == true){
                flag = false;
                     $('.gn-menu-wrapper').stop().animate({
                    marginTop: '0'
                    }, 100, 'swing');
             }
        });

	}else if ( winWidth <= 768 ) {

		/****************************
		 * MOBILE 화면 이벤트
		 ****************************/
        var flag = false;
        $(window).scroll(function(){
            if  ($(window).scrollTop() >= 60 && flag == false){
                flag = true;
                 $('.gn-menu-wrapper').stop().animate({
                    marginTop: '-60px'
                    }, 100, 'swing');
            }
            else if  ($(window).scrollTop() <= 60 && flag == true){
                flag = false;
                     $('.gn-menu-wrapper').stop().animate({
                    marginTop: '0'
                    }, 100, 'swing');
             }
        });
        
        $('.listTab li').click(function () {
            $('.listTab li').toggleClass('on');
        });

	}
};
