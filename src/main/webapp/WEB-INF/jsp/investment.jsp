<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<%
	pageContext.setAttribute("ctp", request.getContextPath());
 %>
 <script type="text/javascript">
 
 function createTime() {
			WdatePicker({
				dateFmt : 'yyyy-MM-dd'
			});
		}
		
 	function valid(){
 		var val=$("#amount").val();
 		if(val=='' || $.trim(val)==''){
 			alert('Amount不能为空！')
 			return false ;
 		}
 		return true;
 	}
 
 </script>
<title>Investment</title>
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
	<form action="addNewInvestment" method="POST">
		<table align="center" border="0" width="800px">
			<tr>
				<td colspan="3" align="center"><font size="10">Home
						Management System</font></td>
			</tr>
			<tr height="80">
				<td><a href="index.jsp">Index</a></td>
				<td colspan="2" ></td>
			</tr>
			<tr>
				<td width="10%"><b>Category:</b></td>
				<td colspan="2"><select id="category" >
					<option value="Pension Fund">Pension Fund</option>
					<option value="Education Fund">Education Fund</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="3" height="10">
			</tr>
			<tr>
				<td><b>SubCategory:</b></td>
				<td colspan="2"><select id="subcategory">
					<option value="Money Fund">Money Fund</option>
					<option value="Bond Fund">Bond Fund</option>
					<option value="Stock Fund">Stock Fund</option>
					<option value="Stock">Stock</option>
					<option value="Bulk Commodity Fund">Bulk Commodity Fund</option>
					<option value="Gold Fund">Gold Fund</option>
				</select></td>
			</tr>
			<tr>
				<td><b>Amount:</b></td>
				<td><input type="text" name="amount" id="amount"/></td>
			</tr>
			<tr>
				<td nowrap><b>Investment Date:</b></td>
				<td><input type="text" name="investdate" id="investdate" onfocus="createTime();"/></td>
			</tr>
			<tr>
				<td nowrap><b>Remark:</b></td>
				<td colspan="2"><textarea rows="10" cols="50" name="remark"
						id="remark"></textarea></td>
			</tr>
			<tr>
				<td colspan="3" height="20">
			</tr>
			<tr>
				<td colspan="3" align="center"><input type="button" id="submit"
					value="Submit"/></td>
			</tr>
		</table>
	</form>
	</div>
	<div id="info" align="center"></div>
</body>
<script type="text/javascript">
	$('#submit').click(	
		function(){
			var cat=$("#category option:selected").val();
			var subcat=$("#subcategory option:selected").val();
			var amt=$("#amount").val();
			if(amt==null || amt==""){
				alert("Amount不能为空！")
				return;
			}
			var investdate=$("#investdate").val(); 
			var remark=$("#remark").val();
			if(remark==null)
				remark="";
			$.ajax({
				url:"${ctp}/addNewInvestment",
				type:"POST",
				data: {c:cat,sc:subcat,am:amt,indt:investdate,re:remark},
				dataType:"JSON",
				success:function(data){
					$("#amount").val("");
					$("#investdate").val("");
					$("#remark").val("");
					$("#info").empty();
					$.each(data,function(index,item){
						
					});	
					
					$("#info").append("<font color='red'>1 record saved successfully!</font></br>");	
				},
				error:function(jqXHR, textStatus, errorThrown){
					$("#info").empty();
					$("#info").append("<span>Record failed to save!</span>");
				}
			});
			return false;
		}
	);
</script>
</html>