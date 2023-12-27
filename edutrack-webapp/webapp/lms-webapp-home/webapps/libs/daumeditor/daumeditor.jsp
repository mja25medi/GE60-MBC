<%@ page pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/page_init.jsp" %>
<c:set scope="page" value="${empty param.imageBtnFlag	 ? 'true' : imageBtnFlag}"     var="imageBtnFlag"/>
<c:set scope="page" value="${empty param.attachBtnFlag	 ? 'true' : attachBtnFlag}"    var="attachBtnFlag"/>
<c:set scope="page" value="${empty param.editorResizeFlag ? 'true' : editorResizeFlag}" var="editorResizeFlag"/>
<spring:url value="/libs/daumeditor" var="daumeditor" />

<!-- 에디터 컨테이너 시작 -->
<div id="tx_trex_container" class="tx-editor-container">
	<!-- 툴바 - 기본 시작 -->
	<!--
		@decsription
		툴바 버튼의 그룹핑의 변경이 필요할 때는 위치(왼쪽, 가운데, 오른쪽) 에 따라 <li> 아래의 <div>의 클래스명을 변경하면 된다.
		tx-btn-lbg: 왼쪽, tx-btn-bg: 가운데, tx-btn-rbg: 오른쪽, tx-btn-lrbg: 독립적인 그룹

		드롭다운 버튼의 크기를 변경하고자 할 경우에는 넓이에 따라 <li> 아래의 <div>의 클래스명을 변경하면 된다.
		tx-slt-70bg, tx-slt-59bg, tx-slt-42bg, tx-btn-43lrbg, tx-btn-52lrbg, tx-btn-57lrbg, tx-btn-71lrbg
		tx-btn-48lbg, tx-btn-48rbg, tx-btn-30lrbg, tx-btn-46lrbg, tx-btn-67lrbg, tx-btn-49lbg, tx-btn-58bg, tx-btn-46bg, tx-btn-49rbg
	-->
	<div id="tx_toolbar_basic" class="tx-toolbar tx-toolbar-basic">
		<div class="tx-toolbar-boundary">
			<ul class="tx-bar tx-bar-left">
				<li class="tx-list">
					<div id="tx_fontfamily" unselectable="on" class="tx-slt-70bg tx-fontfamily">
						<a href="javascript:;" title="<spring:message code="board.title.editor.fontfamily"/>"><spring:message code="board.title.editor.fontfamily.gulim"/></a>
					</div>
					<div id="tx_fontfamily_menu" class="tx-fontfamily-menu tx-menu" unselectable="on"></div>
				</li>
			</ul>
			<ul class="tx-bar tx-bar-left">
				<li class="tx-list">
					<div unselectable="on" class="tx-slt-42bg tx-fontsize" id="tx_fontsize">
						<a href="javascript:;" title="<spring:message code="board.title.editor.fontsize"/>">9pt</a>
					</div>
					<div id="tx_fontsize_menu" class="tx-fontsize-menu tx-menu" unselectable="on"></div>
				</li>
			</ul>
			<ul class="tx-bar tx-bar-left tx-group-font">
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-lbg tx-bold" id="tx_bold">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.fontweight"/> (Ctrl+B)"><spring:message code="board.title.editor.fontweight.bold"/></a>
					</div>
				</li>
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-bg tx-underline" id="tx_underline">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.font.underline"/> (Ctrl+U)"><spring:message code="board.title.editor.font.underline"/></a>
					</div>
				</li>
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-bg tx-italic" id="tx_italic">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.font.italic"/> (Ctrl+I)"><spring:message code="board.title.editor.font.italic"/></a>
					</div>
				</li>
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-bg tx-strike" id="tx_strike">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.font.cancelline"/> (Ctrl+D)"><spring:message code="board.title.editor.font.cancelline"/></a>
					</div>
				</li>
				<li class="tx-list">
					<div unselectable="on" class="tx-slt-tbg tx-forecolor" id="tx_forecolor">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.fontcolor"/>"><spring:message code="board.title.editor.fontcolor"/></a>
						<a href="javascript:;" class="tx-arrow" title="<spring:message code="board.title.editor.fontcolor.select"/>"><spring:message code="board.title.editor.fontcolor.select"/></a>
					</div>
					<div id="tx_forecolor_menu" class="tx-menu tx-forecolor-menu tx-colorpallete" unselectable="on"></div>
				</li>
				<li class="tx-list">
					<div unselectable="on" class="tx-slt-brbg tx-backcolor" id="tx_backcolor">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.fontbgcolor"/>"><spring:message code="board.title.editor.fontbgcolor"/></a>
						<a href="javascript:;" class="tx-arrow" title="<spring:message code="board.title.editor.fontbgcolor.select"/>"><spring:message code="board.title.editor.fontbgcolor.select"/></a>
					</div>
					<div id="tx_backcolor_menu" class="tx-menu tx-backcolor-menu tx-colorpallete" unselectable="on"></div>
				</li>
			</ul>
			<ul class="tx-bar tx-bar-left tx-group-align">
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-lbg tx-alignleft" id="tx_alignleft">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.align.left"/> (Ctrl+,)"><spring:message code="board.title.editor.align.left"/></a>
					</div>
				</li>
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-bg tx-aligncenter" id="tx_aligncenter">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.align.center"/> (Ctrl+.)"><spring:message code="board.title.editor.align.center"/></a>
					</div>
				</li>
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-bg tx-alignright" id="tx_alignright">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.align.right"/> (Ctrl+/)"><spring:message code="board.title.editor.align.right"/></a>
					</div>
				</li>
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-rbg tx-alignfull" id="tx_alignfull">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.align.full"/>"><spring:message code="board.title.editor.align.full"/></a>
					</div>
				</li>
			</ul>
			<ul class="tx-bar tx-bar-left tx-group-tab">
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-lbg tx-indent" id="tx_indent">
						<a href="javascript:;" title="<spring:message code="board.title.editor.text.indent"/> (Tab)" class="tx-icon">들<spring:message code="board.title.editor.text.indent"/></a>
					</div>
				</li>
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-rbg tx-outdent" id="tx_outdent">
						<a href="javascript:;" title="<spring:message code="board.title.editor.text.outdent"/> (Shift+Tab)" class="tx-icon"><spring:message code="board.title.editor.text.outdent"/></a>
					</div>
				</li>
			</ul>
			<ul class="tx-bar tx-bar-left tx-group-list">
				<li class="tx-list">
					<div unselectable="on" class="tx-slt-31lbg tx-lineheight" id="tx_lineheight">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.lineheight"/>"><spring:message code="board.title.editor.lineheight"/></a>
						<a href="javascript:;" class="tx-arrow" title="<spring:message code="board.title.editor.lineheight"/>"><spring:message code="board.title.editor.lineheight.select"/></a>
					</div>
					<div id="tx_lineheight_menu" class="tx-lineheight-menu tx-menu" unselectable="on"></div>
				</li>
				<li class="tx-list">
					<div unselectable="on" class="tx-slt-31rbg tx-styledlist" id="tx_styledlist">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.list"/>"><spring:message code="board.title.editor.list"/></a>
						<a href="javascript:;" class="tx-arrow" title="<spring:message code="board.title.editor.list"/>"><spring:message code="board.title.editor.list.select"/></a>
					</div>
					<div id="tx_styledlist_menu" class="tx-styledlist-menu tx-menu" unselectable="on"></div>
				</li>
			</ul>
			<ul class="tx-bar tx-bar-left tx-group-etc">
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-lbg tx-emoticon" id="tx_emoticon">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.emoticon"/>"><spring:message code="board.title.editor.emoticon"/></a>
					</div>
					<div id="tx_emoticon_menu" class="tx-emoticon-menu tx-menu" unselectable="on"></div>
				</li>
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-bg tx-link" id="tx_link">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.link"/> (Ctrl+K)"><spring:message code="board.title.editor.link"/></a>
					</div>
					<div id="tx_link_menu" class="tx-link-menu tx-menu"></div>
				</li>
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-bg tx-specialchar" id="tx_specialchar">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.specialchar"/>"><spring:message code="board.title.editor.specialchar"/></a>
					</div>
					<div id="tx_specialchar_menu" class="tx-specialchar-menu tx-menu"></div>
				</li>
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-bg tx-table" id="tx_table">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.table.make"/>"><spring:message code="board.title.editor.table.make"/></a>
					</div>
					<div id="tx_table_menu" class="tx-table-menu tx-menu" unselectable="on">
						<div class="tx-menu-inner">
							<div class="tx-menu-preview"></div>
							<div class="tx-menu-rowcol"></div>
							<div class="tx-menu-deco"></div>
							<div class="tx-menu-enter"></div>
						</div>
					</div>
				</li>
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-rbg tx-horizontalrule" id="tx_horizontalrule">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.horizontalrule"/>"><spring:message code="board.title.editor.horizontalrule"/></a>
					</div>
					<div id="tx_horizontalrule_menu" class="tx-horizontalrule-menu tx-menu" unselectable="on"></div>
				</li>
			</ul>
			<ul class="tx-bar tx-bar-left">
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-lbg tx-richtextbox" id="tx_richtextbox">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.textbox"/>"><spring:message code="board.title.editor.textbox"/></a>
					</div>
					<div id="tx_richtextbox_menu" class="tx-richtextbox-menu tx-menu">
						<div class="tx-menu-header">
							<div class="tx-menu-preview-area">
								<div class="tx-menu-preview"></div>
							</div>
							<div class="tx-menu-switch">
								<div class="tx-menu-simple tx-selected"><a><span><spring:message code="board.title.editor.select.easy"/></span></a></div>
								<div class="tx-menu-advanced"><a><span><spring:message code="board.title.editor.select.hard"/></span></a></div>
							</div>
						</div>
						<div class="tx-menu-inner"></div>
						<div class="tx-menu-footer">
							<img class="tx-menu-confirm" src="${daumeditor}/images/icon/editor/btn_confirm.gif?rv=1.0.1" alt=""/>
							<img class="tx-menu-cancel" hspace="3" src="${daumeditor}/images/icon/editor/btn_cancel.gif?rv=1.0.1" alt=""/>
						</div>
					</div>
				</li>
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-bg tx-quote" id="tx_quote">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.quote"/> (Ctrl+Q)"><spring:message code="board.title.editor.quote" /></a>
					</div>
					<div id="tx_quote_menu" class="tx-quote-menu tx-menu" unselectable="on"></div>
				</li>
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-bg tx-background" id="tx_background">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.bgcolor" />"><spring:message code="board.title.editor.bgcolor" /></a>
					</div>
					<div id="tx_background_menu" class="tx-menu tx-background-menu tx-colorpallete" unselectable="on"></div>
				</li>
				<!-- li class="tx-list">
					<div unselectable="on" class="tx-btn-rbg tx-dictionary" id="tx_dictionary">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.dictionary" />"><spring:message code="board.title.editor.dictionary" /></a>
					</div>
				</li -->
			</ul>
			<ul class="tx-bar tx-bar-left tx-group-undo">
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-lbg tx-undo" id="tx_undo">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.undo" /> (Ctrl+Z)"><spring:message code="board.title.editor.undo" /></a>
					</div>
				</li>
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-rbg tx-redo" id="tx_redo">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.redo" /> (Ctrl+Y)"><spring:message code="board.title.editor.redo" /></a>
					</div>
				</li>
			</ul>
			<ul class="tx-bar tx-bar-right">
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-nlrbg tx-advanced" id="tx_advanced">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.toolbar.more"/>"><spring:message code="board.title.editor.toolbar.more"/></a>
					</div>
				</li>
			</ul>
		</div>
	</div>
	<!-- 툴바 - 기본 끝 -->
	<!-- 툴바 - 더보기 시작 -->
	<div id="tx_toolbar_advanced" class="tx-toolbar tx-toolbar-advanced">
		<div class="tx-toolbar-boundary">
			<ul class="tx-bar tx-bar-left">
				<li class="tx-list">
					<div style="padding-top:2px;"><spring:message code="board.title.editor.table.edit"/> </div>
				</li>
			</ul>
			<ul class="tx-bar tx-bar-left tx-group-align">
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-lbg tx-mergecells" id="tx_mergecells">
						<a href="javascript:;" class="tx-icon2" title="<spring:message code="board.title.editor.table.marge"/>"><spring:message code="board.title.editor.table.marge"/></a>
					</div>
					<div id="tx_mergecells_menu" class="tx-mergecells-menu tx-menu" unselectable="on"></div>
				</li>
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-bg tx-insertcells" id="tx_insertcells">
						<a href="javascript:;" class="tx-icon2" title="<spring:message code="board.title.editor.table.insert"/>"><spring:message code="board.title.editor.table.insert"/></a>
					</div>
					<div id="tx_insertcells_menu" class="tx-insertcells-menu tx-menu" unselectable="on"></div>
				</li>
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-rbg tx-deletecells" id="tx_deletecells">
						<a href="javascript:;" class="tx-icon2" title="<spring:message code="board.title.editor.table.delete"/>"><spring:message code="board.title.editor.table.delete"/></a>
					</div>
					<div id="tx_deletecells_menu" class="tx-deletecells-menu tx-menu" unselectable="on"></div>
				</li>
			</ul>
			<ul class="tx-bar tx-bar-left tx-group-align">
				<li class="tx-list">
					<div id="tx_cellslinepreview" unselectable="on" class="tx-slt-70lbg tx-cellslinepreview">
						<a href="javascript:;" title="<spring:message code="board.title.editor.table.preview.line"/>"></a>
					</div>
					<div id="tx_cellslinepreview_menu" class="tx-cellslinepreview-menu tx-menu" unselectable="on"></div>
				</li>
				<li class="tx-list">
					<div id="tx_cellslinecolor" unselectable="on" class="tx-slt-tbg tx-cellslinecolor">
						<a href="javascript:;" class="tx-icon2" title="<spring:message code="board.title.editor.table.select.line"/>"><spring:message code="board.title.editor.table.select.line"/></a>
						<div class="tx-colorpallete" unselectable="on"></div>
					</div>
					<div id="tx_cellslinecolor_menu" class="tx-cellslinecolor-menu tx-menu tx-colorpallete"	unselectable="on"></div>
				</li>
				<li class="tx-list">
					<div id="tx_cellslineheight" unselectable="on" class="tx-btn-bg tx-cellslineheight">
						<a href="javascript:;" class="tx-icon2" title="<spring:message code="board.title.editor.table.border.line"/>"><spring:message code="board.title.editor.table.border.line"/></a>
					</div>
					<div id="tx_cellslineheight_menu" class="tx-cellslineheight-menu tx-menu" unselectable="on"></div>
				</li>
				<li class="tx-list">
					<div id="tx_cellslinestyle" unselectable="on" class="tx-btn-bg tx-cellslinestyle">
						<a href="javascript:;" class="tx-icon2" title="<spring:message code="board.title.editor.table.style.line"/>"><spring:message code="board.title.editor.table.style.line"/></a>
					</div>
					<div id="tx_cellslinestyle_menu" class="tx-cellslinestyle-menu tx-menu" unselectable="on"></div>
				</li>
				<li class="tx-list">
					<div id="tx_cellsoutline" unselectable="on" class="tx-btn-rbg tx-cellsoutline">
						<a href="javascript:;" class="tx-icon2" title="<spring:message code="board.title.editor.table.outline.line"/>"><spring:message code="board.title.editor.table.outline.line"/></a>
					</div>
					<div id="tx_cellsoutline_menu" class="tx-cellsoutline-menu tx-menu" unselectable="on"></div>
				</li>
			</ul>
			<ul class="tx-bar tx-bar-left">
				<li class="tx-list">
					<div id="tx_tablebackcolor" unselectable="on" class="tx-btn-lrbg tx-tablebackcolor" style="background-color:#9aa5ea;">
						<a href="javascript:;" class="tx-icon2" title="<spring:message code="board.title.editor.table.bgcolor"/>"><spring:message code="board.title.editor.table.bgcolor"/></a>
					</div>
					<div id="tx_tablebackcolor_menu" class="tx-tablebackcolor-menu tx-menu tx-colorpallete"	unselectable="on"></div>
				</li>
			</ul>
			<ul class="tx-bar tx-bar-left">
				<li class="tx-list">
					<div id="tx_tabletemplate" unselectable="on" class="tx-btn-lrbg tx-tabletemplate">
						<a href="javascript:;" class="tx-icon2" title="<spring:message code="board.title.editor.table.template"/>"><spring:message code="board.title.editor.table.template"/></a>
					</div>
					<div id="tx_tabletemplate_menu" class="tx-tabletemplate-menu tx-menu tx-colorpallete" unselectable="on"></div>
				</li>
			</ul>
		</div>
	</div>
	<!-- 툴바 - 더보기 끝 -->
	<!-- 사이드바 -->
	<div id="tx_sidebar" class="tx-sidebar">
		<div class="tx-sidebar-boundary">
			<!-- 사이드바 / 첨부 -->
			<ul class="tx-bar tx-bar-left tx-nav-attach">
				<!-- 이미지 첨부 버튼 시작 -->
				<li class="tx-list">
					<div unselectable="on" id="tx_image" class="tx-btn-trans" style="padding:5px 0px 0px 5px;">
						<a href="javascript:;" title="<spring:message code="board.title.editor.photo"/>" ><i class="fa fa-photo"></i> <spring:message code="board.title.editor.photo"/></a>
					</div>
				</li>
				<!-- 이미지 첨부 버튼 끝 -->
				<%--
				<li class="tx-list">
					<div unselectable="on" id="tx_file" class="tx-file tx-btn-trans">
						<a href="javascript:;" title="파일" class="tx-text">파일</a>
					</div>
				</li>
				--%>
				<li class="tx-list">
					<div unselectable="on" id="tx_media" class="tx-btn-trans" style="padding:5px 0px 0px 5px;">
						<a href="javascript:;" title="<spring:message code="board.title.editor.media"/>"><i class="fa fa-film "></i> <spring:message code="board.title.editor.media"/></a>
					</div>
				</li>
			</ul>
			<!-- 사이드바 / 우측영역 -->
			<ul class="tx-bar tx-bar-right">
				<li class="tx-list">
					<div unselectable="on" class="tx-btn-lrbg tx-fullscreen" id="tx_fullscreen">
						<a href="javascript:;" class="tx-icon" title="<spring:message code="board.title.editor.fullscreen"/> (Ctrl+M)"><spring:message code="board.title.editor.fullscreen"/></a>
					</div>
				</li>
			</ul>
			<ul class="tx-bar tx-bar-right tx-nav-opt">
				<li class="tx-list">
					<div unselectable="on" class="tx-switchtoggle" id="tx_switchertoggle">
						<a href="javascript:;" title="<spring:message code="board.title.editor.type"/>"><spring:message code="board.title.editor.type"/></a>
					</div>
				</li>
			</ul>
		</div>
	</div>

	<!-- 편집영역 시작 -->
	<!-- 에디터 Start -->
	<div id="tx_canvas" class="tx-canvas">
		<div id="tx_loading" class="tx-loading">
			<div><img src="${daumeditor}/images/icon/editor/loading2.png" width="113" height="21" align="absmiddle"/></div>
		</div>
		<div id="tx_canvas_wysiwyg_holder" class="tx-holder" style="display:block;">
			<iframe id="tx_canvas_wysiwyg" name="tx_canvas_wysiwyg" allowtransparency="true" frameborder="0"></iframe>
		</div>
		<div class="tx-source-deco">
			<div id="tx_canvas_source_holder" class="tx-holder">
				<textarea id="tx_canvas_source" rows="30" cols="30"></textarea>
			</div>
		</div>
		<div id="tx_canvas_text_holder" class="tx-holder">
			<textarea id="tx_canvas_text" rows="30" cols="30"></textarea>
		</div>
	</div>
	<!-- 높이조절 Start -->
	<div id="tx_resizer" class="tx-resize-bar">
		<div class="tx-resize-bar-bg"></div>
		<img id="tx_resize_holder" src="${daumeditor}/images/icon/editor/skin/01/btn_drag01.gif" width="58" height="12" unselectable="on" alt="" />
	</div>
	<div class="tx-side-bi" id="tx_side_bi">
		<div style="text-align: right;">
			<img hspace="4" height="14" width="78" align="absmiddle" src="${daumeditor}/images/icon/editor/editor_bi.png" />
		</div>
	</div>

	<!-- 편집영역 끝 -->
	<!-- 첨부박스 시작 -->
	<!-- 파일첨부박스 Start -->
	<div id="tx_attach_div" class="tx-attach-div">
		<div id="tx_attach_box" class="tx-attach-box">
			<div class="tx-attach-box-inner">
				<div id="tx_attach_preview" class="tx-attach-preview"><p></p><img src="${daumeditor}/images/icon/editor/pn_preview.gif" width="147" height="108" unselectable="on"/></div>
				<div class="tx-attach-main">
					<div id="tx_upload_progress" class="tx-upload-progress"><div>0%</div><p><spring:message code="board.message.editor.file.uploading"/></p></div>
					<ul class="tx-attach-top">
						<li id="tx_attach_delete" ><a class="btn btn-default btn-xs"><spring:message code="board.title.editor.deleteall"/></a></li>
						<li id="tx_attach_size" class="tx-attach-size">
							<spring:message code="board.title.editor.file"/>: <span id="tx_attach_up_size" class="tx-attach-size-up"></span>/<span id="tx_attach_max_size"></span>
						</li>
						<li id="tx_attach_tools" class="tx-attach-tools">	</li>
					</ul>
					<ul id="tx_attach_list" class="tx-attach-list"></ul>
				</div>
			</div>
		</div>
	</div>
	<!-- 첨부박스 끝 -->
</div>
<!-- 에디터 컨테이너 끝 -->
<script type="text/javascript">
	$(document).ready(function() {
		//-- 모바일 기기에서는 켄버스만 사용 가능하도록 함.
		if(isMobile()) {
			$("#tx_toolbar_basic").hide();
			$("#tx_sidebar").hide();
		}
	});

	function isMobile(){
	    return (/(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino|android|ipad|playbook|silk/i.test(navigator.userAgent||navigator.vendor||window.opera)||/1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test((navigator.userAgent||navigator.vendor||window.opera).substr(0,4)))
	}
</script>