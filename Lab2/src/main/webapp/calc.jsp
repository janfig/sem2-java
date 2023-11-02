<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<form action="calc.jsp">
    <label for="kwota">Kwota pożyczki</label>
    <input name=kwota id="kwota" type="text">
    <br>

    <label for="procent">Procent roczny</label>
    <input name=procent id="procent" type="text">
    <br>

    <label for="liczbaRat">Liczba rat</label>
    <input name=liczbaRat id="liczbaRat" type="text">
    <br>

    <input type="submit" name="wyslij" value="Wyślij">
</form>
<% if (request.getParameter("wyslij") != null) {
    String res = "Rata miesięczna: ";
//    try {
        double k = Double.parseDouble(request.getParameter("kwota"));
        double pr = Double.parseDouble(request.getParameter("procent"));
        double n = Double.parseDouble(request.getParameter("liczbaRat"));
        double p = pr/12;
        double rata = (k * p) / (1 - (1 / Math.pow(1 + p, n)));
        DecimalFormat df = new DecimalFormat("#.00");
        String rataf=df.format(rata);
        res += rataf;
//    } catch (Exception ex) {
//        res = "Błedne dane!";
//    }
    out.println(res);
}
%>
</body>
</html>
