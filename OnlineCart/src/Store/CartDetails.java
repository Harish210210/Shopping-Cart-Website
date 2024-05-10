package Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CartDetails {
	public boolean toCart(int pid, int qty, String cname, String region) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection cn = DriverManager.getConnection("jdbc:postgresql://192.168.110.48/plf_training",
					"plf_training_admin", "pff123");
			Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rss = st.executeQuery(
					"SELECT hp.pname,hp.price,hp.hsn_code,hp.pcatid, hp.image FROM h_products AS hp JOIN h_services AS hs ON hp.pcatid = hs.pcatid JOIN h_regions AS hr ON hs.rid = hr.rid WHERE hp.pid = "
							+ pid + " AND hr.rname = '" + region + "'");
			if (!rss.next()) {
				return false;
			}
			ResultSet rs = st.executeQuery("select * from h_cart where pid=" + pid);
			if (rs.next()) {
				Statement st2 = cn.createStatement();
				String qry = "update h_cart set quantity=quantity+" + qty + " where pid =" + pid;
				st.executeUpdate(qry);
			} else {
				Statement st3 = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs3 = st.executeQuery("select price from h_products where pid=" + pid);
				int prc = 0;
				while (rs3.next()) {
					prc = rs3.getInt(1);
				}
				Statement st2 = cn.createStatement();
				String qry = "insert into h_cart values(" + pid + ", " + qty + ", " + prc + ", '" + cname + "')";
				st.executeUpdate(qry);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return true;
	}
}
