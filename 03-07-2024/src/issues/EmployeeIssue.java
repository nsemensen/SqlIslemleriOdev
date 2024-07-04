package issues;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classes.Department;
import classes.Employee;
import classes.Position;
import crud.DepartmentCRUD;
import crud.EmployeeCRUD;
import crud.PositionCRUD;
import utils.DbConnection;

public class EmployeeIssue {

	private static final Scanner in = new Scanner(System.in);
	private static Connection cnn;

	public static void start() throws SQLException {
		System.err.println("---PERSONEL SAYFASI---");
		while (true) {
			System.out.println(
					"İşlem Seçiniz\n0- Çıkış\n1-Personel Listele\n2-Personel Ekle\n3-Personel Güncelle\n4-Personel Sil");
			int islem = in.nextInt();
			if (islem == 1) {
				select();
			} else if (islem == 2) {
				add();
			} else if (islem == 3) {
				update();
			} else if (islem == 4) {
				delete();
			} else {
				break;
			}
		}
		App.start();
	}

	private static void add() throws SQLException {
		System.err.println("---PERSONEL EKLEME---");
		while (true) {
			System.out.println("İşlem Seçiniz\n0-Çıkış\n1-Personel Ekle");
			int islem = in.nextInt();
			if (islem == 1) {
				cnn = DbConnection.baglan();
				in.nextLine();
				System.out.println("Personel Adı giriniz");
				String ad = in.nextLine();

				System.out.println("Personel Soyadı giriniz");
				String soyad = in.nextLine();

				List<Department> dep = DepartmentCRUD.depatmanListele(cnn);
				System.out.println("Personel Departman ID giriniz");
				for (Department d : dep) {
					System.out.println(d);
				}
				Long depID = in.nextLong();

				List<Position> pos = PositionCRUD.positionListele(cnn);
				System.out.println("Personel Ünvan ID giriniz");
				for (Position p : pos) {
					System.out.println(p);
				}
				Long posID = in.nextLong();

				Employee e = new Employee(ad, soyad, depID, posID);

				EmployeeCRUD.EmployeeEkle(e, cnn);
				cnn = DbConnection.kapat();

			} else {
				start();
				break;
			}
		}
	}

	private static void update() throws SQLException {
		System.err.println("---PERSONEL GÜNCELLEME---");
		Employee emp = new Employee();
		select();
		System.out.println("Güncellenecek Personel ID yi yazınız");
		emp.setId(in.nextLong());
		System.out.println("Güncellenecek Personel Adını yazınız");
		in.nextLine();
		emp.setSurName(in.nextLine());
		System.out.println("Güncellenecek Personel Soyadını yazınız");
		
		emp.setLastName(in.nextLine());
		
		cnn = DbConnection.baglan();
		
		System.out.println("Güncellenecek Personel Ünvanını yazınız");
		List<Position> pos = PositionCRUD.positionListele(cnn);
		for (Position p : pos) {
			System.out.println(p);
		}
		//Long posID = in.nextLong();
		emp.setPositionId(in.nextLong());

		System.out.println("Güncellenecek Personel Departmanını yazınız");
		List<Department> dep = DepartmentCRUD.depatmanListele(cnn);
		for (Department d : dep) {
			System.out.println(d);
		}
		//Long depID = in.nextLong();
		emp.setDepartmentId(in.nextLong());

		EmployeeCRUD.update(emp, cnn);
		cnn = DbConnection.kapat();
		select();
	}

	private static void delete() throws SQLException {
		System.err.println("---PERSONEL SİLME---");
		select();
		System.out.println("Silinecek Personel ID yi yazınız");
		Long id = in.nextLong();
		cnn = DbConnection.baglan();
		EmployeeCRUD.delete(id, cnn);
		cnn = DbConnection.kapat();
		start();
	}

	private static void select() throws SQLException {
		System.err.println("---PERSONEL LİSTELEME---");
		cnn = DbConnection.baglan();
		List<Employee> emp = EmployeeCRUD.employeeListele(cnn);
		cnn = DbConnection.kapat();
		for (Employee e : emp) {
			System.out.println(e);
		}
	}

	public static String selectDepartmentID(Long id) throws SQLException {
		cnn = DbConnection.baglan();
		String sql = "SELECT	* FROM	DEPARTMENT WHERE DURUM=1 and id=?";
		PreparedStatement pst = cnn.prepareStatement(sql);
		pst.setLong(1, id);
		ResultSet rs = pst.executeQuery();
		String dep = "";
		while (rs.next()) {
			dep = rs.getString("name");
		}
		cnn = DbConnection.kapat();
		return dep;
	}

	public static String selectPositionID(Long id) throws SQLException {
		cnn = DbConnection.baglan();
		String sql = "SELECT* FROM	positionn WHERE DURUM=1 and id=?";
		PreparedStatement pst = cnn.prepareStatement(sql);
		pst.setLong(1, id);
		ResultSet rs = pst.executeQuery();
		String pos = "";
		while (rs.next()) {
			pos = rs.getString("name");
		}
		cnn = DbConnection.kapat();
		return pos;
	}
}
