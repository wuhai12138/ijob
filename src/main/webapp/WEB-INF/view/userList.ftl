<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理员列表</title>
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
                        <h2>管理员列表 <button class="btn btn-primary pull-right dim" onclick="showAdd()">添加管理员</button>
                        </h2>
                    </div>
                    <div class="ibox-content">
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                    <th style="text-align:center;">用户名</th>
                                    <th style="text-align:center;">学院</th>
                                    <#--<th style="text-align:center;">权限</th>-->
                                    <th style="text-align:center;">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#if userList?exists>
                                <#list userList as list>
                                <tr>
                                    <td class="text-center">${list.userName}</td>
                                    <td class="text-center">${list.name}</td>
                                    <#--<td class="text-center">${list.auth!''}</td>-->
                                    <td style="padding-top: 8px; padding-bottom: 0px;" class="text-center">
                                        <button type="button" class="btn btn-success btn-xs" onclick="updateOne(${list.tid?c})">修改</button>
                                        <button type="button" class="btn btn-danger btn-xs" onclick="deleteOne(${list.tid?c})">删除</button>
                                    </td>
                                </tr>
                                </#list>
                            </#if>
                            </tbody>
                        </table>
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
        $(document).ready(function(){
            $(".dataTables-example").dataTable();
            var oTable=$("#editable").dataTable();
            oTable.$("td").editable("../example_ajax.php",{
                "callback":function(sValue,y){
                    var aPos=oTable.fnGetPosition(this);
                    oTable.fnUpdate(sValue,aPos[0],aPos[1])},
                "submitdata":function(value,settings){
                    return{
                        "row_id":this.parentNode.getAttribute("id"),
                        "column":oTable.fnGetPosition(this)[2]}},
                "width":"90%","height":"100%"})});
        function fnClickAddRow(){
            $("#editable").dataTable().fnAddData(["Custom row","New row","New row","New row","New row"])};
    </script>
    <script>
        function showAdd() {
            var con = $('#add-new-template').html();
//            var row = $("#row").val();
            var url = 'userDetail.do';
            layer.open({
                type: 2,
                title: '新增管理员 ',
                maxmin: true,
                area: ['500px', '300px'],
                shadeClose: true, //点击遮罩关闭
                scrollbar: false,
                content:url
            });
        }
        function showAddSchool() {
            var con = $('#add-new-template').html();
//            var row = $("#row").val();
            var url = 'schoolDetail.do';
            layer.open({
                type: 2,
                title: '新增学院',
                maxmin: true,
                area: ['500px', '300px'],
                shadeClose: true, //点击遮罩关闭
                scrollbar: false,
                content:url
            });
        }
        function updateOne(id) {
            var con = $('#add-new-template').html();
//            var row = $("#row").val();
            var url = 'userDetail.do?tid='+id;
            layer.open({
                type: 2,
                title: '更新管理员信息',
                maxmin: true,
                area: ['500px', '300px'],
                shadeClose: true, //点击遮罩关闭
                scrollbar: false,
                content:url
            });
        }
        function deleteOne(id) {
            layer.confirm('确认删除？', {
                btn: ['Yes','No']
            }, function(){
                var url = "deleteUser.do";
                var tid = id;
                $.ajax({
                    url:url,
                    type:"post",
                    dataType:"json",
                    data:{"tid":tid},
                    success:function(result){
                        //在父层弹出一个层
                        if(result.status==200){
                            parent.layer.msg('删除成功', {
                                shade: 0.3,
                                icon: 1,
                                time: 1500 //2秒关闭（如果不配置，默认是3秒）
                            }, function(){
                                self.location.reload();
                            });
                        }else if(result==500){
                            parent.layer.msg('访问出错', {
                                shade: 0.3,
                                icon: 1,
                                time: 1500 //2秒关闭（如果不配置，默认是3秒）
                            }, function(){
                                self.location.reload();
                            });
                        }
                    },
                    error:function(){
                        alert("服务器紧张ING");
                    }
                });
            })
        }

//        function chatHis(){
//            var url = "chatHistory.do";
//            var con = $('#add-new-template').html();
//            var row = $("#row").val();
//            layer.open({
//                type: 2,
//                title: '聊天记录',
//                maxmin: true,
//                area: ['500px', '300px'],
//                shadeClose: true, //点击遮罩关闭
//                scrollbar: false,
//                content:url
//            });
//        }

    </script>
</body>

</html>