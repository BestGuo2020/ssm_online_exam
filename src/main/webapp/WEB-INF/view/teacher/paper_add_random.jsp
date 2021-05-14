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
        .layui-carousel {
            height: 538px !important;
        }
    </style>
</head>
<body>
<div class="layuimini-container" style="height: 100%;">
    <div class="layuimini-main">
        <div class="layui-fluid">
            <div class="layui-card">
                <div class="layui-card-body" style="padding-top: 40px;">
                    <div class="layui-carousel" id="stepForm" lay-filter="stepForm" style="margin: 0 auto; ">
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
                                <form class="layui-form layui-form-pane" action="">
                                    <div class="layui-form-item" style="margin-top: 10px;">
                                        <div class="layui-inline">
                                            <label class="layui-form-label">题目数</label>
                                            <div class="layui-input-inline" style="width: 300px;">
                                                <input type="text" name="questionIds" id="questionIds" placeholder="请输入随机抽题的个数，题目生成之后展示在表格中" autocomplete="off" class="layui-input" id="demo" ts-selected="">
                                            </div>
                                            <div class="layui-inline">
                                                <button type="submit" class="layui-btn layui-btn-primary"  lay-submit lay-filter="data-search-btn"> 开始抽题 </button>
                                            </div>
                                            <div class="layui-inline">
                                                <button class="layui-btn" lay-submit lay-filter="formStep">
                                                    &emsp;抽题完成，下一步&emsp;
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
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

        table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/static/backend/api/table.json',
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 80, title: 'ID', sort: true},
                {field: 'username', width: 80, title: '用户名'},
                {field: 'sex', width: 80, title: '性别', sort: true},
                {field: 'city', width: 80, title: '城市'},
                {field: 'sign', title: '签名', minWidth: 150},
                {field: 'experience', width: 80, title: '积分', sort: true},
                {field: 'score', width: 80, title: '评分', sort: true},
                {field: 'classify', width: 80, title: '职业'},
                {field: 'wealth', width: 135, title: '财富', sort: true}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 15,
            page: true,
            skin: 'line'
        });


    })
</script>
</body>
</html>
