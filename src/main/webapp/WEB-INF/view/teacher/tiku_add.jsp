<%--
  Created by IntelliJ IDEA.
  User: He Guo
  Date: 2021/5/8
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../commons/metas.jsp" />
    <title>Title</title>
    <jsp:include page="../commons/styles.jsp" />
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <blockquote class="layui-elem-quote layui-text">
            <p>添加题目有两种方式：</p>
            <p>
                1. 手动录入题目到题库 <br>
                2. 通过 excel 表格的方式导入题目到题库
            </p>
        </blockquote>
        <fieldset class="table-search-fieldset" style="margin-top: 20px;">
            <legend>手动录入题目</legend>
            <form class="layui-form layui-form-pane" action="" lay-filter="example">

                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">题目内容</label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入问题" class="layui-textarea"></textarea>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" title="选项A（第一空）">选项A（第一空）</label>
                    <div class="layui-input-block">
                        <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入选项A（第一空）" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" title="选项B（第二空）">选项B（第二空）</label>
                    <div class="layui-input-block">
                        <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入选项B（第二空）" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" title="选项C（第三空）">选项C（第三空）</label>
                    <div class="layui-input-block">
                        <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入选项C（第三空）" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" title="选项D（第四空）">选项D（第四空）</label>
                    <div class="layui-input-block">
                        <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入选项D（第四空）" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label" title="选项E（第五空）">选项E（第五空）</label>
                    <div class="layui-input-block">
                        <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入选项E（第五空）" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">题型</label>
                    <div class="layui-input-block">
                        <input type="radio" name="sex" value="1" title="选择题" checked="">
                        <input type="radio" name="sex" value="0" title="填空题">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">答案</label>
                    <div class="layui-input-block">
                        <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="若选择题为多选答案，中间不需要逗号隔开（例如：ABD）。若填空题有多个空，答案与答案之间用英文逗号分开（例如：C,Java,Python）" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">解析</label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入内容" class="layui-textarea"></textarea>
                    </div>
                </div>

                <div class="layui-form-item">
                    <button class="layui-btn" lay-submit="" lay-filter="demo1">创建班级</button>
                </div>
            </form>
        </fieldset>
        <fieldset class="table-search-fieldset" style="margin-top: 20px;">
            <legend>通过 excel 表格导入</legend>
            <a type="button" class="layui-btn layui-btn-sm layui-btn-normal" href="#" target="_blank">点此下载导入模板</a>
            <button type="button" class="layui-btn" id="test1">
                <i class="layui-icon">&#xe67c;</i> 导入题目
            </button>
        </fieldset>
    </div>
</div>
<jsp:include page="../commons/scripts.jsp" />
<script>
    layui.use('upload', function(){
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#test1' //绑定元素
            ,url: '/upload/' //上传接口
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
            }
            ,error: function(){
                //请求异常回调
            }
        });
    });
</script>
</body>
</html>
