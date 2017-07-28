<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>职位申请列表</title>
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
                    <div class="ibox-title" style="padding: 0px 20px; text-align: center;">
                        <h2>职位申请列表</h2>
                    </div>
                    <div class="ibox-content">
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                    <th class="text-center">学生账号</th>
                                    <th class="text-center">学生</th>
                                    <th class="text-center">班级</th>
                                    <th class="text-center">申请日期</th>
                                    <th class="text-center">简历链接</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#if applyList?exists>
                                <#list applyList as list>
                                <tr>
                                    <td class="text-center">${list.code!''}</td>
                                    <td class="text-center">${list.studentName!''}</td>
                                    <td class="text-center">${list.introduction!''}</td>
                                    <td class="text-center">${list.createTime?datetime}</td>
                                    <td style="padding-top: 8px; padding-bottom: 0px;" class="text-center">
                                        <button type="button" class="btn btn-warning btn-xs" onclick="personalFile('${list.link!''}')">主页</button>
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
    function personalFile(link){
        window.open("http://"+link);
    }
</script>
</body>

</html>