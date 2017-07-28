<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>商旅i就业</title>

    <meta name="keywords" content="H+">
    <meta name="description" content="napolenion">

    <!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" href="favicon.ico">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold"><#if currentUser??>${currentUser.userName}</#if></strong></span>
                                <span class="text-muted text-xs block">管理员</span>
                                </span>
                            </a>
                        </div>
                    </li>
                    <#if currentUser.auth==1>
                    <li>
                        <a class="J_menuItem" href="userList.do"><i class="fa fa-wrench"></i> <span class="nav-label">管理员列表</span></a>
                    </li>
                    </#if>
                    <#if currentUser.auth==1>
                    <li>
                        <a class="J_menuItem" href="schoolList.do"><i class="fa fa-wrench"></i> <span class="nav-label">学院列表</span></a>
                    </li>
                    </#if>
                    <#if currentUser.auth==1 || currentUser.auth==3 || currentUser.auth==4>
                    <li>
                        <a class="J_menuItem" href="infoListPage.do?type=1"><i class="fa fa-list-ul"></i> <span class="nav-label">新闻管理</span></a>
                    </li>
                    </#if>
                    <#if currentUser.auth==1 || currentUser.auth==2>
                    <li>
                        <a class="J_menuItem" href="jobList.do"><i class="fa fa-briefcase"></i> <span class="nav-label">职位管理</span></a>
                    </li>
                    </#if>
                    <#if currentUser.auth==1 || currentUser.auth==2>
                    <li>
                        <a class="J_menuItem" href="companyList.do"><i class="fa fa-sitemap"></i> <span class="nav-label">校企合作</span></a>
                    </li>
                    </#if>
                    <#if currentUser.auth==1 || currentUser.auth==4>
                    <li>
                        <a class="J_menuItem" href="infoListPage.do?type=3"><i class="fa fa-lightbulb-o"></i> <span class="nav-label">职业测评</span></a>
                    </li>
                    </#if>
                    <#if currentUser.auth==1 || currentUser.auth==2>
                    <li>
                        <a class="J_menuItem" href="infoListPage.do?type=2"><i class="fa fa-file-word-o"></i> <span class="nav-label">实习心语</span></a>
                    </li>
                    </#if>
                    <#if currentUser.auth==1 || currentUser.auth==2>
                    <li>
                        <a class="J_menuItem" href="infoListPage.do?type=4&types=5"><i class="fa fa-hand-o-right"></i> <span class="nav-label">就业升学指导</span></a>
                    </li>
                    </#if>
                    <#if currentUser.auth==1 || currentUser.auth==4>
                    <li>
                        <a class="J_menuItem" href="infoListPage.do?type=7"><i class="fa fa-hand-o-right"></i> <span class="nav-label">缤纷心理</span></a>
                    </li>
                    </#if>
                    <#if currentUser.auth==1 || currentUser.auth==3>
                    <li>
                        <a class="J_menuItem" href="infoListPage.do?type=6"><i class="fa fa-hand-o-right"></i> <span class="nav-label">创业新干线</span></a>
                    </li>
                    </#if>
                    <#if currentUser.auth==1 || currentUser.auth==2 || currentUser.auth==3 || currentUser.auth==4>
                    <li>
                        <a class="J_menuItem" href="studentList.do"><i class="fa fa-group"></i> <span class="nav-label">用户管理</span></a>
                    </li>
                    </#if>
                    <#if currentUser.auth==1 || currentUser.auth==2 || currentUser.auth==3 || currentUser.auth==4>
                    <li>
                        <a class="J_menuItem" href="groupList.do"><i class="fa fa-group"></i> <span class="nav-label">群组管理</span></a>
                    </li>
                    </#if>
                    <#if currentUser.auth==1 || currentUser.auth==2 || currentUser.auth==3 || currentUser.auth==4>
                    <li>
                        <a class="J_menuItem" href="noticeList.do?page=1"><i class="fa fa-group"></i> <span class="nav-label">公告管理</span></a>
                    </li>
                    </#if>
                    <#if currentUser.auth==1 || currentUser.auth==2 || currentUser.auth==3 || currentUser.auth==4>
                    <li>
                        <a class="J_menuItem" href="chatHistory.do?times="><i class="fa fa-group"></i> <span class="nav-label">聊天记录</span></a>
                    </li>
                    </#if>
                    <li>
                        <a class="" href="javascript:void(0)" onclick="openChat()"><i class="fa fa-group"></i> <span class="nav-label">在线聊天</span></a>
                    </li>
                    <script>
                        function openChat(){
                            window.open("http://localhost/webIM/index.html");
                        }
                    </script>
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
            </div>
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <#--<a href="javascript:;" class="active J_menuTab" data-id="userList">管理员</a>-->
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <a href="login.do" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="" frameborder="0" data-id="userList" seamless></iframe>
            </div>
            
        </div>
        <!--右侧部分结束-->
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="js/plugins/layer/layer.min.js"></script>
    <script src="js/hplus.min.js"></script>
    <script type="text/javascript" src="js/contabs.min.js"></script>
    <script src="js/plugins/pace/pace.min.js"></script>
</body>
</html>