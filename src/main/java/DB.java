import java.sql.*;
import java.util.ArrayList;

/**
 * Created by mash4 on 4/19/2017.
 */
public class DB {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";        //Configure the driver needed
    private static final String DB_CONNECTION_URL =
            "jdbc:mysql://localhost:3306/geography";     //Connection string – where's the database?

    private static final String USER = ("mash4peace");   //TODO replace with your username
    private static final String PASSWORD = System.getenv("MYSQL_pw");   //TODO replace with your password
    private static final String TABLE_NAME = "cube";
    private static final String PLAYER_COL = "name";
    private static final String TIME_COL = "time";
    private static  final String ID_COl = "PlayerID";


    DB() {

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Can't instantiate driver class; " +
                    "check you have drives and classpath configured" +
                    " correctly?");
            cnfe.printStackTrace();
            System.exit(-1);  //No driver? Need to fix before anything else will work. So quit the program
        }
    }

    void createTable() {

        try (Connection conn = DriverManager.getConnection(
                DB_CONNECTION_URL, USER, PASSWORD);
             Statement statement = conn.createStatement()) {

            //You should have already created a database via terminal/command prompt

            //Create a table in the database, if it does not exist already
            //Can use String formatting to build this type of String from constants coded in your program
            //Don't do this with variables with data from the user!! That's what ParameterisedStatements are, and that's for queries, updates etc. , not creating tables.
            // You shouldn't make database schemas from user input anyway.
            String createTableSQLTemplate =
                    "CREATE TABLE IF NOT EXISTS %s (% s id VarCHAR(36) NOT NULL AUTO_INCREMENT ,%s VARCHAR (100), %s " +
                            "DOUBLE, PRIMARY KEY(id)";
            String createTableSQL = String.format(createTableSQLTemplate, TABLE_NAME, ID_COl, PLAYER_COL, TIME_COL);

            statement.executeUpdate(createTableSQL);
            System.out.println("Created elevations table");

            statement.close();
            conn.close();

        } catch (SQLException se) {

            se.printStackTrace();
        }
    }


    void addRecord(Rubric rb)  {

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD)) {

            String addElevationSQL = "INSERT INTO " + TABLE_NAME + " VALUES ( ? , ? ) " ;
            PreparedStatement addRubricPS = conn.prepareStatement(addElevationSQL);
            addRubricPS.setString(1,rb.name);

            addRubricPS.setDouble(2, rb.time);

            addRubricPS.execute();

            System.out.println("Added rubric  record for the player " + rb);

            addRubricPS.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        }

    }


    ArrayList<Rubric> fetchAllRecords() {

        ArrayList<Rubric> allRecords = new ArrayList();

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
             Statement statement = conn.createStatement()) {

            String selectAllSQL = "SELECT * FROM " + TABLE_NAME;
            ResultSet rsAll = statement.executeQuery(selectAllSQL);

            while (rsAll.next()) {
                String playerName = rsAll.getString(PLAYER_COL);
                double time = rsAll.getDouble(TIME_COL);
                Rubric rubricRecord = new Rubric(playerName, time);
                allRecords.add(rubricRecord);
            }


            rsAll.close();
            statement.close();
            conn.close();

            return allRecords;    //If there's no data, this will be empty

        } catch (SQLException se) {
            se.printStackTrace();
            return null;  //since we have to return something.
        }

    }


    public void delete(Rubric rb) {

    }


    public void createTables() {
        try(Connection cnn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
        Statement s = cnn.createStatement()){
            String createTableSQLTemplate =
                    "CREATE TABLE IF NOT EXISTS %s (%s VARCHAR (100), %s " +
                            "DOUBLE)";
            String createTableSQL = String.format(createTableSQLTemplate, TABLE_NAME, PLAYER_COL, TIME_COL);

            s.executeUpdate(createTableSQL);
            System.out.println("Created elevations table");

            s.close();
            cnn.close();

        }catch (SQLException sql ){
            sql.printStackTrace();
            sql.getCause();

        }

    }
}
