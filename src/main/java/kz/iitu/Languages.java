package kz.iitu;

import kz.iitu.soap.ws.client.generated.CountryInfoService;
import kz.iitu.soap.ws.client.generated.TCountryInfo;
import kz.iitu.soap.ws.client.generated.TLanguage;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;


public class Languages extends HttpServlet {
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

        out.println("<h1>" + "List of language in " + tCountryInfo.getSName() + ": </h1>");
        List<TLanguage> tLanguages = tCountryInfo.getLanguages().getTLanguage();
        System.out.println(tLanguages);
        int counter = 0;
        for (TLanguage tl : tLanguages) {
            counter++;
            out.println(counter + "." + tl.getSName());
        }
        if (counter == 0) {
            out.println("no special languages");
        }
        out.println("</body>");
        out.println("</html>");

    }
}