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

        function showReserve(id) {
            $("#reserveModel").modal();
            $("#reserveForm input[type='hidden']").val(id);
        }

        function reserve() {

            var parknum = $("#parknum").val();
            if(parknum == null) {
                alert("请选择车位!");
                return ;
            }

            var d = {};
            var t = $('#reserveForm').serializeArray();
            $.each(t, function() {
                d[this.name] = this.value;
            });

            $.ajax({
                url: '/reserve/add',
                type: 'POST',
                data: d,
                success: function(ret) {
                    if(ret == 'exist') {
                        alert("您今天已有预定车位，请勿重复预定!");
                        return ;
                    }
                    $("#reserveModel").modal('hide');
                    alert("预定成功!");
                }
            });

        }

        function getFree() {

            $("#parknum option").remove();

            var date = $("#reserveForm input[name='reservetime']").val();
            if(date.length == 0) {
                return ;
            }
            var df = date.replace("-", "/");
            var d1 = new Date(Date.parse(df));
            var df2 = new Date().toLocaleDateString();
            var d2 = new Date(Date.parse(df2));

            if(d1 < d2) {
                alert("请选择有效时间!");
                return ;
            }

            var start = $("#start").val();
            var end = $("#end").val();

            var id = $("#reserveForm input[type='hidden']").val();

            $.ajax({
                url: '/reserve/getNumByParkId?pid=' + id + '&time=' + date + '&start=' + start + '&end=' + end,
                type: 'GET',
                success: function(ret) {
                    $('#reserveBtn').removeAttr('disabled');
                    $("#freenum").val(ret.length);

                    if(ret.length == 0) {

                        if(!confirm("当前时间段暂无空闲车位,是否开启车位提醒?")) {
                            return ;
                        }

                        remindFree(id, date, start, end);

                        alert('有空余车位时，我们将通过邮件提醒!');
                        $('#reserveBtn').attr("disabled", 'disabled');
                        return ;
                    }

                  for(var i = 0 ; i < ret.length ; i ++) {
                        $("#parknum").append("<option value='" + ret[i] + "'>" + ret[i] + "</option>");
                    }
                }
            });
        }


        function remindFree(id, date, start, end) {

            $.ajax({
                url: '/reserve/remindFree?pid=' + id + "&time=" + date + "&start=" + start + "&end=" + end,
                type: 'GET',
                success: function() {

                }
            });

        }

    </script>


</head>
<body>
<div class="main">
    <div class="nojs" style="text-align: center;"><span style="color: red;">对不起，您的浏览器禁用了JavaScript，一些功能可能无法使用，尝试开启后重新访问!</span></div>

    <div class="panel panel-default">
        <div class="panel-heading">所有车库</div>

        <div class="panel-body">
            <div class="panel panel-default">
                <div class="panel-heading">车库筛选</div>
                <div class="panel-body">

                    <div>
                        <form class="form-horizontal" action="/park/selectByCondition" method="post">
                            <div class="form-group" >
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
                                                      style="text-decoration: none">车库信息</a>
                    </div>
                    <div id="0" class="accordion-body collapse in">
                        <div class="accordion-inner">
                            <table class="table table-striped table-bordered table-hover" style="TABLE-LAYOUT: fixed;">

                                <tr>
                                    <th style="width= 5%">编号</th>
                                    <th style="width: 10%">名称</th>
                                    <th style="width: 20%">地址</th>
                                    <th style="width: 10%">入口数量</th>
                                    <th style="width: 5%">车位数量</th>
                                    <th style="width: 15%">创建时间</th>
                                    <th style="width: 10%">描述</th>
                                    <th style="width: 10%">负责人</th>
                                    <th style="width: 10%">联系方式</th>
                                    <th style="width: 5%">预定 </th>
                                </tr>

                               <#assign x=0>
                               <#list allpark as item>
                                   <#assign x=x+1>
                               <tr>
                                   <td>${x}</td>
                                   <td>${item.name!}</td>
                                   <td>${item.address!}</td>
                                   <td>${item.enterCount!}</td>
                                   <td>${item.count!}</td>
                                   <td>${item.time!}</td>
                                   <td>${item.description!}</td>
                                   <td>${item.sponsor.adminName!}</td>
                                   <td>${item.sponsor.phone!}</td>
                                   <td><a href="#" onclick="showReserve(${item.id});" >预定</a> </td>
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

<div class="modal fade hide" tabindex="-1" role="dialog" id="reserveModel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">车位预定</h4>
            </div>

            <div class="modal-body">

                <form class="form-horizontal" id="reserveForm">
                    <input type="hidden" value="" name="parkid" >
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">预定日期：</label>
                        <div class="col-sm-10">
                            <input type='text' class='form-control date-picker' id='reservetime' name='reservetime'>
                            <input type="button" value="GET" class="btn btn-small btn-success" onclick="getFree()" />
                        </div>
                    </div><br>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">开始时间：</label>
                        <div class="col-sm-10">
                            <select id="start" name="start">
                                <option value="8">08</option>
                                <option value="9">09</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                                <option value="13">13</option>
                                <option value="14">14</option>
                                <option value="15">15</option>
                                <option value="16">16</option>
                                <option value="17">17</option>
                                <option value="18">18</option>
                                <option value="19">19</option>
                                <option value="20">20</option>
                            </select>
                        </div>
                    </div><br>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">结束时间：</label>
                        <div class="col-sm-10">
                            <select id="end" name="end">
                                <option value="9">09</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                                <option value="13">13</option>
                                <option value="14">14</option>
                                <option value="15">15</option>
                                <option value="16">16</option>
                                <option value="17">17</option>
                                <option value="18">18</option>
                                <option value="19">19</option>
                                <option value="20">20</option>
                                <option value="21">21</option>
                            </select>
                        </div>
                    </div><br>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">空闲车位：</label>
                        <div class="col-sm-10">
                            <input type='text' class='form-control' id="freenum" readonly="readonly" />
                        </div>
                    </div><br>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">车位编号：</label>
                        <div class="col-sm-10">
                            <select id="parknum" name="parknum">

                            </select>
                        </div>
                    </div><br>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">备注：</label>
                        <div class="col-sm-10" >
                            <input type='text' class='form-control' id='description' name='description'>
                        </div>
                    </div><br>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-success" id="reserveBtn" onclick="reserve();" >提交</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>