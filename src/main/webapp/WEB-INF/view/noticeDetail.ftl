<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>公告详情</title>
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
            <input id="noticeId" name="noticeId" <#if updateNotice??>value="${updateNotice.noticeId}"</#if> hidden>
        <#if groupName??><input id="groupName" name="groupName" value="${groupName}" hidden></#if>
        <#if groupId??><input id="groupId" name="groupId" value="${groupId}" hidden></#if>
            <div class="col-xs-12">
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">标题：</label>
                    <div class="col-xs-6">
                        <input type="text" name="title" id="title" class="form-control" <#if updateNotice?exists>value="${updateNotice.title!''}"</#if>>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">内容：</label>
                    <div class="col-xs-6">
                        <textarea name="content" id="content" class="form-control"><#if updateNotice?exists>${updateNotice.content!''}</#if></textarea>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">链接：</label>
                    <div class="col-xs-6">
                        <textarea name="link" id="link" class="form-control"><#if updateNotice?exists>${updateNotice.link!''}</#if></textarea>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">图片：</label>
                    <div class="col-xs-6">
                    <#if updateNotice??>
                        <#if updateNotice.picture??>
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
        var content = $('#content').val();
        content = encodeURIComponent(content);
    <#if updateNotice??>
        var url = "updateNotice.do?title="+title+"&noticeId=${updateNotice.noticeId}"+"&link="+link+"&content="+content;
        url = encodeURI(url);
    <#else>
        var groupName = $("#groupName").val();
        var groupId = $("#groupId").val();
        var url = "addNotice.do?title="+title+"&groupName="+groupName+"&content="+content+"&link="+link+"&groupId="+groupId;
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



