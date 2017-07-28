<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>群组详情</title>
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
            <input id="id" name="id" <#if updateGroup??>value="${updateGroup.id?c}"</#if> hidden>
            <div class="col-xs-12">
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">群组名称：</label>
                    <div class="col-xs-6">
                    <#if updateGroup??>
                        <input type="text" name="groupname" id="groupname" class="form-control" <#if updateGroup??>value="${updateGroup.name!''}"</#if>>
                    <#else>
                        <input type="text" name="groupname" id="groupname" class="form-control"/>
                    </#if>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">群组描述：</label>
                    <div class="col-xs-6">
                    <#if updateGroup??>
                        <input type="text" name="description" id="description" class="form-control" <#if updateGroup??>value="${updateGroup.description!''}"</#if>>
                    <#else>
                        <input type="text" name="desc" id="desc" class="form-control"/>
                    </#if>
                        <span class="help-block m-b-none"></span>
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
        <#if updateGroup??>
        var url = "updateGroup.do";
        <#else>
        var url="addGroup.do";
        </#if>
        $.ajax({
            url:url,
            type:"post",
            dataType:"json",
            data:$('#form1').serialize(),
            success:function(result){
                //在父层弹出一个层
                if(result.status==200){
                    parent.layer.msg('保存成功', {
                        shade: 0.3,
                        icon: 1,
                        time: 1500 //2秒关闭（如果不配置，默认是3秒）
                    }, function(){
                        parent.location.reload();
                    });
                }else if(result.status==1005){
                    parent.layer.msg('环信保存不成功', {
                        shade: 0.3,
                        icon: 1,
                        time: 1500 //2秒关闭（如果不配置，默认是3秒）
                    }, function(){
                    });
                }else if(result.status==500){
                    parent.layer.msg('访问出错', {
                        shade: 0.3,
                        icon: 1,
                        time: 1500 //2秒关闭（如果不配置，默认是3秒）
                    }, function(){
                    });
                }
            },
            error:function(){
                alert("服务器紧张ING");
            }
        });
    }
</script>
</body>
</html>



