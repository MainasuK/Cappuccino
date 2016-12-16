<%--
  Created by IntelliJ IDEA.
  User: MainasuK
  Date: 2016-11-30
  Time: 20:57
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags always come first -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <title>Document</title>

    <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.min.css">
</head>
<body>

<div class="container" style="margin-top: 24px">
    <div class="col-lg-8"></div>
    <div class="card col-lg-4">
        <div class="card-block">
            <h4 class="card-title" style="text-align: center">超市管理系统</h4>
            <%--Controller will handle this action--%>
            <form method="post">
                <div class="form-group">
                    <label>用户名</label>
                    <input class="form-control" type="text" name="username">
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input class="form-control" type="password" name="password">
                </div>
                <div class="form-group">
                    <input class="form-control btn btn-outline-primary" type="submit" value="登录">
                </div>
            </form>
        </div>
    </div>
</div>

<script src="/resources/jQuery/jquery-3.1.1.min.js"></script>
<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
