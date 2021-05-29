<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: He Guo
  Date: 2021/5/5
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title>jQuery在线考试倒计时界面</title>
    <link href="${pageContext.request.contextPath}/static/answer/css/main.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/static/answer/css/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/static/answer/css/test.css" rel="stylesheet" type="text/css" />

    <style>
        .hasBeenAnswer {
            background: #5d9cec;
            color: #fff;
        }

        .reading h2 {
            width: 100%;
            margin: 20px 0 70px;
            text-align: center;
            line-height: 2;
            font-size: 20px;
            color: #59595b;
        }

        .reading h2 a {
            text-decoration: none;
            color: #59595b;
            font-size: 20px;
        }

        .reading h2 a:hover {
            color: #2183f1;
        }
    </style>
</head>

<body>
<div class="main">
    <!--nr start-->
    <div class="test_main">
        <div class="nr_left">
            <div class="test">
                <form>
                    <div class="test_title">
                        <p class="test_time">
                            <b class="alt-1">01:40</b>
                        </p>
                        <font>
                            <input type="button" id="commitBtn" value="交卷">
                        </font>
                        <font>
                            <input type="button" style="background: #66ccff" id="saveBtn" value="保存">
                        </font>
                    </div>

                    <div class="test_content">
                        <div class="test_content_title">
                            <h2>单选题</h2>
                            <p>
                                <span>共</span><i class="content_lit">${examInfo.get("singleCount")}</i><span>题，</span><span>合计</span><i class="content_fs">${examInfo.get("singleCount") * examInfo.get("single")}</i><span>分</span>
                            </p>
                        </div>
                    </div>
                    <div class="test_content_nr">
                        <ul>
                            <c:forEach items="${examInfo.get('questions')}" var="question">
                                <c:if test="${!question.ismulti}">
                                    <li id="qu_0_${single}">
                                        <div class="test_content_nr_tt">
                                            <i>${single = single + 1}</i><span style="font-size: 10.5pt;">(${examInfo.get("single")}分)</span><pre style="display: inline; font-family: initial; font-size: 12pt;">${question.questionname}</pre>
                                        </div>

                                        <div class="test_content_nr_main">
                                            <ul>

                                                <li class="option">
                                                    <c:choose>
                                                        <c:when test="${answer[single - 1] eq 'A'}">
                                                            <input type="radio" class="radioOrCheck" name="answer${single}"
                                                                   id="0_answer_${single}_option_1" checked />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input type="radio" class="radioOrCheck" name="answer${single}"
                                                                   id="0_answer_${single}_option_1" />
                                                        </c:otherwise>
                                                    </c:choose>

                                                    <label for="0_answer_${single}_option_1">
                                                        A.
                                                        <pre class="ue" style="display: inline; font-family: initial; font-size: 12pt;">${question.option1}</pre>
                                                    </label>
                                                </li>

                                                <li class="option">
                                                    <c:choose>
                                                    <c:when test="${answer[single - 1] eq 'B'}">
                                                        <input type="radio" class="radioOrCheck" name="answer${single}"
                                                               id="0_answer_${single}_option_2" checked />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" class="radioOrCheck" name="answer${single}"
                                                               id="0_answer_${single}_option_2" />
                                                    </c:otherwise>
                                                    </c:choose>

                                                    <label for="0_answer_${single}_option_2">
                                                        B.
                                                        <pre class="ue" style="display: inline; font-family: initial; font-size: 12pt;">${question.option2}</pre>
                                                    </label>
                                                </li>
                                                <c:if test="${question.option3 != null && !(question.option3 eq '')}">
                                                    <li class="option">
                                                        <c:choose>
                                                            <c:when test="${answer[single - 1] eq 'C'}">
                                                                <input type="radio" class="radioOrCheck" name="answer${single}"
                                                                       id="0_answer_${single}_option_3" checked />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="radio" class="radioOrCheck" name="answer${single}"
                                                                       id="0_answer_${single}_option_3" />
                                                            </c:otherwise>
                                                        </c:choose>

                                                        <label for="0_answer_${single}_option_3">
                                                            C.
                                                            <pre class="ue" style="display: inline; font-family: initial; font-size: 12pt;">${question.option3}</pre>
                                                        </label>
                                                    </li>
                                                </c:if>
                                                <c:if test="${question.option4 != null && !(question.option4 eq '')}">
                                                    <li class="option">
                                                        <c:choose>
                                                            <c:when test="${answer[single - 1] eq 'D'}">
                                                                <input type="radio" class="radioOrCheck" name="answer${single}"
                                                                       id="0_answer_${single}_option_4" checked />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="radio" class="radioOrCheck" name="answer${single}"
                                                                       id="0_answer_${single}_option_4" />
                                                            </c:otherwise>
                                                        </c:choose>

                                                        <label for="0_answer_${single}_option_4">
                                                            D.
                                                            <pre class="ue" style="display: inline; font-family: initial; font-size: 12pt;">${question.option4}</pre>
                                                        </label>
                                                    </li>
                                                </c:if>
                                                <c:if test="${question.option5 != null && !(question.option5 eq '')}">
                                                    <li class="option">
                                                        <c:choose>
                                                            <c:when test="${answer[single - 1] eq 'E'}">
                                                                <input type="radio" class="radioOrCheck" name="answer${single}"
                                                                       id="0_answer_${single}_option_5" checked />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="radio" class="radioOrCheck" name="answer${single}"
                                                                       id="0_answer_${single}_option_5" />
                                                            </c:otherwise>
                                                        </c:choose>

                                                        <label for="0_answer_${single}_option_5">
                                                            E.
                                                            <pre class="ue" style="display: inline; font-family: initial; font-size: 12pt;">${question.option5}</pre>
                                                        </label>
                                                    </li>
                                                </c:if>
                                            </ul>
                                        </div>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div>

                    <div class="test_content">
                        <div class="test_content_title">
                            <h2>多选题</h2>
                            <p>
                                <span>共</span><i class="content_lit">${examInfo.get("multiCount")}</i><span>题，</span><span>合计</span><i class="content_fs">${examInfo.get("multiCount") * examInfo.get("multi")}</i><span>分</span>
                            </p>
                        </div>
                    </div>
                    <div class="test_content_nr">
                        <ul>
                            <c:forEach items="${examInfo.get('questions')}" var="question">
                                <c:if test="${question.ismulti}">
                                    <li id="qu_1_${multi}">
                                        <div style="display: none;">${single = single + 1}</div>
                                        <div class="test_content_nr_tt">
                                            <i>${multi = multi + 1}</i><span style="font-size: 10.5pt;">(${examInfo.get("single")}分)</span><pre style="display: inline; font-family: initial; font-size: 12pt;">${question.questionname}</pre>
                                        </div>

                                        <div class="test_content_nr_main">
                                            <ul>
                                                <li class="option">
                                                    <c:choose>
                                                        <c:when test="${answer[single - 1].contains('A')}">
                                                            <input type="checkbox" class="radioOrCheck" name="answer${multi}"
                                                               id="1_answer_${multi}_option_1" checked />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input type="checkbox" class="radioOrCheck" name="answer${multi}"
                                                                   id="1_answer_${multi}_option_1" />
                                                        </c:otherwise>
                                                    </c:choose>

                                                    <label for="1_answer_${multi}_option_1">
                                                        A.
                                                        <pre class="ue" style="display: inline; font-family: initial; font-size: 12pt;">${question.option1}</pre>
                                                    </label>
                                                </li>

                                                <li class="option">
                                                    <c:choose>
                                                        <c:when test="${answer[single - 1].contains('B')}">
                                                            <input type="checkbox" class="radioOrCheck" name="answer${multi}"
                                                                   id="1_answer_${multi}_option_2" checked />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input type="checkbox" class="radioOrCheck" name="answer${multi}"
                                                                   id="1_answer_${multi}_option_2" />
                                                        </c:otherwise>
                                                    </c:choose>

                                                    <label for="1_answer_${multi}_option_2">
                                                        B.
                                                        <pre class="ue" style="display: inline; font-family: initial; font-size: 12pt;">${question.option2}</pre>
                                                    </label>
                                                </li>
                                                <c:if test="${question.option3 != null && !(question.option3 eq '')}">
                                                    <li class="option">
                                                        <c:choose>
                                                            <c:when test="${answer[single - 1].contains('C')}">
                                                                <input type="checkbox" class="radioOrCheck" name="answer${multi}"
                                                                       id="1_answer_${multi}_option_3" checked />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="checkbox" class="radioOrCheck" name="answer${multi}"
                                                                       id="1_answer_${multi}_option_3" />
                                                            </c:otherwise>
                                                        </c:choose>

                                                        <label for="1_answer_${multi}_option_3">
                                                            C.
                                                            <pre class="ue" style="display: inline; font-family: initial; font-size: 12pt;">${question.option3}</pre>
                                                        </label>
                                                    </li>
                                                </c:if>
                                                <c:if test="${question.option4 != null && !(question.option4 eq '')}">
                                                    <li class="option">
                                                        <c:choose>
                                                            <c:when test="${answer[single - 1].contains('D')}">
                                                                <input type="checkbox" class="radioOrCheck" name="answer${multi}"
                                                                       id="1_answer_${multi}_option_4" checked />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="checkbox" class="radioOrCheck" name="answer${multi}"
                                                                       id="1_answer_${multi}_option_4" />
                                                            </c:otherwise>
                                                        </c:choose>

                                                        <label for="1_answer_${multi}_option_4">
                                                            D.
                                                            <pre class="ue" style="display: inline; font-family: initial; font-size: 12pt;">${question.option4}</pre>
                                                        </label>
                                                    </li>
                                                </c:if>
                                                <c:if test="${question.option5 != null && !(question.option5 eq '')}">
                                                    <li class="option">
                                                        <c:choose>
                                                            <c:when test="${answer[single - 1].contains('E')}">
                                                                <input type="checkbox" class="radioOrCheck" name="answer${multi}"
                                                                       id="1_answer_${multi}_option_5" checked />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="checkbox" class="radioOrCheck" name="answer${multi}"
                                                                       id="1_answer_${multi}_option_5" />
                                                            </c:otherwise>
                                                        </c:choose>

                                                        <label for="1_answer_${multi}_option_5">
                                                            E.
                                                            <pre class="ue" style="display: inline; font-family: initial; font-size: 12pt;">${question.option5}</pre>
                                                        </label>
                                                    </li>
                                                </c:if>
                                            </ul>
                                        </div>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div>

                </form>
            </div>

        </div>
        <div class="nr_right">
            <div class="nr_rt_main">
                <div class="rt_nr1">
                    <div class="rt_nr1_title">
                        <h1>答题卡</h1>
                        <p class="test_time">
                            <b class="alt-1">01:40</b>
                        </p>
                    </div>

                    <div class="rt_content">
                        <div class="rt_content_tt">
                            <h2>单选题</h2>
                            <p>
                                <span>共</span><i class="content_lit">${single}</i><span>题</span>
                            </p>
                        </div>
                        <div class="rt_content_nr answerSheet">
                            <ul>
                                <c:forEach var="index" begin="0" end="${single - 1}" step="1">
                                    <li><a href="#qu_0_${index}">${index + 1}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>

                    <div class="rt_content">
                        <div class="rt_content_tt">
                            <h2>多选题</h2>
                            <p>
                                <span>共</span><i class="content_lit">${multi}</i><span>题</span>
                            </p>
                        </div>
                        <div class="rt_content_nr answerSheet">
                            <ul>
                                <c:forEach var="index" begin="0" end="${multi - 1}" step="1">
                                    <li><a href="#qu_1_${index}">${index + 1}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>

                </div>

            </div>
        </div>
    </div>
    <!--nr end-->
    <div class="foot"></div>
</div>

<jsp:include page="../commons/scripts.jsp" />
<script>
    layui.use('util', function(){
        var util = layui.util;

        // 答题点击事件
        $('li.option').click(function () {
            // debugger;
            var examId = $(this).closest('.test_content_nr_main').closest('li').attr('id'); // 得到题目ID
            var cardLi = $('a[href="#' + examId + '"]'); // 根据题目ID找到对应答题卡
            // 设置已答题
            if (!cardLi.hasClass('hasBeenAnswer')) {
                cardLi.addClass('hasBeenAnswer');
            }
        });

        <%-- 获取当前时间 --%>
        <jsp:useBean id="now" class="java.util.Date" />
        <fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" var="cur_date"/>
        <%-- 结束日期 --%>
        <fmt:formatDate value="${examInfo.get('stoptime')}" type="both" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" var="end"/>

        var endTime = new Date("${end}").getTime() //假设为结束日期
            ,serverTime = new Date("${cur_date}").getTime(); //假设为当前服务器时间，这里采用的是本地时间，实际使用一般是取服务端的

        util.countdown(endTime, serverTime, function(date, serverTime, timer){
            let current = endTime - serverTime; // 时间差
            // 判断时间是否到了
            if(current === 0) {
                // 强制提交试卷答案
                // 关闭提交按钮
                // 给出提示
            } else {
                let strDate = util.toDateString(current, "dd:HH:mm:ss"); // 格式化
                // console.log(strDate);
                $(".alt-1").text(strDate);
            }

        });

        // 保存答案
        $("#saveBtn").click(function (e) {
            saveAnswer();
            layer.msg("题目已保存至本地，正在保存到服务器......", {icon: 1}, function () {
                submitAnswer(0);
            })

        });

        // 提交答案
        $("#commitBtn").click(function (e) {
            layer.confirm('提交之后不能再次作答，确认要提交吗？', function (index) {
                submitAnswer();
            });
        });

    });

    // 数字对应选项
    var select_items = ['A', 'B', 'C', 'D', 'E'];

    // 保存答案到本地存储
    function saveAnswer() {
        // 获取答题区域
        let allSelect = $(".test_content_nr>ul");
        // 获得单选答题区域和多选答题区域
        let singleSelectOptions = allSelect[0].getElementsByClassName("test_content_nr_main"),
            multiSelectOptions = allSelect[1].getElementsByClassName("test_content_nr_main");
        // 单选答案和多选答案
        let singleAns = new Array(singleSelectOptions.length), multiAns = new Array(multiSelectOptions.length);
        // 循环遍历单选题，遍历单选题的每一个题目
        for(let i = 0; i < singleSelectOptions.length; i++) {
            // 循环遍历每一题的选项
            let singleSelectOption = singleSelectOptions[i].getElementsByClassName("radioOrCheck");
            for(let j = 0; j < singleSelectOption.length; j++) {
                // 判断哪些选项被选中
                if(singleSelectOption[j].checked) {
                    singleAns[i] = select_items[j];
                }
            }
        }
        // 循环遍历多选题，遍历单选题的每一个题目
        for(let i = 0; i < multiSelectOptions.length; i++) {
            // 循环遍历每一题的选项
            let multiSelectOption = multiSelectOptions[i].getElementsByClassName("radioOrCheck");
            // 准备一个多选数组
            let multiAnsItemArr = [];
            for(let j = 0; j < multiSelectOption.length; j++) {
                // 判断哪些选项被选中
                if(multiSelectOption[j].checked) {
                    multiAnsItemArr.push(select_items[j]);
                }
            }
            // 将数组组成字符串，添加到多选数组中
            multiAns[i] = multiAnsItemArr.join("");
        }
        // 将当前考生的单选答案和多选答案保存至localStorage中
        localStorage.setItem("singleAns_${stuId}", JSON.stringify(singleAns));
        localStorage.setItem("multiAns_${stuId}", JSON.stringify(multiAns));
    }

    // 出现意外情况时，重新加载
    function reloadAnswer() {
        // 获取答题区域
        let allSelect = $(".test_content_nr>ul");
        // 获得单选答题区域和多选答题区域
        let singleSelectOptions = allSelect[0].getElementsByClassName("test_content_nr_main"),
            multiSelectOptions = allSelect[1].getElementsByClassName("test_content_nr_main");
        // 单选答案和多选答案的json格式
        let singleAnsStr = localStorage.getItem("singleAns_${stuId}"), multiAnsStr = localStorage.getItem("multiAns_${stuId}");
        // 如果当前考生的本地存储已存在
        if(singleAnsStr !== null && singleAnsStr !== "") {
            let singleAns = JSON.parse(singleAnsStr);
            // 循环遍历单选题，遍历单选题的每一个题目
            for(let i = 0; i < singleSelectOptions.length; i++) {
                // 循环遍历每一题的选项
                let singleSelectOption = singleSelectOptions[i].getElementsByClassName("radioOrCheck");
                // 将选中的勾上
                let index = select_items.indexOf(singleAns[i]);
                if(index !== -1) {
                    flag(singleSelectOptions[i]);
                    singleSelectOption[index].checked = true;
                }
            }
        }
        if(multiAnsStr !== null && multiAnsStr !== "") {
            // 循环遍历多选题，遍历单选题的每一个题目
            let multiAns = JSON.parse(multiAnsStr);
            for(let i = 0; i < multiSelectOptions.length; i++) {
                // 循环遍历每一题的选项
                let multiSelectOption = multiSelectOptions[i].getElementsByClassName("radioOrCheck");
                // 获取当前题的选项
                let multiSelect = multiAns[i];
                for(let j = 0; j < multiSelect.length; j++) {
                    // 判断哪些选项被选中
                    let index = select_items.indexOf(multiSelect[j]);
                    if(index !== -1) {
                        multiSelectOption[index].checked = true;
                        // 添加为已答题
                        flag(multiSelectOptions[i]);
                    }
                }
            }
        } else {
            saveAnswer();
        }
    }

    // 标记是否已答
    function flag(that) {
        var examId = $(that).closest('li').attr('id'); // 得到题目ID
        var cardLi = $('a[href="#' + examId + '"]'); // 根据题目ID找到对应答题卡
        // 设置已答题
        if (!cardLi.hasClass('hasBeenAnswer')) {
            cardLi.addClass('hasBeenAnswer');
        }
    }

    // 提交答案到服务器
    function submitAnswer(type) {
        // 参数内容
        let request = {
            type: "POST",
            url: "",
            data: {
                stuId: ${stuId},
                examId: ${examId},
                selectOne: JSON.parse(localStorage.getItem("singleAns_${stuId}")).join(","),
                selectMore: JSON.parse(localStorage.getItem("multiAns_${stuId}")).join(",")
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
                    layer.msg(data.message, {icon: 1});
                } else if (data.code === 1) {
                    layer.msg(data.message, {icon: 2});
                } else if (data.code === -1) {
                    layer.msg(data.message, {icon: 7}, function(){
                        top.location.href = '${pageContext.request.contextPath}/login';
                    });
                }
            }
        };
        // 如果保存类型为0
        if(type === 0) {
            request.url = "${pageContext.request.contextPath}/exam/saveAnswer";
        } else {
            request.url = "${pageContext.request.contextPath}/exam/commitAnswer";
        }
        $.ajax(request);
    }

    // 页面加载完成，再执行重载答案
    window.onload = function () {
        // 不知为啥，需要渲染加载两次才能正确显示已完成的题目
        reloadAnswer();
        reloadAnswer();
    }
</script>


</body>
</html>


