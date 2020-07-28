<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	*{
	margin=0;
	padding=0;
	
	}
	body{
	background-color: lime;
	margin-top: 40px;
	}
	.regform{
	width: 800px;
	background-color: fuchsia;
	margin: auto;
	color: #FFFFFF;
	padding:10px 0px 10px
	text-align=centre;
	border-radius: 15px 15px 0px 0px;
	}
	.main{
	background-color: aqua;
	width: 800px;
	margin: auto;
	}
	form{
	padding: 10px;
	}
</style>
</head>
<body>
${message}
<div class="regform"><h1>Adding product</h1></div>
<div class="main">
<form action="addproduct">
Enter product number:<input type="text" name="pnumber"><br>
Enter product name:<input type="text" name="pname"><br>
Enter product price:<input type="text" name="pprice"><br>
Enter no of quantities:<input type="text" name="pquantities"><br>
<input type="submit" value="addproduct">
</div>




</form>

</body>
</html>