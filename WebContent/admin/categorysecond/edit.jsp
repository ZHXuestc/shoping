<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
	</HEAD>
	<body>
		<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/categorySecond_update.action" method="post" enctype="multipart/form-data">
			<input type="hidden" name="csid" value="<s:property value="model.csid"/>"/>
			&nbsp;
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26">
						<STRONG>编辑二级分类</STRONG>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						二级分类名称：
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
						<input type="text" name="csname" value="<s:property value="categorySecondExit.csname"/>" id="userAction_save_do_logonName" class="bg"/>
					</td>
				</tr>

				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						所属一级分类：
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
					<%// 这个cid是页面上添加的二级分类所属地一级分类的cid ,name="cid"对应的是option value="<s:property value="#c.cid"，要通过name传到action中 %>
						<select name="cid">
						  <s:iterator value="cList" var="c">
						    <option value="<s:property value="#c.cid"/>"><s:property value="#c.cname"/></option>
						  </s:iterator>
						</select>
					</td>
				</tr>
				
				<TR>
					<td align="center" colSpan="4" class="sep1">
						<img src="${pageContext.request.contextPath}/images/shim.gif">
					</td>
				</TR>


				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgColor="#f5fafe" colSpan="4">
						<button type="submit" id="userAction_save_do_submit"  value="确定" class="button_ok">
							&#30830;&#23450;
						</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
						<span id="Label1"></span>
					</td>
				</tr>
			</table>
		</form>
	</body>
</HTML>