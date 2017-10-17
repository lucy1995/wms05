//============================图标面板切换=======================================
$(function () {
    $("#TabPage2 li").click(function () {
        $.each($("#TabPage2 li"),function (index,item) {
            $(item).removeClass("selected");
            $(item).find("img").prop("src","/images/common/"+($(this).index()+1)+".jpg");
        });
        $(this).addClass("selected");
        $(this).find("img").prop("src","/images/common/"+($(this).index()+1)+"_hover.jpg");
         //设置菜单名称切换
        $("#nav_module img").prop("src","/images/common/module_"+($(this).index()+1)+".png");
        //加载对应菜单
        loadMenu($(this).data("rootmenu"));
    });
});

//===========================系统菜单树====================
var setting = {
    async:{
        enable:true,
        url:"/systemMenu_queryMenuBySn.action",
        autoParam:["sn=qo.parentSn"]
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onClick: function (event, treeId, treeNode) {
            if(treeNode.action){
                $("#rightMain").prop("src","/"+treeNode.action+".action");
                $("#here_area").html("当前位置："+treeNode.getParentNode().name+"&nbsp;>&nbsp;"+treeNode.name);
            }
        }
    }
};
var business = [
    {id:1,pId:0,name:"业务模块", isParent:true,sn:"business" }
];
var system=[
    { id:2, pId:0, name:"系统模块", isParent:true,sn:"system"}
];
var chart=[
    { id:3, pId:0, name:"报表模块", isParent:true,sn:"chart"}
];
var zNodes ={
    "business":business,
    "system":system,
    "chart":chart
};


//加载系统菜单的方法
function loadMenu(parentSn) {
    $.fn.zTree.init($("#dleft_tab1"), setting, zNodes[parentSn]);
}
$(function(){
    loadMenu("business");
});
