<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/5/31
  Time: 18:35
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

        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>查看考试成绩</legend>
        </fieldset>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
<%--                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 退出 </button>--%>
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

<%--        <script type="text/html" id="currentTableBar">--%>
<%--            <a class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn"  lay-event="delete">退出</a>--%>
<%--        </script>--%>
    </div>
</div>
<jsp:include page="../commons/scripts.jsp" />
<script>
    layui.use(['form', 'table', 'layer'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table,
            layer = layui.layer;

        table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/exam/studentExamPassed/${sessionScope.student.id}',
            toolbar: '#toolbarDemo',
            parseData: function(res){ //res 即为原始返回的数据
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.total, //解析数据长度
                    "data": res.data //解析数据列表
                };
            },
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[

                {field: 'examcode', width: 95, title: '考试编号'},
                {field: 'examname', width: 200, title: '考试名称', sort: true},
                {field: 'totalscore', width: 400, title: '考试总分'},
                {field: 'myscore', width: 400, title: '我的得分'},

                // {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 15,
            page: true,
            skin: 'line'
        });

        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            var result = JSON.stringify(data.field);
            layer.alert(result, {
                title: '最终的搜索信息'
            });

            //执行搜索重载
            table.reload('currentTableId', {
                page: {
                    curr: 1
                }
                , where: {
                    searchParams: result
                }
            }, 'data');

            return false;
        });



        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });
        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'delete') {  // 监听删除操作
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                // layer.alert(JSON.stringify(data));
                // 将数据保存至数组
                var tmp_data = [];
                for(var i = 0; i < data.length; i++) {
                    tmp_data.push(data[i].id);
                }
                console.log(data);
                layer.confirm('真的要退出选中的这些班级吗？', function (index) {
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/classes/deleteClassMany/${sessionScope.student.id}",
                        data: {
                            classIds: tmp_data,
                            stuId: ${sessionScope.student.id}
                        },
                        traditional: true,
                        success: function(data){
                            console.log(data);
                            // layer.msg(data.message);
                            // 判断返回的数据是json还是字符串
                            if(typeof data === 'string') {
                                data = JSON.parse(data);
                            }
                            if(data.code === 0) {
                                layer.msg(data.message, {icon: 1}, function(){
                                    // 重载表格数据
                                    table.reload("currentTableId", {
                                        page: {
                                            curr: 1 //重新从第 1 页开始
                                        }
                                    });
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
                });
            }
        });

        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'delete') {
                layer.confirm('该班级的相关的所有数据将会消失，真的要退出这个班级吗？', function (index) {
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/classes/deleteClass_do/${sessionScope.student.id}",
                        data: {
                            id: data.id,
                            student: ${sessionScope.student.id}
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
                                    obj.del();
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
                });
            }
        });


    });
</script>
</body>
</html>

