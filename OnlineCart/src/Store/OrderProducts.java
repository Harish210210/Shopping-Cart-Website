package Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class OrderProducts {
	public void setOrderProducts(String cname, int oid) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection cn = DriverManager.getConnection("jdbc:postgresql://192.168.110.48/plf_training",
					"plf_training_admin", "pff123");
			Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery("select pid, quantity, price from h_cart where cname = '" + cname + "'");
			Statement st2 = cn.createStatement();
			while (rs.next()) {
				st2.addBatch("insert into h_oproducts values(" + oid + ", " + rs.getInt(1) + ", " + rs.getInt(2) + ", "
						+ (rs.getInt(3) * rs.getInt(2)) + ")");
			}
			st2.executeBatch();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
