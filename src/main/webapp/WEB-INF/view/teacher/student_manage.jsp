<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: He Guo
  Date: 2021/5/8
  Time: 15:58
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
            <legend>管理您的学生</legend>
        </fieldset>

        <blockquote class="layui-elem-quote layui-text">
            你需要选择一个班级来查看你的班级中的学生
        </blockquote>

        <fieldset class="table-search-fieldset">
            <legend>选择一个班级</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">班级</label>
                            <div class="layui-input-inline">
                                <select name="classId" lay-verify="required" lay-search="">
                                    <option value="">选择你的班级</option>
                                    <c:forEach var="cls" items="${data}">
                                        <option value="${cls.id}">${cls.classcode} - ${cls.classname}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button type="submit" class="layui-btn layui-btn-primary" lay-submit lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索</button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="kickOut"> 踢出 </button>
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="kickOut">踢出</a>
        </script>

    </div>
</div>
<jsp:include page="../commons/scripts.jsp" />
<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table,
            classId = [0];

        table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/static/backend/api/empty.json',
            toolbar: '#toolbarDemo',
            text: {
                none: '请在选择框中选择你的班级查找班级中所在的学生' // 默认：无数据。注：该属性为 layui 2.2.5 开始新增
            },
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
                {type: "checkbox", width: 50},
                {field: 'id', width: 120, title: '序号', type:'numbers'},
                {field: 'username', width: 110, title: '姓名'},
                {field: 'gender', width: 110, title: '性别', templet: function(d){
                        if(d.gender === 1){
                            return '男'
                        }else{
                            return '女'
                        }
                    }
                },
                {field: 'stuid', width: 140, title: '学号'},
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

            classId[0] = data.field.classId;

            //执行搜索重载
            table.reload('currentTableId', {
                url: '${pageContext.request.contextPath}/classes/loadStudentByClass/' + data.field.classId,
                page: {
                    curr: 1
                }
            }, 'data');

            return false;
        });

        /**
         * toolbar监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'kickOut') {  // 监听删除操作
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                // layer.alert(JSON.stringify(data));
                // 将数据保存至数组
                var tmp_data = [];
                for(var i = 0; i < data.length; i++) {
                    tmp_data.push(data[i].id);
                }
                console.log(data);
                layer.confirm('真的要将选中的学生踢出班级吗？', function (index) {
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/classes/kickOutMany",
                        data: {
                            classId: classId[0],
                            stuIds: tmp_data
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

        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });

        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {

                var index = layer.open({
                    title: '修改班级信息',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '/ssm_online_exam/teacher/classAdd?modify=true',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'kickOut') {
                console.log(data);
                layer.confirm('真的要将选中的学生踢出班级吗？', function (index) {
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/classes/kickOut",
                        data: {
                            classId: classId[0],
                            stuId: data.id
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

    });
</script>
</body>
</html>

