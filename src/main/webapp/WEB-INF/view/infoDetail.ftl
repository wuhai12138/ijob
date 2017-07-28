<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户详情</title>
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
            <input id="tid" name="tid" <#if updateInfo??>value="${updateInfo.tid}"</#if> hidden>
            <#if type??><input id="type" name="type" value="${type}" hidden></#if>
            <div class="col-xs-12">
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">标题：</label>
                    <div class="col-xs-6">
                        <input type="text" name="title" id="title" class="form-control" <#if updateInfo?exists>value="${updateInfo.title!''}"</#if>>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">链接：</label>
                    <div class="col-xs-6">
                        <textarea name="link" id="link" class="form-control"><#if updateInfo??>${updateInfo.link!''}</#if></textarea>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">报名按钮：</label>
                    <div class="col-xs-6">
                        <select class="form-control" name="canEnroll" id="canEnroll">
                        <#if updateInfo??>
                            <option value="0" <#if updateInfo.canEnroll==0>selected</#if>>隐藏</option>
                            <option value="1" <#if updateInfo.canEnroll==1>selected</#if>>显示</option>
                        <#else>
                            <option value="0" >隐藏</option>
                            <option value="1" selected>显示</option>
                        </#if>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">图片：</label>
                    <div class="col-xs-6">
                    <#if updateInfo??>
                        <#if updateInfo.img??>
                            <input type="input" value="图片已上传" class="form-control"/>
                            <button class="btn btn-warning btn-xs" type="button"  onclick="picChange()">更换</button>
                            <div id="change" hidden>
                            <input type="file" id="file" multiple="" title="上传图片"
                                   name="file" accept="image/*" value="上传图片" />
                            </div>
                            <span class="help-block m-b-none"></span>
                        <#else>
                            <input type="file" id="file" multiple="" title="上传图片"
                                   name="file" accept="image/*" value="上传图片"/>
                            <span class="help-block m-b-none"></span>
                        </#if>
                    <#else>
                        <input type="file" id="file" multiple="" title="上传图片"
                               name="file" accept="image/*" value="上传图片"/>
                        <span class="help-block m-b-none"></span>
                    </#if>
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
        var file = $("#file").val();
        var title = $("#title").val();
        title = encodeURIComponent(title);
        var link = $("#link").val();
        link = encodeURIComponent(link);
        var canEnroll = $("#canEnroll").val();
//        link = encodeURIComponent(link);
        <#if updateInfo??>
            var url = "updateInfo.do?title="+title+"&tid=${updateInfo.tid}"+"&canEnroll="+canEnroll+"&link="+link;
            url = encodeURI(url);
        <#else>
            var type = $("#type").val();
            var url = "addInfo.do?title="+title+"&type="+type+"&canEnroll="+canEnroll+"&link="+link;
            url = encodeURI(url);
        </#if>
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
                    alert("图片上传失败！");
                    return false;
                }
            }
        });
    }

    function pageReload(){
        parent.location.reload();
    }
    function picChange(){
        $("#change").show();
    }
</script>
</body>
</html>



