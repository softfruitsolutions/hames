<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
	#div_form{
		margin-left: 30px;
		margin-right: 50px;
		background: #76D6E3;
		padding-left: 20px;
		border-radius: 8px;
		padding-top: 20px;
		padding-bottom: 20px;
	}
	#heading_div{
		margin-bottom: 20px;
		margin-left: 20px;
	}
	#div_form div{
		margin-top: 30px;
	}
	#div_form label{
		margin-right: 20px;
	}
	#et_expense,#div_heads input{
		border: 1px solid activeborder;
		border-radius:5px;
		float: inherit;
		padding-left: 3px;
		min-height: 30px;
		margin-right: 100px;
		font-size: 15px;
		font-style: inherit;
	}
	#et_expense:FOCUS,#div_heads input:FOCUS{
		border: 3px solid activeborder;
		border-radius:5px;
		float: inherit;
		padding-left: 3px;
		min-height: 30px;
		font-size: 15px;
		font-style: inherit;
	}
	#btn_submit{
		
	}
	#div_heads{
		padding-left: 20px;
		padding-right: 30px;
		
	}
	#div_heads label{
		margin-right: 180px;
		font-size: 20px;
		margin-left: 40px;
	}
</style>
<script type="text/javascript">
	function saveexpense() {
		document.location.href="/test";
	}
</script>
</head>
<body>
	<div  id="heading_div">
		<h3 class="headline m-top-md">
		Expense Sheet
		<span class="line"></span>
		</h3>
	</div>
	
	<div id="div_form">
		<form action="saveexpense" method="get">
			<div><label>Expense:</label><input name="expense" id="et_expense"/></div>
			<div id="div_heads">
				<label>Description</label><label>Cost</label>
				<div>
					<input id="input_item1_cause" name="item1name"/><input id="input_item1_cost" name="item1cost" value="0"/>
				</div>
				<div>
					<input id="input_item2_cause" name="item2name"/><input id="input_item2_cost" name="item2cost" value="0"/>
				</div>
				<div>
					<input id="input_item3_cause" name="item3name"/><input id="input_item3_cost" name="item3cost" value="0"/>
				</div>
				<div> 
					<input id="input_item4_cause" name="item4name"/><input id="input_item4_cost" name="item4cost" value="0"/>
				</div>
				<div>
					<button>Submit</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>