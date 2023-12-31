package p230608;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Oi3 {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "c##himedia";
		String password = "himedia";

		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success. \n");
			Statement stmt = conn.createStatement();

			String sql3 = "SELECT deptno FROM(SELECT * FROM dept ORDER BY deptno DESC) WHERE rownum <= 1";

			ResultSet rs2 = stmt.executeQuery(sql3);
			int ideptno = 0;

			while (rs2.next()) {
				ideptno = Integer.parseInt(rs2.getString("deptno"));
			}

			String sdeptno = Integer.toString(ideptno + 10), sdname = "IT", sloc = "SEOUL";
			String sql = "INSERT INTO dept VALUES ('" + sdeptno + "','" + sdname + "','" + sloc + "')";
			boolean b = stmt.execute(sql);
			if (!b) {
				System.out.println("Insert success.\n");
			} else {
				System.out.println("Insert fail.\n");
			}
			// String sql = "SELECT * FROM dept";
			String sql2 = "SELECT deptno, dname, loc FROM dept";
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				System.out.print(rs.getString("deptno") + "\t");
				System.out.print(rs.getString("dname") + "\t");
				System.out.println(rs.getString("loc") + " ");
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}