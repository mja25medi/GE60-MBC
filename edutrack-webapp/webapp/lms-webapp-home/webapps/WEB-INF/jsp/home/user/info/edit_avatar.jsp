<%@ page language="java" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/jsp/common/page_init.jsp" %>

<script type="module" src="https://unpkg.com/@google/model-viewer/dist/model-viewer.min.js"></script>
<style>
model-viewer {
  width: 280px;
  height: 310px;
}
</style>

<!--
<iframe id="avatarFrame" src="${avatarEditUrl}" style="width: 100%; height: 60vh;"></iframe> 
   -->                   

         <div class="member">
             <div class="flex-container">
                 <div class="con_avatar">
                     <div class="title">나의 아바타</div>
                     <div class="avatar_area">
                     	<c:if test="${empty vo.avatar}">
                         	<i class="xi-man"></i>
                         </c:if>
                         <!-- 아바타 -->
                   		  <c:if test="${not empty vo.avatar}">
                        		<!-- <img id="avatar_img" src="${fn:replace(vo.avatar,'glb','png')}">-->
                        		<model-viewer id="model-ex" st src="${vo.avatar}" alt="model sample" camera-controls></model-viewer>
                          </c:if>
                     </div>
                     <div class="entry_btn">
                         <button type="submit" title="${empty vo.avatar ? '등록하기' : '편집하기'}" onclick="editAvatar();">${empty vo.avatar ? '등록하기' : '편집하기'}</button>
                     </div>
                 </div>
             </div>
         </div>


        <script type="text/javascript">
        
	        var modalBox = null;
	        $(document).ready(function() {
	        	modalBox = new $M.ModalDialog({
	        		"modalid" : "modal1",
	        		"nomargin"	: true
	        	});
	        });	
        
            
            /**
             * 아바타 편집
             */
            function editAvatar() {
            	var addContent = "<iframe id='avatartForm' name='avatartForm' width='100%' height='100%' "
            		+ "frameborder='0' scrolling='no' src='<c:url value="/home/user/editFormUserAvatar"/>'/>";
            	modalBox.clear();
            	modalBox.addContents(addContent);
            	modalBox.resize(900, 680);
            	modalBox.setTitle("아바타 편집");
            	modalBox.show();
            }
            
        </script>

