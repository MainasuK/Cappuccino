<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: MainasuK
  Date: 2016-11-30
  Time: 20:57
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags always come first -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <title>Document</title>

    <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.min.css">
</head>
<body>


<div>
    <%--navbar--%>
    <%@include file="../component/navigation.jsp"%>
    <%--card--%>
    <div class="container">
        <div class="card" style="margin-top: 40px">
            <div class="card-header">
                <h4>员工表</h4>
            </div>
            <div class="card-block">
                <button type="button" class="btn btn-sm btn-outline-primary float-sm-right" data-toggle="modal" data-target="#addModel" style="margin-bottom: 14px">新增员工</button>
                <%--table--%>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>姓名</th>
                        <th>用户名</th>
                        <th>职位</th>
                        <th>是否有效</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${employees}" var="employee">
                        <tr>
                            <td>${employee.fullName}</td>
                            <td>${employee.username}</td>
                            <td>${employee.rolePlainString}</td>
                            <td>${employee.valid}</td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-secondary btn-sm" data-toggle="modal" data-target="#modifyModal_${employee.username}">修改</button>
                                    <button type="button"
                                            class="btn btn-secondary btn-sm dropdown-toggle dropdown-toggle-split"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" data-toggle="modal" href="#disableModal_${employee.username}">${employee.enabled ? "停用" : "启用"}</a>
                                    </div>
                                </div>
                            </td>
                        </tr>

                        <!-- Modify Modal -->
                        <div class="modal fade" id="modifyModal_${employee.username}" tabindex="-1" role="dialog" aria-labelledby="myModalDisableLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                        <h4 class="modal-title" id="myModalDisableLabel">修改员工信息</h4>
                                    </div>
                                    <form action="/dashboard/modify" method="post">
                                        <div class="modal-body">

                                            <div class="form-group">
                                                <label for="username" class="form-control-label">用户名：</label>
                                                <input type="text" class="form-control" value="${employee.username}" disabled="true">
                                                <input type="text" class="form-control" id="username" name="username"
                                                       value="${employee.username}" hidden="true">
                                            </div>
                                            <div class="form-group">
                                                <label for="fullName" class="form-control-label">姓名：</label>
                                                <input type="text" class="form-control" id="fullName" name="fullName"
                                                       value="${employee.fullName}" >
                                            </div>
                                            <div class="form-group">
                                                <label for="password" class="form-control-label">密码：</label>
                                                <input type="password" class="form-control" id="password" name="password"
                                                       value="${employee.password}">
                                            </div>
                                            <div class="form-group">
                                                <label for="position" class="form-control-label">职位：</label>
                                                <select class="form-control" id="position" name="role">
                                                    <option value="ROLE_ADMIN" ${employee.role.equalsIgnoreCase("ROLE_ADMIN") ? "selected" : ""}>管理员</option>
                                                    <option value="ROLE_STOCK_KEEPER" ${employee.role.equalsIgnoreCase("ROLE_STOCK_KEEPER") ? "selected" : ""}>理货员</option>
                                                    <option value="ROLE_TELLER" ${employee.role.equalsIgnoreCase("ROLE_TELLER") ? "selected" : ""}>收银员</option>
                                                </select>
                                            </div>

                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                                            <button type="submit" class="btn btn-primary">修改</button>
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <!-- Disable Modal -->
                        <div class="modal fade" id="disableModal_${employee.username}" tabindex="-1" role="dialog" aria-labelledby="myModalDeleteLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                        <h4 class="modal-title" id="myModalDeleteLabel">${employee.enabled ? "停用" : "启用"}员工</h4>
                                    </div>
                                    <div class="modal-body">
                                            是否${employee.enabled ? "停用" : "启用"}"${employee.fullName}"
                                    </div>
                                    <div class="modal-footer">
                                        <sf:form action="/dashboard/delete" method="post">
                                            <input type="hidden" name="username" value="${employee.username}">
                                            <input type="hidden" name="flag" value="${employee.enabled}">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                                            <button type="submit" class="btn btn-primary">${employee.enabled ? "停用" : "启用"}</button>
                                        </sf:form>
                                    </div>
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

<!-- Add Modal -->
<div class="modal fade" id="addModel" tabindex="-1" role="dialog" aria-labelledby="myModalAddLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalAddLabel">添加员工</h4>
            </div>
            <sf:form action="/dashboard/add" method="post">
                <div class="modal-body">

                    <div class="form-group">
                        <label for="add_username" class="form-control-label">用户名：</label>
                        <input type="text" class="form-control" id="add_username" name="username">
                    </div>
                    <div class="form-group">
                        <label for="add_fullName" class="form-control-label">姓名：</label>
                        <input type="text" class="form-control" id="add_fullName" name="fullName">
                    </div>
                    <div class="form-group">
                        <label for="add_password" class="form-control-label">密码：</label>
                        <input type="text" class="form-control" id="add_password" name="password">
                    </div>
                    <div class="form-group">
                        <label for="add_position" class="form-control-label">职位：</label>
                        <select class="form-control" id="add_position" name="role">
                            <option value="ROLE_ADMIN">管理员</option>
                            <option value="ROLE_STOCK_KEEPER">理货员</option>
                            <option value="ROLE_TELLER">收银员</option>
                        </select>
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



<script src="/resources/jQuery/jquery-3.1.1.min.js"></script>
<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
