package kz.iitu;

import kz.iitu.soap.ws.client.generated.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;

@WebServlet(
        name = "CountryServlet",
        urlPatterns = "/CountryServlet"
)
public class CountryList extends HttpServlet {
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
        CountryInfoServiceSoapType countryISS = countryIS.getCountryInfoServiceSoap();

        ArrayOftContinent arrayOftContinent = countryISS.listOfContinentsByName();
        List<TContinent> tContinent = arrayOftContinent.getTContinent();

        ArrayOftCountryCodeAndName arrayOftCountryCodeAndName = countryISS.listOfCountryNamesByName();
        List<TCountryCodeAndName> tCountryCodeAndNames = arrayOftCountryCodeAndName.getTCountryCodeAndName();

        response.setContentType("text/html; charset=UTF-8");
        ResultSet resultSet = null;
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Country</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>List of сountries</h1>");
        out.println("<table cellpadding=\"4\">");
        out.println("<tr>\n" +
                "        <th>id</th>\n" +
                "        <th>Code</th>\n" +
                "        <th>Name</th>\n" +
                "        <th>Information</th>\n" +
                "        <th>Languages </th>\n" +
                "        <th>Currency</th>\n" +
                "        <th>Flag</th>\n" +

                "    </tr>");
        System.out.println(tContinent.toString());
        for (TCountryCodeAndName countryCodeAndName : tCountryCodeAndNames) {
            int lastIndex = tCountryCodeAndNames.lastIndexOf(countryCodeAndName);

            out.print("<tr>");
            out.print(" <td>" + lastIndex + "</td>");
            out.print(" <td>" + countryCodeAndName.getSISOCode() + "</td>");
            out.print(" <td>" + countryCodeAndName.getSName() + "</td>");
            out.print(" <td><a href=\"/WSLab1-1.0-SNAPSHOT/Information?countryCode=" + countryCodeAndName.getSISOCode() + "\"> " + "Information" + "</a></td>");
            out.print(" <td><a href=\"/WSLab1-1.0-SNAPSHOT/Languages?countryCode=" + countryCodeAndName.getSISOCode() + "\"> " + "Languages" + "</a></td>");
            out.print(" <td><a href=\"/WSLab1-1.0-SNAPSHOT/Currency?countryCode=" + countryCodeAndName.getSISOCode() + "\"> " + "Currency" + "</a></td>");
            out.print(" <td><a href=\"/WSLab1-1.0-SNAPSHOT/Flag?countryCode=" + countryCodeAndName.getSISOCode() + "\"> " + "Flag" + "</a></td>");

            out.print("</tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}