<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript">
    /*更新和修改的时候弹出页面信息*/
    $(function () {
        <s:if test="hasActionMessages()">
        var msg="<s:property value="actionMessages[0]"/>";
        showDialogMsg("",msg);
        </s:if>
        <s:if test="hasActionErrors()">
        msg="<s:property value="actionErrors[0]"/>";
        showDialogMsg("",msg);
        </s:if>
    })
</script>
