<html>
<head>
    <title>Title</title>
    <link href="/css/top.css?v=13" rel="stylesheet" type="text/css"/>
    <link href="/css/font-awesome.css?v=1" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>


    <script type="text/javascript">

    </script>
</head>
<body bgcolor="#3c8dbc" class="top">
    <img id="image_lg" src="/images/mt_logo.png">
    <a href="javascript:void(0);" onclick="showMonitor('index')" class="logo">
                        <span class="logo-lg">停车位管理系统</span>
    </a>

    <span style="margin-right: 15px">
        <ul id="namenav">
            <#if user??>
                <#if user.username??>
                    <li><a href="#"><i class="fa fa-user"></i> ${user.username}</a></li>
                    <li><a href="/login/exit"><i class="fa fa-user"></i>注销</a></li>
                <#elseif user.adminName??>
                    <li><a href="#"><i class="fa fa-user"></i> ${user.adminName}</a></li>
                    <li><a href="/login/exit" ><i class="fa fa-user"></i>注销</a></li>
                <#else>
                    <li><a href="#" target="main"><i class="fa fa-user"></i>请登录</a></li>
                </#if>
            <#else>
                <li><a href="#"><i class="fa fa-user"></i>请登录</a></li>
            </#if>
        </ul>
    </span>
</body>




</html>
