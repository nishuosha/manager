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


</head>
<body>
<div class="main">
    <div class="table-bordered index"><a style="text-decoration: none" href="">个人信息</a><a href="#" style="text-decoration: none"> / 详情操作</a></div>
    <div class="panel panel-default">
        <div class="panel-heading">更新信息</div>
        <div class="panel-body">
                <form method="post" action="/user/update" class="form-horizontal" style="margin: 0 auto;width: 60%;border: 1px #ffffff">
                    <input type="hidden" name="id" value="${user.id}" />
                    <dl class="dl-horizontal">
                        <dt>用户名:</dt>
                        <dd><input type="text" name="username" value="${user.username!}" style="width: 300px"></dd>
                    </dl>

                    <dl class="dl-horizontal">
                        <dt>用户密码:</dt>
                        <dd><input type="text" name="userpwd" value="${user.userpwd!}" style="width: 300px"></dd>
                    </dl>

                    <dl class="dl-horizontal">
                        <dt>手机号:</dt>
                        <dd><input type="text" name="phone" value="${user.phone!}" style="width: 300px"></dd>
                    </dl>

                    <dl class="dl-horizontal">
                        <dt>邮箱:</dt>
                        <dd><input type="text" name="email" value="${user.email!}" style="width: 300px"></dd>
                    </dl>

                    <dl class="dl-horizontal">
                        <dt>注册时间:</dt>
                        <dd><input type="text" name="registertime" value="${user.registertime!}" style="width: 300px" readonly="readonly"></dd>
                    </dl>

                    <dl class="dl-horizontal">
                        <dt></dt><dd><input type="submit" value="提交信息" class="btn btn-success""></dd>
                    </dl>
                </form>
        </div>
    </div>
</div>
</body>
</html>