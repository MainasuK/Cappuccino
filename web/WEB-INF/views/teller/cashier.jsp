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

<%--navbar--%>
<%@include file="../component/navigation.jsp"%>

<div class="container" style="margin-top: 22px; margin-bottom: 60px">
    <div class="row">
        <div class="col-md-10">
            <div class="card">
                <div class="card-block" style="padding: 0px; min-height: 500px">
                    <table class="table table-hover">
                        <%--table--%>
                        <thead>
                        <tr>
                            <th width="30%">条形码</th>
                            <th width="40%">名称</th>
                            <th width="10%">单价</th>
                            <th width="10%">数量</th>
                            <th width="10%">总价</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${commodities}" var="commodity">
                            <tr>
                                <td>${commodity.barCode}</td>
                                <td>${commodity.name}</td>
                                <td>${commodity.price}</td>
                                <td>${commodity.quantity}</td>
                                <td>${commodity.quantity * commodity.price}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="col-md-2">
            <div class="row">
                <div class="card">
                    <div class="card-block">
                        <h6 class="card-title" style="text-align: center">商品信息</h6>
                        <p>名称：${(null != currentCommodity) ? currentCommodity.name : "—"}</p>
                        <p>条码：${(null != currentCommodity) ? currentCommodity.barCode : "—"}</p>
                        <p>单价：¥${(null != currentCommodity) ? currentCommodity.price : "0.00"}</p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="card">
                    <div class="card-block">
                        <h6 class="card-title" style="text-align: center">收银总计</h6>
                        <p>数量：${totalQuantity}</p>
                        <p>总计：¥${totalPrice}</p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="card">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item"><a data-toggle="modal" href="#payModal" class="card-link">结算</a></li>
                        <li class="list-group-item"><a data-toggle="modal" href="#clearModal" class="card-link">删单</a></li>
                    </ul>
                </div>
            </div>
        </div>

    </div>
</div>

<%--Pay modal--%>
<div id="payModal" class="modal fade" role="dialog" aria-labelledby="myPayModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <sf:form action="/cashier/pay">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myPayModalLabel">结算</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="shouldPay" class="form-control-label">应付：</label>
                        <input id="shouldPay" name="shouldPay" class="form-control" value="${totalPrice}" disabled/>
                    </div>
                    <div class="form-group">
                        <label for="pay" class="form-control-label">实付：</label>
                        <input type="text" class="form-control" id="pay" name="pay" autocomplete="off" autofocus="autofocus">
                    </div>
                    <div class="form-group">
                        <label for="change" class="form-control-label">找零：</label>
                        <input type="text" class="form-control" id="change" name="change" value="" disabled>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary">结算</button>
                </div>
            </sf:form>
        </div>
    </div>
</div>

<div id="clearModal" class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="mySmallModalLabel">是否删单</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                <a href="/cashier/clear"><button type="submit" class="btn btn-danger">删单</button></a>
            </div>
        </div>
    </div>
</div>


<nav class="navbar navbar-fixed-bottom nav navbar-dark bg-inverse">
    <a class="navbar-brand" href="#">条形码</a>
    <li class="text-truncate">
        <sf:form class="input-group" method="post">
            <input class="form-control" name="search" placeholder="" autocomplete="off" autofocus="autofocus" type="text">
            <div class="input-group-btn">
                <button class="btn btn-secondary" type="submit">录入</button>
            </div>
        </sf:form>
    </li>
</nav>

<script src="/resources/jQuery/jquery-3.1.1.min.js"></script>
<script src="/resources/bootstrap/js/bootstrap.min.js"></script>

<script>
    $("#pay").on('keyup', function() {
        $("#change").val($("#pay").val() - $("#shouldPay").val());
    });
</script>

</body>
</html>
