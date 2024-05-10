package Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class GetCategories {
	public HashMap<String, ArrayList<String>> getCateList() {
		HashMap<String, ArrayList<String>> hm = new HashMap<>();
		try {
			Class.forName("org.postgresql.Driver");
			Connection cn = DriverManager.getConnection("jdbc:postgresql://192.168.110.48/plf_training",
					"plf_training_admin", "pff123");
			Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery("select * from h_pcategory");
			hm.put("pcatid", new ArrayList<String>());
			hm.put("pcatname", new ArrayList<String>());
			while (rs.next()) {
				ArrayList<String> pcatid = new ArrayList<>(hm.get("pcatid"));
				pcatid.add(rs.getString(1));
				ArrayList<String> pcatname = new ArrayList<>(hm.get("pcatname"));
				pcatname.add(rs.getString(2));
				hm.put("pcatid", pcatid);
				hm.put("pcatname", pcatname);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return hm;
	}
}
