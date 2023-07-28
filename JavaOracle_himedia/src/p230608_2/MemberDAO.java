package p230608_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "c##himedia";
	String password = "himedia";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	public ArrayList<MemberVo> list() {
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();

		try {
			connDB();

			String query = "SELECT * FROM emp";
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String empno = rs.getString("empno");
				String ename = rs.getString("ename");
				int sal = rs.getInt("sal");
				int comm = rs.getInt("comm");
				int realsal = sal+comm;
				MemberVo data = new MemberVo(empno, ename, sal, comm, realsal);
				list.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void connDB() {
		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.");
			stmt = con.createStatement();
			System.out.println("statement create success.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
