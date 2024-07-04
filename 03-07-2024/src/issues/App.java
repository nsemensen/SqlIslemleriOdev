package issues;
import java.sql.SQLException;
import java.util.Scanner;

import issues.DepartmentIssue;

public class App {
	public static void start() throws SQLException {
		Scanner in=new Scanner(System.in);
		System.err.println("---ANA SAYFA---");
		while(true) {
			System.out.println("İşlem Seçiniz\n0- Çıkış\n1-Departman İşlemleri\n2-Ünvan İşlemleri\n3-Personel İşlemleri");
			int islem=in.nextInt();
			if(islem==1) {
				DepartmentIssue.start();
			}else if(islem==2) {
				PositionIssue.start();
			}else if(islem==3) {				
				EmployeeIssue.start();
			}else {
				break;
			}
		}
	}
}
