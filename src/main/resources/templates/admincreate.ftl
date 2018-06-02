<!DOCTYPE html>
<head xmlns="http://www.w3.org/1999/html">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap-datepicker.js" ></script>
    <script src="/js/bootstrap.js"></script>
    <link href="/css/bootstrap.css?v=1" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/datepicker.css">
    <link href="/css/common.css?v=1" rel="stylesheet" type="text/css"/>
    <link href="/css/font-awesome.css" rel="stylesheet" type="text/css" />

<#--禁用js时的提示-->
    <script type="text/javascript">

        $(function () {
            $(".nojs").css("display", "none");
        });
    </script>

    <style type="text/css">
        .table th, .table td {
            text-align: center;
            vertical-align: middle!important;
        }
    </style>

    <script type="text/javascript">

        function stopService(pid) {

            if(!confirm("确认停用此车库?")){
                return ;
            }

            $.ajax({
                url: '/park/stopService?pid=' + pid,
                type: 'GET',
                success: function(ret) {
                    if(ret == 'success') {
                        window.location.href = "/park/admin";
                    }
                }
            });
        }

        function startService(pid) {

            if(!confirm("确认重新启用此车库，以前的预定信息将被清空?")) {
                return ;
            }

            $.ajax({
                url: '/park/startService?pid=' + pid,
                type: 'GET',
                success: function(ret) {
                    if(ret == 'success') {
                        window.location.href = "/park/admin";
                    }
                }

            });
        }

    </script>

</head>
<body>
<div class="main">
    <div class="nojs" style="text-align: center;"><span style="color: red;">对不起，您的浏览器禁用了JavaScript，一些功能可能无法使用，尝试开启后重新访问!</span></div>
    <div class="table-bordered index">
        <a href="#" style="text-decoration: none">我的创建</a>
        <a style="float: right;" href="/park/tocreate" class="btn btn-small btn-success">创建车库</a>
    </div>

    <div class="panel panel-default">
        <div class="panel-body text-center" >
            <div class="form-inline pull-left" style="padding-bottom: 20px">
            </div>

            <table class="table table-striped table-bordered table-hover" style="TABLE-LAYOUT: fixed;">

                <tr>
                    <th style="width= 5%">编号</th>
                    <th style="width: 15%">名称</th>
                    <th style="width: 20%">地址</th>
                    <th style="width: 15%">创建时间</th>
                    <th style="width: 10%">入口数量</th>
                    <th style="width: 10%">车位数量</th>
                    <th style="width: 10%">描述</th>
                    <th style="width: 10%">运行状态</th>
                    <th style="width: 5%">操作</th>
                </tr>
                <#assign x=0>
                <#list parks as item>
                <#assign x=x+1>
                <tr>
                    <td>${x}</td>
                    <td>${item.name!}</td>
                    <td>${item.address!}</td>
                    <td>${item.time!}</td>
                    <td>${item.enterCount!}</td>
                    <td>${item.count!}</td>
                    <td>${item.description!}</td>
                    <td>
                        <#if item.status == 0>
                            <span style="color: green">运行中</span>
                        <#else>
                            <span style="color: red">停止服务</span>
                        </#if>
                    </td>
                    <td>
                        <#if item.status == 0>
                            <button class="btn btn-danger btn-small" onclick="stopService(${item.id!});">停止</button>
                        <#else>
                            <button class="btn btn-small btn-success" onclick="startService(${item.id!});">启用</button>
                        </#if>
                    </td>
                </tr>
                </#list>

            </table>

        </div>
    </div>
</div>
</body>
</html>