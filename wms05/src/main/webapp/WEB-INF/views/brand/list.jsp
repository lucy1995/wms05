<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.source.js?skin=blue"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>

<title>PSS-品牌管理</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<jsp:include page="../common/common_msg.jsp"/>
	<s:form id="searchForm" action="brand" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_bottom">
							<input type="button" value="新增" class="ui_input_btn01 btn_input"
								   data-url='<s:url namespace="/" action="brand_input"/>'/>
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>编号</th>
							<th>品牌编号</th>
							<th>品牌名称</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#pageResult.listData">
								<tr>
									<td><input type="checkbox" name="IDCheck" class="acb" data-oid="<s:property value="id"/>" /></td>
									<td><s:property value="id"/></td>
									<td><s:property value="sn"/></td>
									<td><s:property value="name"/></td>
									<td>
										<s:a namespace="/" action="brand_input">修改
											<s:param name="brand.id" value="id"/>
										</s:a>|
										<s:url namespace="/" action="brand_delete" var="deleteUrl">
											<s:param name="brand.id" value="id"/>
										</s:url>
										<a href="javascript:;" class="btn_delete" data-url="<s:property value="#deleteUrl"/>">删除</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<jsp:include page="../common/common_page.jsp"/>
			</div>
		</div>
	</s:form>
</body>
</html>
