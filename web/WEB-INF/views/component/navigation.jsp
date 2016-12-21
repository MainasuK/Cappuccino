<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: MainasuK
  Date: 2016-11-30
  Time: 20:57
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--navbar--%>
<nav class="navbar navbar-light bg-faded">
    <div class="container">
        <a class="navbar-brand" href="/">百世超市管理系统</a>
        <c:choose>
            <c:when test='${null != username && !username.equals("")}'>
                <a class="btn btn-outline-danger float-lg-right" href="/logout">登出</a>
            </c:when>
            <c:otherwise>
                <a class="btn btn-outline-primary float-lg-right" href="/login">登录</a>
            </c:otherwise>
        </c:choose>
    </div>
</nav>