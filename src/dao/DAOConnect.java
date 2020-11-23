package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOConnect {

	public static Connection getConnection() {
		String jdbcClassName="com.ibm.db2.jcc.DB2Driver";
		String url="jdbc:db2://dashdb-txn-sbox-yp-dal09-11.services.dal.bluemix.net:50000/BLUDB";
		String user="rfg21552";
		String password="n^zpqf2cg1s4k4p9";

		Connection connection = null;
		try {
			//Load class into memory
			Class.forName(jdbcClassName);
			//Establish connection
			connection = DriverManager.getConnection(url, user, password);


		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(connection!=null){
				System.out.println("Connected successfully.");
				
			}
		}
		return connection;

	}
	
	

}