<%--
  Created by IntelliJ IDEA.
  User: He Guo
  Date: 2021/5/8
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../commons/metas.jsp" />
    <title>Title</title>
    <jsp:include page="../commons/styles.jsp" />
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <c:if test="${modify eq 'false'}">
            <blockquote class="layui-elem-quote layui-text">
                <p>添加题目有两种方式：</p>
                <p>
                    1. 手动录入题目到题库 <br>
                    2. 通过 excel 表格的方式导入题目到题库
                </p>
                <p>必须选择一个班级才能导入</p>
            </blockquote>
        </c:if>
        <fieldset class="table-search-fieldset" style="margin-top: 20px;">
            <c:choose>
                <c:when test="${modify eq 'false'}">
                    <legend>手动录入题目</legend>
                </c:when>
                <c:otherwise>
                    <legend>修改题目</legend>
                </c:otherwise>
            </c:choose>
            <form class="layui-form layui-form-pane" action="" lay-filter="example">
                <input type="hidden" name="id" value="${question.id}" />
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">选择导入的班级</label>
                    <div class="layui-input-block">
                        <select name="classId" id="classId" lay-verify="required" lay-search="">
                            <option value="">选择你的班级</option>
                            <c:forEach var="cls" items="${data}">
                                <c:choose>
                                    <c:when test="${cls.id == question.belongclass}">
                                        <option value="${cls.id}" selected>${cls.classcode} - ${cls.classname}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${cls.id}">${cls.classcode} - ${cls.classname}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">题目内容</label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入问题" lay-verify="required" name="question" class="layui-textarea">${question.questionname}</textarea>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" title="选项A">选项A</label>
                    <div class="layui-input-block">
                        <input type="text" value="${question.option1}" name="optionA" lay-verify="required" lay-verify="title" autocomplete="off" placeholder="请输入选项A" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" title="选项B">选项B</label>
                    <div class="layui-input-block">
                        <input type="text" value="${question.option2}" name="optionB" lay-verify="required" lay-verify="title" autocomplete="off" placeholder="请输入选项B" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" title="选项C">选项C</label>
                    <div class="layui-input-block">
                        <input type="text" value="${question.option3}" name="optionC" autocomplete="off" placeholder="请输入选项C" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" title="选项D">选项D</label>
                    <div class="layui-input-block">
                        <input type="text" value="${question.option4}" name="optionD" autocomplete="off" placeholder="请输入选项D" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" title="选项E">选项E</label>
                    <div class="layui-input-block">
                        <input type="text" value="${question.option5}" name="optionE" autocomplete="off" placeholder="请输入选项E" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">题型</label>
                    <div class="layui-input-block">
                        <c:choose>
                            <c:when test="${question.ismulti}">
                                <input type="radio" name="type" value="0" lay-verify="required" title="单选题">
                                <input type="radio" name="type" value="1" lay-verify="required" title="多选题" checked="">
                            </c:when>
                            <c:otherwise>
                                <input type="radio" name="type" value="0" lay-verify="required" title="单选题" checked="">
                                <input type="radio" name="type" value="1" lay-verify="required" title="多选题">
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">难度系数</label>
                    <div class="layui-input-block">
                        <c:choose>
                            <c:when test="${question.level == 2}">
                                <input type="radio" name="level" value="1" lay-verify="required" title="简单">
                                <input type="radio" name="level" value="2" lay-verify="required" title="中等" checked="">
                                <input type="radio" name="level" value="3" lay-verify="required" title="困难">
                            </c:when>
                            <c:when test="${question.level == 2}">
                                <input type="radio" name="level" value="1" lay-verify="required" title="简单">
                                <input type="radio" name="level" value="2" lay-verify="required" title="中等">
                                <input type="radio" name="level" value="3" lay-verify="required" title="困难" checked="">
                            </c:when>
                            <c:otherwise>
                                <input type="radio" name="level" value="1" lay-verify="required" title="简单" checked="">
                                <input type="radio" name="level" value="2" lay-verify="required" title="中等">
                                <input type="radio" name="level" value="3" lay-verify="required" title="困难">
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">答案</label>
                    <div class="layui-input-block">
                        <input type="text" name="answer" value="${question.answer}" lay-verify="title" lay-verify="required" autocomplete="off" placeholder="若选择题为多选答案，中间不需要逗号隔开（例如：ABD）" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">解析</label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入内容" lay-verify="required" name="analyse" class="layui-textarea">${question.reason}</textarea>
                    </div>
                </div>

                <div class="layui-form-item">
                    <button class="layui-btn" lay-submit="" lay-filter="submit-question">提交题目</button>
                </div>
            </form>
        </fieldset>
        <c:if test="${modify eq 'false'}">
            <fieldset class="table-search-fieldset" style="margin-top: 20px;">
                <legend>通过 excel 表格导入</legend>
                <a type="button" class="layui-btn layui-btn-sm layui-btn-normal" href="${pageContext.request.contextPath}/static/template/题库导入模板.xlsx" target="_blank">点此下载导入模板</a>
                <br/><br/>
                <button type="button" class="layui-btn" id="test1">
                    <i class="layui-icon">&#xe67c;</i> 选择文件
                </button>
                <button type="button" class="layui-btn" id="test9">开始上传</button>
            </fieldset>
        </c:if>
    </div>
</div>
<jsp:include page="../commons/scripts.jsp" />
<script>

    layui.use(['form', 'upload', 'element'], function(){
        var upload = layui.upload,
        form = layui.form, element = layui.element,
        $ = layui.jquery;

        <c:if test="${modify eq 'false'}">
        // 文档上传
        var uploadInst = upload.render({
            elem: '#test1' //绑定元素
            ,url: '${pageContext.request.contextPath}/tiku/uploadQuestionSet' //上传接口
            ,auto: false // 不自定上传
            ,accept: 'file' //普通文件
            ,data: {
                belongclass: function () {
                    return $("#classId").val();
                }
            }
            ,exts: 'xls|xlsx' // 表格
            ,bindAction: '#test9'
            ,progress: function(n, elem, res, index){ // 上传进度
                var percent = n + '%' //获取进度百分比
                element.progress('demo', percent); //可配合 layui 进度条元素使用

                console.log(elem); //得到当前触发的元素 DOM 对象。可通过该元素定义的属性值匹配到对应的进度条。
                console.log(res); //得到 progress 响应信息
                console.log(index); //得到当前上传文件的索引，多文件上传时的进度条控制，如：
                element.progress('demo-'+ index, n + '%'); //进度条
            }
            ,done: function(res){
                //上传完毕回调
                console.log(res);
                // 判断返回的数据是json还是字符串
                if(typeof res === 'string') {
                    res = JSON.parse(res);
                }
                if(res.code === 0) {
                    layer.msg(res.message, {icon: 1, offset: '200px'}, function() {
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index);
                    });
                } else if (res.code === 1) {
                    layer.msg(res.message, {icon: 2, offset: '200px'});
                } else if (res.code === -1) {
                    layer.msg(res.message, {icon: 7, offset: '200px'}, function(){
                        top.location.href = '${pageContext.request.contextPath}/login';
                    });
                }
            }
            ,error: function(){
                //请求异常回调
            }
        });
        </c:if>

        // 手动导入上传
        form.on("submit(submit-question)", function (data) {
            console.log(data.field);
            layer.confirm(${modify.equals('true') ? '"确定要修改吗？"' : '"确定要添加吗？"'}, {offset: '200px'}, function (index) {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/tiku/manageQuestion/" + ${modify.equals('true') ? 2 : 1},
                    data: {
                        id: (data.field.id === "" || data.field.id === null) ? null : data.field.id,
                        belongclass: data.field.classId,
                        questionname: data.field.question,
                        option1: data.field.optionA,
                        option2: data.field.optionB,
                        option3: data.field.optionC,
                        option4: data.field.optionD,
                        option5: data.field.optionE,
                        ismulti: data.field.type,
                        answer: data.field.answer,
                        reason: data.field.analyse,
                        level: data.field.level
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
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index);
                            });
                        } else if (data.code === 1) {
                            layer.msg(data.message, {icon: 2, offset: '200px'});
                        } else if (data.code === -1) {
                            layer.msg(data.message, {icon: 7, offset: '200px'}, function(){
                                top.location.href = '${pageContext.request.contextPath}/login';
                            });
                        }
                    }
                });
            });
            return false;
        });

    });
</script>
</body>
</html>
