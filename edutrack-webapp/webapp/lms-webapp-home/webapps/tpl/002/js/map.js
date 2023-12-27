$(function () { 	
	 /* kakao_map - map_marker */ 
	   var $makerInfo = $(".marker_info");
	   $(document).on("click",".map_api .marker img", function(event){	   
	    $makerInfo.css("z-index", "20");
	    $(this).next().css("z-index","999").parent().toggleClass(AC);
	   }); 

	/*   $(document).on("click","#likeLink", function(event){
		   $(this).toggleClass(AC);
	   });*/
	   
	   //map_search 지역선택
	   var listSelect = ".list_select",
	       listOpen = ".list_open",
	       list = ".list";
	   $(document).on("click",".list_open", function(event){	   
	    $(this).next().slideToggle(200)
	    .closest(listSelect).toggleClass(AC)
	    .siblings().removeClass(AC).children(list).slideUp(100);
	   });
	   
	   //map_search 업종선택
	   $(document).on("click",".map_close", function(event){
		   $(".map_modal").fadeOut(200);
	   });

	    var $mapPiece = $("[class^='group']"), //지도 행정구역
	        $selectEnd = $("#selectEnd"); //시군구

	   // 정성훈 프로 - 동적 html로 인하여 이벤트 수정
	   $(document).on("click",".selectButton", function(event){
	    var btnValue = $(this).val();
	    $(this).closest(listSelect).toggleClass(AC).find(listOpen).text(btnValue);
	    $(this).closest(list).slideToggle(200);
	    //시군구
	    btnValue=="전국" ? $selectEnd.fadeOut(200).add($mapPiece).removeClass(AC) : $selectEnd.fadeIn(200);
	    //리스트 클릭시 지도 활성화
	    $mapPiece.each(function(){
	        if(btnValue == $(this).data("name")) $(this).toggleClass(AC).siblings().removeClass(AC);
	    }); 
	   });
	   
	   $(".select_btn").on("click", function(){
			$(this).add(".map_search").toggleClass(AC);			   
			$("#map_search").stop().animate({scrollTop: 0}, 200);
	   });
		
	   /* 지도로 찾는 유기견센터 */
	   $mapPiece.on("click", function(){
	        $(this).toggleClass(AC).siblings().removeClass(AC);
	        $("#select .list_open").text($(this).data("name"));
	        $selectEnd.fadeIn(200); 
	        
	        /* 유기견 맵 찍고 서취 */
	        $('#keyWord').val("");	/*검색 초기화*/
	        $("#selectText").val($(this).data("name"));
	        
	        /* 시군도 리스트 생성 */
	        selectEvent01(null,$(this).data("name"));
	    });
})
