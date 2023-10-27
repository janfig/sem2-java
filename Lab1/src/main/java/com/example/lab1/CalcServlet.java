package com.example.lab1;

import java.io.*;
import java.util.ArrayList;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "calcServlet", value = "/calc-servlet")
public class CalcServlet extends HttpServlet {

    public void init() {}

    public void processRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");

        String nazwaCookie = "visitCount";
        int visitCount = 1;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
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
            operationHist = new ArrayList<>();
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
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        processRequest(req, res);
    }

    public String calculate(String num1, String num2, String operation) {
        if ((num1 == null) || (num1.trim().isEmpty())) {
            return "Num1 should be, a valid number";
        }
        if ((num2 == null) || (num2.trim().isEmpty())) {
            return "Num2 should be, a valid number";
        }
        float a = Float.parseFloat(num1);
        float b = Float.parseFloat(num2);

        String operationSymbol = "";
        float result = 0;
        switch (operation) {
            case "add":
                result = a + b;
                operationSymbol  = "+";
                break;
            case "sub":
                result = a - b;
                operationSymbol  = "-";
                break;
            case "mul":
                result = a * b;
                operationSymbol  = "*";
                break;
            case "div":
                if (b == 0) {
                    return "Cannot divide by 0";
                }
                result = a / b;
                operationSymbol  = "/";
                break;
        }

        return num1 + " " + operationSymbol + " " + num2 + "=" + result;
    }

    public void destroy() {
    }
}