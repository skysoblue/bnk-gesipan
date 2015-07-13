<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="context" value="<%=request.getContextPath() %>"></c:set>
<style>@import url(${context}/css/common.css);</style>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>	

 <table style="width: 100%; border: 1px solid black;">
        <tr style="align:center;border: 1px solid black;">
            <th style="width: 20%">ID</th>
            <th style="width: 30%">이름</th>
            <th style="width: 10%">나이</th>
            <th style="width: 40%">이메일</th>
        </tr>
        <c:forEach var="member" items="${memberList}" varStatus="status">
            <tr style="align:center;border: 1px solid black;">
                <td>${member.id}</td>
                <td>${member.name }</td>
                <td>${member.age }</td>
                <td>${member.email }</td>
            </tr>
        </c:forEach>
        
    </table>    