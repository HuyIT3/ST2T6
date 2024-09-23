package vn.iostar.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectMySQL {
	 private static String USERNAME = "root";
	 private static String PASSWORD = "DuHoangHuy@abc1";
	 private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	 private static String URL = "jdbc:mysql://localhost:3306/ltwebst2";
	 public static Connection getDatabaseConnection() throws SQLException {
		 try {
			 Class.forName(DRIVER);
		 } catch (ClassNotFoundException e) {
			 e.printStackTrace();
		 }
		 return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	 public static void main(String[] args) {
		 try {
			 new DBConnectMySQL();
			 System.out.println(DBConnectMySQL.getDatabaseConnection());
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
}