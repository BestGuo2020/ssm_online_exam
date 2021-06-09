<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/5/31
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <legend>我的考试</legend>
        </fieldset>

        <blockquote class="layui-elem-quote layui-text">
            你需要选择一个您加入的班级来进行考试。考试状态分为3类
            <ol style="margin: 10px 0 0 25px;">
                <li style="list-style: initial;"><span style="color: gray">未开始</span> -- 考试还没有开始</li>
                <li style="list-style: initial;"><span style="color: green">进行中</span> -- 考试正在进行中</li>
                <li style="list-style: initial;"><span style="color: black;">已结束</span> -- 考试时间已结束</li>
            </ol>
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
                                    <c:forEach items="${list}" var="temp">

                                </c:forEach>
                                    <c:forEach var="map" items="${data}">

                                            <option value="${map.id}">${map.classcode} - ${map.classname}</option>

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

        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete"  lay-event="attendExam">点击考试</a>
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
                none: "请选择一个班级，参与考试"
            },
            cols: [[
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
                {title: '操作', minWidth: 250, toolbar: '#currentTableBar', align: "center"}
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



        table.on('tool(currentTableFilter)', function (obj) {
            console.log(obj);
            var data = obj.data;
            if (obj.event === 'show') {
                console.log("查看试卷");
                var index = layer.open({
                    title: '查看试卷',
                    type: 2,
                    shade: 0.2,
                    maxmin: true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '${pageContext.request.contextPath}/exam/paperDetail?id=' + obj.data.id,
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'showGrade') {
                console.log("查看成绩");
            } else if (obj.event === 'attendExam') {
                // console.log("examId: ", obj.data.examId);
                layer.confirm('真的要参加考试吗？', function (index) {
                    window.location.href="http://localhost:8080/ssm_online_exam/exam/answerCard/"+obj.data.id+","+${sessionScope.student.id};
                    <%--$.ajax({--%>
                    <%--    type: "POST",--%>
                    <%--    url: "${pageContext.request.contextPath}/student/myexam",--%>
                    <%--    data: {--%>
                    <%--        examId: obj.data.id--%>
                    <%--    },--%>
                    <%--    success: function(data){--%>
                    <%--        console.log(data);--%>
                    <%--        // layer.msg(data.message);--%>
                    <%--        // 判断返回的数据是json还是字符串--%>
                    <%--        if(typeof data === 'string') {--%>
                    <%--            data = JSON.parse(data);--%>
                    <%--        }--%>
                    <%--        if(data.code === 0) {--%>
                    <%--            layer.msg(data.message, {icon: 1});--%>
                    <%--            obj.del();--%>
                    <%--            layer.close(index);--%>
                    <%--        } else if (data.code === 1) {--%>
                    <%--            layer.msg(data.message, {icon: 2});--%>
                    <%--        } else if (data.code === -1) {--%>
                    <%--            layer.msg(data.message, {icon: 7}, function(){--%>
                    <%--                top.location.href = '${pageContext.request.contextPath}/login';--%>
                    <%--            });--%>
                    <%--        }--%>
                    <%--    }--%>
                    <%--});--%>
                });
            }
        });

    });
</script>
</body>
</html>