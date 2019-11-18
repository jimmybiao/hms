<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<%
	pageContext.setAttribute("ctp", request.getContextPath());
 %>
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<%
	pageContext.setAttribute("ctp", request.getContextPath());
 %>
 <script type="text/javascript">
 	function valid(){
 		var val=$("#title").val();
 		if(val=='' || $.trim(val)==''){
 			alert('title不能为空！')
 			return false ;
 		}
 		return true;
 	}
 </script>
<title>Documents</title>
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
	<form action="${ctp }/uploadFile" method="POST" enctype="multipart/form-data">
		<table align="center" border="0" width="800px">
			<tr>
				<td colspan="3" align="center"><font size="10">Home
						Management System</font></td>
			</tr>
			<tr height="80">
				<td><a href="${ctp }/investment">Investment</a></td>
				<td align="center"><a href="${ctp }/expense">Expense</a></td>
				<td align="center"><a href="${ctp }/income">Income</a></td>
				<td><a href="index.jsp">Memo</a></td>
			</tr>
			
			<tr>
				<td width="10%"><b>File:</b></td>
				<td colspan="2"><input type="file" name="hmsfile"/>&nbsp;&nbsp;
					<input type="submit" value="Upload" id="upload"/>
				</td>
			</tr>
			
		</table>
	</form>
	</div>
	<div align="center" id="info">
		<b><font color='red'>${msg }</font></b>
	</div>
	
	<hr/>
	
</body>

</html>