import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import com.tibco.security.ObfuscationEngine;

public class MyExecuteMethod {
 
    public static void main(String a[]) throws Exception {

			String hostname = a[0];
			String port = a[1];
			String SID = a[2];
			String UserName = a[3];
			Connection con = null;
			Scanner LCINRead = new Scanner(System.in);
			System.out.println("Enter LCIN :");
			String LCIN=LCINRead.nextLine();
			if(LCIN.length() == 0){
				System.out.println("LCIN not provided exiting from script.");
				System.exit(0);
			}
			String DbPass = new String(ObfuscationEngine.decrypt(a[4]));
			System.out.println("Choose one of the following option:");
			System.out.println("1 - Customer Status");
			System.out.println("2 - Customer Suspensions details");
			System.out.println("3 - Active Cards of Customer");
			System.out.println("4 - All Cards and Status of Cards");
			System.out.println("5 - Customer State model with status");
			System.out.println("6 - Customer Active Phone and Email");
			System.out.println("7 - Customers All Account with Status");
			System.out.println("8 - Customers all FD account");
			System.out.println("9 - exit");
			Scanner scanchoice = new Scanner(System.in);
			System.out.println();
			System.out.println("Enter \"1\", \"2\", \"3\", \"4\", \"5\", \"6\", \"7\", \"8\" or \"9\"");
			int choiceentry = scanchoice.nextInt();
            try{
			
            String query = null;
			//The query is being formed based on choice
			if (choiceentry == 1){
				//query = "select * from TESTTAB where NAME='"+LCIN+"'";
				query = "Select * from CUSTOMER_DATA Where PARTY_ID='" + LCIN +"'";
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
			con = DriverManager.getConnection("jdbc:oracle:thin:@"+ hostname +":"+port+"/"+SID,UserName,DbPass);

            Statement stmt = con.createStatement();
            boolean status = stmt.execute(query);
			if(status){
                //query is a select query.
				ResultSet rs = stmt.getResultSet();
                int Frow=1;
				int rsc=0;
		
				while(rs.next()){
					ResultSetMetaData rsmd = rs.getMetaData();
					if ( Frow < rs.getMetaData().getColumnCount()+ 1 ){
						for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
							System.out.print(" " + rs.getMetaData().getColumnName(i));
							Frow=Frow+1;
						}
					System.out.println();
					}

					for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
						
						String type = rsmd.getColumnTypeName(i);

						if (type == "TIMESTAMP" && rs.getTimestamp(i) != null){
							SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MMM-yy HH:mm:ss.SSSS");
							System.out.print(" " + sdf.format(rs.getTimestamp(i)));
						}
						else if (type == "DATE" && rs.getTimestamp(i) != null){
							SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MMM-yy");
							System.out.print(" " + sdf.format(rs.getTimestamp(i)));
						}
						else{							
						System.out.print(" " + rs.getObject(i));
						}
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
			
        } catch (Exception e) {
            e.printStackTrace();
        } 
        finally{
            if(con != null) con.close();
		    //System.out.println("Closed DB Connection");
			}
        }
}
