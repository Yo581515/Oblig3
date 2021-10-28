package oppgave1Packe;

import static oppgave1Packe.UrlMappings.LOGIN_URL;
import static oppgave1Packe.UrlMappings.HANDLELISTE_URL;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/" + LOGIN_URL)
@WebServlet(name = "LoggInnServlet", urlPatterns = "/" + LOGIN_URL)
public class LoggInnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String rettPassord = "Hemmeligt";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String utskrift = "";
		String feilmelding = request.getParameter("feilmelding");

		if (feilmelding != null && feilmelding.equals("feil")) {
			utskrift = "<p1>Passordet du gav inn var feil. Prøv igjen:</p1>";
		} else {
			utskrift = "<p1>Gi inn passord:</p1>";
		}

		response.setContentType("text/html; charset=ISO-8859-1");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Innlogging</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action=\"" + LOGIN_URL + "\" method='post'>");
		out.println(utskrift);
		out.println(
				"<p><input type='text' name='passord'/></p>" + "	<p><input type=\"submit\" value='Logg inn'/></p>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String inputPassord = request.getParameter("passord");
		int timeoutISekunder = 20;

		if (InnloggingUtil.isGyldigPassord(inputPassord, rettPassord)) {
			InnloggingUtil.loggInnMedTimeout(request, timeoutISekunder);
			response.sendRedirect(HANDLELISTE_URL);
		} else {
			response.sendRedirect(LOGIN_URL + "?feilmelding=feil");
		}
	}

}
