package issues;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import classes.Department;
import classes.Employee;
import crud.DepartmentCRUD;
import utils.DbConnection;

public class DepartmentIssue {
	private static final Scanner in = new Scanner(System.in);
	private static Connection cnn;

	public static void start() throws SQLException {
		System.err.println("---DEPARTMAN SAYFASI---");
		while (true) {
			System.out.println(
					"İşlem Seçiniz\n0- Çıkış\n1-Departman Listele\n2-Departman Ekle\n3-Departman Güncelle\n4-Departman Sil");
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
		System.err.println("---DEPARTMAN EKLEME---");
		while (true) {
			System.out.println("İşlem Seçiniz\n0-Çıkış\n1-Departman Ekle");
			int islem = in.nextInt();
			if (islem == 1) {
				in.nextLine();
				System.out.println("Departman Adı giriniz");
				String dep = in.nextLine();
				Department d = new Department(dep);
				cnn = DbConnection.baglan();
				DepartmentCRUD.depatmanEkle(d, cnn);
				cnn = DbConnection.kapat();

			} else {
				start();
				break;
			}
		}
	}

	private static void update() throws SQLException {
		System.err.println("---DEPARTMAN GÜNCELLEME---");
		Department dep=new Department();
		select();
		System.out.println("Güncellenecek Departman ID yi yazınız");
		dep.setId(in.nextLong());
		System.out.println("Güncellenecek Departman Adını yazınız");
		in.nextLine();
		dep.setName(in.nextLine());
		System.out.println("Güncellenecek Departman Durumunu yazınız");
		dep.setDurum(in.nextInt());		
		cnn=DbConnection.baglan();
		DepartmentCRUD.update(dep, cnn);
		cnn=DbConnection.kapat();
		select();
	}

	private static void delete() throws SQLException {
		System.err.println("---DEPARTMAN SİLME---");
		select();
		System.out.println("Silinecek Departman ID yi yazınız");
		Long id = in.nextLong();
		cnn = DbConnection.baglan();
		DepartmentCRUD.delete(id, cnn);
		cnn = DbConnection.kapat();
		start();
	}

	private static void select() throws SQLException {
		System.err.println("---DEPARTMAN LİSTELEME---");
		cnn = DbConnection.baglan();
		List<Department> dep = DepartmentCRUD.depatmanListele(cnn);
		cnn = DbConnection.kapat();
		for (Department d : dep) {
			System.out.println(d);
		}
	}
}
