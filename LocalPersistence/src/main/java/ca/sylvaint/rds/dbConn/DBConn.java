/*---------------------------------------------------------------------------------------------------------------------
 -     Copyright (C) 2022  Sylvain Tousignant                                                                         -
 -                                                                                                                    -
 -     This program is free software: you can redistribute it and/or modify                                           -
 -     it under the terms of the GNU Affero General Public License as                                                 -
 -     published by the Free Software Foundation, either version 3 of the                                             -
 -     License, or (at your option) any later version.                                                                -
 -                                                                                                                    -
 -     This program is distributed in the hope that it will be useful,                                                -
 -     but WITHOUT ANY WARRANTY; without even the implied warranty of                                                 -
 -     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                                                  -
 -     GNU Affero General Public License for more details.                                                            -
 -                                                                                                                    -
 -     You should have received a copy of the GNU Affero General Public License                                       -
 -     along with this program.  If not, see <https://www.gnu.org/licenses/agpl-3.0.html>                             -
 ---------------------------------------------------------------------------------------------------------------------*/

package ca.sylvaint.rds.dbConn;

public final class DBConn {

    private static DBConn DBConn;

    //private static final ConnSettings connSettings;

    static {
        DBConn = null;
    }

     /*
    Singleton Pattern

    Builds a MariaDB JDBC connection. Stores it in memory and gives the object on demand to any class within the
    Local Persistence module.

    Could be expanded to work with connection pools if needed.

    Must also contain a static function to test DB Connection using a ConnSettings Record
    */

    /*private DBConn() {
        connSettings = Configurations.getConfigurations();

        Properties connProperties = new Properties();
        connProperties.setProperty("user",connSettings.DbUser());
        connProperties.setProperty("password", connSettings.DbUserPass());

        connProperties.setProperty("sslMode", "verify-full");
        connProperties.setProperty("trustStore", connSettings.Keystore());
        connProperties.setProperty("trustStorePassword", connSettings.KeystorePassword());



        //jdbc:mariadb:[replication:|loadbalance:|sequential:]//<hostDescription>[,<hostDescription>...]/[database][?<key1>=<value1>[&<key2>=<value2>]]
        //address=(host=localhost)(port=3306)(type=master)
        StringBuilder connStr = new StringBuilder();
        connStr.append(connSettings.DbUrlProtocol());
        connStr.append(connSettings.DbUrl());


        try (Connection con = DriverManager.getConnection("jdbc:mariadb://localhost/myDb", connProperties)) {
            try (Statement stmt = con.createStatement()) {
                stmt.execute("select 1");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBConn getInstance() {
        if (DBConn.DBConn == null) {
            DBConn.DBConn = new DBConn();
        }
        return DBConn.DBConn;
    }

    /*public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://192.168.100.174/db", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            String sql = "CREATE TABLE REGISTRATION "
                    + "(id INTEGER not NULL, "
                    + " first VARCHAR(255), "
                    + " last VARCHAR(255), "
                    + " age INTEGER, "
                    + " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }*/









}
