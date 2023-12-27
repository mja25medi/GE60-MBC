	<script type="text/javascript">
		var curPage = 1;
		$(document).ready(function(){
			$("._enterBind").bind("keydown", eventForSearch);
			function eventForSearch(event) {
				if (event.keyCode == '13') {
					listPageing(1);
				}
			}
		});
		
		function listPageing(page) {
			curPage = page;
			var searchKey = $("#searchKey option:selected").val();
			var searchValue = $("#searchValue").val();
			var headCd = $("#headCd option:selected").val();
			var url = generateUrl("/mng/brd/bbs/listAtclMain", { "pageIndex":page, "bbsCd":"${vo.bbsCd}", "searchKey":searchKey, "searchValue":searchValue, "headCd":headCd});
			document.location.href = url;
		}
		
		function goBoardInfoMain(){
			var url = generateUrl("/mng/brd/bbs/infoMain");
			document.location.href = url;
		}

		function readAtcl(atclSn) {
			var searchKey = $("#searchKey option:selected").val();
			var searchValue = $("#searchValue").val();
			var headCd = $("#headCd option:selected").val();
			var url = generateUrl("/mng/brd/bbs/viewAtclMain", { "pageIndex":curPage, "bbsCd":"${vo.bbsCd}", "searchKey": searchKey, "searchValue":searchValue, "headCd":headCd, "atclSn": atclSn});
			document.location.href = url;
		}

		/**
		 * 게시판 글 등록 폼
		 */
		function addAtcl() {
			var searchKey = $("#searchKey option:selected").val();
			var searchValue = $("#searchValue").val();
			var headCd = $("#headCd option:selected").val();
			var url = generateUrl("/mng/brd/bbs/addFormAtclMain",{"pageIndex":curPage, "bbsCd":"${vo.bbsCd}", "searchKey": searchKey, "searchValue":searchValue, "headCd":headCd})
			document.location.href = url;
		}

		/**
		 * 게시판 글 수정 폼
		 */
		function editAtcl(atclSn) {
			var searchKey = $("#searchKey option:selected").val();
			var searchValue = $("#searchValue").val();
			var headCd = $("#headCd option:selected").val();
			var url = generateUrl("/mng/brd/bbs/editFormAtclMain", { "pageIndex":curPage, "bbsCd":"${vo.bbsCd}", "searchKey": searchKey, "searchValue":searchValue, "headCd":headCd, "atclSn": atclSn});
			document.location.href = url;	
		}
	</script>