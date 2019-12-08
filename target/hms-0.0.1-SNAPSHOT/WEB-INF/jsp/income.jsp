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
<title>Expense</title>
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
	<form action="addNewIncome" method="POST">
		<table align="center" border="0" width="800px">
			<tr>
				<td colspan="3" align="center"><font size="10">Home
						Management System</font></td>
			</tr>
			<tr height="80">
				<td><a href="index.jsp">Index</a></td>
				<td align="center"><a href="${ctp }/investment">Investment</a></td>
				<td><a href="expense">Expense</a></td>
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
				<td><input type="text" name="amount" id="amount"/></td>
			</tr>
			<tr>
				<td nowrap><b>Income Date:</b></td>
				<td><input type="text" name="incomedate" id="incomedate" onfocus="createTime();"/></td>
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
		<hr/>
	<div>
	<table width="800px" align="center">
		<tr>
			<td colspan="3" align="center"><b>Query</b> </td>
		</tr>
		<tr>
			<td>Input date(yyyy-MM-dd):</td>
			<td><input type="text" name="querydt" id="querydt" onfocus="createTime();"/></td>
			<td></td>
		</tr>
		<tr>
			<td>Category:</td>
			<td>
				<select id="querycat" >
					<option value=""></option>
					<option value="Salary">Salary</option>
					<option value="Investment">Investment</option>
					<option value="Others">Others</option>
				</select>
			</td>
			<td><input type="button" id="query" value="query"></td>
		</tr>
		
	</table>
	<hr/>
	<form action='${ctp}/ajaxIncomeDel' method='post'><span><input type='button' id ='del' value='Delete'/></span>
	<div id="displayIncome" align="left" style="margin-left:80px" ></div>
	</form>
	</div>
</body>
<script type="text/javascript">
	$('#submit').click(	
		function(){
			var cat=$("#category option:selected").val();
			var amt=$("#amount").val();
			if(amt==null || amt==""){
				alert("Amount不能为空！")
				return;
			}
			var createddate=$("#incomedate").val(); 
			var remark=$("#remark").val();
			if(remark==null)
				remark="";
			$.ajax({
				url:"${ctp}/addNewIncome",
				type:"POST",
				//async:false,
				data: {c:cat,am:amt,cd:createddate,re:remark},
				dataType:"JSON",
				success:function(data){
					$("#amount").val("");
					$("#incomedate").val("");
					$("#remark").val("");
					$("#info").empty();
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

<script type="text/javascript">
	$('#query').click(	
		function(){
			var dtData=$("#querydt").val();
			if(dtData==null)
				dtData="";
			var catData=$("#querycat option:selected").val();
			if(catData==null)
				catData="";
							
			$.ajax({
				url:"${ctp}/ajaxQueryIncome",
				type:"POST",
				data: {dt:dtData,cat:catData},
				dataType:"JSON",
				success:function(data){
					//console.log(data);
					$("#displayIncome").empty();
					$.each(data,function(index,item){
						$("#displayIncome").append("<div><input type='checkbox' name='incomeDel' value='"+item.id+"'/><b>Category:</b><font color='blue'> "+item.category+"</font>&nbsp;&nbsp;<b>Amount:</b> <font color='blue'>"+item.amount+"</font>&nbsp;&nbsp;<b>IncomeDate:&nbsp;</b><font color='blue'>"+item.createdTime+"</font>&nbsp;&nbsp;<b>Remark:</b> <font color='blue'>"+item.remark+"</font>&nbsp;&nbsp;<a href='${ctp}/incomeEdit/"+item.id+"'>Edit</a><br/>");
					}					
					);
				}
			});
			return false;
		}
	);
</script>

<script type="text/javascript">
	$('#del').click(	
		function(){
			var dels=[];
			$('input[name=incomeDel]:checked').each(
				function(i){
					dels[i]=$(this).val();
				}
			);
			if(dels.length==0){
				alert("No item is checked!");
				return false;
			}
			var dtData=$("#querydt").val();
			if(dtData==null)
				dtData="";
			var catData=$("#querycat option:selected").val();
			if(catData==null)
				catData="";
				
			$.ajax({
				url:"${ctp}/ajaxDelIncome",
				type:"POST",
				traditional:true, 
				data:{d:dels,dt:dtData,cat:catData},
				dataType:"JSON",
				success:function(data){
					//console.log(data);
					$("#displayIncome").empty();
					$.each(data,function(index,item){
						$("#displayIncome").append("<div><input type='checkbox' name='incomeDel' value='"+item.id+"'/><b>Category:</b><font color='blue'> "+item.category+"</font>&nbsp;&nbsp;<b>Amount:</b> <font color='blue'>"+item.amount+"</font>&nbsp;&nbsp;<b>IncomeDate:&nbsp;</b><font color='blue'>"+item.createdTime+"</font>&nbsp;&nbsp;<b>Remark:</b> <font color='blue'>"+item.remark+"</font>&nbsp;&nbsp;<a href='${ctp}/incomeEdit/"+item.id+"'>Edit</a><br/>");
					}					
					);
				}
			});
			return false;
		}
	);
</script>
</html>