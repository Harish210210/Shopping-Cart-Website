package Store;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

public class CartServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String cname = (String) session.getAttribute("cname");
		GetCartDetails cd = new GetCartDetails();
		HashMap<String, ArrayList<String>> hm = cd.getCartDetails(cname);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < (hm.get("pid")).size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("pid", (hm.get("pid")).get(i));
			obj.put("pname", (hm.get("pname")).get(i));
			obj.put("price", (hm.get("price")).get(i));
			obj.put("image", (hm.get("image")).get(i));
			obj.put("quantity", (hm.get("quantity")).get(i));
			jsonArray.put(obj);
		}
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString());
	}

}
