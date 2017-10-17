//禁用提交数组的时候带有中括号
$.ajaxSettings.traditional = true;
//input
$(function () {
    $(".btn_input").click(function () {
        window.location.href=$(this).data("url");
    });
});
//delete
$(function () {
    $(".btn_delete").click(function () {
        var deteleUrl = $(this).data("url");
        showDialogMsg("","亲,您确定要删除么?",function () {
            $.get(deteleUrl,function (data) {
                showDialogMsg("",data,function () {
                    window.location.reload();
                })
            });
        },true)
    });
});
function showDialogMsg(title,content,ok,cancel) {
    $.dialog({
        title:"温馨提示",
        content:content||"",
        ok:ok||true,
        cancel:cancel||false,
        icon:'face-smile'
    })
}
//btn_page
$(function () {
    $(".btn_page").click(function () {
        var pageNo = $(this).data("page")||$("#currentPage").val();
        $("#currentPage").val(pageNo);
        $("#searchForm").submit();
    });
    $("[name='qo.pageSize']").change(function () {
        $("#currentPage").val(1);
        $("#searchForm").submit();
    });
})
//reload
$(function () {
    $(".btn_reload").click(function () {
        var reloadUrl = $(this).data("url");
        showDialogMsg("","亲,您确定要重新加载么?",function () {
            $.get(reloadUrl,function (data) {
                showDialogMsg("",data,function () {
                    window.location.reload();
                })
            });
        },true)
    });
});
//btn_batchDelete
$(function () {
    $(".btn_batchDelete").click(function () {
        var batchDeleteUrl = $(this).data("url");
        var ids =$.map($(".acb:checked"),function (item, index) {
            return $(item).data("id");
        })
        if (ids.length==0){
            showDialogMsg("","请选择要批量删除的对象",true);
        }
        showDialogMsg("","您确定要批量删除么?",function () {
            $.get(batchDeleteUrl,{ids:ids},function (data) {
                showDialogMsg("",data,function () {
                    window.location.reload();
                })
            })
        },true);
    });
})
//选择框
$(function () {
    $(".all").change(function () {
        $(".acb").prop("checked",$(this).prop("checked"));
    });
    $(".acb").prop("checked",false);
    $(".acb").click(function () {
        if($(".acb:checked").size()==$(".acb").size()){
            $(".all").prop("checked",true);
        }
        if($(".acb:checked").size()==0){
            $(".all").prop("checked",false);
        }
    });
})
//审核
$(function () {
    $(".btn_audit").click(function () {
        var auditUrl = $(this).data("url");
        showDialogMsg("","亲,您确定要审核么?",function () {
            $.get(auditUrl,function (data) {
                showDialogMsg("",data,function () {
                    window.location.reload();
                })
            });
        },true)
    });
});
























