<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>公告列表</title>
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
                    <h2>公告列表</h2>
                </div>
                <div class="ibox-content">
                    <table class="table table-striped table-bordered table-hover dataTables-example">
                        <thead>
                        <tr>
                            <th style="text-align:center;">ID</th>
                            <th style="text-align:center;">标题</th>
                            <th style="text-align:center;">内容</th>
                             <th style="text-align:center;">群组名称</th>
                            <th style="text-align:center;">创建时间</th>
                            <th style="text-align:center;">链接</th>
                            <th style="text-align:center;">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#if noticeList?exists>
                            <#list noticeList as list>
                            <tr>
                                <td class="text-center">${list.noticeId}</td>
                                <td class="text-center">${list.title}</td>
                                <td class="text-center">${list.content}</td>
                                <td class="text-center">${list.groupName}</td>
                                <td class="text-center">${list.createTime?string('yyyy-MM-dd')}</td>
                                <td class="text-center">${list.link}</td>
                            <#--<td class="text-center">${list.content}</td>-->
                                <td style="padding-top: 8px; padding-bottom: 0px;" class="text-center">
                                    <button type="button" class="btn btn-success btn-xs" onclick="updateOne(${list.noticeId})">修改</button>
                                    <button type="button" class="btn btn-danger btn-xs" onclick="deleteOne(${list.noticeId})">删除</button>
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
<script>
    function updateOne(id) {
        var con = $('#add-new-template').html();
//            var row = $("#row").val();
        var url = 'noticeDetail.do?noticeId='+id;
        layer.open({
            type: 2,
            title: '更新新闻',
            maxmin: true,
            area: ['500px', '300px'],
            shadeClose: true, //点击遮罩关闭
            scrollbar: false,
            content:url
        });
    }
    function deleteOne(noticeId) {
        layer.confirm('确认删除？', {
            btn: ['Yes','No']
        }, function() {
            var url = "deleteNotice.do";
            $.ajax({
                url: url,
                type: "post",
                dataType: "json",
                data: {"noticeId": noticeId},
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
</script>
</body>

</html>