<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>聊天记录</title>
    <meta name="keywords" content="H+">
    <meta name="description" content="napolenion">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

    <!-- Data Tables -->
    <link href="css/plugins/cropper/cropper.min.css" rel="stylesheet">
    <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="css/plugins/datapicker/datepicker3.css" rel="stylesheet">

    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title" style="padding: 0px 20px;">
                        <h2>聊天记录
                        </h2>
                    </div>
                    <div class="ibox-content">
                        <div id="DataTables_Tables_0" class="dataTables_wrapper form-inline" role="grid">
                            <div class="row">
                                    <div class="col-sm-6">
                                        <div class="dataTables_filter">
                                            <div class="input-group has-success">
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <#--<input type="text" class="form-control" id="textFind"  oninput="fadeAll()" placeholder="聊天记录搜索">-->
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                <div class="col-sm-6">
                                    <div class="dataTables_filter" id="DataTables_Table_0_filter">
                                        <div class="input-group has-success" style="float: right;">
                                            <div class="form-group">
                                                <#--<div class="col-sm-10">-->
                                                    <#--<input type="text" class="form-control" data-mask="9999-99-99-99" placeholder="" name="times">-->
                                                    <#--<span class="help-block">年-月-日-小时</span>-->
                                                <#--</div>-->
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                                    <input id="times" name="times" type="text" class="form-control" data-mask="9999-99-99-99" value="${times!''}" placeholder="年-月-日-小时">
                                                </div>
                                            </div>
                                            <#--<span class="input-group-btn">-->
                                                <#--<input id="textFind" name="textFind" value="" placeholder="文本搜索"/>-->
                                            <#--</span>-->
                                            <span class="input-group-btn">
                                                <button type="button" class="btn btn-success btn-sm dim" onclick="searchThis()">搜索</button>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div>
                                ${downloadURL!""}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/content.min.js?v=1.0.0"></script>
    <script src="js/plugins/chosen/chosen.jquery.js"></script>
    <script src="js/plugins/jasny/jasny-bootstrap.min.js"></script>
    <#--<script src="js/plugins/datapicker/bootstrap-datepicker.js"></script>-->
    <script src="js/plugins/cropper/cropper.min.js"></script>
    <script src="js/demo/form-advanced-demo.min.js"></script>
    <script src="js/plugins/layer/layer.min.js"></script>
    <script src="js/plugins/jeditable/jquery.jeditable.js"></script>
    <script src="js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script type="text/template" id="add-new-template"></script>

    <script>
        function showAdd() {
            var con = $('#add-new-template').html();
//            var row = $("#row").val();
            var url = 'careerDetail.do';
            layer.open({
                type: 2,
                title: '新增指导',
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
            var url = 'careerDetail.do?tid='+id;
            layer.open({
                type: 2,
                title: '更新指导',
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
            }, function() {
                var url = "deleteInfo.do";
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
                        } else if (result == 500) {
                            parent.layer.msg('访问出错', {
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
        function changeStatus(id) {
            layer.confirm('确认修改？', {
                btn: ['Yes','No']
            }, function() {
                var url = "changeInfoStatus.do";
                $.ajax({
                    url: url,
                    type: "post",
                    dataType: "json",
                    data: {"tid": id},
                    success: function (result) {
                        //在父层弹出一个层
                        if (result.status == 200) {
                            parent.layer.msg('修改成功', {
                                shade: 0.3,
                                icon: 1,
                                time: 1500 //2秒关闭（如果不配置，默认是3秒）
                            }, function () {
                                self.location.reload();
                            });
                        } else if (result == 500) {
                            parent.layer.msg('访问出错', {
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
        function showEnroll(id){
            var con = $('#add-new-template').html();
            var url = 'enrollInfoList.do?infoId='+id;
            layer.open({
                type: 2,
                title: '报名学生列表',
                maxmin: true,
                area: ['800px', '500px'],
                shadeClose: true, //点击遮罩关闭
                scrollbar: false,
                content:url
            });
        }
        function searchThis(){
            var times = $("#times").val();
//            var row = $("#row").val();
            var url = "chatHistory.do?times="+times;
            self.location.replace(url);
        }
        function goPage(num){
            var times = $("#times").val();
            var url = "chatHistory.do?page="+num+"&times="+times;
            self.location.replace(url);
        }

        function fadeAll(){
            var textFind = $('#textFind').val();
            $(".abc").each(function(){
                var t = ($(this).text()).indexOf(textFind);
                if(t<0){
                    $(this).parent().fadeOut();
                }else{
                    $(this).parent().fadeIn();
                }
            });
        }

//        $(document).ready(function(){
//            $("#textFind").delegate("button","click",function(){
//
//                $("p").slideToggle();
//            });
//        });


//        function filterColumn () {
//            $('.dataTables-example').DataTable().column(3).search(
//                    $('#textFind').val()
//            ).draw();
//        }
    </script>
</body>

</html>