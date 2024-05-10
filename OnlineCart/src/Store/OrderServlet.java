package Store;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int total = Integer.parseInt(request.getParameter("total"));
		String date = request.getParameter("date");
		HttpSession session = request.getSession();
		String cname = (String) session.getAttribute("cname");
		CartOrders co = new CartOrders();
		int oid = co.setCartOrder(cname, date, total);
		OrderProducts op = new OrderProducts();
		op.setOrderProducts(cname, oid);
		EmptyCart ec = new EmptyCart();
		ec.cartEmpty(cname);
		response.setContentType("plain/text");
		PrintWriter out = response.getWriter();
		out.print("Success");
	}

}
