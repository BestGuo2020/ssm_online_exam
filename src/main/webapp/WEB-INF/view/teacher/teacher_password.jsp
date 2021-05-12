<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改密码</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <jsp:include page="../commons/styles.jsp" />
    <style>
        .layui-form-item .layui-input-company {width: auto;padding-right: 10px;line-height: 38px;}
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <div class="layui-form layuimini-form">
            <div class="layui-form-item">
                <label class="layui-form-label required">旧的密码</label>
                <div class="layui-input-block">
                    <input type="password" name="old_password" lay-verify="pass" lay-reqtext="旧的密码不能为空" placeholder="请输入旧的密码"  value="" class="layui-input">
                    <tip>填写自己账号的旧的密码。</tip>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label required">新的密码</label>
                <div class="layui-input-block">
                    <input type="password" name="new_password" lay-verify="pass" lay-reqtext="新的密码不能为空" placeholder="请输入新的密码"  value="" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">新的密码</label>
                <div class="layui-input-block">
                    <input type="password" name="again_password" lay-verify="pass" lay-reqtext="新的密码不能为空" placeholder="请输入新的密码"  value="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../commons/scripts.jsp" />
<script>
    layui.use(['form','miniTab'], function () {
        var form = layui.form,
            layer = layui.layer,
            miniTab = layui.miniTab;

        form.verify({
            pass: [
                /^[\S]{6,12}$/
                , '密码必须6到12位，且不能出现空格'
            ]
        });

        //监听提交
        form.on('submit(saveBtn)', function (data) {
            console.log(data);
            if(data.field.new_password !== data.field.again_password) {
                layer.msg("两次输入的密码不一致");
            } else {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/teacher/teacherPassword_do",
                    data: {
                        id: ${sessionScope.teacher.id},
                        old_password: data.field.old_password,
                        password: data.field.new_password
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
            }
            return false;
        });

    });
</script>
</body>
</html>