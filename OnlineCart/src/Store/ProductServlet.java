package Store;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GetProductsList gp = new GetProductsList();
		HashMap<String, ArrayList<String>> hm = gp.getProducts();
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < (hm.get("pid")).size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("pid", (hm.get("pid")).get(i));
			obj.put("pname", (hm.get("pname")).get(i));
			obj.put("price", (hm.get("price")).get(i));
			obj.put("image", (hm.get("image")).get(i));
			obj.put("pcatid", (hm.get("pcatid")).get(i));
			jsonArray.put(obj);
		}
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString());
	}

}
