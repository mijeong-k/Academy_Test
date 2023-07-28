package p230608;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class Oi2 {

	public static void main(String[] args) {
		 String driver = "oracle.jdbc.driver.OracleDriver";
	      String url = "jdbc:oracle:thin:@localhost:1521:himedia";
	      String user = "c##himedia";
	      String password = "himedia";

	      String plusDeptno = "50";
	      while (true) {
	         try {
	            Class.forName(driver);
	            System.out.println("jdbc driver loading success.");
	            Connection conn = DriverManager.getConnection(url, user, password);
	            System.out.println("oracle connection success.\n");

	            Statement stmt = conn.createStatement();

	            String ideptno = plusDeptno, sdname = "IT", sloc = "SEOUL";
	            String sql = "INSERT INTO dept VALUES ('" + ideptno + "','" + sdname + "','" + sloc + "')";
	            boolean b = stmt.execute(sql);

	            if (!b) {
	               System.out.println("Insert success. \n");
	            } else {
	               System.out.println("Insert fail. \n");
	            }

	            String sql2 = "SELECT deptno, dname, loc FROM dept";
	            ResultSet rs = stmt.executeQuery(sql2);
	            while (rs.next()) {
	               System.out.print(rs.getString("deptno") + "\t");
	               System.out.print(rs.getString("dname") + "\t");
	               System.out.println(rs.getString("loc") + " ");
	            }
	         } catch (ClassNotFoundException e) {
	            System.out.println("jdbc driver loading fail.");
	            System.out.println(e);
	         } catch (SQLIntegrityConstraintViolationException e) {
	            int temp = Integer.parseInt(plusDeptno) + 10;
	            plusDeptno = temp + "";
	            System.out.println(e + "ideptno 를 10 증가시킵니다. \n");
	            continue;
	         } catch (SQLException e) {
	            System.out.println("oracle connection fail.");
	            System.out.println(e);
	         }
	         break;
	      }
	   }
	}