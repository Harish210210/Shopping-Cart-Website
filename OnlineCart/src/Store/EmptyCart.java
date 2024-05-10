package Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class EmptyCart {
	public void cartEmpty(String cname) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection cn = DriverManager.getConnection("jdbc:postgresql://192.168.110.48/plf_training",
					"plf_training_admin", "pff123");
			Statement st = cn.createStatement();
			st.executeUpdate("delete from h_cart where cname='" + cname + "'");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
