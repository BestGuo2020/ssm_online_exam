<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: He Guo
  Date: 2021/5/8
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>${exam.examname} - 成绩表</title>
    <jsp:include page="../commons/styles.jsp"/>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        div {
            width: 60%;
            margin: 0 auto;
            text-align: center;
        }

        h3 {
            text-align: center;
            height: 50px;
            line-height: 50px;
        }

        table, tr, td, th {
            text-align: center;
            border: 1px solid gray;
            border-collapse: collapse;
            height: 50px;
        }

        table {
            width: 80%;
            margin: 5px auto;
        }

        tr:hover {
            background-color: rgb(255, 235, 149) !important;
        }

        #tt {
            text-align: right;
            padding-right: 20px;
        }
    </style>
    <script src="${pageContext.request.contextPath}/static/backend/lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/plugins/FileSaver.js"></script>
    <script src="${pageContext.request.contextPath}/static/plugins/xlsx.core.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/plugins/tableExport.min.js"></script>
</head>
<div style="padding: 5px 0;">
    <a id="export" class="layui-btn layui-btn-xs">导出成绩</a>
    <a id="sortedBydesc" class="layui-btn layui-btn-xs layui-btn-normal" href="?desc=1">成绩从高到低</a>
    <a id="sortedByasc" class="layui-btn layui-btn-xs layui-btn-normal" href="?desc=0">成绩从低到高</a>
</div>
<body>
    <table id="table_233">
        <thead>
        <tr>
            <td colspan="6">
                <h3>“${exam.examname}”成绩信息表</h3>
            </td>
        </tr>
        <tr>
            <th>序号</th>
            <th>学号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>成绩</th>
            <th>备注</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${students}" var="student">
            <tr>
                <td>${id = id + 1}</td>
                <td>${student.stuId}</td>
                <td>${student.username}</td>
                <td>${student.gender == 1 ? '男' : '女'}</td>
                <td>${student.score}</td>
                <td>${student.status}</td>
            </tr>
        </c:forEach>
        <tr>
            <td id="tt" colspan="6">总计：${students.size()}人，平均分（不含未考、缺考）：${avg1.toString() eq 'NaN' ? '' : avg1}</td>
        </tr>
        <tr>
            <td colspan="6" style="text-align: left;padding: 8px;font-size: 12px;">
                注：备注一栏是自动生成的，用于查看学生是否已考。如果学生没有交卷，那么该学生的成绩这一栏为空<br>
                <br>
                考试中 -- 该学生已答题，但未交卷。<br>
                已交卷 -- 该学生已交卷，当前学生的考试已结束。<br>
                未考 -- 该学生还没进入本次考试，但考试时间未到，本次考试未考<br>
                缺考 -- 该学生还没进入本次考试，但考试时间已到；或者该学生忘记在规定的时间内点击交卷。本次考试缺考
            </td>
        </tr>
        </tbody>
    </table>
</body>
<script>
    $("#export").click(function () {
        $('#table_233').tableExport({
            type: 'excel',
            fileName: '“${exam.examname}”成绩信息表',
            // 加载样式
            mso: {
                styles: ['margin', 'padding', 'box-sizing', 'width', 'text-align',
                    'height', 'line-height', 'border-collapse', 'border', 'background-color', 'padding-right']
            }
        });
    });
</script>
</html>
