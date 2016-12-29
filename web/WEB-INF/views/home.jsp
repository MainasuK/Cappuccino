<%--
  Created by IntelliJ IDEA.
  User: MainasuK
  Date: 2016-11-30
  Time: 20:57
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags always come first -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <title>主页</title>

    <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.min.css">
</head>
<body>

<c:set var="username" value="${username}" scope="session"/>
<%@include file="component/navigation.jsp"%>

<div class="container" style="margin-top: 24px">
    <div class="card-group">
        <div class="card">
            <img class="card-img-top img-fluid" src="/resources/img/cashier.jpg">
            <div class="card-block">
                <h4 class="card-title">前台收银</h4>
                <a href="/cashier" class="btn btn-primary">进入</a>
            </div>
        </div>
        <div class="card">
            <img class="card-img-top img-fluid" src="/resources/img/warehouse.jpg">
            <div class="card-block">
                <h4 class="card-title">仓库管理</h4>
                <a href="/warehouse" class="btn btn-primary">进入</a>
            </div>
        </div>
        <div class="card">
            <img class="card-img-top img-fluid" src="/resources/img/dashboard.jpg">
            <div class="card-block">
                <h4 class="card-title">员工管理</h4>
                <a href="/dashboard" class="btn btn-primary">进入</a>
            </div>
        </div>
    </div>
</div>

<script src="/resources/jQuery/jquery-3.1.1.min.js"></script>
<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
