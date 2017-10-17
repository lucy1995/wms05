<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.source.js?skin=blue"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
	<script type="text/javascript">
        $(function () {
            $("[name='oqo.beginDate']").click(function () {
                WdatePicker({
                    maxDate:$("[name='oqo.endDate']").val()||new Date()
                });
            });
            $("[name='oqo.endDate']").click(function () {
                WdatePicker({
                    minDate:$("[name='oqo.beginDate']").val(),
                    maxDate:new Date()
                });
            });
        })
	</script>
<title>PSS-订货报表</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<jsp:include page="../common/common_msg.jsp"/>
	<s:form id="searchForm" action="chart_orderChart" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center ">
							<s:date name="oqo.beginDate" format="yyyy-MM-dd" var="beginDate"/>
							<s:date name="oqo.endDate" format="yyyy-MM-dd" var="endDate"/>
							业务时间
							<s:textfield class="ui_input_txt02 Wdate" name="oqo.beginDate" value="%{beginDate}"/>
							~
							<s:textfield class="ui_input_txt02 Wdate" name="oqo.endDate"  value="%{endDate}"/>
							货品
							<s:textfield class="ui_input_txt02" name="oqo.keyword" />
							供应商
							<s:select class="ui_select01" name="oqo.supplier_id" list="#suppliers"
									  listKey="id" listValue="name" headerKey="-1" headerValue="--请选择--">
							</s:select>
							品牌
							<s:select class="ui_select01" name="oqo.brand_id" list="#brands"
									  listKey="id" listValue="name" headerKey="-1" headerValue="--请选择--">
							</s:select>
							状态
							<s:select list='#{
								"e.name":"订货人员",
								"p.name":"货品名称",
								"s.name":"供应商",
								"b.name":"货品品牌",
								"DATE_FORMAT(bill.vdate,\'%Y-%m\')":"订货日期(月)",
								"DATE_FORMAT(bill.vdate,\'%Y-%m-%d\')":"订货日期(日)"
							}' name="oqo.groupType" class="ui_select01"></s:select>
						</div>
						<div id="box_bottom">
							<input type="submit" value="查询" class="ui_input_btn01 btn_input"/>
							<input type="button" value="新增" class="ui_input_btn01 btn_input"
								   data-url='<s:url namespace="/" action="orderBill_input"/>'/>
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>分组类型</th>
							<th>采购总数量</th>
							<th>采购总金额</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#mapList">
								<tr>
									<td><input type="checkbox" name="IDCheck" class="acb" data-oid="<s:property value="id"/>" /></td>
									<td><s:property value="groupType"/></td>
									<td><s:property value="totalNumber"/></td>
									<td><s:property value="totalAmount"/></td>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</s:form>
</body>
</html>
