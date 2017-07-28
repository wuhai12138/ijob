<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>缤纷心理</title>
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
                    <label class="col-xs-3 control-label" style="text-align:right">标题：</label>
                    <div class="col-xs-6">
                        <input type="text" name="title" id="title" class="form-control" value="<#if updateColourful??>${updateColourful.title!''}</#if>">
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">人数：</label>
                    <div class="col-xs-6">
                        <input type="text" name="limitNum" id="limitNum" class="form-control" value="<#if updateColourful??>${updateColourful.limitNum!''}</#if>">
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">描述：</label>
                    <div class="col-xs-6">
                        <textarea name="description" id="description" class="form-control"><#if updateColourful??>${updateColourful.description!''}</#if></textarea>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">链接：</label>
                    <div class="col-xs-6">
                        <input type="text" name="link" id="link" class="form-control" value="<#if updateColourful??>${updateColourful.link!''}</#if>">
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <#--<div class="form-group">-->
                    <#--<label class="col-xs-3 control-label" style="text-align:right">类型：</label>-->
                    <#--<div class="col-xs-6">-->
                        <#--<select class="form-control" name="type" id="type">-->
                        <#--<#if updateColourful??>-->
                            <#--<option value="4" <#if updateColourful.type=="4">selected</#if>>就业指导</option>-->
                            <#--<option value="5" <#if updateColourful.type=="5">selected</#if>>升学指导</option>-->
                        <#--<#else>-->
                            <#--<option value="4" selected>就业指导</option>-->
                            <#--<option value="5">升学指导</option>-->
                        <#--</#if>-->
                        <#--</select>-->
                    <#--</div>-->
                <#--</div>-->
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">报名按钮：</label>
                    <div class="col-xs-6">
                        <select class="form-control" name="canEnroll" id="canEnroll">
                        <#if updateColourful??>
                            <option value="0" <#if updateColourful.canEnroll==0>selected</#if>>隐藏</option>
                            <option value="1" <#if updateColourful.canEnroll==1>selected</#if>>显示</option>
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
                    <#if updateColourful??>
                        <#if updateColourful.img??>
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
            </div>
        </div>
    </form>
</div>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/plugins/jasny/jasny-bootstrap.min.js"></script>
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
        var title = $("#title").val();
        var description = $("#description").val();
        var link = $("#link").val();
        var type = $("#type").val();
        var canEnroll = $("#canEnroll").val();
        var limitNum = $("#limitNum").val();
        link = encodeURIComponent(link);
        description = encodeURIComponent(description);
    <#if updateColourful??>
        var url = "updateInfo.do?tid=${updateColourful.tid}"+"&title="+title+"&description="+description+"&link="+link
                +"&type=7&canEnroll="+canEnroll+"&limitNum="+limitNum;
    <#else>
        var url = "addInfo.do?title="+title+"&description="+description+"&link="+link
                +"&type=7&canEnroll="+canEnroll+"&limitNum="+limitNum;
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
    function picChange(){
        $("#change").show();
    }
</script>
</body>
</html>



