<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="context" value="<%=request.getContextPath() %>"></c:set>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<!doctype html> <!-- html:5  html5로 기본 구성잡기 -->
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>Only Content Frame</title>
	<style>@import url("${context}/css/common.css");</style>
</head>
<body>
<div id="wrap" class="clear">
	<jsp:include page="header.jsp"/>
		<section class="content">
			<div>
			<br />
				<table class="tab" align="center">
					<tr style="height: 50px">
						<td ><a href="#">방명록</a></td>
						<td ><a href="#">답글게시판</a></td>
						<td ><a href="#">자료실</a></td>
					</tr>
					<tr >
						<td colspan="3">
						
						</td>
					</tr>
				</table>
				
				<br /><br /><br /><br />
				
				</div>
			</section>
</div>
		
<jsp:include page="footer.jsp"/>
</body>
</html>