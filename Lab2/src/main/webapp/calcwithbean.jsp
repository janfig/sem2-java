<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="loan" class="com.example.lab2.LoanBean" scope="session" />
<jsp:setProperty name="loan" property="*" />
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date now = new Date();
    String date = dateFormat.format(now);
%>
<%= date %>
<h1>Kalkulator rat</h1>
<form action="calcwithbean.jsp">
    <label for="kwota">Kwota pożyczki</label>
    <input name=kwota id="kwota" type="text" value="<%=loan.getKwota()%>">
    <br>

    <label for="procent">Procent roczny</label>
    <input name=procent id="procent" type="text" value="<%=loan.getProcent()%>">
    <br>

    <label for="liczbaRat">Liczba rat</label>
    <input name=liczbaRat id="liczbaRat" type="text" value="<%=loan.getLiczbaRat()%>">
    <br>

    <input type="submit" name="wyslij" value="Wyślij">
</form>
<% if (request.getParameter("wyslij") != null) {
    out.println("Rata miesięczna: " + loan.getRata());
}
%>
</body>
</html>
