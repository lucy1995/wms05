$(function () {
    $("#select").click(function () {
        $(".permissions_all option:selected").appendTo($(".permissions_selected"))
    });
    $("#selectAll").click(function () {
        $(".permissions_all option").appendTo($(".permissions_selected"))
    });
    $("#deselect").click(function () {
        $(".permissions_selected option:selected").appendTo($(".permissions_all"))
    });
    $("#deselectAll").click(function () {
        $(".menus_selected option").appendTo($(".menus_all"))
    });
    $("#mselect").click(function () {
        $(".menus_all option:selected").appendTo($(".menus_selected"))
    });
    $("#mselectAll").click(function () {
        $(".menus_all option").appendTo($(".menus_selected"))
    });
    $("#mdeselect").click(function () {
        $(".menus_selected option:selected").appendTo($(".menus_all"))
    });
    $("#mdeselectAll").click(function () {
        $(".menus_selected option").appendTo($(".menus_all"))
    });
})
$(function () {
    $("#editForm").submit(function () {
        $(".permissions_selected option").prop("selected",true);
        $(".menus_selected option").prop("selected",true);
    });
});
$(function () {
    var selectedIds = $.map($(".permissions_selected option"),function (item,index) {
        return $(item).val();
    });
    $.each($(".permissions_all option"),function (index,item) {
        //>-1 表示当前option的value 在被选中的数组中,则移除
        if($.inArray($(item).val(),selectedIds)>-1){
            $(item).remove();
        }
    })
});
$(function () {
    var selectedIds = $.map($(".menus_selected option"),function (item,index) {
        return $(item).val();
    });
    $.each($(".menus_all option"),function (index,item) {
        //>-1 表示当前option的value 在被选中的数组中,则移除
        if($.inArray($(item).val(),selectedIds)>-1){
            $(item).remove();
        }
    })
});