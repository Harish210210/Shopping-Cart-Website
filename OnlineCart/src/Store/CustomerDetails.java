package Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerDetails {
	public void setCustomer(String email, String password, String mobile, String address) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection cn = DriverManager.getConnection("jdbc:postgresql://192.168.110.48/plf_training",
					"plf_training_admin", "pff123");
			Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery("select * from h_customers");
			int cid = 1;
			while (rs.next()) {
				cid++;
			}
			Statement st2 = cn.createStatement();
			String qry = "insert into h_customers values(" + cid + ", '" + email + "', '" + password + "', '" + mobile
					+ "', '" + address + "')";
			st.executeUpdate(qry);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
