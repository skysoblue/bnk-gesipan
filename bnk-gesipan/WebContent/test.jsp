<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>테스트</title>
</head>
<body>
	<c:set var="hello" value="Hello" />
	${hello}
	<!-- scope 속성은 선택적이며 page로 기본 설정되어 있다. -->
	<c:set var="변수명" value="할당된 값"
		scope="변수의 유효 범위 page|request|session|application" />

	<!-- 간단 사용 예 -->
	<c:set var="country" value="Korea" />
	<c:set var="intArray" value="<%=new int[] { 1, 2, 3, 4, 5 }%>" />
	<c:remove var="country" />
	<!-- Syntax -->


	<!-- 간단 사용 예 -->
	<p>
		<c:out value="${country}" default="Korea" escapeXml="true" />
	</p>
	<p>${country}</p>
	<p>${intArray[0]}</p>

	<!-- Syntax -->
	<c:if test="expression" var="name" scope="scope">
		body content
	</c:if>

	<!-- 간단 사용 예 -->
	<c:set var="login" value="true" />

	<c:if test="${!login}">
		<p>
			<a href="/login.ok">로그인</a>
		</p>
	</c:if>
	<c:if test="${login}">
		<p>
			<a href="/logout.ok">로그아웃</a>
		</p>
	</c:if>

	<!-- 아래 예제와 같이 null 비교를 하지 않고 empty 비교를 하면 null과 ""를 동시에 체크할 수 있다. -->
	<c:if test="${!empty country}">
		<p>
			<b>${country}</b>
		</p>
	</c:if>
	<!-- Syntax -->
	<c:choose>
		<c:when test="expression">
			body content
		</c:when>
			...
		<c:otherwise>
			body content
		</c:otherwise>
	</c:choose>

	<!-- 간단 사용 예 -->
	<c:choose>
		<c:when test="${login}">
			<p>
				<a href="/logout.ok">로그아웃</a>
			</p>
		</c:when>
		<c:otherwise>
			<p>
				<a href="/login.ok">로그인</a>
			</p>
		</c:otherwise>
	</c:choose>
	<!-- formatNumber -->
	<p>
		number :
		<fmt:formatNumber value="1234567.89" type="number" />
	</p>
	<p>
		currency :
		<fmt:formatNumber value="1234567.89" type="currency"
			currencySymbol="￦" />
	</p>
	<p>
		percent :
		<fmt:formatNumber type="percent">0.159</fmt:formatNumber>
	</p>
	<p>
		pattern=".000" :
		<fmt:formatNumber value="1234567.1" pattern=".000" />
	</p>
	<p>
		pattern="#,#00.0#":
		<fmt:formatNumber value="1234567.891" pattern="#,#00.0#" />
	</p>

	<!-- parseNumber  -->
	<p>
		number :
		<fmt:parseNumber value="1,234,567.89" type="number" />
	</p>
	<p>
		currency :
		<fmt:parseNumber value="12345abcdef" integerOnly="false" type="number" />
	</p>

	<!-- formatDate -->
	<c:set var="now" value="<%=new java.util.Date()%>" />
	<p>
		date full :
		<fmt:formatDate value="${now}" type="date" dateStyle="full" />
	</p>
	<p>
		date short :
		<fmt:formatDate value="${now}" type="date" dateStyle="short" />
	</p>
	<p>
		time :
		<fmt:formatDate value="${now}" type="time" />
	</p>
	<p>
		both full :
		<fmt:formatDate value="${now}" type="both" dateStyle="full"
			timeStyle="full" />
	</p>
	<p>
		pattern 1 :
		<fmt:formatDate value="${now}" pattern="yyyy-MM-dd aa hh:mm:ss" />
	</p>
	<p>
		pattern 2 :
		<fmt:formatDate value="${now}" pattern="yyyy-MM-dd  hh:mm:ss" />
	</p>
</body>
</html>