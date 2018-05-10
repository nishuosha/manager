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

        $(function(){
            $('.date-picker').datepicker({
                format:'yyyy-mm-dd',
            });
        })

    </script>

    <style type="text/css">
        .table th, .table td {
            text-align: center;
            vertical-align: middle!important;
        }

        .dropdown-menu {
            z-index: 1050;
        }

    </style>

    <script type="text/javascript">

        function showAdvice(id) {

            var time = $("table input[name='time" + id + "']").val();
            if(time.length == 0) {
                alert("请选择时间!");
                return;
            }

            var df = time.replace("-", "/");
            var d1 = new Date(Date.parse(df));
            var df2 = new Date().toLocaleDateString();
            var d2 = new Date(Date.parse(df2));

            if(d1 < d2) {
                alert("请选择有效时间!");
                return ;
            }


            $.ajax({

                url: '/reserve/getAdvice?pid=' + id + '&time=' + time,
                type: 'GET',
                success: function(ret) {
                    $("#d").attr('hidden', "hidden");
                    $("#adviceModel").modal();
                    $('#ar').html(ret['A']);
                    $('#br').html(ret['B']);
                    $('#cr').html(ret['C']);
                    if(ret['D'] != null) {
                        $("#d").removeAttr('hidden');
                        $("#dr").html(ret['D']);
                    }
                }
            });

        }

    </script>


</head>
<body>
<div class="main">
    <div class="nojs" style="text-align: center;"><span style="color: red;">对不起，您的浏览器禁用了JavaScript，一些功能可能无法使用，尝试开启后重新访问!</span></div>

    <div class="panel panel-default">
        <div class="panel-heading">车库推荐</div>

        <div class="panel-body">
            <div class="panel panel-default">
                <div class="panel-heading">车库筛选</div>
                <div class="panel-body">

                    <div>
                        <form class="form-horizontal" action="/park/selectByCondition" method="post">
                            <div class="form-group" >
                                <input type="hidden" value="true" name="guide" />
                                名称:<input type="text" style="width: 180px;" name="name" <#if model??> value="${model.name!}" </#if>/>
                                &nbsp;&nbsp;地址:<input type="text" style="width: 180px;" name="address" <#if model??> value="${model.address!}" </#if>/>
                                &nbsp;&nbsp;入口数量:<input type="text" style="width: 180px;" name="entercount" <#if model??> value="${model.enterCount!}" </#if>/>
                                &nbsp;&nbsp;负责人:<input type="text" style="width: 180px;" name="sponsor.adminName"
                                                      <#if model??> <#if model.sponsor??> value="${model.sponsor.adminName!}" </#if> </#if> />
                                &nbsp;&nbsp;<button class="btn btn-small btn-success" type="submit">筛选</button>
                            </div>
                        </form>
                    </div>

                </div>
            </div>


            <div class="accordion" id="box">
                <div class="accordion-group">
                    <div class="accordion-heading"><a class="accordion-toggle" data-toggle="collapse" href="#0"
                                                      style="text-decoration: none">车库推荐</a>
                    </div>
                    <div id="0" class="accordion-body collapse in">
                        <div class="accordion-inner">

                            <table class="table table-striped table-bordered table-hover" style="TABLE-LAYOUT: fixed;">

                                <tr>
                                    <th style="width= 5%">编号</th>
                                    <th style="width: 20%">名称</th>
                                    <th style="width: 20%">地址</th>
                                    <th style="width: 20%">车位数量</th>
                                    <th style="width: 23%">时间 </th>
                                    <th style="width: 12%">推荐 </th>
                                </tr>

                                <#assign x=0>
                                <#list allpark as item>
                                    <#assign x=x+1>
                                <tr>
                                    <td>${x}</td>
                                    <td>${item.name!}</td>
                                    <td>${item.address!}</td>
                                    <td>${item.count!}</td>
                                    <td> <input type="text" class="date-picker" name="time${item.id}" /> </td>
                                    <td><a href="#" onclick="showAdvice(${item.id});" >推荐</a> </td>
                                </tr>
                                </#list>

                            </table>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>

<div class="modal fade hide" tabindex="-1" role="dialog" id="adviceModel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">车位推荐</h4>
            </div>

            <div class="modal-body">

                <table class="table table-striped table-bordered table-hover" style="TABLE-LAYOUT: fixed;">

                    <tr>
                        <th>入口</th>
                        <th>最近车位编号</th>
                    </tr>

                    <tr>
                        <td>A</td>
                        <td id="ar"></td>
                    </tr>

                    <tr>
                        <td>B</td>
                        <td id="br"></td>
                    </tr>

                    <tr>
                        <td>C</td>
                        <td id="cr"></td>
                    </tr>

                    <tr id="d" hidden="hidden">
                        <td>D</td>
                        <td id="dr"></td>
                    </tr>

                </table>

            </div>
        </div>
    </div>
</div>

</body>
</html>