package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import classes.Department;
import utils.DbConnection;

public class DepartmentCRUD {

	public static void depatmanEkle(Department department, Connection cnn) throws SQLException {
		String sql = "INSERT INTO	DEPARTMENT (NAME, DURUM) VALUES(?, ?)";
		PreparedStatement pst = cnn.prepareStatement(sql);
		pst.setString(1, department.getName());
		pst.setInt(2, department.getDurum());
		int islem = pst.executeUpdate();
		if (islem > 0) {
			System.out.println("Departman Ekleme İşlemi Başarılı");
		} else {
			System.out.println("Hay aksi birşeyler yanlış gitti!!!");
		}
	}

	public static List<Department> depatmanListele(Connection cnn) throws SQLException {
		List<Department> departmanlar = new ArrayList<Department>();
		String sql = "SELECT	* FROM	DEPARTMENT WHERE DURUM=1";
		Statement st = cnn.createStatement();
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			Department d = new Department();
			d.setName(rs.getString("name"));
			d.setId(rs.getLong("id"));
			departmanlar.add(d);
		}

		return departmanlar;

	}

	public static void delete(Long id, Connection cnn) throws SQLException {
		String sql = "DELETE FROM DEPARTMENT WHERE ID = ?";
		PreparedStatement pst = cnn.prepareStatement(sql);
		pst.setLong(1, id);
		int row = pst.executeUpdate();
		if (row > 0) {
			System.out.println("Silme işlemmi başarılı");
		} else {
			System.out.println("Silme işlemi başarısız");
		}
	}

	public static void update(Department dep, Connection cnn) throws SQLException {
		String sql = "UPDATE DEPARTMENT SET NAME = ?, DURUM = ? WHERE ID = ?";
		PreparedStatement pst = cnn.prepareStatement(sql);
		pst.setString(1, dep.getName());
		pst.setInt(2, dep.getDurum());
		pst.setLong(3, dep.getId());
		int row=pst.executeUpdate();
		if(row>0) {
			System.out.println("Güncelleme Başarılı");
		}else {
			System.out.println("Güncelleme Başarısız");
		}

	}

}
