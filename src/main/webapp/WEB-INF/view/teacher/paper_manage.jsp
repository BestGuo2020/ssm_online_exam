<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: He Guo
  Date: 2021/5/8
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <jsp:include page="../commons/metas.jsp"/>
    <title>Title</title>
    <jsp:include page="../commons/styles.jsp"/>
    <style>
        .layui-form-checkbox {
            margin-top: 6px !important;
        }
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>管理您的试卷</legend>
        </fieldset>

        <blockquote class="layui-elem-quote layui-text">
            你需要选择一个班级来管理你所在的班级中的试卷、创建试卷
        </blockquote>

        <fieldset class="table-search-fieldset">
            <legend>选择一个班级</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">班级</label>
                            <div class="layui-input-inline">
                                <select name="classId" lay-filter="selectClassId" lay-verify="required" lay-search="">
                                    <option value="">选择你的班级</option>
                                    <c:forEach var="cls" items="${data}">
                                        <option value="${cls.id}">${cls.classcode} - ${cls.classname}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button type="submit" class="layui-btn layui-btn-primary" lay-submit
                                    lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 自主选题 </button>
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add_random"> 随机抽题 </button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除</button>
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="show">查看试卷</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        </script>

    </div>
</div>
<jsp:include page="../commons/scripts.jsp"/>
<script>
    sessionStorage.setItem("examClassId", null);
    layui.use(['form', 'table', 'layer', 'util'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table,
            layer = layui.layer,
            util = layui.util;

        // 监听班级选择事件
        form.on("select(selectClassId)", function (data) {
            // console.log(data.value);
            // 将班级id保存到会话中
            sessionStorage.setItem("examClassId", data.value);
        });

        table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/static/backend/api/empty.json',
            toolbar: '#toolbarDemo',
            parseData: function(res){ //res 即为原始返回的数据
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.total, //解析数据长度
                    "data": res.data //解析数据列表
                };
            },
            text: {
                none: "输入单选题或多选题的个数，开始随机抽题"
            },
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 60, title: '序号', type:'numbers'},
                {field: 'examname', width: 170, title: '考试名称'},
                {field: 'starttime', width: 170, title: '开始时间', templet: function (d) {
                        return util.toDateString(d.starttime, "yyyy-MM-dd HH:mm:ss");
                    }
                },
                {field: 'stoptime', width: 170, title: '结束时间', templet: function (d) {
                        return util.toDateString(d.stoptime, "yyyy-MM-dd HH:mm:ss");
                    }
                },
                {field: 'selectone', title: '单选每题分数', width: 140},
                {field: 'selectmore', width: 140, title: '多选每题分数'},
                {field: 'score', width: 80, title: '总分', sort: true},
                {field: 'status', width: 110, title: '考试状态', templet: function (d) {
                        var timestamp=new Date().getTime();
                        console.log(d);
                        if(timestamp < d.starttime) {
                            return "<font color='gray'>未开始</font>";
                        }
                        if(timestamp <=  d.stoptime) {
                            return "<font color='green'>进行中</font>";
                        }
                        if(timestamp > d.stoptime) {
                            return "<font color='black'>已结束</font>";
                        }
                    }
                },
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 10,
            page: true,
            skin: 'line'
        });

        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            var result = JSON.stringify(data.field);

            console.log(result);

            //执行搜索重载
            table.reload('currentTableId', {
                url: '${pageContext.request.contextPath}/exam/findExam',
                method: "post",
                page: {
                    curr: 1
                },
                where: {
                    classId: data.field.classId
                }
            }, 'data');

            return false;
        });

        /**
         * toolbar监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            var cid = sessionStorage.getItem("examClassId");
            if(cid !== null && cid !== undefined && cid !== "" && cid !== "null") {
                if (obj.event === 'add') {  // 监听添加操作
                    var index = layer.open({
                        title: '自主选题',
                        type: 2,
                        shade: 0.2,
                        maxmin: true,
                        shadeClose: true,
                        area: ['100%', '100%'],
                        content: '/ssm_online_exam/teacher/paperAdd',
                    });
                    $(window).on("resize", function () {
                        layer.full(index);
                    });
                } else if (obj.event === 'add_random') {  // 监听添加操作
                    var index = layer.open({
                        title: '随机抽题',
                        type: 2,
                        shade: 0.2,
                        maxmin: true,
                        shadeClose: true,
                        area: ['100%', '100%'],
                        content: '/ssm_online_exam/teacher/paperAddRandom',
                    });
                    $(window).on("resize", function () {
                        layer.full(index);
                    });
                } else if (obj.event === 'delete') {  // 监听删除操作
                    var checkStatus = table.checkStatus('currentTableId')
                        , data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                }
            } else {
                layer.msg("你未选择班级，请在上面的选择栏中选一个班级创建试卷", {icon: 2});
            }
        });

        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj);
            var checkStatus = table.checkStatus('currentTableId'); //idTest 即为基础参数 id 对应的值
            console.log(checkStatus.data) //获取选中行的数据
        });

        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {

                var index = layer.open({
                    title: '管理试题',
                    type: 2,
                    shade: 0.2,
                    maxmin: true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '/ssm_online_exam/teacher/paperAdd?modify=true',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'show') {
                console.log("查看试卷");
            } else if (obj.event === 'delete') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                });
            }
        });

    });
</script>
</body>
</html>