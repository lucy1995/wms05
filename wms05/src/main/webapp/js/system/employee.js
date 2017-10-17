$(function () {
    $("#editForm").validate({
        rules:{
            'employee.name':{
                required:true,
                minlength:2
            },
            'employee.password':{
                required:true,
                minlength:2
            },
            'repassword':{
                required:true,
                equalTo:"#password"
            },
            'employee.email':{
                email:true
            },
            'employee.age':{
                required:true,
                range:[18,60]
            }
        },
        messages:{
            'employee.name':{
                required:'必填',
                minlength:'最小长度为2'
            },
            'employee.password':{
                required:'必填',
                minlength:'最小长度为2'
            },
            repassword:{
                required:'必填',
                equalTo:'两次密码必须相同'
            },
            'employee.email':{
                email:'必须满足email格式'
            },
            'employee.age':{
                required:'必填',
                range:'密码必须在{0}和{1}之间'
            }
        }
    })
})


$(function () {
    $("#select").click(function () {
        $(".roles_all option:selected").appendTo($(".roles_selected"))
    });
    $("#selectAll").click(function () {
        $(".roles_all option").appendTo($(".roles_selected"))
    });
    $("#deselect").click(function () {
        $(".roles_selected option:selected").appendTo($(".roles_all"))
    });
    $("#deselectAll").click(function () {
        $(".roles_selected option").appendTo($(".roles_all"))
    });
})
$(function () {
    $("#editForm").submit(function () {
        $(".roles_selected option").prop("selected",true);
    });
});
$(function () {
    var selectedIds = $.map($(".roles_selected option"),function (item,index) {
        return $(item).val();
    });
    $.each($(".roles_all option"),function (index,item) {
        //>-1 表示当前option的value 在被选中的数组中,则移除
        if($.inArray($(item).val(),selectedIds)>-1){
            $(item).remove();
        }
    })
});