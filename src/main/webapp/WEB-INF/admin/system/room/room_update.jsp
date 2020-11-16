<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>房屋修改</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            系统管理
            <small>房屋管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="/"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="${ctx}/admin/room/list">房屋列表</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">
        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">房屋信息</div>
            <form id="editForm" action="${ctx}/admin/room/update" method="post">
                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">房屋修改标题</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="房屋标题" name="title" required value="${room.title}">
                    </div>

                    <div class="col-md-2 title">月租金</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="月租金" name="rent" required value="${room.rent}">
                    </div>

                    <div class="col-md-2 title">房型</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="房型" name="roomType" required value="${room.roomType}">
                    </div>

                    <div class="col-md-2 title">房源区域</div>
                    <input type="hidden" value="${room.regionId}" id="regionId">
                    <div class="col-md-4 data">
                        <select class="form-control" name="regionId" id="regionSlect">
                            <option value="1">江岸区</option>
                            <option>洪山区</option>
                            <option>江夏区</option>
                            <option>黄陂区</option>
                            <option>江汉区</option>
                        </select>
                    </div>

                    <div class="col-md-2 title">房源地址</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="房源地址" name="address" required value="${room.address}">
                    </div>
                    <div class="col-md-2 title">房源详细地址</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="房源详细地址" name="detailAddress" required value="${room.detailAddress}">
                    </div>
                    <div class="col-md-2 title">房源面积</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="房源面积" name="area" required value="${room.area}">
                    </div>

                    <div class="col-md-2 title">出租方式</div>
                    <div class="col-md-4 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio" name="rentType" value="合租" checked>合租</label></div>
                            <div class="radio"><label><input type="radio" name="rentType" value="整租">整租</label></div>
                        </div>
                    </div>

                    <div class="col-md-2 title">交通情况</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="交通情况" name="trafficCondition" required value="${room.trafficCondition}">
                    </div>
                    <div class="col-md-2 title">设备情况</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="设备情况" name="facilitiesCondition" required value="${room.facilitiesCondition}">
                    </div>

                    <div class="col-md-2 title">房屋描述</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="房屋描述" name="roomDesc" required value="${room.roomDesc}">
                    </div>

                    <div class="col-md-2 title">上传图片</div>
                    <div class="col-md-4 data">
                    <div class="form-group form-inline">
                    <input type="file" class="form-control" name="roomImgs" multiple>
                    </div>
                    </div>

                </div>
            </form>
        </div>
        <!--订单信息/-->

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <!--工具栏/-->

    </section>
    <!-- 正文区域 /-->

</div>
<!-- 内容区域 /-->
</body>
<script src="/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="/css/style.css">
<script>
    // 异步请求获取房源所有区域信息
    $.get("${pageContext.request.contextPath}/admin/region/findAll",function(data){
        // alert("Data Loaded: " + data);
        let regionId= $("#regionId").val();
        let str="";
        $.each(data, function(i, n){
            // alert( "Name: " + i + ", Value: " + n );
            if(n.id ==regionId){
                str+='<option selected value="'+n.id+'">'+n.regionName+'</option>';
            }else{
                str+='<option value="'+n.id+'">'+n.regionName+'</option>';
            }
        });
        $("#regionSlect").append(str);
    });

    $('#datepicker').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
    $('#datepicker1').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
</script>
</html>
