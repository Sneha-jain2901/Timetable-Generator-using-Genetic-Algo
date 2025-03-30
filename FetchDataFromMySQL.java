import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
 
public class FetchDataFromMySQL {
 
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jcodebook?serverTimezone=UTC";
        try {
            // Step 1: Import JDBC Packages (done above)
 
            // Step 2: Load and Register JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Step 3: Establish a Connection
            Connection con=DriverManager.getConnection(url, "root","root");
            // Step 4: Create a Statement
            Statement stmt = con.createStatement(); 
            // Step 5: Create and Execute a Query
            String sql="Select * from Employees";
            ResultSet rs=stmt.executeQuery(sql);
            System.out.println("EmpID\tFirstName\tBirthDate\tSalary");
            // Step 6: Process the Result Set
            while(rs.next()) {
                int empID = rs.getInt(1);
                String fname =  rs.getString(2);
                Date bdate = rs.getDate(3);
                double salary = rs.getDouble(4);
 
                System.out.println(empID+"\t"+fname+"\t\t"+bdate+"\t"+salary);
            }
            // Step 7: Close the Connection
            con.close();stmt.close();rs.close();
        }
        catch(Exception e) {    
            System.out.println(e);
        }
    }
}
