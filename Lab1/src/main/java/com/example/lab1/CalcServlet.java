package com.example.lab1;

import java.io.*;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "calcServlet", value = "/calc-servlet")
public class CalcServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void processRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");

        String nazwaCookie = "visitCount";
        int visitCount = 1;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie c = cookies[i];
                if (nazwaCookie.equals(c.getName())) {
                    visitCount = Integer.parseInt(c.getValue());
                    visitCount++;
                }
            }
        }
        if (visitCount == 1) {
            res.addCookie(new Cookie("visitCount", "" + visitCount));
        }

        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");
        String operation = req.getParameter("operation");
        String result = calculate(num1, num2, operation);

        HttpSession session = req.getSession(true);
        ArrayList<String> operationHist = (ArrayList<String>) session.getAttribute("opHis");
        if (operationHist == null || (req.getParameter("clearSession") != null && req.getParameter("clearSession").equals("true"))) { //nie ma szukanego obiektu w sesji
            operationHist = new ArrayList<String>();
        }


        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        if (visitCount == 1) {
            out.println("<p>Witamy po raz pierwszy</p>");
        } else {
            out.println("<p>Witamy po raz kolejny</p>");
        }
        out.println(
                "<a href=\"calc.html\">Kalkulatorek</a> "+
                "<a href=\"calc-servlet?clearSession=true&num1="+num1+"&num2="+num2+"&operation="+operation+"\">Wyczyść sesję</a>"
        );
        out.println("<p>Operation history:</p>");
        for (String el : operationHist) {
            out.println("<p>" + el + "</p>");
        }

        out.println("<p>Result:</p>");
        out.println("<p>" + result + "</p>");
        out.println("</body></html>");

        operationHist.add(result);
        session.setAttribute("opHis", operationHist);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        processRequest(req, res);
    }

    public String calculate(String num1, String num2, String operation) {
        String errorMess = null;
        if ((num1 == null) || (num2.trim().equals(""))) {
            return "Num1 should be, a valid number";
        }
        if ((num2 == null) || (num2.trim().equals(""))) {
            return "Num1 should be, a valid number";
        }
        float a = Float.parseFloat(num1);
        float b = Float.parseFloat(num2);

        float result = 0;
        switch (operation) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                if (b == 0) {
                    return "Cannot divide by 0";
                }
                result = a / b;
                break;
        }

        return num1 + " " + operation + " " + num2 + "=" + result;
    }

    public void destroy() {
    }
}