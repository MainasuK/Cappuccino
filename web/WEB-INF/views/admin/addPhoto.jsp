<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: MainasuK
  Date: 2016-11-30
  Time: 20:57
--%>
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

<%@include file="../component/navigation.jsp"%>

<div class="container" style="margin-top: 22px">
    <sf:form action="/dashboard/addPhoto" method="post" enctype="multipart/form-data">
        <div class="input-group">
            <input type="file" name="photo">
        </div>
        <div class="input-group">
            <input type="submit" value="提交">
        </div>
    </sf:form>
</div>


<script src="/resources/jQuery/jquery-3.1.1.min.js"></script>
<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
