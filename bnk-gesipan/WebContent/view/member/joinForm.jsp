<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="context" value="<%=request.getContextPath() %>"></c:set>
<style>@import url(${context}/css/common.css);</style>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>	
    
    <form action="<%=request.getContextPath()%>/member/join.do" method="post" name="frmJoin">
        <fieldset>
        <legend>회원 가입</legend>
        <table>
            <tr>
                <td>이름 </td>
                <td>
                    <input type="text" id="name" name="name" placeholder="이름을 입력하세요" />
                </td>
            </tr>
            <tr>
                <td>ID </td>
                <td>
                    <input type="text" id="id" name="id" placeholder="ID 값을 입력하세요" />
                </td>
            </tr>
            <tr>
                <td>Password </td>
                <td>
                    <input type="password" id="password" name="password"  />
                </td>
            </tr>
                <tr>
                <td>나이 </td>
                <td>
                    <input type="text" id="age" name="age" placeholder="나이를 입력하세요" />
                </td>
            </tr>
                <tr>
                <td>이메일 </td>
                <td>
                    <input type="text" id="email" name="email" placeholder="이메일을 입력하세요" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <!-- <input type="submit" value="전 송" /> -->
                    <img src="<%=request.getContextPath()%>/image/btnJoin.jpg" 
                            style="cursor: pointer;width: 150px;" 
                    alt="" onclick="return member.join()"/>
                      <img src="<%=request.getContextPath()%>/image/btnJoin.jpg" class="popupClose"
                            style="cursor: pointer;width: 150px;" 
                    alt="" onclick="return member.join()"/>
                </td>
            </tr>
        </table>
        </fieldset>
    </form>
    
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/member.js"></script>