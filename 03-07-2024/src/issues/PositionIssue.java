package issues;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import classes.Position;
import crud.PositionCRUD;
import utils.DbConnection;

public class PositionIssue {

	private static final Scanner in = new Scanner(System.in);
	private static Connection cnn;

	public static void start() throws SQLException {
		System.out.println("---ÜNVAN SAYFASI---");
		while (true) {
			System.out.println(
					"İşlem Seçiniz\n0- Çıkış\n1- Ünvan Listele\n2- Ünvan Ekle\n3- Ünvan Güncelle\n4- Ünvan Sil");
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
		System.err.println("---ÜNVAN EKLEME---");
		while (true) {
			System.out.println("İşlem Seçiniz\n0-Çıkış\n1-Ünvan Ekle");
			int islem = in.nextInt();
			if (islem == 1) {
				in.nextLine();
				System.out.println("Ünvan Adı giriniz");
				String pos = in.nextLine();
				Position p = new Position(pos);
				cnn = DbConnection.baglan();
				PositionCRUD.positionEkle(p, cnn);
				cnn = DbConnection.kapat();
			} else {
				start();
				break;
			}
		}
	}

	private static void update() throws SQLException {
		System.err.println("---ÜNVAN GÜNCELLEME---");
		Position pos=new Position();
		select();
		System.out.println("Güncellenecek Ünvan ID yi yazınız");
		pos.setId(in.nextLong());
		System.out.println("Güncellenecek Ünvan Adını yazınız");
		in.nextLine();
		pos.setName(in.nextLine());
		System.out.println("Güncellenecek Ünvan Durumunu yazınız");
		pos.setDurum(in.nextInt());		
		cnn=DbConnection.baglan();
		PositionCRUD.update(pos, cnn);
		cnn=DbConnection.kapat();
		select();
	}

	private static void delete() throws SQLException {
		System.err.println("---ÜNVAN SİLME---");
		select();
		System.out.println("Silinecek Ünvan ID yi yazınız");
		Long id = in.nextLong();
		cnn = DbConnection.baglan();
		PositionCRUD.delete(id, cnn);
		cnn = DbConnection.kapat();
		start();
	}

	private static void select() throws SQLException {
		System.err.println("---ÜNVAN LİSTELEME---");
		cnn = DbConnection.baglan();
		List<Position> pos = PositionCRUD.positionListele(cnn);
		cnn = DbConnection.kapat();
		for (Position p : pos) {
			System.out.println(p);
		}
	}	
}
