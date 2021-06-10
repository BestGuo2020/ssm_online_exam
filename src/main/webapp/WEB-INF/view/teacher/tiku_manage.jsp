<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: He Guo
  Date: 2021/5/8
  Time: 16:00
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
            <legend>管理您的题库</legend>
        </fieldset>

        <blockquote class="layui-elem-quote layui-text">
            你需要选择一个班级，才可以查看当前班级的题库，对题库中的题目进行操作。
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
                            <button type="submit" class="layui-btn layui-btn-primary"  lay-submit lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索</button>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">问题关键字</label>
                        <div class="layui-input-block">
                            <input type="text" name="questionname" autocomplete="off" placeholder="请输入查询问题关键字" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">难度系数</label>
                        <div class="layui-input-block">
                            <input type="checkbox" name="level" value="1" title="简单">
                            <input type="checkbox" name="level" value="2" title="中等">
                            <input type="checkbox" name="level" value="3" title="困难">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">题型</label>
                        <div class="layui-input-block">
                            <input type="radio" name="ismulti" value="1" title="多选">
                            <input type="radio" name="ismulti" value="0" title="单选">
                            <input type="radio" name="ismulti" value="" title="未指定" checked>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加题目 </button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除 </button>
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">修改</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
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
            url: '${pageContext.request.contextPath}/static/backend/api/empty.json',
            toolbar: '#toolbarDemo',
            text: {
                none: "请在选择框中选择你的班级，查找班级中的题库"
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
                {field: 'id', width: 80, title: '序号', type:'numbers'},
                {field: 'questionname', width: 150, title: '问题描述'},
                {field: 'ismulti', width: 80, title: '选择题类型', templet: function(d){
                        if(d.ismulti){
                            return '多选'
                        }else{
                            return '单选'
                        }
                    }
                },
                {field: 'option1', width: 80, title: '选项A'},
                {field: 'option2', title: '选项B', width: 80},
                {field: 'option3', width: 80, title: '选项C', templet: function(d){
                        if(d.option3 === "" || d.option3 === null){
                            return '无'
                        }
                        return d.option3;
                    }},
                {field: 'option4', width: 80, title: '选项D', templet: function(d){
                        if(d.option4 === "" || d.option4 === null){
                            return '无'
                        }
                        return d.option4;
                    }},
                {field: 'option5', width: 80, title: '选项E', templet: function(d){
                        if(d.option5 === "" || d.option5 === null){
                            return '无'
                        }
                        return d.option5;
                    }
                },
                {field: 'answer', width: 135, title: '答案'},
                {field: 'reason', width: 135, title: '解析'},
                {field: 'level', width: 135, title: '难度', templet: function(d){
                        switch (d.level) {
                            case 1:
                                return "简单";
                            case 2:
                                return "一般";
                            case 3:
                                return "困难";
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
            console.log(data);
            var arr_box = [];
            $('input[type=checkbox]:checked').each(function() {
                arr_box.push(parseInt($(this).val()));
            });
            //执行搜索重载
            table.reload('currentTableId', {
                url: '${pageContext.request.contextPath}/tiku/findQuestion',
                method: "post",
                page: {
                    curr: 1
                },
                where: {
                    belongclass: data.field.classId,
                    questionname: data.field.questionname,
                    levels: arr_box.join(),
                    ismulti: data.field.ismulti.checked
                }
            }, 'data');

            return false;
        });

        /**
         * toolbar监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                var index = layer.open({
                    title: '添加题目',
                    type: 2,
                    shade: 0.2,
                    maxmin:false,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '/ssm_online_exam/teacher/tikuAdd?modify=false',
                });
            } else if (obj.event === 'delete') {  // 监听删除操作
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data, delId = [];
                // layer.alert(JSON.stringify(data));
                for(var i = 0; i < data.length; i++){
                    delId.push(data[i].id);
                }
                layer.confirm('真的删除选中的题目吗？', function (index) {
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/tiku/deleteQuestionMore",
                        data: {
                            ids: delId
                        },
                        traditional: true,
                        success: function(data){
                            console.log(data);
                            layer.close(index);
                            // layer.msg(data.message);
                            // 判断返回的数据是json还是字符串
                            if(typeof data === 'string') {
                                data = JSON.parse(data);
                            }
                            if(data.code === 0) {
                                layer.msg(data.message, {icon: 1, offset: '200px'}, function() {
                                    table.reload('currentTableId', {
                                        method: "post",
                                        page: {
                                            curr: 1
                                        },
                                    }, 'data');
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
                    layer.close(index);
                });

            }
        });

        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });

        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            console.log(data);
            if (obj.event === 'edit') {

                var index = layer.open({
                    title: '修改题目',
                    type: 2,
                    shade: 0.2,
                    maxmin:false,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '/ssm_online_exam/teacher/tikuAdd?modify=true&id=' + data.id,
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'delete') {
                layer.confirm('真的删除这道题目吗？', function (index) {
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/tiku/manageQuestion/3",
                        data: {
                            id: data.id
                        },
                        success: function(data){
                            console.log(data);
                            layer.close(index);
                            // layer.msg(data.message);
                            // 判断返回的数据是json还是字符串
                            if(typeof data === 'string') {
                                data = JSON.parse(data);
                            }
                            if(data.code === 0) {
                                layer.msg(data.message, {icon: 1, offset: '200px'}, function() {
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
                    layer.close(index);
                });
            }
        });

    });
</script>
</body>
</html>