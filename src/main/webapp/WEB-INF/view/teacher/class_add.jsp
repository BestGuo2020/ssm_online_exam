<%--
  Created by IntelliJ IDEA.
  User: He Guo
  Date: 2021/5/7
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <jsp:include page="../commons/metas.jsp" />
    <title>Title</title>
    <jsp:include page="../commons/styles.jsp" />
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <c:if test="${modify eq 'false'}">
            <blockquote class="layui-elem-quote layui-text">
                老师创建班级，创建班级之后生成的班级编号告诉自己的学生。学生通过老师给的加课码对班级进行添加
            </blockquote>
        </c:if>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <c:choose>
                <c:when test="${modify.equals('true')}">
                    <legend>修改班级</legend>
                </c:when>
                <c:otherwise>
                    <legend>创建班级</legend>
                </c:otherwise>
            </c:choose>
        </fieldset>

        <form class="layui-form" action="" lay-filter="example">

            <div class="layui-form-item">
                <label class="layui-form-label">班级名称</label>
                <div class="layui-input-block">
                    <input type="text" name="classname" lay-verify="title" autocomplete="off" required placeholder="请输入标题" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">班级介绍</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入内容" class="layui-textarea" name="desc"></textarea>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit="" lay-filter="demo1">创建班级</button>
                </div>
            </div>
        </form>

    </div>
</div>
<jsp:include page="../commons/scripts.jsp" />
</body>
</html>
