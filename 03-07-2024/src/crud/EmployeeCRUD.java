package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import classes.Employee;

public class EmployeeCRUD {

	public static void EmployeeEkle(Employee personel, Connection cnn) throws SQLException {
		String sql = "INSERT INTO EMPLOYEE (SURNAME, LASTNAME, POSITIONID, DEPARTMENTID)VALUES(?, ?, ?, ?)";
		PreparedStatement pst = cnn.prepareStatement(sql);
		pst.setString(1, personel.getSurName());
		pst.setString(2, personel.getLastName());
		pst.setLong(3, personel.getPositionId());
		pst.setLong(4, personel.getDepartmentId());
		int islem = pst.executeUpdate();
		if (islem > 0) {
			System.out.println("Personel Ekleme İşlemi Başarılı");
		} else {
			System.out.println("Hay aksi birşeyler yanlış gitti!!!");
		}
	}

	public static List<Employee> employeeListele(Connection cnn) throws SQLException {
		List<Employee> personeller = new ArrayList<Employee>();
		String sql = "SELECT * FROM	EMPLOYEE";
		Statement st = cnn.createStatement();
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			Employee e = new Employee();
			e.setSurName(rs.getString("SURNAME"));
			e.setLastName(rs.getString("LASTNAME"));
			e.setPositionId(rs.getLong("POSITIONID"));
			e.setDepartmentId(rs.getLong("DEPARTMENTID"));
			e.setId(rs.getLong("id"));
			personeller.add(e);
		}
		return personeller;
	}

	public static void delete(Long id, Connection cnn) throws SQLException {
		String sql = "DELETE FROM EMPLOYEE WHERE ID = ?";
		PreparedStatement pst = cnn.prepareStatement(sql);
		pst.setLong(1, id);
		int row = pst.executeUpdate();
		if (row > 0) {
			System.out.println("Silme işlemmi başarılı");
		} else {
			System.out.println("Silme işlemi başarısız");
		}
	}

	public static void update(Employee emp, Connection cnn) throws SQLException {
		String sql = "UPDATE EMPLOYEE SET SURNAME = ?, LASTNAME = ?, POSITIONID = ?, DEPARTMENTID = ? WHERE ID = ?";
		PreparedStatement pst = cnn.prepareStatement(sql);
		pst.setString(1, emp.getSurName());
		pst.setString(2, emp.getLastName());
		pst.setLong(3, emp.getPositionId());
		pst.setLong(4, emp.getDepartmentId());		
		pst.setLong(5, emp.getId());
		int row = pst.executeUpdate();
		if (row > 0) {
			System.out.println("Güncelleme Başarılı");
		} else {
			System.out.println("Güncelleme Başarısız");
		}

	}

}
