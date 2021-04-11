<%--
  Created by IntelliJ IDEA.
  User: He Guo
  Date: 2021/4/11
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎来到在线考试系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="static/css/style.css">
    <link rel="stylesheet" href="static/layui/css/layui.css">
</head>
<body>
<div class="login-container">
    <div class="left-container">
        <div class="title"><span>登录</span></div>
        <div class="input-container">
            <input type="text" name="username" placeholder="用户名">
            <input type="password" name="password" placeholder="密码">
        </div>
        <div class="message-container">
            <span>忘记密码</span>
        </div>
    </div>
    <div class="right-container">
        <div class="regist-container">
            <span class="regist">注册</span>
        </div>
        <div class="actoin-container">
            <span>提交</span>
        </div>
    </div>
</div>
<script src="static/layui/layui.js"></script>
<script>
    layui.use(['layer', 'form'], function(){
        let layer = layui.layer ,form = layui.form;
        layer.msg("layui弹出层测试", {icon: 1});

    });
</script>
</body>
</html>
