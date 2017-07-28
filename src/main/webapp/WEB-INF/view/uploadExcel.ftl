<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>批量导入用户</title>
    <link rel="shortcut icon" href="img/favicon.ico">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="wrapper wrapper-content">
    <form class="form-horizontal" id="form1">
        <div class="row">
            <div class="col-xs-12">
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">名单：</label>
                    <div class="col-xs-6">
                        <input type="file" id="file" multiple="" title="上传图片" name="file"/>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div> <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">模板：</label>
                    <div class="col-xs-6">
                        <button class="btn btn-primary dim" onclick="demoDownload()">模板下载</button>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-12 col-xs-offset-2" style="margin-left: 25%">
                        <button class="btn btn-primary dim" type="button" id="save" onclick="saveOrder()">保存</button>
                        <button class="btn btn-info dim" type="button" id="closeIframe">取消</button>
                    </div>
                </div>
                <iframe name='hidden_frame' id="hidden_frame" style='display:none'></iframe>
            </div>
        </div>
    </form>
</div>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/plugins/layer/layer.min.js"></script>
<script src="js/jquery.form.js"></script>
<script src="js/jquery.js"></script>
<script src="js/ajaxfileupload.js"></script>
<script>
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    //关闭iframe
    $('#closeIframe').click(function(){
        parent.layer.close(index);
    });

    function saveOrder() {
        var url = "saveExcel111.do";
        $.ajaxFileUpload({
            url:url, //你处理上传文件的服务端
            secureuri:false,
            fileElementId:'file',
            dataType: 'json',
            success: function(data, status){
                if(status == "success"){
                    parent.layer.msg('保存成功', {
                        shade: 0.3,
                        icon: 1,
                        time: 1500 //2秒关闭（如果不配置，默认是3秒）
                    }, function(){
                        parent.location.reload();
                    });
                }else{
                    alert("导入失败！");
                    return false;
                }
            }
        });
    }

    function demoDownload(){
        window.open("downloadDemo.do");
    }

</script>
</body>
</html>



