package Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class CheckoutList {
	public HashMap<String, ArrayList<String>> getCheckoutList(String cname) {
		HashMap<String, ArrayList<String>> hm = new HashMap<>();
		try {
			Class.forName("org.postgresql.Driver");
			Connection cn = DriverManager.getConnection("jdbc:postgresql://192.168.110.48/plf_training",
					"plf_training_admin", "pff123");
			Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(
					"SELECT h_products.pid, h_products.price, h_cart.quantity, h_products.pname, h_hsncode.gst FROM h_products JOIN h_cart ON h_products.pid = h_cart.pid JOIN h_hsncode ON h_products.hsn_code = h_hsncode.hsn_code where cname = '"
							+ cname + "'");
			hm.put("pid", new ArrayList<String>());
			hm.put("pname", new ArrayList<String>());
			hm.put("price", new ArrayList<String>());
			hm.put("quantity", new ArrayList<String>());
			hm.put("gst", new ArrayList<String>());
			while (rs.next()) {
				ArrayList<String> pid = new ArrayList<>(hm.get("pid"));
				pid.add(rs.getInt(1) + "");
				ArrayList<String> pname = new ArrayList<>(hm.get("pname"));
				pname.add(rs.getString(4));
				ArrayList<String> price = new ArrayList<>(hm.get("price"));
				price.add(rs.getInt(2) + "");
				ArrayList<String> quantity = new ArrayList<>(hm.get("quantity"));
				quantity.add(rs.getInt(3) + "");
				ArrayList<String> gst = new ArrayList<>(hm.get("gst"));
				gst.add(rs.getInt(5) + "");
				hm.put("pid", pid);
				hm.put("pname", pname);
				hm.put("price", price);
				hm.put("quantity", quantity);
				hm.put("gst", gst);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return hm;
	}
}
