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
    </script>

    <script type="text/javascript">

        $(function(){
            var num = (page - 1) * $("#showNum").val() + 1;
            var len = page * $("#showNum").val();
            var temp = 1;
            for(var i = num;i<=len;i++){
                $('table tr:eq('+temp+') td:first').text(i);
                temp = temp + 1;
            }
        });

        function removeR(id) {
            if(!confirm("确认取消?")){
                return ;
            }

            $.ajax({

                url: '/reserve/deleteById?id=' + id,
                type: 'GET',
                success: function(ret) {
                    window.location.href = "/reserve/user";
                }
            });
        }

    </script>

</head>
<body>
<div class="main">
    <div class="nojs" style="text-align: center;"><span style="color: red;">对不起，您的浏览器禁用了JavaScript，一些功能可能无法使用，尝试开启后重新访问!</span></div>
    <div class="table-bordered index"><a href="#" style="text-decoration: none">我的预定</a>
    </div>

    <div class="panel panel-default">
        <div class="panel-body text-center" >
            <div class="form-inline pull-left" style="padding-bottom: 20px">
            </div>

            <table class="table table-striped table-bordered table-hover" style="TABLE-LAYOUT: fixed;">

                <tr>
                    <th style="width= 5%">编号</th>
                    <th style="width: 15%">创建时间</th>
                    <th style="width: 15%">预定时间</th>
                    <th style="width: 10%">描述</th>
                    <th style="width: 10%">车库名称</th>
                    <th style="width: 20%">车库地址</th>
                    <th style="width: 5%">车位号码</th>
                    <th style="width: 10%">状态</th>
                    <th style="width: 10%">操作</th>
                </tr>

                <#assign x=0>
                <#list urList as item>
                <#assign x=x+1>
                <tr>
                    <td>${x}</td>
                    <td>${item.createtime!}</td>
                    <td>${item.reservetime!}</td>
                    <td>${item.description!}</td>
                    <td>${item.park.name!}</td>
                    <td>${item.park.address!}</td>
                    <td>${item.parknum!}</td>
                    <td>${item.status!}</td>
                    <td> <a href="#" onclick="removeR(${item.id});">取消</a> </td>
                </tr>
                </#list>

            </table>

        </div>
    </div>
</div>
</body>
</html>