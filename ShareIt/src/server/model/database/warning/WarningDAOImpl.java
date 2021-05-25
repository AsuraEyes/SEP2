package server.model.database.warning;

import shared.transferobjects.Message;
import shared.transferobjects.Warning;

import java.sql.*;
import java.time.Instant;

public class WarningDAOImpl implements WarningDAO {
    private static WarningDAOImpl instance;
    private String password;

    private WarningDAOImpl()throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized WarningDAOImpl getInstance() throws SQLException {
        if(instance == null){
            instance = new WarningDAOImpl();
        }
        return instance;
    }

    public void setPassword(String password){
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        System.out.println("password: " + password);
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", password);
    }

    @Override
    public Warning sendWarning(Warning warning) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO share_it.warning(text, time, administrator_from, member_to) VALUES (?, ?, ?, ?);",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, warning.getText());
            Timestamp ts = Timestamp.from(Instant.now());
            statement.setTimestamp(2, ts);
            statement.setString(3, warning.getAdministratorFrom());
            statement.setInt(4, warning.getMemberTo());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return new Warning(warning.getAdministratorFrom(), warning.getMemberTo(), warning.getText(), ts);
            }
            else
            {
                throw new SQLException("No keys generated");
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return null;
    }
}
