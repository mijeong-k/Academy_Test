package p230608;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleInsert2 {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "c##himedia";
		String password = "himedia";
		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.\n");
			Statement stmt = conn.createStatement();

			// insert 쿼리 = true 이냐 false 이냐로 결과값이 나오기 때문에
			// resultset 이 아닌 boolean 사용

			
			String sql3 = "SELECT max(DEPTNO) FROM dept";
			ResultSet rs2 = stmt.executeQuery(sql3);
			
			String ret = "";
			while(rs2.next()) {
				System.out.println(rs2.getString("MAX(DEPTNO)")+" ");
				ret = rs2.getString("MAX(DEPTNO)");
			}
			int ideptret = Integer.parseInt(ret);
			ideptret +=10;
			String strdept = Integer.toString(ideptret);
			
			String ideptno = strdept , sdname = "IT", sloc = "SEOUL";
			String sql = "INSERT INTO dept VALUES ('" + ideptno + "','" + sdname + "','" + sloc + "')";
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
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
