<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <style>
        body { margin:0; background:#fff; }
        a { color:#666; text-decoration:none; outline:0; font-family:"Times New Roman", Times, serif; font-size:12px; }
        a:hover { color:#999; }
    </style>
</head>
<body style="background-color: #f9f9f9">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td>
            <a href="###" hidefocus="true" id="toggle" title="点击隐藏侧栏">&#9668;</a>
        </td>
    </tr>
</table>
<script type="text/javascript">
    function init(){
        var elFrame = parent.document.getElementById("mainframe");
        var el = document.getElementById('toggle');
        var oldCols = elFrame.cols;
        var newCols = oldCols.replace(/^\d+/, '0');
        var flag = false;

        el.onclick = function(){
            flag = !flag;
            elFrame.cols = flag ? newCols : oldCols;
            el.innerHTML = flag ? "&#9658;" : "&#9668;";
            el.title = flag ? '点击显示侧栏' : '点击隐藏侧栏';
        };
    }
    window.onload = init;
</script>
</body>
</html>
