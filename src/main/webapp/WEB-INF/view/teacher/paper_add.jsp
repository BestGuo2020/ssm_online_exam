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
    <style>
        .layui-form-checkbox {
            margin-top: 6px !important;
        }
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <div class="layui-fluid">
            <div class="layui-card">
                <div class="layui-card-body" style="padding-top: 40px;">
                    <div class="layui-carousel" id="stepForm" lay-filter="stepForm" style="margin: 0 auto;">
                        <div carousel-item>
                            <div>
                                <form class="layui-form" style="margin: 0 auto;max-width: 460px;padding-top: 40px;">
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">试卷名称</label>
                                        <div class="layui-input-block">
                                            <input type="text" placeholder="请填写试卷名称" class="layui-input" required />
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">考试时间</label>
                                        <div class="layui-input-block">
                                            <input type="text" id="date_time_range" class="layui-input" placeholder="考试时间">
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">选择题分数</label>
                                        <div class="layui-input-block">
                                            <input type="number" name="title" lay-verify="title" autocomplete="off" placeholder="请输入选择题每题分数" class="layui-input">
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">填空题分数</label>
                                        <div class="layui-input-block">
                                            <input type="number" name="title" lay-verify="title" autocomplete="off" placeholder="请输入填空题每题分数" class="layui-input">
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <div class="layui-input-block">
                                            <button class="layui-btn" lay-submit lay-filter="formStep">
                                                &emsp;提交试卷信息，下一步&emsp;
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div>
                                <form class="layui-form" style="margin: 0 auto;max-width: 660px;padding-top: 40px;">
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">选择题目</label>
                                        <div class="layui-input-inline" style="width: 465px;">
                                            <input type="text" name="questionIds" id="questionIds" placeholder="选择完成的题目题号将显示出来" autocomplete="off" class="layui-input" id="demo" ts-selected="">
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <div class="layui-input-block">
                                            <button class="layui-btn" lay-submit lay-filter="formStep">
                                                &emsp;选题完成，下一步&emsp;
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div>
                                <div style="text-align: center;margin-top: 90px;">
                                    <i class="layui-icon layui-circle"
                                       style="color: white;font-size:30px;font-weight:bold;background: #52C41A;padding: 20px;line-height: 80px;">&#xe605;</i>
                                    <div style="font-size: 24px;color: #333;font-weight: 500;margin-top: 30px;">
                                        创建成功
                                    </div>
                                    <div style="font-size: 14px;color: #666;margin-top: 20px;">考试将会在考试开始时生效</div>
                                </div>
                                <div style="text-align:center; margin-top: 50px;">
                                    <button class="layui-btn next">再次创建</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../commons/scripts.jsp" />
<script>
    layui.use(['table', 'form', 'step', 'tableSelect', 'laydate'], function () {
        var $ = layui.$,
            form = layui.form,
            step = layui.step,
            table = layui.table,
            tableSelect = layui.tableSelect,
            laydate = layui.laydate;

        // 日期
        laydate.render({
            elem: '#date_time_range',
            type: 'datetime',
            range: true
        });

        // 分步
        step.render({
            elem: '#stepForm',
            filter: 'stepForm',
            width: '100%', //设置容器宽度
            stepWidth: '750px',
            height: '500px',
            stepItems: [{
                title: '创建试卷'
            }, {
                title: '添加试题'
            }, {
                title: '发布考试'
            }]
        });


        form.on('submit(formStep)', function (data) {
            step.next('#stepForm');
            return false;
        });

        form.on('submit(formStep2)', function (data) {
            step.next('#stepForm');
            return false;
        });

        $('.pre').click(function () {
            step.pre('#stepForm');
        });

        $('.next').click(function () {
            step.next('#stepForm');
        });

        tableSelect.render({
            elem: '#questionIds',
            searchKey: 'my',
            checkedKey: 'id',
            searchPlaceholder: '自定义文字和name',
            table: {
                url: '${pageContext.request.contextPath}/static/backend/api/tableSelect.json',
                cols: [[
                    { type: 'checkbox' },
                    { field: 'id', title: 'ID', width: 100 },
                    { field: 'username', title: '姓名', width: 300 },
                    { field: 'sex', title: '性别', width: 100 }
                ]]
            },
            done: function (elem, data) {
                var NEWJSON = []
                layui.each(data.data, function (index, item) {
                    NEWJSON.push(item.id)
                })
                elem.val(NEWJSON.join(","))
            }
        })
    })
</script>
</body>
</html>
