<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.source.js?skin=blue"></script>
<script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<script type="text/javascript">
	$(function () {
		$("[name='qo.beginDate']").click(function () {
			WdatePicker({
                maxDate:$("[name='qo.endDate']").val()||new Date()
            });
        });
		$("[name='qo.endDate']").click(function () {
			WdatePicker({
                minDate:$("[name='qo.beginDate']").val(),
                maxDate:new Date()
            });
        });
    })
</script>
<title>WMS-出库单管理</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/common_msg.jsp"/>
	<s:form id="searchForm" namespace="/" action="stockOutcomeBill" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_bottom">
							<s:url namespace="/" action="stockOutcomeBill_input" var="#inputUrl"/>
							<input type="button" value="新增" class="ui_input_btn01 btn_input"
								   data-url="<s:property value='#inputUrl'/>"/>
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" class="all" /></th>
							<th>出库单编号</th>
							<th>业务时间</th>
							<th>仓库</th>
							<th>客户</th>
							<th>出库总数量</th>
							<th>出库总金额</th>
							<th>录出人</th>
							<th>审核人</th>
							<th>审核状态</th>
							<th>操作</th>
						</tr>
						<tbody>
							<s:iterator value="#pageResult.listData">
								<tr>
									<td><input type="checkbox" name="IDCheck" class="acb" /></td>
									<td><s:property value="sn"/></td>
									<s:date name="vdate" format="yyyy-MM-dd" var="#formatDate"/>
									<td><s:property value="#formatDate"/></td>
									<td><s:property value="depot.name"/></td>
									<td><s:property value="client.name"/></td>
									<td><s:property value="totalNumber"/></td>
									<td><s:property value="totalAmount"/></td>
									<td><s:property value="inputUser.name"/></td>
									<td><s:property value="auditor.name"/></td>
									<s:if test="status==0">
										<td><span style="color: green">未审核</span></td>
										<td>
											<s:url namespace="/" action="stockOutcomeBill_audit" var="#auditUrl">
												<s:param name="stockOutcomeBill.id" value="id"/>
											</s:url>
											<a href="javascript:;" class="btn_audit" data-url="<s:property value='#auditUrl'/>">审核</a>
											<s:a namespace="/" action="stockOutcomeBill_input">修改
												<s:param name="stockOutcomeBill.id" value="id"/>
												<s:param name="qo.currentPage" value="qo.currentPage"/>
											</s:a>|
											<s:url namespace="/" action="stockOutcomeBill_delete" var="#deleteUrl">
												<s:param name="stockOutcomeBill.id" value="id"/>
											</s:url>
											<a href="javascript:;" class="btn_delete" data-url="<s:property value='#deleteUrl'/>">删除</a>
										</td>
									</s:if>
									<s:else>
										<td><span style="color: red">已审核</span></td>
										<td>
											<s:a namespace="/" action="stockOutcomeBill_show">查看
												<s:param name="stockOutcomeBill.id" value="id"/>
											</s:a>
										</td>
									</s:else>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<jsp:include page="/WEB-INF/views/common/common_page.jsp"/>
			</div>
		</div>
	</s:form>
</body>
</html>
