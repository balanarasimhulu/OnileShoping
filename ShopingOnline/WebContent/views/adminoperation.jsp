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
<div class="regform"><h1>"Admin Operations"</h1></div>
			<div class="main">
	<a href="addproduct.jsp"><h1>add product</h1></a>
	<a href="display"><h1>display products</h1></a>
	<a href="addcustomer.jsp"><h1>Add a customer</h1></a>
	<a href="deletecustomer.jsp"><h1>Delete a customer</h1></a>
	
</div>
</body>
</html>