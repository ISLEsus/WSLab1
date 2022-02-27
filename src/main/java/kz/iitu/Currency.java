package kz.iitu;

import kz.iitu.soap.ws.client.generated.CountryInfoService;
import kz.iitu.soap.ws.client.generated.TCountryInfo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;


public class Currency extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        generateView(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        generateView(request, response);
    }

    public void generateView(HttpServletRequest request,
                             HttpServletResponse response)
            throws IOException {
        CountryInfoService countryIS = new CountryInfoService();
        TCountryInfo tCountryInfo =
                countryIS.getCountryInfoServiceSoap().fullCountryInfo(request.getParameter("countryCode"));

        response.setContentType("text/html; charset=UTF-8");
        ResultSet resultSet = null;
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Country</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>" + "Currency used in " + tCountryInfo.getSName() + ": </h1>");
        out.println("<table cellpadding=\"4\">");

//        out.print("<tr><td>" + "Selected country uses the following Currency: " + "</td>");
        out.print("<td>" + tCountryInfo.getSCurrencyISOCode() + "</td></tr>");

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");

    }
}
