<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	pageContext.setAttribute("basePath", request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort());
 %>
<title>Success</title>
<style>
		.inputdiv{
			margin:0 auto;
			margin-top:20px;
			position:relative;
		}
</style>
</head>
<body>
<div class="inputdiv">
		<table align="center" border="0" width="800px">
			<tr>
				<td colspan="3" align="center"><font size="10">Home
						Management System</font></td>
			</tr>
			<tr height="50px"><td colspan="3"></td></tr>
			<tr><td colspan="3" align="center"><font size="6">操作成功！</font></td></tr>
			<tr><td colspan="3" height="50px"></td></tr>
			<tr><td colspan="3" align="center"><a href="${basePath }/index.jsp"><font size="6">返回首页</font></a></td></tr>
			</table>
			</div>

</body>
</html>