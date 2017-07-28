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
            <input id="tid" name="tid" <#if updateStudent??>value="${updateStudent.tid?c}"</#if> hidden>
            <div class="col-xs-12">
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">账号：</label>
                    <div class="col-xs-6">
                        <input type="text" name="code" id="code" class="form-control" <#if updateStudent?exists>value="${updateStudent.code!''}" readonly</#if>>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">昵称：</label>
                    <div class="col-xs-6">
                        <input type="text" name="nick" id="nick" class="form-control" <#if updateStudent??>value="${updateStudent.nick!''}"</#if>>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">密码：</label>
                    <div class="col-xs-6">
                        <input type="text" name="password" id="password" class="form-control" <#if updateStudent??>value="${updateStudent.password!''}"</#if>>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">邮箱：</label>
                    <div class="col-xs-6">
                        <input type="text" name="email" id="email" class="form-control" <#if updateStudent??>value="${updateStudent.email!''}"</#if>>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">姓名：</label>
                    <div class="col-xs-6">
                        <input type="text" name="name" id="name" class="form-control" <#if updateStudent??>value="${updateStudent.name!''}"</#if>>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">手机号码：</label>
                    <div class="col-xs-6">
                        <input type="text" name="phone" id="phone" class="form-control" <#if updateStudent??>value="${updateStudent.phone!''}"</#if>>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">个人介绍：</label>
                    <div class="col-xs-6">
                        <input type="text" name="introduction" id="introduction" class="form-control" <#if updateStudent??>value="${updateStudent.introduction!''}"</#if>>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">录取状态：</label>
                    <div class="col-xs-6">
                        <select class="form-control" name="status" id="status">
                        <#if updateStudent??>
                            <option value="0" <#if updateStudent.status=="0">selected</#if>>否</option>
                            <option value="1" <#if updateStudent.status=="1">selected</#if>>是</option>
                        <#else>
                            <option value="0" selected>否</option>
                            <option value="1">是</option>
                        </#if>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label" style="text-align:right">学院：</label>
                    <div class="col-xs-6">
                        <select class="form-control" name="schoolId" id="schoolId">
                            <option>请选择</option>
                        <#list schoolList as list>
                            <#if updateStudent?exists>
                                <#if list.tid?c == updateStudent.schoolId>
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
            url="addStudent.do";
        }else{
            url="updateStudent.do";
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
//                        parent.location.reload();
                    });
                }else if(result.status==1004){
                    parent.layer.msg('环信创建不成功', {
                        shade: 0.3,
                        icon: 1,
                        time: 1500 //2秒关闭（如果不配置，默认是3秒）
                    }, function(){
                        parent.location.reload();
                    });
                }else if(result.status==500){
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



