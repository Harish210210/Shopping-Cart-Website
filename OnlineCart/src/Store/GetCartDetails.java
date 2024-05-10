package Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class GetCartDetails {
	public HashMap<String, ArrayList<String>> getCartDetails(String cname) {
		HashMap<String, ArrayList<String>> hm = new HashMap<>();
		try {
			Class.forName("org.postgresql.Driver");
			Connection cn = DriverManager.getConnection("jdbc:postgresql://192.168.110.48/plf_training",
					"plf_training_admin", "pff123");
			Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(
					"SELECT * FROM h_products JOIN h_cart ON h_products.pid = h_cart.pid where h_cart.cname = '" + cname
							+ "'");
			hm.put("pid", new ArrayList<String>());
			hm.put("pname", new ArrayList<String>());
			hm.put("price", new ArrayList<String>());
			hm.put("image", new ArrayList<String>());
			hm.put("quantity", new ArrayList<String>());
			while (rs.next()) {
				ArrayList<String> pid = new ArrayList<>(hm.get("pid"));
				pid.add(rs.getString(1));
				ArrayList<String> pname = new ArrayList<>(hm.get("pname"));
				pname.add(rs.getString(2));
				ArrayList<String> price = new ArrayList<>(hm.get("price"));
				price.add(rs.getString(9));
				ArrayList<String> image = new ArrayList<>(hm.get("image"));
				image.add(rs.getString(6));
				ArrayList<String> quantity = new ArrayList<>(hm.get("quantity"));
				quantity.add(rs.getString(8));
				hm.put("pid", pid);
				hm.put("pname", pname);
				hm.put("price", price);
				hm.put("image", image);
				hm.put("quantity", quantity);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return hm;
	}
}
