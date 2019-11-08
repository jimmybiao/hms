<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
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
<title>Index</title>
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
	<form action="memo" method="POST">
		<table align="center" border="0" width="800px">
			<tr>
				<td colspan="3" align="center"><font size="10">Home
						Management System</font></td>
			</tr>
			<tr height="80">
				<td><a href="investment">Investment</a></td>
				<td colspan="2" ></td>
			</tr>
			<tr>
				<td width="10%"><b>Title:</b></td>
				<td colspan="2"><textarea rows="1" cols="50" name="title"
						id="title"></textarea></td>
			</tr>
			<tr>
				<td colspan="3" height="10">
			</tr>
			<tr>
				<td><b>Amount:</b></td>
				<td colspan="2"><input type="text" name="amount" /></td>
			</tr>
			<tr>
				<td colspan="3" height="10">
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
				<td colspan="3" align="center"><input type="submit"
					value="Submit" onclick="return valid();"/></td>
			</tr>
		</table>
	</form>
	</div>
	<hr/>
	<div>
	<table width="800px" align="center">
		<tr>
			<td colspan="3" align="center"><b>Query</b> </td>
		</tr>
		<tr>
			<td>Input date(yyyy-MM-dd):</td>
			<td><input type="text" name="dt" id="dt"/></td>
			<td><input type="button" id="query" value="query"></td>
		</tr>
	</table>
	<hr/>
	<form action='${ctp}/memoDel' method='post'><span><input type='button' id ='del' value='Delete'/></span>
	<div id="displayMemos" align="left" style="margin-left:80px" ></div>
	</form>
	</div>
</body>

<script type="text/javascript">
	$('#query').click(	
		function(){
			var dtData=$("#dt").val();
			if(dtData==null)
				dtData="";
			$.ajax({
				url:"${ctp}/ajaxQueryMemos",
				type:"POST",
				data: {q:dtData},
				dataType:"JSON",
				success:function(data){
					//console.log(data);
					$("#displayMemos").empty();
					$.each(data,function(index,item){
						$("#displayMemos").append("<div><input type='checkbox' name='memoDel' value='"+item.id+"'/><b>Title:</b><font color='blue'> "+item.title+"</font>  <b>Amount:</b> <font color='blue'>"+item.amount+"</font>  <b>Remark:</b> <font color='blue'>"+item.remark+"</font>&nbsp;&nbsp;<a href='${ctp}/memoEdit/"+item.id+"'>Edit</a><br/>");
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
			$('input[name=memoDel]:checked').each(
				function(i){
					dels[i]=$(this).val();
				}
			);
			if(dels.length==0){
				alert("No item is checked!");
				return false;
			}
			var dtData=$("#dt").val();
			if(dtData==null)
				dtData="";
			$.ajax({
				url:"${ctp}/ajaxDelMemo",
				type:"POST",
				data:{d:JSON.stringify(dels),q:dtData},
				dataType:"JSON",
				success:function(data){
					//console.log(data);
					$("#displayMemos").empty();
					//$("#displayMemos").append("<form action='${ctp}/memoDel' method='post'><input type='submit' value='Delete'/>")
					$.each(data,function(index,item){
						$("#displayMemos").append("<div><input type='checkbox' name='memoDel' value='"+item.id+"'/><b>Title:</b><font color='blue'> "+item.title+"</font>  <b>Amount:</b> <font color='blue'>"+item.amount+"</font>  <b>Remark:</b> <font color='blue'>"+item.remark+"</font>&nbsp;&nbsp;<a href='${ctp}/memoEdit/"+item.id+"'>Edit</a></div><br/>");
					}					
					);
					//$("#displayMemos").append("</form>")
				}
			});
			return false;
		}
	);
</script>
</html>