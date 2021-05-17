<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/5/14
  Time: 15:21
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
            <legend>申请加入班级</legend>
        </fieldset>

        <h4 style="color: #0f6c8d;margin-left: 10px">输入要加入班级的编号，点击「查询」按钮，核实班级信息无误后点击「申请加入」按钮。</h4>

        <div style="margin: 10px 10px 10px 10px" id="btn">
            <form class="layui-form layui-form-pane" onsubmit="show()" action="findClassInfo">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">班级编号</label>
                        <div class="layui-input-inline">
                            <!--注意此处input标签里的id-->
                            <input class="layui-input" name="classCode" value="${classInfo.classCode}" id="demoReload" autocomplete="off">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <!--注意此处button标签里的type属性-->
                        <button type="submit" class="layui-btn layui-btn-primary"  lay-submit data-type="reload"
                                lay-filter="findClassInfo"><i class="layui-icon"></i> 查 询</button>
                    </div>
                </div>
            </form>
        </div>

        <div class="classinfo" id="classinfo">
            <c:if test="${classInfo != null}">
                <form action="joinClass">
                    <h3 style="margin-top: 20px;margin-left: 10px">班级信息：</h3>
                    <p style="margin-top: 10px;margin-left: 10px;font-size: 13.5px">班级编号：${classInfo.classCode}</p>
                    <p style="margin-top: 10px;margin-left: 10px;font-size: 13.5px">班级名称：${classInfo.className}</p>
                    <p style="margin-top: 10px;margin-left: 10px;font-size: 13.5px">班级信息：${classInfo.classDesc}</p>
                    <p style="margin-top: 10px;margin-left: 10px;font-size: 13.5px">教师名称：${classInfo.username}</p>
                    <a style="margin-top: 10px;margin-left: 10px" href="${pageContext.request.contextPath}/student/joinclass?classId=${classInfo.classCode}"
                       type="button" class="layui-btn layui-btn-primary" lay-filter="joinClass">加入班级</a>
                </form>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="../commons/scripts.jsp" />
<%--<script>
    var classCode = document.getElementById('demoReload');
    var vvv = sessionStorage.getItem('classCode');
    classCode.value = vvv;
    function show() {
        var classinfo = document.getElementById('classinfo');
        if (classCode.value!=""){
            sessionStorage.setItem('classCode', classCode.value);
            classinfo.style.display="block";
        }else {
            classinfo.style.display="none";
            return false;
        }
        sessionStorage.clear();
    }

    show();

</script>--%>
</body>
</html>
