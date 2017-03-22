import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.util.Scanner;
 
public class MyExecuteMethod {
 
    public static void main(String a[]){
         
			Connection con = null;
			Scanner LCINRead = new Scanner(System.in);
			System.out.println("Enter LCIN :");
			String LCIN=LCINRead.nextLine();
			if(LCIN.length() == 0){
				System.out.println("Invalid LCIN provided.");
				System.exit(0);
			}
			System.out.println("Choose one of the following option:");
			System.out.println("1 - something..");
			System.out.println("2 - something else..");
			System.out.println("3 - something else..");
			System.out.println("4 - something else..");
			System.out.println("5 - something else..");
			System.out.println("6 - something else..");
			System.out.println("7 - something else..");
			System.out.println("8 - something else..");
			System.out.println("9 - exit");
			Scanner scanchoice = new Scanner(System.in);
			System.out.println();
			System.out.println("Enter \"1\", \"2\", \"3\", \"4\", \"5\", \"6\", \"7\", \"8\" or \"9\"");
			int choiceentry = scanchoice.nextInt();
            try{
            String query = null;
			//The query is being formed based on choice
			if (choiceentry == 1){
				query = "select * from TESTTAB where NAME='"+LCIN+"'";
			}
			else if (choiceentry == 2){
				query = "select * from tab";
			}
            else if (choiceentry == 3){
				query = "select * from tab";
			}
			else if (choiceentry == 4){
				query = "select * from tab";
			}
			else if (choiceentry == 5){
				query = "select * from tab";
			}
			else if (choiceentry == 6){
				query = "select * from tab";
			}
			else if (choiceentry == 7){
				query = "select * from tab";
			}
			else if (choiceentry == 8){
				query = "select * from tab";
			}
			else if (choiceentry == 9){
				System.out.println("Bye Bye !!!!!!");
				System.exit(0);
			}
			else {
				System.out.println("Invalid Option selected.");
				System.exit(0);
			}
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","bwuser","bwuser");
            Statement stmt = con.createStatement();
            boolean status = stmt.execute(query);
			if(status){
                //query is a select query.
				ResultSet rs = stmt.getResultSet();
                int Frow=1;
				int rsc=0;
		
				while(rs.next()){
					if ( Frow < rs.getMetaData().getColumnCount()+ 1 ){
						for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
							System.out.print(" " + rs.getMetaData().getColumnName(i));
							Frow=Frow+1;
						}
					System.out.println();
					}

					for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
						System.out.print(" " + rs.getObject(i)); 
					}
					rsc=rsc+1;
					System.out.println();
				}
				if(rsc == 0){
					System.out.println("No Records Found For LCIN : " + LCIN);
				}
                rs.close();
			}
			else {
                System.out.println("No Records Found For LCIN : " + LCIN);
            }
			
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            try{
                if(con != null) con.close();
		System.out.println();
            } catch (Exception ex){
				ex.printStackTrace();
			}
        }
    }
}
