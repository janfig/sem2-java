<%@ page import="com.example.lab3.CountryBean" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: jan
  Date: 11/2/23
  Time: 11:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Country list</title>
</head>
<body>
<h1>Country List</h1>
<% ArrayList<CountryBean> list = (ArrayList<CountryBean>) session.getAttribute("list");%>
<br>
<%
    out.println("Name Code Population Details<br>");
    for (CountryBean country : list) {
        out.println(String.format("%s %s %s <a href=details.jsp?i=%d>Details</a><br>",
                country.getName(),
                country.getCode(),
                country.getPopulation(),
                list.indexOf(country)
        ));
    }
%>
</body>
</html>
