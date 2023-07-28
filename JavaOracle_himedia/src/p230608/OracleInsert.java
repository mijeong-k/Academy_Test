package p230608;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// import java.sql.*; 와 동일

public class OracleInsert {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "c##himedia";
		String password = "himedia";

		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.\n");
			Statement stmt = conn.createStatement();

			// insert 쿼리 = true 이냐 false 이냐로 결과값이 나오기 때문에
			// resultset 이 아닌 boolean 사용
			
			
			String sql = "SELECT * FROM dept";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) { //다음 레코드가 존재하면 true 없으면 false
				System.out.print(rs.getString("deptno") + " ");
				System.out.print(rs.getString("dname") + " ");
				System.out.println(rs.getString("loc") + " ");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver loading fail.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("oracle connection fail.");
			e.printStackTrace();
		}

	}

}
