package oppgave1Packe;

import static oppgave1Packe.UrlMappings.LOGIN_URL;
import static oppgave1Packe.UrlMappings.HANDLELISTE_URL;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringEscapeUtils;

@WebServlet("/" + HANDLELISTE_URL)
public class HandlelisteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	Vaagn handleliste = new Vaagn();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!InnloggingUtil.isInnlogget(request)) {
			response.sendRedirect(LOGIN_URL);
		} else {
			HttpSession sesjon = request.getSession(true);
			Vaagn handleliste = (Vaagn) sesjon.getAttribute("handelliste");// får Null her ?
			// Legger til elementa frå handlelista inn på sida.
			String utskrift = "";
			for (int i = 0; i < handleliste.Vaagnlength(); i++) {
				utskrift += "<form action='" + HANDLELISTE_URL + "' method='post'>"
						+ "<p><input type=\"hidden\" name=\"SlettVareNr\" value=\"" + i + "\" /></p>"
						+ "<p><input type=\"hidden\" name=\"SlettVare\" value=\"" + handleliste.vareAtIndex(i)
						+ "\" /></p>" + "<p><input type=\"submit\" value='Slett'/> " + handleliste.vareAtIndex(i)
						+ "</p>" + "</form>";
			}

			response.setContentType("text/html; charset=ISO-8859-1");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Innlogging</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<form action=\"" + HANDLELISTE_URL + "\" method='post'>");
			out.println("<p><input type=\"submit\" value='Legg til'/> <input type='text' name='vare'/></p>");
			out.println("</form>");
			out.println(utskrift);
			out.println("</body>");
			out.println("</html>");
		}
	}

	protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!InnloggingUtil.isInnlogget(request)) {
			response.sendRedirect(LOGIN_URL);
		} else {
			HttpSession sesjon = request.getSession(true);

			Vaagn handleliste = (Vaagn) sesjon.getAttribute("handelliste");

			// Oppdatere tidsreleet
			int timeoutISekunder = 20;
			sesjon.setMaxInactiveInterval(timeoutISekunder);

			// Ufarliggjøring av brukerinput
			String nyVare = StringEscapeUtils.escapeHtml4(request.getParameter("vare"));

			String SlettVareNr = request.getParameter("SlettVareNr");
			String SlettVare = request.getParameter("SlettVare");

			// Nye vare
			if (nyVare != null && !nyVare.replaceAll(" ", "").equals("") && handleliste != null) {
				handleliste.addVare(nyVare);

			}
			// Slett vare
			else if (SlettVareNr != null && handleliste != null) {
				int SlettVareNrIndeks = Integer.parseInt(SlettVareNr);
				if (SlettVareNrIndeks < handleliste.Vaagnlength()
						&& SlettVare.equals(handleliste.vareAtIndex(SlettVareNrIndeks))) {
					handleliste.removeAtIndex(SlettVareNrIndeks);
				}
			}

			sesjon.setAttribute("handleliste", handleliste);

			// Oppdater sida
			response.sendRedirect(HANDLELISTE_URL);
		}

	}
}
