package Store;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		SearchCustomer sc = new SearchCustomer();
		if (sc.searchCustomer(email, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("cname", email);
			response.setContentType("plain/text");
			PrintWriter out = response.getWriter();
			out.print("Success");
		} else {
			response.setContentType("plain/text");
			PrintWriter out = response.getWriter();
			out.print("Error");
		}
	}

}
