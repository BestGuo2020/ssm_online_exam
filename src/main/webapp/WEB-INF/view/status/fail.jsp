<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2021/5/16
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <jsp:include page="../commons/metas.jsp" />
    <title>提示</title>
    <jsp:include page="../commons/styles.jsp" />
</head>
<body>

<jsp:include page="../commons/scripts.jsp" />
<script>
    layui.use(['layer'], function () {
        var layer = layui.layer;
        layer.msg('${msg}', {icon: 2}, function(){
            <c:choose>
                <c:when test="${islayuilayer == true}">
                    <%-- 如果这个错误是在layer的弹出层出现，关闭该弹出层即可 --%>
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index);
                </c:when>
                <c:otherwise>
                    location.href = '${pageContext.request.contextPath}/${path}';
                </c:otherwise>
            </c:choose>
        });
    });
</script>
</body>
</html>

