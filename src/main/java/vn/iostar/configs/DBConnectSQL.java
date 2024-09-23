package vn.iostar.configs;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectSQL {
	private final String serverName = "DESKTOP-LI7EG0J";
	 private final String dbName = "ltwebst2";
	 private final String portNumber = "1433";
	 //private final String instance = "";// MSSQLSERVER LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INSTANCE
	 private final String userID = "sa";


	 private final String password = "DuHoangHuy@abc1";



	 public Connection getConnection() throws Exception {



	 String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;


	 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");


	 return DriverManager.getConnection(url, userID, password);


	 }



	 // Test chương trình. Kích phải chuột chọn run as->java application


	 public static void main(String[] args) {


	 try {


	 System.out.println(new DBConnectSQL().getConnection());


	 } catch (Exception e) {


	 e.printStackTrace();


	 }


	 }
}