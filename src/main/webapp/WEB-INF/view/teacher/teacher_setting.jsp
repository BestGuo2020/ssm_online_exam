<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>基本资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/backend/lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/backend/css/public.css" media="all">
    <style>
        .layui-form-item .layui-input-company {width: auto;padding-right: 10px;line-height: 38px;}
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <div class="layui-form layuimini-form">
            <input type="hidden" name="id" value="${teacher.id}" />
            <div class="layui-form-item">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-block">
                    <input type="email" name="email" lay-verify="email" placeholder="请输入邮箱" value="${teacher.email}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" name="username" placeholder="用户名" lay-verify="username" required value="${teacher.username}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item" pane="">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <c:choose>
                        <c:when test="${teacher.gender == 0}">
                            <input type="radio" name="gender" value="0" title="男" checked="">
                            <input type="radio" name="gender" value="1" title="女">
                        </c:when>
                        <c:otherwise>
                            <input type="radio" name="gender" value="0" title="男">
                            <input type="radio" name="gender" value="1" title="女" checked="">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="saveBtn">确认保存</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/backend/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/backend/js/lay-config.js?v=1.0.4" charset="utf-8"></script>
<script>
    layui.use(['form','miniTab'], function () {
        var form = layui.form,
            layer = layui.layer,
            miniTab = layui.miniTab,
            $ = layui.jquery;

        form.verify({
            username: function (value) {
                if (value.length < 3) {
                    return '至少得3个字符啊';
                }
            }
            , pass: [
                /^[\S]{6,12}$/
                , '密码必须6到12位，且不能出现空格'
            ]
        });

        //监听提交
        form.on('submit(saveBtn)', function (data) {
            console.log(data);
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/teacher/teacherInfo_do",
                data: {
                    id: data.field.id,
                    email: data.field.email,
                    username: data.field.username,
                    gender: data.field.gender
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
                            top.location.href = "${pageContext.request.contextPath}/teacher";
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