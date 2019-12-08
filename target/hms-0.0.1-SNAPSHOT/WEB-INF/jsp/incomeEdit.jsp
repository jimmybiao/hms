<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
<%
	pageContext.setAttribute("ctp", request.getContextPath());
	pageContext.setAttribute("basePath", request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort());
 %>
<script type="text/javascript" src="${basePath }/js/jquery-2.1.4.js"></script>
<script type="text/javascript">
		
 	function valid(){
 		var val=$("#amount").val();
 		if(val=='' || $.trim(val)==''){
 			alert('Amount不能为空！')
 			return false ;
 		}
 		return true;
 	}
 
 </script>
 
 <style>
		.inputdiv{
			margin:0 auto;
			margin-top:20px;
			position:relative;
		}
</style>
<title>Income Edit</title>
</head>
<body>
<div class="inputdiv">
	<form action="${ctp}/incomeUpdate/${requestScope.id}" method="POST">
		<table align="center" border="0" width="800px">
			<tr>
				<td colspan="3" align="center"><font size="10">Home
						Management System</font></td>
			</tr>
			<tr height="80">
				<td><a href="index.jsp">Index</a></td>
				<td align="center"><a href="${basePath}/investment">Investment</a></td>
				<td><a href="${basePath}/income">Income</a></td>
			</tr>
			<tr>
				<td width="10%"><b>Category:</b></td>
				<td colspan="2"><select id="category" >
					<option value="Salary">Salary</option>
					<option value="Investment">Investment</option>
					<option value="Others">Others</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="3" height="10">
			</tr>
			
			<tr>
				<td><b>Amount:</b></td>
				<td><input type="text" name="amount" id="amount" value="${requestScope.amt} "/></td>
			</tr>
			<tr>
			<td><b>Created Time:</b></td>
			<td colspan="2"><label>${requestScope.dt}</label></td>
			</tr>
			
			<tr>
				<td nowrap><b>Remark:</b></td>
				<td colspan="2"><textarea rows="10" cols="50" name="remark"
						id="remark">${requestScope.remark}</textarea></td>
			</tr>
			<tr>
				<td colspan="3" height="20">
			</tr>
			<tr>
				<td colspan="3" align="center"><input type="button"
						value="Submit" id="update" /></td>
			</tr>
		</table>
	</form>
	</div>
	<div id="info" align="center"></div>
</body>
<script>
$("#category").val("${requestScope.cat}");
</script>

<script type="text/javascript">
	$('#update').click(	
		function(){
			var catData=$("#category option:selected").val();
			var amount=$("#amount").val();
			if(amount=='' || $.trim(amount)==''){
				alert('Amount不能为空！');
				return ;
 			}
 			if(parseFloat(amount)<1.0){
 				alert('Amount金额太小！');
 				return;
 			}
 				
 			var remark= $("#remark").val();
			$.ajax({
				url:"${ctp}/incomeUpdate/${requestScope.id}",
				type:"POST",
				data:{cat:catData,amt:amount,re:remark},
				dataType:"JSON",
				success:function(data){
					//console.log(data);
					$("#info").empty();
					$("#info").append("<font color='red'>The record has been updated successfully!</font>");			
				},
				error:function(jqXHR, textStatus, errorThrown){
					$("#info").empty();
					$("#info").append("<font color='red'>The record failed to save!</font>");
				}
			});
			return false;
		}
	);
</script>
</html>