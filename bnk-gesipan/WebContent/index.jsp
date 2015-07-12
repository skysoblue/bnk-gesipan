<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="context" value="<%=request.getContextPath() %>"></c:set>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>인덱스</title>
</head>
<body>
	<jsp:forward page="${context}/home/main.do"></jsp:forward>
</body>
</html>