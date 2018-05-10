<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="/css/bootstrap.css?v=1" rel="stylesheet" type="text/css"/>
    <link href="/css/common.css?v=1" rel="stylesheet" type="text/css"/>
    <script src="//cdn.jsdelivr.net/jquery/1.12.1/jquery.min.js"></script>
    <script type="text/javascript">

    </script>
</head>
<body>
<div style="margin: 11px;">
    <div class="table-bordered index"><a style="text-decoration: none" href="">个人信息</a><a href="#" style="text-decoration: none"> / 信息详情</a></div>
    <div class="panel panel-default">
        <div class="panel-heading">个人ID： <span id="myId">1</span></div>
        <div class="panel-body" style="padding-top: 10px; padding-bottom: 5px">

            <dl class="dl-horizontal">
                <dt>用户名:</dt>
                <dd>${user.adminName!}</dd>
            </dl>

            <dl class="dl-horizontal">
                <dt>密码:</dt>
                <dd>${user.adminpwd!}</dd>
            </dl>

            <dl class="dl-horizontal">
                <dt>手机号:</dt>
                <dd>${user.phone!}</dd>
            </dl>

            <dl class="dl-horizontal">
                <dt>邮箱:</dt>
                <dd>${user.email!}</dd>
            </dl>

            <dl class="dl-horizontal">
                <dt>注册时间:</dt>
                <dd>${user.registertime!}</dd>
            </dl>

            <div style="padding: 0" class="form-actions">
                <dl class="dl-vertical text-center">
                    <dd>
                        <a style="margin-right: 20px" class="btn btn-info" href="/admin/toedit?id=${user.id}">修改</a>
                    </dd>
                </dl>
            </div>
        </div>
    </div>
</div>
</body>
</html>