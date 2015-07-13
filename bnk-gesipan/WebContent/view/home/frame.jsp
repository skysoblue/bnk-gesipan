<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="context" value="<%=request.getContextPath() %>"></c:set>

<!doctype html> <!-- html:5  html5로 기본 구성잡기 -->
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>Only Content Frame</title>
	<style>@import url("${context}/css/common.css");</style>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
<div id="wrap" class="clear">
	<jsp:include page="header.jsp"/>
		<section class="content">
			<div>
			<br />
			<ul class="tabKey" style="width:1000px;">
				<li class="depth" style="width:32%;text-align: center;border:1px solid black">
					<a href="javascript:void(0);" style="width: 98%">방명록</a>
					<div > 1 </div>
				</li>
				<li class="depth" style="width:32%;text-align: center;border:1px solid black">
					<a href="javascript:void(1)"  style="width: 98%">답글게시판</a>
					<div > 2 </div>
				</li>
				<li class="depth" style="width:32%;text-align: center;border:1px solid black">
					<a href="javascript:void(2)"  style="width: 98%">자료실</a>
					<div > 3 </div>
				</li>
			</ul>
				
				
				<br /><br /><br /><br />
				
				</div>
			</section>
</div>
<script src="${context}/js/tabKey.js"></script>
<script type="text/javascript">
	$(function() {
		tabKey.tab();
	});
</script>
<jsp:include page="footer.jsp"/>
</body>
</html>