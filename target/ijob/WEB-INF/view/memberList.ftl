<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>成员列表</title>
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
                    <input id="groupid" name="groupid" value="${groupid}" hidden>
                    <div class="ibox-title" style="padding: 0px 20px;">
                        <h2>成员列表
                        <#if flag=="add">
                            --无法删除任何成员
                        <#elseif flag=="delete">
                            --无法添加任何成员(选中=删除)
                        </#if>
                            <#if flag!="show">
                            <button class="btn btn-primary pull-right dim" onclick="saveMember()">保存</button>
                            </#if>
                        </h2>
                    </div>
                    <div class="ibox-content">
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                    <th class="center">
                                        <label>
                                            <input type="checkbox" id="checkAll"/>
                                        </label>
                                    </th>
                                    <th class="text-center">账号</th>
                                    <th class="text-center">昵称</th>
                                    <th class="text-center">班级</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#--删除或者查看-->
                                <#if flag!="add">
                                <#if studentList?exists>
                                    <#list studentList as list>
                                    <#if list.check==1>
                                    <tr>
                                        <td class="text-center" style="width: 50px">
                                            <label class="checkbox-inline i-checks">
                                                <input type="checkbox" class="checkFellow" value="${list.code}" style="margin-left: 0px">
                                            </label>
                                        </td>
                                        <td class="text-center">${list.code!''}</td>
                                        <td class="text-center">${list.nick!''}</td>
                                        <td class="text-center">${list.introduction!''}</td>
                                    </tr>
                                    </#if>
                                    </#list>
                                </#if>
                                </#if>
                                <#--增加-->
                                <#if flag=="add">
                                <#if studentList?exists>
                                    <#list studentList as list>
                                    <tr>
                                        <td class="text-center" style="width: 30px">
                                            <label class="checkbox-inline i-checks">
                                                <input type="checkbox" class="<#if list.check!=1>checkFellow"</#if>" value="${list.code}" style="margin-left: 0px" <#if list.check==1>disabled</#if>>
                                            </label>
                                        </td>
                                        <td class="text-center">${list.code!''}</td>
                                        <td class="text-center">${list.nick!''}</td>
                                        <td class="text-center">${list.introduction!''}</td>
                                    </tr>
                                    </#list>
                                </#if>
                                </#if>
                                </tbody>
                            </table>
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
    <script src="js/plugins/iCheck/icheck.min.js"></script>
    <script type="text/template" id="add-new-template"></script>
    <script>
        $(document).ready(function() {
            $(".dataTables-example").dataTable({
                "bPaginate": false
            });
            $('#checkAll').on('click', function () {
                var that = this;
                $('.checkFellow').each(function () {
                    this.checked = that.checked;
                    $(this).closest('tr').toggleClass('selected');
                });
            });
        })
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
        function saveMember(){
            $(".input-sm").val('');
            var member = "";
            $("input:checked").each(function(i){
                if(0==i){
                    member = $(this).val();
                }else{
                    member += (","+$(this).val());
                }
            });//获取所有选中的checkbox,chks是一个元素数组

            var groupid = $("#groupid").val();
            <#if flag=="add">
            var url = "saveMember.do";
            <#else>
            var url = "deleteMember.do";
            </#if>
            $.ajax({
                url: url,
                type: "post",
                dataType: "json",
                data: {"groupid": groupid , "member": member},
                success: function (result) {
                    //在父层弹出一个层
                    if (result.status == 200) {
                        parent.layer.msg('保存成功', {
                            shade: 0.3,
                            icon: 1,
                            time: 1500 //2秒关闭（如果不配置，默认是3秒）
                        }, function () {
                            parent.location.reload();
                        });
                    } else if (result.status == 500) {
                        parent.layer.msg('访问出错', {
                            shade: 0.3,
                            icon: 1,
                            time: 1500 //2秒关闭（如果不配置，默认是3秒）
                        }, function () {
                            parent.location.reload();
                        });
                    }else if (result.status == 1005) {
                        parent.layer.msg('访问环信出错', {
                            shade: 0.3,
                            icon: 1,
                            time: 1500 //2秒关闭（如果不配置，默认是3秒）
                        }, function () {

                        });
                    }
                },
                error: function () {
                    alert("服务器紧张ING");
                }
            });
        }
    </script>
</body>

</html>