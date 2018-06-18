<!DOCTYPE html>
 <html lang="en">
 <head>
     <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width,initial-scale=1">
     <title>登陆页</title>
     <link rel="stylesheet" href="/css/bootstrap.min.css">
     <script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
     <script src="/js/bootstrap.js"></script>

     <style>
         img {
             1 width: 100%;
             /*height: 462px;*/
         }
     </style>

     <script type="text/javascript">

         function userrg() {

             var pwd1 = $("#userpwd").val();
             var pwd2 = $("#userpwd2").val();
             if(pwd1 != pwd2) {
                 alert("两次密码不一致!");
                 return ;
             }

             var name = $("#userrgForm input[name='username']").val();
             if(name.length == 0) {
                 alert("用户名不能为空!");
                 return ;
             }

             var isNull = false;

             var d = {};
             var t = $('#userrgForm').serializeArray();
             $.each(t, function() {
                 d[this.name] = this.value;
                 if(this.value.length == 0) {
                     isNull = true;
                 }
             });

             if(isNull) {
                 alert("请输入有效信息!");
                 return ;
             }

             $.ajax({
                 url: '/user/getByName?name=' + name,
                 type: 'GET',
                 success: function (ret) {
                     if(ret == 'exist') {
                         alert("用户名已存在!");
                         return ;

                     } else {

                         $.ajax({
                             url: '/user/add',
                             type: 'POST',
                             data: d,
                             success: function(ret) {
                                if(ret == 'success') {
                                    alert("注册成功，请登陆!");
                                    $("#register").modal('hide');
                                    $("#login").modal();
                                }
                             }
                         });
                     }
                 }
             });

         }

         function adminrg() {

             var pwd1 = $("#adminpwd").val();
             var pwd2 = $("#adminpwd2").val();
             if(pwd1 != pwd2) {
                 alert("两次密码不一致!");
                 return ;
             }

             var name = $("#adminrgForm input[name='adminName']").val();
             if(name.length == 0) {
                 alert("用户名不能为空!");
                 return;
             }

             var isNull = false;

             var d = {};
             var t = $('#adminrgForm').serializeArray();
             $.each(t, function() {
                 d[this.name] = this.value;
                 if(this.value.length == 0) {
                     isNull = true;
                 }
             });

             if(isNull) {
                 alert("请输入有效信息!");
                 return ;
             }

             $.ajax({
                 url: '/admin/getByName?name=' + name,
                 type: 'GET',
                 success: function (ret) {
                     if(ret == 'exist') {
                         alert("用户名已存在!");
                         return ;
                     } else {

                         $.ajax({
                             url: '/admin/add',
                             type: 'POST',
                             data: d,
                             success: function(ret) {
                                 if(ret == 'success') {
                                     alert("注册成功，请登陆!");
                                     $("#register1").modal('hide');
                                     $("#login1").modal();
                                 }
                             }
                         });
                     }
                 }
             });
         }

         function userlg() {



             var d = {};
             var t = $('#userlgForm').serializeArray();
             $.each(t, function() {
                 d[this.name] = this.value;
             });

             $.ajax({

                 url: "/login/user",
                 type: 'POST',
                 data: d,
                 success: function(ret) {
                     if(ret == 'register') {
                         alert("用户名不存在，请注册后登陆!")
                         return ;
                     } else if(ret == 'error') {
                         alert("密码错误!")
                         return ;
                     } else {
                        window.location.href = "/";
                     }
                 }
             });
         }

         function adminlg() {
             var d = {};
             var t = $('#adminlgForm').serializeArray();
             $.each(t, function() {
                 d[this.name] = this.value;
             });

             $.ajax({

                 url: "/login/admin",
                 type: 'POST',
                 data: d,
                 success: function(ret) {
                     if(ret == 'register') {
                         alert("用户名不存在，请注册后登陆!")
                         return ;
                     } else if(ret == 'error') {
                         alert("密码错误!")
                         return ;
                     } else {
                         window.location.href = "/";
                     }
                 }
             });
         }

//         个人密码找回
         function findUserPwd() {

             var username = $("#findUserPwd input[name='username']").val();
             var email = $("#findUserPwd input[name='email']").val();

             if(username.length == 0 || email.length == 0) {
                 alert("请输入正确的信息!");
                 return ;
             }

             $.ajax({

                 url: "/user/findUserPwd?username=" + username + "&email=" + email,
                 type: 'GET',
                 success: function(ret) {
                     if(ret == '0') {
                         alert("用户名不存在!");
                         return ;
                     }

                     if(ret == '1') {
                         alert("用户名与邮箱不匹配!");
                         return ;
                     }

                     if(ret == '2') {
                         alert("您的密码已发送至邮箱，请查收!");
                         $("#findUserPwd").modal('hide');
                         $("#login").modal();
                     }
                 }
             });
         }

         //         商家密码找回
         function findAdminPwd() {

             var adminName = $("#findAdminPwd input[name='adminName']").val();
             var email = $("#findAdminPwd input[name='email']").val();

             if(adminName.length == 0 || email.length == 0) {
                 alert("请输入正确的信息!");
                 return ;
             }

             $.ajax({

                 url: "/admin/findAdminPwd?adminName=" + adminName + "&email=" + email,
                 type: 'GET',
                 success: function(ret) {
                     if(ret == '0') {
                         alert("用户名不存在!");
                         return ;
                     }

                     if(ret == '1') {
                         alert("用户名与邮箱不匹配!");
                         return ;
                     }

                     if(ret == '2') {
                         alert("您的密码已发送至邮箱，请查收!");
                         $("#findUserPwd").modal('hide');
                         $("#login").modal();
                     }
                 }
             });
         }

     </script>
 </head>
 <body>
 <ul class="nav navbar-nav navbar-right">
     <li><a data-toggle="modal" data-target="#login" href=""><span class="glyphicon glyphicon-log-in"></span> 个人登录</a></li>
     <li><a data-toggle="modal" data-target="#login1" href=""><span class="glyphicon glyphicon-log-in"></span> 商家登录</a></li>
 </ul>

 <h3>欢迎来到停车位诱导分配系统</h3>

 <!-- 个人注册窗口 -->
 <div id="register" class="modal fade" tabindex="-1">
     <div class="modal-dialog">
         <div class="modal-content">
             <div class="modal-body">
                 <button class="close" data-dismiss="modal">
                     <span>&times;</span>
                 </button>
             </div>
             <div class="modal-title">
                 <h3 class="text-center">个人注册</h3>
             </div>
             <div class="modal-body">
                 <form class="form-group" action="#" id="userrgForm">
                     <div class="form-group">
                         <label for="">用户名</label>
                         <input class="form-control" type="text" name="username" placeholder="6-15位字母或数字">
                     </div>
                     <div class="form-group">
                         <label for="">密码</label>
                         <input class="form-control" type="password" name="userpwd" id="userpwd" placeholder="至少6位字母或数字">
                     </div>

                     <div class="form-group">
                         <label for="">再次输入密码</label>
                         <input class="form-control" type="password" name="userpwd" id="userpwd2" placeholder="至少6位字母或数字">
                     </div>

                     <div class="form-group">
                         <label for="">联系方式</label>
                         <input class="form-control" type="text" name="phone">
                     </div>

                     <div class="form-group">
                         <label for="">邮箱</label>
                         <input class="form-control" type="text" name="email">
                     </div>

                     <div class="text-right">
                         <input type="button" class="btn btn-primary" onclick="userrg();" value="注册" />
                         <button class="btn btn-danger" data-dismiss="modal">取消</button>
                     </div>
                     <a href="" data-toggle="modal" data-dismiss="modal" data-target="#login">已有账号？点我登录</a>
                 </form>
             </div>
         </div>
     </div>
 </div>

 <!-- 商家注册窗口 -->
 <div id="register1" class="modal fade" tabindex="-1">
     <div class="modal-dialog">
         <div class="modal-content">
             <div class="modal-body">
                 <button class="close" data-dismiss="modal">
                     <span>&times;</span>
                 </button>
             </div>
             <div class="modal-title">
                 <h3 class="text-center">商家注册</h3>
             </div>
             <div class="modal-body">
                 <form class="form-group" action="#" id="adminrgForm">
                     <div class="form-group">
                         <label for="">用户名</label>
                         <input class="form-control" type="text" name="adminName" placeholder="6-15位字母或数字">
                     </div>
                     <div class="form-group">
                         <label for="">密码</label>
                         <input class="form-control" type="password" name="adminpwd" id="adminpwd" placeholder="至少6位字母或数字">
                     </div>

                     <div class="form-group">
                         <label for="">再次输入密码</label>
                         <input class="form-control" type="password" name="adminpwd" id="adminpwd2" placeholder="至少6位字母或数字">
                     </div>

                     <div class="form-group">
                         <label for="">联系方式</label>
                         <input class="form-control" type="text" name="phone">
                     </div>

                     <div class="form-group">
                         <label for="">邮箱</label>
                         <input class="form-control" type="text" name="email">
                     </div>

                     <div class="text-right">
                         <input class="btn btn-primary" type="button" onclick="adminrg();"  value="提交" />
                         <button class="btn btn-danger" data-dismiss="modal">取消</button>
                     </div>
                     <a href="" data-toggle="modal" data-dismiss="modal" data-target="#login1">已有账号？点我登录</a>
                 </form>
             </div>
         </div>
     </div>
 </div>

 <!-- 个人登录窗口 -->
 <div id="login" class="modal fade">
     <div class="modal-dialog">
         <div class="modal-content">
             <div class="modal-body">
                 <button class="close" data-dismiss="modal">
                     <span>&times;</span>
                 </button>
             </div>
             <div class="modal-title">
                 <h3 class="text-center">个人登录</h3>
             </div>
             <div class="modal-body">
                 <form class="form-group" action="#" id="userlgForm">
                     <div class="form-group">
                         <label for="">用户名</label>
                         <input class="form-control" type="text" name="username" placeholder="">
                     </div>
                     <div class="form-group">
                         <label for="">密码</label>
                         <input class="form-control" type="password" name="userpwd" placeholder="">
                     </div>
                     <div class="text-right">
                         <input class="btn btn-primary" type="button" value="登陆" onclick="userlg();" />
                         <button class="btn btn-danger" data-dismiss="modal">取消</button>
                     </div>
                     <a href="" data-toggle="modal" data-dismiss="modal" data-target="#register">还没有账号？点我注册</a>
                     <a href="" style="float: right;" data-toggle="modal" data-dismiss="modal" data-target="#findUserPwd">忘记密码？点我找回</a>
                 </form>
             </div>
         </div>
     </div>
 </div>

 <!-- 商家登录窗口 -->
 <div id="login1" class="modal fade">
     <div class="modal-dialog">
         <div class="modal-content">
             <div class="modal-body">
                 <button class="close" data-dismiss="modal">
                     <span>&times;</span>
                 </button>
             </div>
             <div class="modal-title">
                 <h3 class="text-center">商家登录</h3>
             </div>
             <div class="modal-body">
                 <form class="form-group" action="#" id="adminlgForm">
                     <div class="form-group">
                         <label for="">用户名</label>
                         <input class="form-control" type="text" name="adminName" placeholder="">
                     </div>
                     <div class="form-group">
                         <label for="">密码</label>
                         <input class="form-control" type="password" name="adminpwd" placeholder="">
                     </div>
                     <div class="text-right">
                         <input class="btn btn-primary" type="button" value="登陆" onclick="adminlg();"  />
                         <button class="btn btn-danger" data-dismiss="modal">取消</button>
                     </div>
                     <a href="" data-toggle="modal" data-dismiss="modal" data-target="#register1">还没有账号？点我注册</a>
                     <a href="" style="float: right;" data-toggle="modal" data-dismiss="modal" data-target="#findAdminPwd">忘记密码？点我找回</a>
                 </form>
             </div>
         </div>
     </div>
 </div>

 <!-- 个人密码找回 -->
 <div id="findUserPwd" class="modal fade">
     <div class="modal-dialog">
         <div class="modal-content">
             <div class="modal-body">
                 <button class="close" data-dismiss="modal">
                     <span>&times;</span>
                 </button>
             </div>
             <div class="modal-title">
                 <h3 class="text-center">密码找回</h3>
             </div>
             <div class="modal-body">
                 <form class="form-group" action="#">
                     <div class="form-group">
                         <label for="">用户名</label>
                         <input class="form-control" type="text" name="username" placeholder="">
                     </div>
                     <div class="form-group">
                         <label for="">注册时邮箱</label>
                         <input class="form-control" type="text" name="email" placeholder="">
                     </div>
                     <div class="text-right">
                         <input class="btn btn-primary" type="button" value="找回" onclick="findUserPwd();" />
                     </div>
                 </form>
             </div>
         </div>
     </div>
 </div>

 <!-- 商家密码找回 -->
 <div id="findAdminPwd" class="modal fade">
     <div class="modal-dialog">
         <div class="modal-content">
             <div class="modal-body">
                 <button class="close" data-dismiss="modal">
                     <span>&times;</span>
                 </button>
             </div>
             <div class="modal-title">
                 <h3 class="text-center">密码找回</h3>
             </div>
             <div class="modal-body">
                 <form class="form-group" action="#">
                     <div class="form-group">
                         <label for="">用户名</label>
                         <input class="form-control" type="text" name="adminName" placeholder="">
                     </div>
                     <div class="form-group">
                         <label for="">注册时邮箱</label>
                         <input class="form-control" type="text" name="email" placeholder="">
                     </div>
                     <div class="text-right">
                         <input class="btn btn-primary" type="button" value="找回" onclick="findAdminPwd();" />
                     </div>
                 </form>
             </div>
         </div>
     </div>
 </div>

 </body>
 </html>