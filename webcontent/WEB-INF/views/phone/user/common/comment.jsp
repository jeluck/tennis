
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 文件路径地址：/views/phone/user/common/comment.jsp -->


<!--评论 start-->

<section class="lz-mb lz-bg-ground lz-list-re">
    <h4 class="lz-tit-1">评论</h4>
    <ul class="lz-lsit-1" id="lz-comment-list-box">
    	<c:if test="${not empty comment }">
	        <c:forEach items="${comment }" var="co">
	            <li class="lz-default">
	                <div class="lz-headimg"><i style="background-image:url('<c:if test="${not empty co.weuser.head_photo }">${co.weuser.head_photo }</c:if><c:if test="${empty co.weuser.head_photo }">/images/coachJap.png</c:if>');');"></i></div>
	                <div class="lz-content">
	                    <h4><span class="lz-name">${co.weuser.username }</span><span class="lz-sex"><c:if test="${co.weuser.sex=='男' }"><i class="lz-iconfont lz-icon-nan"></i></c:if>
	                            <c:if test="${co.weuser.sex=='女' }"><i class="lz-iconfont lz-icon-nv"></i></c:if>
	                            </span>
	                            <time>${co.create_time }</time>
	                    </h4>
	                    <p> ${co.commentcontent } </p>
	                </div>
	            </li>
        </c:forEach>
        </c:if>
        <c:if test="${empty comment }">
        	<li class="lz-default" id="notComment">暂无评论</li>
        </c:if>
    </ul>
</section>

<!--评论 end-->