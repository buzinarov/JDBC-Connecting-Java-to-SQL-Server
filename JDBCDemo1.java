import java.sql.*;

public class JDBCDemo1 {

	public static void main(String[] args) {
		Connection conn = null;		 
        try {
			// create a variable for the connection string
            String dbURL = "jdbc:sqlserver://localhost;"+ "instanceName=MSSQLSERVER;databaseName=university;user=sa;password=csis3300";
			// establish the connection to the database server
            conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				
				// print the driver information to check if connection has been established.
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
              
				// create a Statement object. A Statement is an interface that represents a SQL statement
				// You execute Statement objects, and they generate ResultSet objects
                Statement stmt = conn.createStatement();							
                ResultSet rs;
                                                
				// Statement objects are used to implement simple SQL statements with no parameters
                rs = stmt.executeQuery("SELECT * FROM instructor");
                while ( rs.next() ) {
   //             	System.out.println(rs.getString("ID") + " " + rs.getString("name") + " " + rs.getDouble("salary"));
                	System.out.println(rs.getString(1) + "\t" + rs.getDouble(4));
                }
                
                rs = stmt.executeQuery("SELECT ID, name FROM instructor");
                while ( rs.next() ) {
   //             	System.out.println(rs.getString("ID") + " " + rs.getString("name") + " " + rs.getDouble("salary"));
                	System.out.println(rs.getString(1) + "\t" + rs.getString(2));
                }
            }
			// handle any errors that may have occurred
         } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
			// close the connection after job is finished
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
