package com.example.lab3;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "listServlet", value = "/list-servlet")
public class ListServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        try {
            // pobranie sterownika do MySQL:
            Class.forName("com.mysql.cj.jdbc.Driver");
            // utworzenie obiektu połączenia do bazy danych MySQL:
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world? serverTimezone=UTC", "root", "root");
            // utworzenie obiektu do wykonywania zapytań do bd:
            Statement st = conn.createStatement();
            String query = "SELECT * FROM country WHERE Continent = 'Europe'";
            // wykonanie zapytania SQL:
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                // pobierz i wyświetl dane z odpowiedniej kolumny
                out.println(String.format("<p>%s</p>",rs.getString("name")));
            }
        } catch (ClassNotFoundException e) {
            out.println("siema");
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
