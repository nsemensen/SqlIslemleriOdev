package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import classes.Position;

public class PositionCRUD {

	public static void positionEkle(Position position, Connection cnn) throws SQLException {
		String sql = "INSERT INTO POSITIONN (NAME, DURUM) VALUES (?, ?)";
		PreparedStatement pst = cnn.prepareStatement(sql);
		pst.setString(1, position.getName());
		pst.setInt(2, position.getDurum());
		int islem = pst.executeUpdate();
		if (islem > 0) {
			System.out.println("Ünvan Ekleme İşlemi Başarılı");
		} else {
			System.out.println("Hay aksi birşeyler yanlış gitti!!!");
		}
	}

	public static List<Position> positionListele(Connection cnn) throws SQLException {
		List<Position> position = new ArrayList<Position>();
		String sql = "SELECT * FROM POSITIONN WHERE DURUM=1";
		Statement st = cnn.createStatement();
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			Position d = new Position();
			d.setName(rs.getString("name"));
			d.setId(rs.getLong("id"));
			position.add(d);
		}
		return position;
	}

	public static void delete(Long id, Connection cnn) throws SQLException {
		String sql = "DELETE FROM POSITIONN WHERE ID = ?";
		PreparedStatement pst = cnn.prepareStatement(sql);
		pst.setLong(1, id);
		int row = pst.executeUpdate();
		if (row > 0) {
			System.out.println("Silme işlemmi başarılı");
		} else {
			System.out.println("Silme işlemi başarısız");
		}
	}

	public static void update(Position position, Connection cnn) throws SQLException {
		String sql = "UPDATE POSITIONN SET NAME = ?, DURUM = ? WHERE ID = ?";
		PreparedStatement pst = cnn.prepareStatement(sql);
		pst.setString(1, position.getName());
		pst.setInt(2, position.getDurum());
		pst.setLong(3, position.getId());
		int row = pst.executeUpdate();
		if (row > 0) {
			System.out.println("Güncelleme Başarılı");
		} else {
			System.out.println("Güncelleme Başarısız");
		}
	}
}
