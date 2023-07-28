package p230608;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Oi4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "c##himedia";
		String password = "himedia";

		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection sucess.\n");
			Statement stmt = conn.createStatement();

			String q = sc.next();
			String w = sc.next();
			String e = sc.next();
			sc.close();

			String ideptno = q, sdname = w, sloc = e;
			String sql = "INSERT INTO dept VALUES ('" + ideptno + "', '" + sdname + "', '" + sloc + "')";
			boolean b = stmt.execute(sql);
			if (!b) {
				System.out.println("Insert success.\n");
			} else {
				System.out.println("INsert fail.\n");
			}
			// String sql = "SELECT * FROM dept";
			String sq12 = "SELECT deptno, dname, loc FROM dept";
			ResultSet rs = stmt.executeQuery(sq12);
			while (rs.next()) {
				System.out.print(rs.getString("deptno") + "\t");
				System.out.print(rs.getString("dname") + "\t");
				System.out.println(rs.getString("loc") + " ");
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}

	}
}
