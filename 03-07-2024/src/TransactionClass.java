import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionClass {

	public static void main(String[] args) {
		String path = "jdbc:postgresql://localhost:5432/employee";
		String username = "postgres";
		String password = "1234";
		int miktar1=0;
		int miktar2=0;
		int transferMiktari=500;
		try {
			Connection cnn=DriverManager.getConnection(path, username, password);
			String  sql1="select * from account where id=1";
			String  sql2="select * from account where id=2";
			Statement st=cnn.createStatement();
			ResultSet rs=st.executeQuery(sql1);
			
			
			if(rs.next()) {
				miktar1=rs.getInt("miktar");
				System.out.println(rs.getString("name")+" adlı kişinin bakiyesi: "+miktar1);
			}
			rs=st.executeQuery(sql2);
			if(rs.next()) {
				miktar2=rs.getInt("miktar");
				System.out.println(rs.getString("name")+" adlı kişinin bakiyesi: "+miktar2);
			}
			miktar1-=transferMiktari;
			miktar2+=transferMiktari;
			String update1="update account set miktar="+miktar1+" where id=1";
			st.executeUpdate(update1);
			int a=5/1;
			String update2="update account set miktar="+miktar2+" where id=2";			
			st.executeUpdate(update2);
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		

	}

}
