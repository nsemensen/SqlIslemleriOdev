package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private static final String path = "jdbc:postgresql://localhost:5432/employee";
	private static final String username = "postgres";
	private static final String password = "1234";
	private static Connection cnn;

	public static Connection baglan() throws SQLException {
		if(cnn==null) {
			cnn = DriverManager.getConnection(path, username, password);
			//System.out.println("Veritabanı Bağlantısı Oluşturuldu...");
		}		
		return cnn;
	}
	
	public static Connection kapat() throws SQLException {
		if(cnn==null) {
			System.out.println("Veritabanı bağlantısı açık değil");
		}else {
			cnn.close();
			cnn=null;
			//System.out.println("Veritabanı bağlantısı kesildi");
		}
		return cnn;
	}
}
