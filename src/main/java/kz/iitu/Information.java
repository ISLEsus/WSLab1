package kz.iitu;

import kz.iitu.soap.ws.client.generated.CountryInfoService;
import kz.iitu.soap.ws.client.generated.TCountryInfo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;


public class Information extends HttpServlet {
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

        out.println("<h1>" + "Info of " + tCountryInfo.getSName() + ": </h1>");
        out.println("<table cellpadding=\"4\">");

        out.print("<tr><td>" + "The selected country is called: " + "</td>");
        out.print("<td>" + tCountryInfo.getSName() + "</td></tr>");
        out.print("<tr><td>" + "Its Capital's name is: " + "</td>");
        out.print("<td>" + tCountryInfo.getSCapitalCity() + "</td></tr>");
        out.print("<tr><td>" + "The country has the following telephone code: " + "</td>");
        out.print("<td>" + tCountryInfo.getSPhoneCode() + "</td></tr>");
        out.print("<tr><td>" + "The country's continent code is: " + "</td>");
        out.print("<td>" + tCountryInfo.getSContinentCode() + "</td></tr>");
        out.print("<tr><td>" + "Selected country uses the following Currency: " + "</td>");
        out.print("<td>" + tCountryInfo.getSCurrencyISOCode() + "</td></tr>");
        out.print("<tr><td>" + "And the country's flag looks like this: " + "</td>");
        out.print("<td>" + "<img src=\"" + tCountryInfo.getSCountryFlag() + "\" " +
                "alt=\"flag of " + tCountryInfo.getSName() + "\">" + "</td></tr>");


        out.println("</table>");
        out.println("</body>");
        out.println("</html>");

    }
}
