$(function () { 

    //첨부파일 삭제버튼 
    $attArea = $(".attach_area");
    attName = ".attach_name";
	$attArea.on("click", ".attach_close", function(){
		$(this).closest(attName).remove();
        if(!$(attName).is(attName)){
            $(".attach_list").remove();
        }
        
	});

    /*  해시태그  */  
    $hr = $(".hash_area");
    $hi = $(".hash_input");
    //해시태그 추가 
    $(".hash_add").on("click", function(){
    	$iT = $hi.val();
    	
    	// text 수 제한
    	if($iT.length > 10){
    		alert("10자 이상 택스트를 작성할 수 없습니다.\n다시 작성 해주세요.");
    		$hi.val("");
    		return false;
    	}
    	
    	// '#' Text 막기
    	if($iT.indexOf("#") > -1){
    		alert("'#'은 사용할 수 없습니다.\n다시 작성 해주세요.");
    		$hi.val("");
    		return false;
    	}

        if($iT){		      
            $hr.append("<button type='button' class='hash_tag'><span class='tagSpan'>#"+$iT+"</span><i class='xi-close-circle'></i><span class='sr-only'>파일삭제</span></button>");
            $hi.val("");
        }
	});
    $hi.on("keyup", function(key){        
		if(key.keyCode == 13){           
            $(".hash_add").trigger("click");
        }        
	});
    
    //해시태그 삭제
    $hr.on("click", "button", function(){
		$(this).remove();
	});

    //리스트 sort
    clickAC(".sort button");    
    
    //리스트 슬라이드
    $listUl = $(".list_slide .list_li");
    if($listUl.length > 3){
        bizSlider(".list_slide .list_ul")
    }


    //댓글
     $(".commentList .btn-recomment").on("click", function(){
        $(this).closest("li").toggleClass("active");
      });

    //FAQ
    $(".listFaq .question").on("click", function(){
        active(this, "accordion");
    });
    
    if($(".admin_btn").text()!=""){
        $(".admin_btn").closest(".btns").addClass("justify-content-between");
    }
    
 
   
});

//갤러리 리스트 슬라이드 옵션
var bizSlickOption = {
    slidesToShow: 3,
    slidesToScroll: 1,
    autoplay: false,       
    infinite: true,
    arrows: true, 
    speed: 1000,
    responsive: [
      {
          breakpoint: 767,
          settings: {
            slidesToShow: 1,
            slidesToScroll: 1
          }
      }
    ]
}
function bizSlider(obj){
    $(obj).slick(bizSlickOption);    
}

/* 좋아요 */
function randomNum(m, n) {
    return Math.floor((Math.random() * (n - m + 1)) * 100)/100 + m;
}
function heartAnimation() {
    $this = $('.etc_info .heart_ani');
    var heartCount = ($this.width()/50)*5;
    for (var i = 0; i< heartCount; i++) {
        var heartSize = (randomNum(50, 100) / 10);
        $this.append('<i class="xi-heart" style="top: '+ randomNum(-30, 80) +'%; left: '+ randomNum(0, 80) +'%; font-size: '+ heartSize +'rem; animation-delay: '+ randomNum(0, 1) +'s; animation-duration: '+ heartSize/10 +'s"></i>')
    }
}

/* 탭메뉴 */
function depth4(id){    
    $(id).show().siblings("div").hide();    
}