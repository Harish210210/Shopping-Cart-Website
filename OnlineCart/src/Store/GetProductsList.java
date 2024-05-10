package Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class GetProductsList {
	public HashMap<String, ArrayList<String>> getProducts() {
		HashMap<String, ArrayList<String>> hm = new HashMap<>();
		try {
			Class.forName("org.postgresql.Driver");
			Connection cn = DriverManager.getConnection("jdbc:postgresql://192.168.110.48/plf_training",
					"plf_training_admin", "pff123");
			Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery("select * from h_products where pid < 8");
			hm.put("pid", new ArrayList<String>());
			hm.put("pname", new ArrayList<String>());
			hm.put("price", new ArrayList<String>());
			hm.put("image", new ArrayList<String>());
			hm.put("pcatid", new ArrayList<String>());
			while (rs.next()) {
				ArrayList<String> pid = new ArrayList<>(hm.get("pid"));
				pid.add(rs.getString(1));
				ArrayList<String> pname = new ArrayList<>(hm.get("pname"));
				pname.add(rs.getString(2));
				ArrayList<String> price = new ArrayList<>(hm.get("price"));
				price.add(rs.getString(3));
				ArrayList<String> image = new ArrayList<>(hm.get("image"));
				image.add(rs.getString(6));
				ArrayList<String> pcatid = new ArrayList<>(hm.get("pcatid"));
				pcatid.add(rs.getString(5));
				hm.put("pid", pid);
				hm.put("pname", pname);
				hm.put("price", price);
				hm.put("image", image);
				hm.put("pcatid", pcatid);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return hm;
	}
}
