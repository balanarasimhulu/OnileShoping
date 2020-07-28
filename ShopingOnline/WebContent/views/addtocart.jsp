</html><%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>"Product List"</title>
<style type="text/css">


body{
background-color: white;

}
table,th,td{
border :ipx dotted whilte;
border-collapse: collapse;
padding:20px;
font-size: 20px;
}
th{
background:purple;
padding:30px;
text-transform: uppercase;

}

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
<div class="regform"><h1>"Product Table"</h1></div>
			<div class="main">
<form action="takeproduct">
		<%! int fontSize; %>
		
		<table border ="5">
		<tr>
		<td align="centre" bgcolor="green" colspan="7"><h1>Wellcome To Necessary Things</h1></td>
		</tr>	
		
				<tr>
						
							<td>Product Number</td>
							<td>Product Name</td>
							<td>Product Quantities</td>
							
							
						
							
				</tr>
				<c:forEach var="l" items="${list}">
				
				<tr>
				
				
							<td>"${l.pnumber}"</td>
							<td>"${l.pname}"</td>
						<form:select path="quantity" items="${l.pquantities}" />
							  <td> <select id="quantity" name="cquantity"  >
			  	
			  	<c:forEach var="j" begin="0" end="${l.pquantities}">  
			  	
                	  <option value=  "${j} ">  ${j} </option>
			  	</c:forEach>            
    		 </select></td>	
			
				</tr>	
				</c:forEach>
		
		</table>
		
<input type="submit">
</form>
	</div>
</body>
</html>