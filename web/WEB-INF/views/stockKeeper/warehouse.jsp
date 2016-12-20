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
        <div class="col-md-12">
            <c:choose>
                <c:when test="${isSearching == true}">
                    <a href="/warehouse"><button type="button" class="btn btn-sm btn-outline-primary float-sm-right">重置</button> </a>
                </c:when>
                <c:otherwise>
                    <button type="button" class="btn btn-sm btn-outline-primary float-sm-right" data-toggle="modal" data-target="#searchModal" style="margin-bottom: 14px">查询</button>
                </c:otherwise>
            </c:choose>
            <button type="button" class="btn btn-sm btn-outline-primary float-sm-right" data-toggle="modal" data-target="#addModel" style="margin-bottom: 14px; margin-right: 8px">添加</button>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="card">
                <div class="card-block" style="padding: 0px; min-height: 500px">
                    <table class="table table-hover">
                        <%--table--%>
                        <thead>
                        <tr>
                            <th width="30%">条形码</th>
                            <th width="40%">名称</th>
                            <th width="10%">单价</th>
                            <th width="10%">库存</th>
                            <th width="10%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${commodities}" var="commodity">
                            <tr ${commodity.enabled ? "" : "style='color: #8c8c8c'"}>
                                <td>${commodity.barCode}</td>
                                <td>${commodity.name}</td>
                                <td>${commodity.price}</td>
                                <td>${commodity.storage}</td>
                                <td>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-secondary btn-sm" data-toggle="modal" data-target="#procurementModal_${commodity.commodityID}">进货</button>
                                        <button type="button"
                                                class="btn btn-secondary btn-sm dropdown-toggle dropdown-toggle-split"
                                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <span class="sr-only">Toggle Dropdown</span>
                                        </button>
                                        <div class="dropdown-menu">
                                            <a class="dropdown-item" data-toggle="modal" href="#modifyModal_${commodity.commodityID}">修改</a>
                                            <a class="dropdown-item" data-toggle="modal" href="#disableModal_${commodity.commodityID}">${(commodity.enabled == true) ? "下架" : "上架"}</a>
                                        </div>
                                    </div>
                                </td>
                            </tr>

                            <!-- Disable Modal -->
                            <div class="modal fade" id="disableModal_${commodity.commodityID}" tabindex="-1" role="dialog" aria-labelledby="myModalDeleteLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                            <h4 class="modal-title" id="myModalDeleteLabel">${commodity.enabled ? "下架" : "上架"}商品</h4>
                                        </div>
                                        <div class="modal-body">
                                            是否${commodity.enabled ? "下架" : "上架"}"${commodity.name}"
                                        </div>
                                        <div class="modal-footer">
                                            <sf:form action="/warehouse/disable" method="post">
                                                <input type="hidden" name="commodityID" value="${commodity.commodityID}">
                                                <input type="hidden" name="flag" value="${commodity.enabled}">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                                                <button type="submit" class="btn ${commodity.enabled ? "btn-danger" : "btn-primary"}">${commodity.enabled ? "下架" : "上架"}</button>
                                            </sf:form>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <%--procurement modal--%>
                            <div id="procurementModal_${commodity.commodityID}" class="modal fade" role="dialog" aria-labelledby="myProcurementModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-sm">
                                    <div class="modal-content">
                                        <sf:form action="/warehouse/procurement">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                                <h4 class="modal-title" id="myProcurementModalLabel">进货</h4>
                                            </div>
                                            <div class="modal-body">
                                                <div class="form-group">
                                                    <label for="quantity" class="form-control-label">数量：</label>
                                                    <input type="text" class="form-control" id="quantity" name="quantity" autocomplete="off" autofocus="autofocus">
                                                    <input type="text" value="${commodity.commodityID}" name="commodityID" hidden>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                                                <button type="submit" class="btn btn-primary">完成</button>
                                            </div>
                                        </sf:form>
                                    </div>
                                </div>
                            </div>

                            <%--modify modal--%>
                            <div class="modal fade" id="modifyModal_${commodity.commodityID}" tabindex="-1" role="dialog" aria-labelledby="myModalModifyLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                            <h4 class="modal-title" id="myModalModifyLabel">修改商品</h4>
                                        </div>
                                        <sf:form action="/warehouse/modify" method="post">
                                            <div class="modal-body">
                                                <input type="text" name="commodityID" value="${commodity.commodityID}" hidden>
                                                <div class="form-group">
                                                    <label for="modify_barCode" class="form-control-label">条形码：</label>
                                                    <input type="text" class="form-control" id="modify_barCode" name="barCode" value="${commodity.barCode}">
                                                </div>
                                                <div class="form-group">
                                                    <label for="modify_name" class="form-control-label">名称：</label>
                                                    <input type="text" class="form-control" id="modify_name" name="name" value="${commodity.name}">
                                                </div>
                                                <div class="form-group">
                                                    <label for="modify_price" class="form-control-label">价格：</label>
                                                    <input type="text" class="form-control" id="modify_price" name="price" value="${commodity.price}">
                                                </div>

                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                                                <button type="submit" class="btn btn-primary">修改</button>
                                            </div>
                                        </sf:form>
                                    </div>
                                </div>
                            </div>

                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Add Modal -->
<div class="modal fade" id="addModel" tabindex="-1" role="dialog" aria-labelledby="myModalAddLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalAddLabel">添加商品</h4>
            </div>
            <sf:form action="/warehouse/add" method="post">
                <div class="modal-body">

                    <div class="form-group">
                        <label for="add_barCode" class="form-control-label">条形码：</label>
                        <input type="text" class="form-control" id="add_barCode" name="barCode">
                    </div>
                    <div class="form-group">
                        <label for="add_name" class="form-control-label">名称：</label>
                        <input type="text" class="form-control" id="add_name" name="name">
                    </div>
                    <div class="form-group">
                        <label for="add_price" class="form-control-label">价格：</label>
                        <input type="text" class="form-control" id="add_price" name="price">
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary">添加</button>
                </div>
            </sf:form>
        </div>
    </div>
</div>

<%--search modal--%>
<div id="searchModal" class="modal fade" role="dialog" aria-labelledby="mySearchModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <sf:form action="/warehouse/search">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="mySearchModalLabel">查找商品</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="keyword" class="form-control-label">关键字：</label>
                        <input type="text" class="form-control" id="keyword" name="keyword" autocomplete="off" autofocus="autofocus">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary">查找</button>
                </div>
            </sf:form>
        </div>
    </div>
</div>


<script src="/resources/jQuery/jquery-3.1.1.min.js"></script>
<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
