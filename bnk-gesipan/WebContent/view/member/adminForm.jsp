<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="context" value="<%=request.getContextPath() %>"></c:set>
<style>@import url(${context}/css/common.css);</style>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>	

 <form action="${context}/member/searchAllMembers.do"  
            name="frmAdmin">
        <fieldset>
        <legend>회원관리</legend>
        <table>
            <tr>
                <td>전체회원 목록보기 </td>
                <td>
                    <jsp:include page="memberList.jsp"></jsp:include>
                </td>
            </tr>
            <tr>
                <td>ID 로 회원검색</td>
                <td>
                    <!-- <input type="text" id="password" name="password" /> -->
                </td>
            </tr>
        
            <tr>
                <td>이름으로 회원검색(동명이인 허용)</td>
                <td>
                    <!-- <input type="text" id="password" name="password" /> -->
                </td>
            </tr>
                <tr>
                <td>회원 삭제</td>
                <td>
                    <!-- <input type="text" id="password" name="password" /> -->
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="전 송" /> 
                    
                </td>
            </tr>
        </table>
        </fieldset>
    </form>