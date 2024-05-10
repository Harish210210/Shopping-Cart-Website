package Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CartOrders {
	public int setCartOrder(String cname, String date, int total) {
		int oid = 1;
		try {
			Class.forName("org.postgresql.Driver");
			Connection cn = DriverManager.getConnection("jdbc:postgresql://192.168.110.48/plf_training",
					"plf_training_admin", "pff123");
			Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery("select * from h_cartorders");
			while (rs.next()) {
				oid++;
			}
			Statement st2 = cn.createStatement();
			String qry = "insert into h_cartorders values(" + oid + ", '" + date + "', " + total + ", '" + cname + "')";
			st.executeUpdate(qry);

		} catch (Exception e) {
			System.out.println(e);
		}
		return oid;
	}
}
