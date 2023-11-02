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
    <title>Country details</title>
</head>
<body>
<h1>Country List</h1>
<% ArrayList<CountryBean> list = (ArrayList<CountryBean>) session.getAttribute("list");%>
<br>
<%
    int index = Integer.parseInt(request.getParameter("i"));
    CountryBean country = list.get(index);
    out.println("Details of " + country.getName() + "<br>");
    out.println("Country code: " + country.getCode() + "<br>");
    out.println("Population: " + country.getPopulation() + "<br>");
    out.println("Surface area: " + country.getSurfaceArea() + "<br>");
%>
<a href="list-servlet2">Country list</a>
</body>
</html>
