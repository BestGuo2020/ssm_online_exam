<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: He Guo
  Date: 2021/5/28
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>试卷详情</title>
    <style>
        #exportWord {
            position: absolute;
            right: 5px;
            top: 5px;
        }
    </style>
    <script src="${pageContext.request.contextPath}/static/backend/lib/jquery-3.4.1/jquery-3.4.1.min.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/static/plugins/Blob.js"></script>
    <script src="${pageContext.request.contextPath}/static/plugins/FileSaver.js"></script>
    <script src="${pageContext.request.contextPath}/static/plugins/jquery.wordexport.js"></script>
</head>
<body>

    <button id="exportWord" class="layui-btn layui-btn-normal">导出试卷</button>
    <div id="page-content">
        <div style="text-align: center;">
            <h2>${examInfo.get("name")}</h2>
            考试科目：${examInfo.get("name")}&nbsp;
            时间：${examInfo.get("time")}分钟&nbsp;
            总分：${examInfo.get("score")}分<br><br>
            班级：____________&nbsp; 学号：____________&nbsp; 姓名：____________<br>
        </div>
        <div style="text-align: left; width: 768px; margin: 0 auto;">
            <h4>一、选择题（每题${examInfo.get("single")}分，共${examInfo.get("singleCount") * examInfo.get("single")}分）</h4>
            <div style="margin-block-start: 1em;
                margin-block-end: 1em;
                margin-inline-start: 0px;
                margin-inline-end: 0px;
                padding-inline-start: 40px;">
                <c:forEach items="${examInfo.get('questions')}" var="question">
                    <c:if test="${question.ismulti == false}">
                        <pre style="font-size: 12pt;
                        word-wrap: break-word;
                        white-space: pre-wrap;">${single = single + 1}、${question.questionname}</pre>
                        <pre style="font-size: 10pt;
                        word-wrap: break-word;
                        white-space: pre-wrap;">（A）${question.option1}</pre>
                        <pre style="font-size: 10pt;
                        word-wrap: break-word;
                        white-space: pre-wrap;">（B）${question.option2}</pre>
                        <c:if test="${question.option3 != null && !(question.option3 eq '')}">
                            <pre style="font-size: 10pt;
                                word-wrap: break-word;
                                white-space: pre-wrap;">（C）${question.option3}</pre>
                        </c:if>
                        <c:if test="${question.option4 != null && !(question.option4 eq '')}">
                            <pre style="font-size: 10pt;
                                word-wrap: break-word;
                                white-space: pre-wrap;">（D）${question.option4}</pre>
                        </c:if>
                        <c:if test="${question.option5 != null && !(question.option5 eq '')}">
                            <pre style="font-size: 10pt;
                                word-wrap: break-word;
                                white-space: pre-wrap;">（E）${question.option5}</pre>
                        </c:if>
                    </c:if>
                </c:forEach>
            </div>
            <h4>二、多项选择题（每题${examInfo.get("multi")}分，共${examInfo.get("multiCount") * examInfo.get("multi")}分）</h4>
            <div style="margin-block-start: 1em;
                margin-block-end: 1em;
                margin-inline-start: 0px;
                margin-inline-end: 0px;
                padding-inline-start: 40px;">
                <c:forEach items="${examInfo.get('questions')}" var="question">
                    <c:if test="${question.ismulti == true}">
                        <pre style="font-size: 12pt;
                        word-wrap: break-word;
                        white-space: pre-wrap;">${multi = multi + 1}、${question.questionname}</pre>
                        <pre style="font-size: 10pt;
                        word-wrap: break-word;
                        white-space: pre-wrap;">（A）${question.option1}</pre>
                        <pre style="font-size: 10pt;
                        word-wrap: break-word;
                        white-space: pre-wrap;">（B）${question.option2}</pre>
                        <c:if test="${question.option3 != null && !(question.option3 eq '')}">
                            <pre style="font-size: 10pt;
                                word-wrap: break-word;
                                white-space: pre-wrap;">（C）${question.option3}</pre>
                        </c:if>
                        <c:if test="${question.option4 != null && !(question.option4 eq '')}">
                            <pre style="font-size: 10pt;
                                word-wrap: break-word;
                                white-space: pre-wrap;">（D）${question.option4}</pre>
                        </c:if>
                        <c:if test="${question.option5 != null && !(question.option5 eq '')}">
                            <pre style="font-size: 10pt;
                                word-wrap: break-word;
                                white-space: pre-wrap;">（E）${question.option5}</pre>
                        </c:if>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
<script>
    $("#exportWord").click(function(event) {
        $("#page-content").wordExport("${examInfo.get('name')}");
    });
</script>
</html>
