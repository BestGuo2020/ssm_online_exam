<%--
  Created by IntelliJ IDEA.
  User: He Guo
  Date: 2021/4/11
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>在线考试系统 - 登录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/backend/lib/layui-v2.6.3/css/layui.css" media="all">
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        html, body {width: 100%;height: 100%;overflow: hidden}
        body {background: #1E9FFF;}
        body:after {content:'';background-repeat:no-repeat;background-size:cover;-webkit-filter:blur(3px);-moz-filter:blur(3px);-o-filter:blur(3px);-ms-filter:blur(3px);filter:blur(3px);position:absolute;top:0;left:0;right:0;bottom:0;z-index:-1;}
        .layui-container {width: 100%;height: 100%;overflow: hidden}
        .admin-login-background {width:360px;height:300px;position:absolute;left:50%;top:40%;margin-left:-180px;margin-top:-100px;}
        .logo-title {text-align:center;letter-spacing:2px;padding:14px 0;}
        .logo-title h1 {color:#1E9FFF;font-size:25px;font-weight:bold;}
        .login-form {background-color:#fff;border:1px solid #fff;border-radius:3px;padding:14px 20px;box-shadow:0 0 8px #eeeeee;}
        .login-form .layui-form-item {position:relative;}
        .login-form .layui-form-item label {position:absolute;left:1px;top:1px;width:38px;line-height:36px;text-align:center;color:#d2d2d2;}
        .login-form .layui-form-item input {padding-left:36px;}
        .captcha {width:60%;display:inline-block;}
        .captcha-img {display:inline-block;width:34%;float:right;}
        .captcha-img img {height:34px;border:1px solid #e6e6e6;height:36px;width:100%;}
    </style>
</head>
<body>
<div class="layui-container">
    <div class="admin-login-background">
        <div class="layui-form login-form">
            <form class="layui-form" action="">
                <div class="layui-form-item logo-title">
                    <h1>在线考试系统登录</h1>
                </div>
                <div class="layui-form-item">
                    <%--@declare id="username"--%><label class="layui-icon layui-icon-username" for="username"></label>
                    <input type="text" name="username" lay-verify="email" placeholder="邮箱" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-item">
                    <%--@declare id="password"--%><label class="layui-icon layui-icon-password" for="password"></label>
                    <input type="password" name="password" lay-verify="pass" placeholder="密码" autocomplete="off" class="layui-input">
                </div>

                <div class="layui-form-item">
                    <input type="radio" name="type"  checked="checked" value="1" lay-skin="primary" title="学生">
                    <input type="radio" name="type"  value="2" lay-skin="primary" title="老师">

                </div>
                <div class="layui-form-item">
                    <button class="layui-btn layui-btn layui-btn-normal layui-btn-fluid" lay-submit="" lay-filter="login">登 入</button>
                </div>
                <div class="layui-form-item">
                    <a class="layui-btn layui-btn layui-btn-normal layui-btn-fluid" href="${pageContext.request.contextPath}/register">注 册</a>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/backend/lib/jquery-3.4.1/jquery-3.4.1.min.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/backend/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/backend/lib/jq-module/jquery.particleground.min.js" charset="utf-8"></script>
<script>
    layui.use(['form'], function () {
        var form = layui.form,
            layer = layui.layer;

        form.verify({
            pass: [
                /^[\S]{6,12}$/
                , '密码必须6到12位，且不能出现空格'
            ]
        });

        // 登录过期的时候，跳出ifram框架
        if (top.location != self.location) top.location = self.location;

        // 粒子线条背景
        $(document).ready(function(){
            $('.layui-container').particleground({
                dotColor:'#7ec7fd',
                lineColor:'#7ec7fd'
            });
        });

        // 进行登录操作
        form.on('submit(login)', function (data) {
            data = data.field;
            if (data.username == '') {
                layer.msg('邮箱不能为空');
                return false;
            }
            if (data.password == '') {
                layer.msg('密码不能为空');

                 layer.msg(s)
                return false;
            }



                $.ajax({
                    url: "${pageContext.request.contextPath}/logindo",
                    data: {
                        email: $("input[name=username]").val(),
                        password: $("input[name=password]").val(),
                        type:$("input[name=type]:checked").val(),

                    },
                    type: "POST",
                    success: function (result) {

                        if(result=="student"){
                            layer.msg("登录成功");
                            window.location.href="student";
                        }
                        if(result=="fail"){
                            layer.msg("登录失败");
                        }
                        if(result=="teacher"){
                            layer.msg("登录成功");
                            window.location.href="teacher";
                        }
                    },
                    beforeSend: function () {
                        console.log("请求发送之前")
                    },
                    error: function () {
                        console.log("失败时调用")
                    }
                })
                return false;
        }
        );




    });


</script>
</body>
</html>