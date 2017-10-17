$(function () {
    $(".appendRow").on("click",function () {
        var cloneTr = $("#edit_table_body tr:first").clone(true);
        cloneTr.find("[tag=name],[tag=pid],[tag=costPrice],[tag=number],[tag=remark]").val("");
        cloneTr.find("[tag=brand],[tag=amount]").html("");
        $("#edit_table_body").append(cloneTr);
    })
    $(".removeItem").on("click",function () {
        var currentTr = $(this).closest("tr");
        if ($("#edit_table_body tr").size()>1){
            currentTr.remove();
        }
        else {
            currentTr.find("[tag=name],[tag=pid],[tag=costPrice],[tag=number],[tag=remark]").val("");
            currentTr.find("[tag=brand],[tag=amount]").html("");
        }
    })
    
    $(".searchproduct").on("click",function () {
        var url="product_selectProduct.action";
        var currentTr = $(this).closest("tr");
        $.dialog.open(url,{
            id:'productSelect',
            title:'商品选择',
            width:950,
            height:680,
            close:function () {
                var productJson = $.dialog.data("productJson");
                currentTr.find("[tag=name]").val(productJson.name);
                currentTr.find("[tag=pid]").val(productJson.pId);
                currentTr.find("[tag=brand]").html(productJson.brandName);
                currentTr.find("[tag=costPrice]").val(productJson.costPrice);
            }
        })
    })
    $("[tag=costPrice],[tag=number]").change(function () {
        var currentTr = $(this).closest("tr");
        var costPrice = currentTr.find("[tag=costPrice]").val();
        var number = currentTr.find("[tag=number]").val();
        if (costPrice&&number){
            currentTr.find("[tag=amount]").html((costPrice*number).toFixed(2));
        }
    });

    $("#editForm").submit(function () {
        $.each($("#edit_table_body tr"),function (index, item) {
            $(item).find("[tag=pid]").prop("name","orderBill.items["+index+"].product.id");
            $(item).find("[tag=costPrice]").prop("name","orderBill.items["+index+"].costPrice");
            $(item).find("[tag=number]").prop("name","orderBill.items["+index+"].number");
            $(item).find("[tag=remark]").prop("name","orderBill.items["+index+"].remark");
        })
    });
})