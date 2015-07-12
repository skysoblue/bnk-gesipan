<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="context" value="<%=request.getContextPath() %>"></c:set>
 <%=request.getAttribute("msg")%> 
    <!-- 
     로그인 실패의 원인이
     ID 값이 틀린 건지, 비번이 틀린 건지 알려주세요
     -->
    