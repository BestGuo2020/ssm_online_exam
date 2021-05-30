<%--
  Created by IntelliJ IDEA.
  User: He Guo
  Date: 2021/5/8
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        div {
            width: 60%;
            margin: 0 auto;
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
            width: 100%;
        }

        tr:nth-child(2n) {
            background-color: rgba(88, 73, 65, 0.18);
        }

        tr:hover {
            background-color: rgb(255, 235, 149);
        }

        #tt {
            text-align: right;
            padding-right: 20px;
        }
    </style>
</head>
<body>
<table>

    <h3>2016级计算机科学成绩信息表</h3>
    <table>
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>专业</th>
            <th>成绩</th>
        </tr>
        <tr>
            <td>201601</td>
            <td>张三</td>
            <td>计算机科学</td>
            <td>85</td>
        </tr>
        <tr>
            <td>201601</td>
            <td>张三</td>
            <td>计算机科学</td>
            <td>85</td>
        </tr>
        <tr>
            <td>201601</td>
            <td>张三</td>
            <td>计算机科学</td>
            <td>85</td>
        </tr>
        <tr>
            <td>201601</td>
            <td>张三</td>
            <td>计算机科学</td>
            <td>85</td>
        </tr>
        <tr>
            <td>201601</td>
            <td>张三</td>
            <td>计算机科学</td>
            <td>85</td>
        </tr>
        <tr>
            <td>201601</td>
            <td>张三</td>
            <td>计算机科学</td>
            <td>85</td>
        </tr>
        <tr>
            <td>201601</td>
            <td>张三</td>
            <td>计算机科学</td>
            <td>85</td>
        </tr>
        <tr>
            <td>201601</td>
            <td>张三</td>
            <td>计算机科学</td>
            <td>85</td>
        </tr>
        <tr>
            <td>201601</td>
            <td>张三</td>
            <td>计算机科学</td>
            <td>85</td>
        </tr>
        <tr>
            <td id="tt" colspan="5">总计：10人，平均分：80</td>
        </tr>
    </table>

</table>
</body>
</html>
