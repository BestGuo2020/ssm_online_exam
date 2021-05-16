<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/5/14
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: He Guo
  Date: 2021/5/7
  Time: 19:05
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
            <legend>查看你参与的班级</legend>
        </fieldset>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">

            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="delete">退出</a>
        </script>
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
            url: '${pageContext.request.contextPath}/classes/loadJoinedClasses/${sessionScope.student.id}',
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'classcode', width: 95, title: '班级码'},
                {field: 'classname', width: 200, title: '班级名', sort: true},
                {field: 'classdesc', width: 400, title: '班级描述'},
                {field: 'classcount', width: 400, title: '班级人数'},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
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

        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
         if (obj.event === 'delete') {
                layer.confirm('该班级的相关的所有数据将会消失，真的要退出这个班级吗？', function (index) {
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/classes/deleteClass_do/${sessionScope.student.id}",
                        data: {
                            id: data.,
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

