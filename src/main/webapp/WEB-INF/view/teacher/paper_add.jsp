<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: He Guo
  Date: 2021/5/9
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <jsp:include page="../commons/metas.jsp" />
    <title>Title</title>
    <jsp:include page="../commons/styles.jsp" />
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <fieldset class="table-search-fieldset" style="margin-top: 20px;">
            <c:choose>
                <c:when test="${modify eq 'false'}">
                    <legend>创建考试</legend>
                </c:when>
                <c:otherwise>
                    <legend>修改考试</legend>
                </c:otherwise>
            </c:choose>
            <form class="layui-form layui-form-pane" action="" lay-filter="example">

                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">考试名称</label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入问题" class="layui-textarea"></textarea>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">考试时间</label>
                        <div class="layui-input-block">
                            <input type="text" name="date" id="date1" autocomplete="off" class="layui-input" lay-key="2">
                        </div>
                        <div class="layui-form-mid">-</div>
                        <div class="layui-input-block">
                            <input type="text" name="date" id="date2" autocomplete="off" class="layui-input" lay-key="3">
                        </div>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" title="选择题每题分数">选择题每题分数</label>
                    <div class="layui-input-block">
                        <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入选项B（第二空）" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" title="填空题每题分数">填空题每题分数</label>
                    <div class="layui-input-block">
                        <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入选项B（第二空）" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" title="设置试卷">填空题每题分数</label>
                    <div class="layui-input-block">
                        <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入选项B（第二空）" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <button class="layui-btn" lay-submit="" lay-filter="demo1">提交题目</button>
                </div>
            </form>
        </fieldset>
    </div>
</div>
<jsp:include page="../commons/scripts.jsp" />
</body>
</html>
