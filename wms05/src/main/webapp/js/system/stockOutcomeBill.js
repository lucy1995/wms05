$(function () {
    $(".appendRow").on("click",function () {
        var cloneTr = $("#edit_table_body tr:first").clone(true);
        cloneTr.find("[tag=name],[tag=pid],[tag=salePrice],[tag=number],[tag=remark]").val("");
        cloneTr.find("[tag=brand],[tag=amount]").html("");
        $("#edit_table_body").append(cloneTr);
    })
    $(".removeItem").on("click",function () {
        var currentTr = $(this).closest("tr");
        if ($("#edit_table_body tr").size()>1){
            currentTr.remove();
        }
        else {
            currentTr.find("[tag=name],[tag=pid],[tag=salePrice],[tag=number],[tag=remark]").val("");
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
                currentTr.find("[tag=salePrice]").val(productJson.salePrice);
            }
        })
    })
    $("[tag=salePrice],[tag=number]").change(function () {
        var currentTr = $(this).closest("tr");
        var salePrice = currentTr.find("[tag=salePrice]").val();
        var number = currentTr.find("[tag=number]").val();
        if (salePrice&&number){
            currentTr.find("[tag=amount]").html((salePrice*number).toFixed(2));
        }
    });

    $("#editForm").submit(function () {
        $.each($("#edit_table_body tr"),function (index, item) {
            $(item).find("[tag=pid]").prop("name","stockOutcomeBill.items["+index+"].product.id");
            $(item).find("[tag=salePrice]").prop("name","stockOutcomeBill.items["+index+"].salePrice");
            $(item).find("[tag=number]").prop("name","stockOutcomeBill.items["+index+"].number");
            $(item).find("[tag=remark]").prop("name","stockOutcomeBill.items["+index+"].remark");
        })
    });
})