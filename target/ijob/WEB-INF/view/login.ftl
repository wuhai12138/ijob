<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    

    <title>登录</title>
    <meta name="keywords" content="H+">
    <meta name="description" content="napolenion">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet">
    <#--开启新页面-->
    <#--<base target="_blank">-->

    <!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <#--将当前窗口放置到最上层-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body class="gray-bg">
    <#--<div class="middle-box text-center loginscreen  animated fadeInDown">-->
    <div class="middle-box text-center loginscreen animated fadeInDown">
        <#--<div>-->
            <div style="margin-bottom: 30px;">
                <#--<h5 class="logo-name"  style="width: 328px;font-size: 77px;">商 旅 I 就 业</h5>-->
                <img src="img/ijob-logo.png" style="width:296px"/>
            </div>
            <form id="form1" action="main.do" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" id="userName" name="userName" placeholder="用户名" required="">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="password" name="password" placeholder="密码" required="">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>
            </form>
        <#--</div>-->
    </div>
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.5"></script>
    <#--<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>-->
    <script type="text/javascript">
        function login() {
            $("#form1").submit();
        }
        $("#password").keyup(function (event) {
            e = event ? event :(window.event ? window.event : null);
            if(e.keyCode == 13) {
                login();
            }
        });
    </script>
</body>

</html>