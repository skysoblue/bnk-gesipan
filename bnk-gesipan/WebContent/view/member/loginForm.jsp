<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="context" value="<%=request.getContextPath() %>"></c:set>
<style>@import url(${context}/css/common.css);</style>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>	

    <form action="${context}/member/login.do" method="post" name="frmLogin">
        <fieldset>
        <legend>로그인</legend>
        <table>
            <tr>
                <td>아이디 </td>
                <td>
                    <input type="text" id="id" name="id" placeholder="ID 입력" />
                </td>
            </tr>
            <tr>
                <td>비밀번호</td>
                <td>
                    <input type="text" id="password" name="password" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <!-- <input type="submit" value="전 송" /> -->
                    <img src="${context}/images/btnLogin.jpg"
                         style="cursor: pointer" alt="" onclick="return login()"/>
                </td>
            </tr>
        </table>
        </fieldset>
    </form>
    <div>
        <span>
            <a href="#" onclick="searchId('${context}/member/searchIdForm.do')">
                아이디 찾기</a>
        </span>
        <span>
            <a href="#" onclick="searchPass('${context}/member/searchPassForm.do')">
                비밀번호 찾기</a>
        </span>
    </div>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/member.js"></script>