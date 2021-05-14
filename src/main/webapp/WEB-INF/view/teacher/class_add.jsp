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

        <form class="layui-form" method="post" action="" lay-filter="example">

            <input type="hidden" name="id" value="${oneClass.data.id}" />

            <div class="layui-form-item">
                <label class="layui-form-label">班级名称</label>
                <div class="layui-input-block">
                    <input type="text" name="classname" lay-verify="required" value="${oneClass.data.classname}" autocomplete="off" required placeholder="请输入标题" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">班级介绍</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入内容" class="layui-textarea" lay-verify="required" name="desc">${oneClass.data.classdesc}</textarea>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <c:choose>
                        <c:when test="${modify.equals('true')}">
                            <button class="layui-btn" lay-submit="" lay-filter="saveBtn">修改班级</button>
                        </c:when>
                        <c:otherwise>
                            <button class="layui-btn" lay-submit="" lay-filter="saveBtn">创建班级</button>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </form>

    </div>
</div>
<jsp:include page="../commons/scripts.jsp" />
<script>
    layui.use(['form','miniTab'], function () {
        var form = layui.form,
            layer = layui.layer,
            miniTab = layui.miniTab,
            $ = layui.jquery;

        //监听提交
        form.on('submit(saveBtn)', function (data) {
            console.log(data);
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/classes/classManager_do/${type}?modify=${modify}",
                data: {
                    id: data.field.id === "" ? null : data.field.id,
                    classname: data.field.classname  === "" ? null : data.field.classname,
                    classdesc: data.field.desc  === "" ? null : data.field.desc,
                    belongteacher: ${sessionScope.teacher.id}
                },
                success: function(data){
                    console.log(data);
                    // layer.msg(data.message);
                    // 判断返回的数据是json还是字符串
                    if(typeof data === 'string') {
                        data = JSON.parse(data);
                    }
                    if(data.code === 0) {
                        layer.msg(data.message, {icon: 1}, function(){
                            top.location.reload();
                        });
                    } else if (data.code === 1) {
                        layer.msg(data.message, {icon: 2});
                    } else if (data.code === -1) {
                        layer.msg(data.message, {icon: 7}, function(){
                            top.location.href = '${pageContext.request.contextPath}/login';
                        });
                    }
                }
            });
            return false;
        });
    });
</script>
</body>
</html>
