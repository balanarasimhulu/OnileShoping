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
<div class="regform"><h1>"Customer Operations"</h1></div>
<div class="main">

<a href="select"><h1>AddtoCart</h1></a>
<a href="displaycart"><h1>Display Cart</h1></a>
<a href="displaybill"><h1>Display Bill</h1></a>
<a href="paybill"><h1>Pay Bill</h1></a>
<a href= "addamount.jsp"><h1>Add Amount</h1></a>
</div>
</body>
</html>