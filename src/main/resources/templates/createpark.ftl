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

        function validateForm() {
            var submit = true;
            var d = {};
            var t = $('#createForm').serializeArray();
            $.each(t, function() {
                if(this.value.length == 0) {
                    submit = false;
                }
                d[this.name] = this.value;
            });
            if(!submit){
                alert("请输入有效信息!");
            }
            return submit;
        }

    </script>

</head>
<body>
<div class="main">
    <div class="nojs" style="text-align: center;"><span style="color: red;">对不起，您的浏览器禁用了JavaScript，一些功能可能无法使用，尝试开启后重新访问!</span></div>
    <div class="table-bordered index"><a style="text-decoration: none" href="/sdk/authlist">车库操作</a><a href="#" style="text-decoration: none"> / 车库创建</a></div>
    <div class="panel panel-default">
        <div class="panel-heading">车库信息</div>
        <div class="panel-body">

                <form method="post" action="/park/add" id="createForm" class="form-horizontal" style="margin: 0 auto;width: 60%;border: 1px #ffffff">
                    <dl class="dl-horizontal">
                        <dt>车库名称</dt>
                        <dd><input type="text" name="name" style="width: 300px"></dd>
                    </dl>
                    <dl class="dl-horizontal">
                        <dt>车库地址</dt><dd>
                        <input type="text" name="address" style="width: 300px"></dd>
                    </dl>
                    <dl class="dl-horizontal">
                        <dt>入口数量</dt>
                        <dd>
                            <select name="enterCount" style="width: 300px">
                                <option value="3" >3</option>
                                <option value="4" >4</option>
                            </select>
                        </dd>
                    </dl>
                    <dl class="dl-horizontal">
                        <dt>长</dt>
                        <dd><input type="text" name="length" style="width: 300px"></dd>
                    </dl>
                    <dl class="dl-horizontal">
                        <dt>宽</dt>
                        <dd><input type="text" name="width" style="width: 300px"></dd>
                    </dl>
                    <dl class="dl-horizontal">
                        <dt>描述</dt>
                        <dd><input type="text" name="description" style="width: 300px"></dd>
                    </dl>
                    <dl class="dl-horizontal">
                        <dt></dt><dd><input type="submit" onclick="return validateForm();" value="提交信息" class="btn btn-success"></dd>
                    </dl>
                </form>
        </div>
    </div>
</div>
</body>
</html>