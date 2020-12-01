<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
    <%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">--%>
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>--%>
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>

    <link rel="stylesheet" href="${ctx}/bootstrap/css/bootstrap.css">
    <script src="${ctx}/bootstrap/js/jquery-3.2.1.min.js"></script>
    <script src="${ctx}/bootstrap/js/bootstrap.js"></script>

    <title>云租房+-首页</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
</head>
<body>

<%-- 1、头部导航--%>
<jsp:include page="${pageContext.request.contextPath}/common/header.jsp"></jsp:include>

<%-- 2、轮播图--%>
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img src="index/img/1.jpg" alt="">
            <div class="carousel-caption">
            </div>
        </div>
        <div class="item">
            <img src="index/img/2.jpg" alt="">
            <div class="carousel-caption">
            </div>
        </div>
        <div class="item">
            <img src="index/img/3.jpg" alt="">
            <div class="carousel-caption">
            </div>
        </div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>

<%--  3、尾部--%>
<div id="commonFooter" class="commonFooter">
    <div class="upWrap">
        <a target="_blank"  href="javascript:void(0)" rel="nofollow">常见问题</a><span>|</span>
        <a target="_blank"  href="javascript:void(0)" rel="nofollow">帮助中心</a><span>|</span>
        <a  target="_blank" href="javascript:void(0)" rel="nofollow">意见反馈</a><span>|</span>
        <a target="_blank" href="javascript:void(0)" rel="nofollow">了解厚溥云租房</a><span>|</span><a
            target="_blank" href="javascript:void(0)" rel="nofollow">加入厚溥云租房</a><span>|</span>
        <a target="_blank"   href="javascript:void(0)" rel="nofollow">反欺诈联盟</a><span>|</span>
        <a target="_blank" href="javascript:void(0)" rel="nofollow">报案平台</a><span>|</span>
        <a target="_blank"  href="javascript:void(0)"  rel="nofollow">推广服务</a><span>|</span>
        <a target="_blank" href="javascript:void(0)" rel="nofollow">渠道招商</a><span>|</span>
        <a target="_blank"  href="javascript:void(0)"   rel="nofollow">维权中心</a><span>|</span>
        <a target="_blank" href="javascript:void(0)" rel="nofollow">Investor Relations</a>
    </div>
    <div class="downWrap">
        <div><em>2006-2021 myhopu.com版权所有</em><span>|</span><a target="_blank"
                                                               href="javascript:void(0)"
                                                               rel="nofollow">鄂ICP备11005077号-1</a><span>|</span>
            <a target="_blank" href="javascript:void(0)">联系我们</a></div>
        <div>
            <a target="_blank"
               href="javascript:void(0)">人力资源服务许可证</a><span>|</span>
            <a target="_blank" href="javascript:void(0)"
               rel="nofollow">互联网药品信息服务资格证</a><span>|</span>
            <a target="_blank"  href="javascript:void(0)"  rel="nofollow">广播电视节目制作经营许可证</a>

        </div>
    </div>
</div>

</body>
</html>
