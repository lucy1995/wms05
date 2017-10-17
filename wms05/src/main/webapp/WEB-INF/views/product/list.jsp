<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/js/fancybox/jquery.fancybox.css" media="screen" />
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.source.js?skin=blue"></script>
<script type="text/javascript" src="/js/fancybox/jquery.fancybox.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
	<script type="text/javascript">
        $(function () {
            $('.fancybox').fancybox();
        })
	</script>
<title>WMS-商品管理</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/common_msg.jsp"/>
	<s:form id="searchForm" namespace="/" action="product" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							编码/名称
							<s:textfield name="qo.keyword" class="ui_input_txt02" />
							品牌
							<s:select name="qo.brand_id" list="#brands" listKey="id" listValue="name"
									  headerKey="-1" headerValue="全部" class="ui_select01"/>
						</div>
						<div id="box_bottom">
							<input type="submit" value="查询" class="ui_input_btn01"/>
							<s:url namespace="/" action="product_input" var="#inputUrl"/>
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
							<th>货品图片</th>
							<th>货品名称</th>
							<th>货品编码</th>
							<th>货品品牌</th>
							<th>成本价格</th>
							<th>销售价格</th>
							<th>操作</th>
						</tr>
						<tbody>
							<s:iterator value="#pageResult.listData">
								<tr>
									<td><input type="checkbox" name="IDCheck" class="acb" /></td>
									<td>
										<a class="fancybox"
										   title="<s:property value='name'/>"
										   href="<s:property value='imagePath'/>">
											<img src="<s:property value='smallImagePath'/>" width='80px'>
										</a>
									</td>
									<td><s:property value="name"/></td>
									<td><s:property value="sn"/></td>
									<td><s:property value="brand.name"/></td>
									<td><s:property value="costPrice"/></td>
									<td><s:property value="salePrice"/></td>
									<td>
										<s:a namespace="/" action="product_input">修改
											<s:param name="product.id" value="id"/>
											<s:param name="qo.currentPage" value="qo.currentPage"/>
										</s:a>|
										<s:url namespace="/" action="product_delete" var="#deleteUrl">
											<s:param name="product.id" value="id"/>
										</s:url>
										<a href="javascript:;" class="btn_delete" data-url="<s:property value='#deleteUrl'/>">删除</a>
									</td>
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
