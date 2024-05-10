package Store;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class CategoryServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GetCategories gc = new GetCategories();
		HashMap<String, ArrayList<String>> hm = gc.getCateList();
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < (hm.get("pcatid")).size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("pcatid", (hm.get("pcatid")).get(i));
			obj.put("pcatname", (hm.get("pcatname")).get(i));
			jsonArray.put(obj);
		}
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(jsonArray.toString());
	}

}
