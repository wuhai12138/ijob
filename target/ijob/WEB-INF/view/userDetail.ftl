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
            <input id="tid" name="tid" <#if updateUser??>value="${updateUser.tid}"</#if> hidden>
            <div class="col-xs-12">
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">用户名：</label>
                    <div class="col-xs-6">
                        <input type="text" name="userName" id="userName" class="form-control" <#if updateUser?exists>value="${updateUser.userName!''}"</#if>>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">密码：</label>
                    <div class="col-xs-6">
                        <input type="text" name="password" id="password" class="form-control" <#if updateUser??>value="${updateUser.password!''}"</#if>>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">学院：</label>
                    <div class="col-xs-6">
                        <select class="form-control" name="schoolId" id="schoolId">
                            <option>请选择</option>
                        <#list schoolList as list>
                            <#if updateUser?exists>
                                <#if list.tid?c == updateUser.schoolId>
                                    <option value="${list.tid?c}" selected="selected">${list.name}</option>
                                <#else>
                                    <option value="${list.tid?c}">${list.name}</option>
                                </#if>
                            <#else>
                                <option value="${list.tid?c}">${list.name}</option>
                            </#if>
                        </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">权限：</label>
                    <div class="col-xs-6">
                        <select class="form-control" name="auth" id="auth">
                            <#if updateUser??>
                                <option value="1" <#if updateUser.auth==1>selected</#if>>超级管理员</option>
                                <option value="2" <#if updateUser.auth==2>selected</#if>>就业指导教师</option>
                                <option value="3" <#if updateUser.auth==3>selected</#if>>创新创业教师</option>
                                <option value="4" <#if updateUser.auth==4>selected</#if>>心理指导教师</option>
                                <option value="5" <#if updateUser.auth==5>selected</#if>>会议室管理员</option>
                                <option value="6" <#if updateUser.auth==6>selected</#if>>财务单据审核员</option>
                            <#else>
                                <option value="1" selected>超级管理员</option>
                                <option value="2">就业指导教师</option>
                                <option value="3">创新创业教师</option>
                                <option value="4">心理指导教师</option>
                                <option value="5">会议室管理员</option>
                                <option value="6">财务单据审核员</option>
                            </#if>
                        </select>
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
<script src="js/plugins/layer/layer.min.js"></script>
<script src="js/jquery.form.js"></script>
<script src="js/jquery.js"></script>
<script>
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    //关闭iframe
    $('#closeIframe').click(function(){
        parent.layer.close(index);
    });

    function saveOrder() {
        var tid = $("#tid").val();
        var url="";
        if(tid==''){
            url="addUser.do";
        }else{
            url="updateUser.do";
        }
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
                }else if(result.status==1002){
                    parent.layer.msg('用户名已存在', {
                        shade: 0.3,
                        icon: 1,
                        time: 1500 //2秒关闭（如果不配置，默认是3秒）
                    }, function(){
                        parent.location.reload();
                    });
                }else if(result==500){
                    parent.layer.msg('访问出错', {
                        shade: 0.3,
                        icon: 1,
                        time: 1500 //2秒关闭（如果不配置，默认是3秒）
                    }, function(){
                        parent.location.reload();
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



