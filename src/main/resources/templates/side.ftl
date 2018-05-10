<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Filter Nav</title>

    <link href="/css/jquery-accordion-menu.css?v=9" rel="stylesheet" type="text/css" />
    <link href="/css/font-awesome.css" rel="stylesheet" type="text/css" />

    <style type="text/css">
        *{box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;}
        body{background:#222d32;}
        .content{width:180px;margin: auto;}

        #demo-list a{
            overflow:hidden;
            text-overflow:ellipsis;
            -o-text-overflow:ellipsis;
            white-space:nowrap;
            width:100%;
        }
    </style>

    <script src="/js/jquery-1.11.2.min.js" type="text/javascript"></script>
    <script src="/js/jquery-accordion-menu.js" type="text/javascript"></script>
    <script type="text/javascript">
        jQuery(document).ready(function () {
            jQuery("#jquery-accordion-menu").jqueryAccordionMenu();
        });

        $(function(){
            //顶部导航切换
            $("#demo-list li").click(function(){
                $("#demo-list li.active").removeClass("active")
                $(this).addClass("active");
            })
        })
    </script>
</head>
<body>
<div class="content">

    <div id="jquery-accordion-menu" class="jquery-accordion-menu custom">
        <#--<div class="jquery-accordion-menu-header" id=""></div>-->
        <div style="height: 5px; border-top: 1px solid #222222"></div>
        <ul id="demo-list">
            <li><a href="/reserve/user" target="main"><i class="fa fa-location-arrow"></i>我的预定</a></li>
            <li><a href="/park/all" target="main"><i class="fa fa-location-arrow"></i>车位预定</a></li>
            <li><a href="/park/all?guide=true" target="main"><i class="fa fa-location-arrow"></i>车位推荐</a></li>
            <li><a href="/user/getBySession" target="main"><i class="fa fa-briefcase"></i>我的信息</a></li>

        </ul>
    </div>
</div>
</body>
</html>