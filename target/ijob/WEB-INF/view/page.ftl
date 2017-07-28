<div class="row">
    <div class="col-sm-6" style="width:40%">
    <#if total==0>
        <div class="dataTables_info pull-left">共0/${total} 页</div>
    <#else>
        <div class="dataTables_info pull-left">共${currentPage}/${total} 页</div>
    </#if>
        <div class="dataTables_length pull-left form-inline" style="margin: 3px 0 0 20px;">
            <label>每页
                <select name="row" id="row" aria-controls="editable" class="form-control input-sm" onchange="goRow()">
                <#if row == 10>
                    <option value="10" selected="selected">10</option>
                <#else>
                    <option value="10">10</option>
                </#if>
                <#if row == 25>
                    <option value="25" selected="selected">25</option>
                <#else>
                    <option value="25">25</option>
                </#if>
                <#if row == 50>
                    <option value="50" selected="selected">50</option>
                <#else>
                    <option value="50">50</option>
                </#if>
                <#if row == 100>
                    <option value="100" selected="selected">100</option>
                <#else>
                    <option value="100">100</option>
                </#if>
                </select> 条记录</label>
        </div>
    </div>
<#if total == 0 >

<#else>
    <div class="col-sm-6" style="width:40%">
        <div class="dataTables_paginate paging_simple_numbers" id="editable_paginate">
            <input type="hidden" value="${currentPage}" id="currentPage">
            <ul class="pagination">
                <#if currentPage==1>
                    <li class="paginate_button previous disabled" aria-controls="editable" tabindex="0" id="editable_previous"><a>上一页</a></li>
                <#else>
                    <li class="paginate_button previous" aria-controls="editable" tabindex="0" id="editable_previous"><a href="javascript:void(0);" onclick="showLastPage()">上一页</a></li>
                </#if>
                <!--中间情况-->
                <#if (currentPage-2 gte 1) && (currentPage+2 lte total) >
                    <#list currentPage-2..currentPage+2 as list>
                        <#if list == currentPage>
                            <li class="paginate_button active disabled" aria-controls="editable" tabindex="0"><a>${list}</a></li>
                        <#else>
                            <li class="paginate_button " aria-controls="editable" tabindex="0"><a href="#" onclick="showLastPage(${list})">${list}</a></li>
                        </#if>
                    </#list>
                <#else>
                    <!--当前页太小-->
                    <#if currentPage-2 lte 1>
                        <#if currentPage+2 lte total>
                            <#list 1..currentPage+2 as list>
                                <#if list == currentPage>
                                    <li class="paginate_button active disabled" aria-controls="editable" tabindex="0"><a>${list}</a></li>
                                <#else>
                                    <li class="paginate_button " aria-controls="editable" tabindex="0"><a href="#" onclick="showLastPage(${list})">${list}</a></li>
                                </#if>
                            </#list>
                        <#else>
                            <#list 1..total as list>
                                <#if list == currentPage>
                                    <li class="paginate_button active disabled" aria-controls="editable" tabindex="0"><a>${list}</a></li>
                                <#else>
                                    <li class="paginate_button " aria-controls="editable" tabindex="0"><a href="#" onclick="showLastPage(${list})">${list}</a></li>
                                </#if>
                            </#list>
                        </#if>
                    <#else>
                        <!--当前页太大-->
                        <#list currentPage-2..total as list>
                            <#if list == currentPage>
                                <li class="paginate_button active disabled" aria-controls="editable" tabindex="0"><a>${list}</a></li>
                            <#else>
                                <li class="paginate_button " aria-controls="editable" tabindex="0"><a href="#" onclick="showLastPage(${list})">${list}</a></li>
                            </#if>
                        </#list>
                    </#if>
                </#if>

                <#if currentPage == total >
                    <li class="paginate_button next disabled" aria-controls="editable" tabindex="0" id="editable_next"><a>下一页</a></li>
                <#else>
                    <li class="paginate_button next" aria-controls="editable" tabindex="0" id="editable_next"><a href="javascript:void(0);" onclick="showNextPage()">下一页</a></li>
                </#if>
            </ul>
        </div>
    </div>
    <div class="col-sm-6" style="width:20%">
        <input type="text" class="input-sm" aria-controls="editable" style="width:30px" id="goPage"/>
        <a href="javascript:void(0);" class="btn btn-primary " onclick="goPage()">GO</a>
    </div>
</#if>
</div>
<script src="js/jquery.min.js"></script>
<script>
    var pageName = $("#ListPageName").val();
    var schoolId = $("#schoolId").val();
    var courseId = $("#courseId").val();
    var row = $("#row").val();
    var name = $("#name").val();
    var handlerId = $("#handlerId").find("option:selected").val();
    //var search = $("#searchWhere").val();
    if(typeof(schoolId)=="undefined"){
        schoolId = 0;
    }
    if(typeof(name)=="undefined"){
        name = "";
    }
    if(typeof(courseId)=="undefined"){
        courseId=0;
    }
    if(typeof(handlerId)=="undefined"){
        handlerId=0;
    }

    function showLastPage(p) {
        var search = where();
        if (typeof(p) != "undefined") {
            //通过点击数字按钮翻页
            this.location.replace(pageName+"?currentPage=" + p + "&row=" + row + search);
        } else {
            p = $("#currentPage").val() - 1;
            this.location.replace(pageName+"?currentPage=" + p + "&row=" + row + search);
        }
    }
    function goPage() {
        var search = where();
        var goPage = $("#goPage").val();
        if (isNaN(goPage)) {
            alert("请输入数字");
        } else {
            this.location.replace(pageName+"?currentPage=" + goPage + "&row=" + row + search);
        }
    }
    function showNextPage() {
        var search = where();
        var currentPage = $("#currentPage").val();
        var p = parseInt(currentPage) + 1;
        this.location.replace(pageName+"?currentPage=" + p + "&row=" + row + search);
    }
    $("#goPage").keyup(function () {
        if (event.keyCode == 13) {
            goPage(where);
        }
    });

    function goRow(){
        var search = where();
        //获取选择值
        var row = $("#row option:selected").text();
        var currentPage = $("#currentPage").val();
        //发送请求
        self.location.href=pageName+"?currentPage=" + 1 + "&row=" + row+ search;
    }
</script>