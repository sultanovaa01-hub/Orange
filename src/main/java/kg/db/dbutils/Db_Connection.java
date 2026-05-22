package kg.db.dbutils;

import kg.xiaomi.utils.file.ConfugurationManager;
import lombok.Getter;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.*;

public class Db_Connection {

    @Getter
    private static Connection connection;
    private static Statement statement;

    private Db_Connection() {
        //Singleton Pattern
    }

    private static PGSimpleDataSource getBaseDataSource(String database) {
        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource() {{
            setServerName(ConfugurationManager.getBaseConfig().server());
            setPortNumber(ConfugurationManager.getBaseConfig().port());
            setUser(ConfugurationManager.getBaseConfig().user());
            setPassword("Alinur1210");
            setDatabaseName(database);
        }};
        return pgSimpleDataSource;
    }

    public static void openConnection(String database) throws SQLException {
        if (connection == null) {
            connection = getBaseDataSource(database).getConnection();
            statement = connection.createStatement();
        }
    }
    public static void closeConnection () {
        try {
            if (statement != null) {
                statement.close();
                statement = null;
            }
            if (connection != null)
                connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ResultSet makeQuery(String query, Object... params) throws SQLException {
        if (params.length == 0) {
            return statement.executeQuery(query);
        } else {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
                return preparedStatement.executeQuery();
            }
            return preparedStatement.executeQuery();
        }
    }
    public static int makeUpdate(String query, Object... params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        preparedStatement.executeUpdate();

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        }
        return 0;
    }
}
