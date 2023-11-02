package com.example.lab3;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "listServlet2", value = "/list-servlet2")
public class ListServlet2 extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

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

            HttpSession session = request.getSession(true);
            CountryBean country;
            ArrayList<CountryBean> list = new ArrayList<CountryBean>();
            while (rs.next()) {
                country = new CountryBean();
                //pobierz dane z odpowiedniej kolumny
                //przypisz je do właściwości obiektu CountryBean
                country.setName(rs.getString("Name"));
                country.setCode(rs.getString("Code"));
                country.setPopulation(rs.getString("Population"));
                country.setSurfaceArea(rs.getString("SurfaceArea"));
// ...
                list.add(country);
            }
            session.setAttribute("list", list);

            response.sendRedirect("countryList.jsp");
        } catch (ClassNotFoundException e) {

        } catch (SQLException e) {

        }
    }

    public void destroy() {
    }
}
