<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户列表</title>
    <meta name="keywords" content="H+">
    <meta name="description" content="napolenion">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

    <!-- Data Tables -->
    <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title" style="padding: 0px 20px;">
                        <h2>用户列表
                            <button class="btn btn-primary pull-right dim" onclick="showAdd()">添加</button>
                            <button class="btn btn-primary pull-right dim" onclick="studentExcel()">批量导入</button>
                        </h2>
                    </div>
                    <div class="ibox-content">
                        <div id="DataTables_Tables_0" class="dataTables_wrapper form-inline" role="grid">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div id="DataTables_Table_0_length" class="dataTables_length">
                                        <label>每页
                                            <select id="row" onchange="changeRow()" class="form-control input-sm" aria-controls="DataTables_Table_0" name="DataTables_Table_0_length">
                                            <#if param?exists>
                                                <option value="10" <#if param.row==10>selected</#if>>10</option>
                                                <option value="20" <#if param.row==20>selected</#if>>20</option>
                                                <option value="30" <#if param.row==30>selected</#if>>30</option>
                                                <option value="50" <#if param.row==50>selected</#if>>50</option>
                                            <#else>
                                                <option value="10" selected>10</option>
                                                <option value="20">20</option>
                                                <option value="30">30</option>
                                                <option value="50">50</option>
                                            </#if>
                                            </select>条记录
                                        </label>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="dataTables_filter" id="DataTables_Table_0_filter">
                                        <div class="input-group has-success" style="float: right;">
                                            <input class="form-control input-sm" id="search" style="height: 36px;" placeholder="请输入账号搜索" <#if param.search??>value="${param.search}"</#if>>
                                            <span class="input-group-btn">
                                                <button type="button" class="btn btn-success btn-sm dim" onclick="searchThis()">搜索</button>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <table class="table table-striped table-bordered table-hover dataTables-example">
                                <thead>
                                <tr>
                                    <th class="text-center">账号</th>
                                    <th class="text-center">学院</th>
                                    <th class="text-center">昵称</th>
                                    <th class="text-center">密码</th>
                                    <th class="text-center">邮箱</th>
                                    <th class="text-center">姓名</th>
                                    <th class="text-center">联系方式</th>
                                    <th class="text-center">班级</th>
                                    <th class="text-center">是否已录取</th>
                                    <th class="text-center">简历</th>
                                    <th class="text-center">心理档案</th>
                                    <th class="text-center">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if studentList?exists>
                                    <#list studentList as list>
                                    <tr>
                                        <td class="text-center">${list.code!''}</td>
                                        <td class="text-center">${list.schoolName!''}</td>
                                        <td class="text-center">${list.nick!''}</td>
                                        <td class="text-center">${list.password!''}</td>
                                        <td class="text-center">${list.email!''}</td>
                                        <td class="text-center">${list.name!''}</td>
                                        <td class="text-center">${list.phone!''}</td>
                                        <td class="text-center">${list.introduction!''}</td>
                                        <td class="text-center">
                                            <#if list.status??>
                                            <#if list.status=="0">
                                                否
                                            <#else>
                                                是
                                            </#if>
                                            </#if>
                                        </td>
                                        <td style="padding-top: 8px; padding-bottom: 0px;" class="text-center">
                                            <button type="button" class="btn btn-warning btn-xs" onclick="personalFile('${list.link!''}')">简历</button>
                                        </td>
                                        <td style="padding-top: 8px; padding-bottom: 0px;" class="text-center">
                                            <button type="button" class="btn btn-warning btn-xs" onclick="goArchive('${list.code!''}')">心理档案</button>
                                        </td>
                                        <td style="padding-top: 8px; padding-bottom: 0px;" class="text-center">
                                            <button type="button" class="btn btn-success btn-xs" onclick="updateOne(${list.tid?c})">修改</button>
                                            <button type="button" class="btn btn-danger btn-xs" onclick="deleteOne(${list.tid?c})">删除</button>
                                        </td>
                                    </tr>
                                    </#list>
                                </#if>
                                </tbody>
                            </table>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div aria-relevant="all" aria-live="polite" role="alert" id="DataTables_Table_0_info" class="dataTables_info">共 ${param.total!''} 条 数 据</div>
                                </div>
                                <div class="col-sm-6">
                                    <div id="DataTables_Table_0_paginate" class="dataTables_paginate paging_simple_numbers">
                                        <ul class="pagination">
                                            <li id="DataTables_Table_0_previous" tabindex="0" aria-controls="DataTables_Table_0" class="paginate_button previous <#if param.currentPage==1>disabled</#if>">
                                                <a href="javascript:void(0)" <#if param.currentPage!=1>onclick="goPage(${param.currentPage-1})"</#if>>上一页</a>
                                            </li>
                                            <#if param.currentPage!=1>
                                            <li tabindex="0" aria-controls="DataTables_Table_0" class="paginate_button">
                                                <a href="javascript:void(0)" <#if param.currentPage!=1>onclick="goPage(${param.currentPage-1})"</#if>>${param.currentPage-1}</a>
                                            </li>
                                            </#if>
                                            <li tabindex="0" aria-controls="DataTables_Table_0" class="paginate_button active">
                                                <a href="javascript:void(0)">${param.currentPage!''}</a>
                                            </li>
                                            <#if param.currentPage*param.row lt param.total>
                                            <li tabindex="0" aria-controls="DataTables_Table_0" class="paginate_button">
                                                <a href="javascript:void(0)" <#if param.currentPage*param.row lt param.total>onclick="goPage(${param.currentPage+1})"</#if>>${param.currentPage+1}</a>
                                            </li>
                                            </#if>
                                            <li id="DataTables_Table_0_next" tabindex="0" aria-controls="DataTables_Table_0" class="paginate_button next <#if param.currentPage*param.row gte param.total>disabled</#if>">
                                                <a href="javascript:void(0)" <#if param.currentPage*param.row lt param.total>onclick="goPage(${param.currentPage+1})"</#if>>下一页</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/plugins/layer/layer.min.js"></script>
    <script src="js/plugins/jeditable/jquery.jeditable.js"></script>
    <script src="js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="js/content.min.js?v=1.0.0"></script>
    <script type="text/template" id="add-new-template"></script>
    <script>
//        $(document).ready(function(){
//            $(".dataTables-example").dataTable();
//            var oTable=$("#editable").dataTable();
//            oTable.$("td").editable("../example_ajax.php",{
//                "callback":function(sValue,y){
//                    var aPos=oTable.fnGetPosition(this);
//                    oTable.fnUpdate(sValue,aPos[0],aPos[1])},
//                "submitdata":function(value,settings){
//                    return{
//                        "row_id":this.parentNode.getAttribute("id"),
//                        "column":oTable.fnGetPosition(this)[2]}},
//                "width":"90%","height":"100%"})});
//        function fnClickAddRow(){
//            $("#editable").dataTable().fnAddData(["Custom row","New row","New row","New row","New row"])};
    </script>
    <script>
        function showAdd() {
            var con = $('#add-new-template').html();
//            var row = $("#row").val();
            var url = 'studentDetail.do';
            layer.open({
                type: 2,
                title: '新增用户',
                maxmin: true,
                area: ['500px', '400px'],
                shadeClose: true, //点击遮罩关闭
                scrollbar: false,
                content:url
            });
        }
        function updateOne(id) {
            var con = $('#add-new-template').html();
//            var row = $("#row").val();
            var url = 'studentDetail.do?tid='+id;
            layer.open({
                type: 2,
                title: '更新用户',
                maxmin: true,
                area: ['500px', '400px'],
                shadeClose: true, //点击遮罩关闭
                scrollbar: false,
                content:url
            });
        }
        function deleteOne(id) {
            layer.confirm('确认删除？', {
                btn: ['Yes','No']
            }, function() {
                var url = "deleteStudent.do";
                $.ajax({
                    url: url,
                    type: "post",
                    dataType: "json",
                    data: {"tid": id},
                    success: function (result) {
                        //在父层弹出一个层
                        if (result.status == 200) {
                            parent.layer.msg('删除成功', {
                                shade: 0.3,
                                icon: 1,
                                time: 1500 //2秒关闭（如果不配置，默认是3秒）
                            }, function () {
                                self.location.reload();
                            });
                        } else if (result.status == 500) {
                            parent.layer.msg('访问出错', {
                                shade: 0.3,
                                icon: 1,
                                time: 1500 //2秒关闭（如果不配置，默认是3秒）
                            }, function () {
                                self.location.reload();
                            });
                        }else if (result.status == 1004) {
                            parent.layer.msg('环信删除不成功', {
                                shade: 0.3,
                                icon: 1,
                                time: 1500 //2秒关闭（如果不配置，默认是3秒）
                            }, function () {
                                self.location.reload();
                            });
                        }
                    },
                    error: function () {
                        alert("服务器紧张ING");
                    }
                });
            })
        }
        function studentExcel(){
            var url = 'uploadExcel.do';
            layer.open({
                type: 2,
                title: '导入用户',
                maxmin: true,
                area: ['500px', '400px'],
                shadeClose: true, //点击遮罩关闭
                scrollbar: false,
                content:url
            });
        }

        function personalFile(link){
            window.open("http://"+link);
        }
        function goArchive(link){
            window.open("http://www.shminde.com:9090/smly/resume/index.php/archives?u="+link);
        }

        function searchThis(){
            var search = $("#search").val();
            var row = $("#row").val();
            var url = "studentList.do?type=1&row="+row+"&search="+search;
            self.location.replace(url);
        }
        function changeRow(){
            var search = $("#search").val();
            var row = $("#row").val();
            var url = "studentList.do?type=1&row="+row+"&search="+search;
            self.location.replace(url);
        }
        function goPage(id){
            var search = $("#search").val();
            var row = $("#row").val();
            var url = "studentList.do?type=1&row="+row+"&search="+search+"&currentPage="+id;
            self.location.replace(url);
        }
    </script>
</body>

</html>