<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>职位详情</title>
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
            <input id="tid" name="tid" <#if updateJob??>value="${updateJob.tid}"</#if> hidden>
            <div class="col-xs-12">
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">职位名称：</label>
                    <div class="col-xs-6">
                        <input type="text" name="name" id="name" class="form-control" <#if updateJob?exists>value="${updateJob.name!''}"</#if>>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">企业名称：</label>
                    <div class="col-xs-6">
                        <input type="text" name="company" id="company" class="form-control" <#if updateJob??>value="${updateJob.company!''}"</#if>>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">人数限制：</label>
                    <div class="col-xs-6">
                        <input type="text" name="limitNum" id="limitNum" class="form-control" <#if updateJob??>value="${updateJob.limitNum!''}"</#if>>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">薪资：</label>
                    <div class="col-xs-6">
                    <div class="input-group m-b">
                        <span class="input-group-addon">￥</span>
                        <input type="text" name="salary" id="salary" class="form-control" <#if updateJob??>value="${updateJob.salary}"</#if>>
                        <span class="help-block m-b-none"></span>
                    </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">描述：</label>
                    <div class="col-xs-6">
                        <textarea name="description" id="description" class="form-control"><#if updateJob??>${updateJob.description!''}</#if></textarea>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">要求：</label>
                    <div class="col-xs-6">
                        <textarea name="requirement" id="requirement" class="form-control"><#if updateJob??>${updateJob.requirement!''}</#if></textarea>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">联系方式：</label>
                    <div class="col-xs-6">
                        <input type="text" name="phone" id="phone" class="form-control" <#if updateJob??>value="${updateJob.phone!''}"</#if>>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">类型：</label>
                    <div class="col-xs-6">
                        <select class="form-control" name="type" id="type">
                            <#if updateJob??>
                                <option value="1" <#if updateJob.type==1>selected</#if>>职位信息</option>
                                <option value="2" <#if updateJob.type==2>selected</#if>>勤工俭学</option>
                            <#else>
                                <option value="1" selected>职位信息</option>
                                <option value="2">勤工俭学</option>
                            </#if>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">图片：</label>
                    <div class="col-xs-6">
                    <#if updateJob??>
                        <#if updateJob.image??>
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
        var salary = $("#salary").val();
//        var reg = new RegExp("^[0-9]*$");
//        if(!reg.test(salary)){
//            self.layer.msg('工资只能是数字', {
//                shade: 0.3,
//                icon: 1,
//                time: 1500 //2秒关闭（如果不配置，默认是3秒）
//            }, function(){
//            });
//            return;
//        }
        var name = $("#name").val();
        var company = $("#company").val();
        var description = $("#description").val();
        description = encodeURIComponent(description);
        var requirement = $("#requirement").val();
        requirement = encodeURIComponent(requirement);
        var phone = $("#phone").val();
        var type = $("#type").val();
        var limitNum = $("#limitNum").val();
        <#if updateJob??>
            var url = "updateJob.do?tid=${updateJob.tid}"+"&name="+name+"&company="+company+"&salary="+salary
                    +"&description="+description+"&requirement="+requirement+"&phone="+phone+"&type="+type+"&limitNum"+limitNum;
        <#else>
            var url = "addJob.do?name="+name+"&company="+company+"&salary="+salary+"&description="
                    +description+"&requirement="+requirement+"&phone="+phone+"&type="+type+"&limitNum="+limitNum;
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



